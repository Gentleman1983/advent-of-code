package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import static de.havox_design.aoc.utils.java.model.computer.aoc2019.OpCode.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class IntComputer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(IntComputer.class.getName());
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final boolean PRINT_HALT = false;
    private static final Set<OpCodeData> OPCODES = Set.of(
            new OpCodeData("ADD", 1, 3),
            new OpCodeData("MULTIPLY", 2, 3),
            new OpCodeData("IN", 3, 1),
            new OpCodeData("OUT", 4, 1),
            new OpCodeData("JUMP_TRUE", 5, 2),
            new OpCodeData("JUMP_FALSE", 6, 2),
            new OpCodeData("LESS_THAN", 7, 3),
            new OpCodeData("EQUALS", 8, 3),
            new OpCodeData("RELATIVE_BASE", 9, 1),
            new OpCodeData("HALT", 99, 0),
            new OpCodeData("NOP", -1, 0)
    );

    private Map<Long, Long> memory;
    private long pointer = 0L;
    private long relativeBase = 0L;
    private final boolean modes;
    private final BlockingQueue<Long> in;
    private final BlockingQueue<Long> out;

    public IntComputer(final List<Long> program) {
        this(false, program, null, null);
    }

    public IntComputer(final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out) {
        this(true, program, in, out);
    }

    private IntComputer(final boolean modes, final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out) {
        this.modes = modes;
        this.in = in;
        this.out = out;
        loadProgram(program);
    }

    public Map<Long, Long> getMemory() {
        return memory;
    }

    protected long getPointer() {
        return pointer;
    }

    protected void setPointer(long value) {
        pointer = value;
    }

    protected long getRelativeBase() {
        return relativeBase;
    }

    protected void setRelativeBase(long value) {
        relativeBase = value;
    }

    protected BlockingQueue<Long> getIn() {
        return in;
    }

    protected BlockingQueue<Long> getOut() {
        return out;
    }

    protected int getMode(final long value, final int param) {
        int position = 10;

        for (int i = 0; i < param; i++) {
            position *= 10;
        }

        return (int) ((value / position) % 10);
    }

    protected Long getNextParameter(final int mode) {
        if (mode == 1) {
            return getValueDirect(advancePointer());
        } else {
            return getValueIndirect(advancePointer(), mode == 2);
        }
    }

    protected Long setNextParameter(final Long val, final int mode) {
        return setValueIndirect(advancePointer(), val, mode == 2);
    }

    protected String printParameter(final int mode, final int param) {
        final Long address = getValueDirect(pointer + param);

        if (mode == 0) {
            return String.format("m[%s]", address);
        } else {
            return Long.toString(address);
        }
    }

    private Long getValueIndirect(final Long index, final boolean relative) {
        final long offSet = relative ? relativeBase : 0;

        return getValueDirect(getValueDirect(index) + offSet);
    }

    private Long getValueDirect(final Long address) {
        return memory.getOrDefault(address, 0L);
    }

    private Long setValueIndirect(final Long index, final Long val, final boolean relative) {
        final long offSet = relative ? relativeBase : 0;

        return setValueDirect(getValueDirect(index) + offSet, val);
    }

    private Long setValueDirect(final Long address, final Long val) {
        Objects.requireNonNull(val);

        return memory.put(address, val);
    }

    private void loadProgram(final List<Long> program) {
        memory = new HashMap<>();

        for (long i = 0L; i < program.size(); i++) {
            memory.put(i, program.get((int) i));
        }
    }

    private void setPointer(final int pointer) {
        this.pointer = pointer;
    }

    private Optional<OpCode> getCurrentOpCode() {
        long opCode = currentInstruction();

        if (modes) {
            opCode %= 100;
        }

        return Optional.ofNullable(OpCode.valueOf(opCode));
    }

    @SuppressWarnings("squid:S1452")
    public static Future<?> runComputer(final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out, final boolean async) {
        final IntComputer computer = new IntComputer(program, in, out);
        final Future<?> future;

        if (async) {
            future = computer.runAsync();
        } else {
            computer.run();
            future = CompletableFuture.completedFuture(null);
        }

        return future;
    }protected Long currentInstruction() {
        return getValueDirect(pointer);
    }

    public boolean executeOneStep() {
        final Optional<OpCode> opCode = getCurrentOpCode();

        opCode.orElse(NOP).accept(this);
        advancePointer();

        return opCode.orElse(HALT) != HALT;
    }

    @Override
    public void run() {
        while (true) {
            if (!executeOneStep()) {
                return;
            }
        }
    }

    public Long advancePointer() {
        return ++pointer;
    }

    public void reset() {
        this.pointer = 0L;
        this.relativeBase = 0L;
    }

    @SuppressWarnings("squid:S1452")
    public Future<?> runAsync() {
        return EXECUTOR.submit(this);
    }

    public static void shutdownAll() {
        EXECUTOR.shutdown();
    }

    public Optional<OpCode> printOneStep() {
        final Optional<OpCode> opCode = getCurrentOpCode();

        LOGGER.info(() -> opCode.orElse(NOP).toString(this));

        return opCode;
    }

    public void printProgram() {
        LOGGER.info("=======BEGIN PROGRAM=======");
        final long origPointer = pointer;

        setPointer(0);

        while (memory.containsKey(pointer)) {
            final Optional<OpCode> opCode = printOneStep();
            pointer += opCode.orElse(NOP).getNumberOfParameters() + 1;
        }

        LOGGER.info("========END PROGRAM========");

        this.pointer = origPointer;
    }

    public static OpCodeData getDataFor(String opcodeName) {
        return OPCODES
                .stream()
                .filter(opcode -> opcode.name().equalsIgnoreCase(opcodeName))
                .findFirst()
                .orElseThrow();
    }

    public static boolean getPrintHalt() {
        return PRINT_HALT;
    }
}
