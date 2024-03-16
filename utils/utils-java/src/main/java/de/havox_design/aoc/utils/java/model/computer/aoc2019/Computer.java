package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import static de.havox_design.aoc.utils.java.model.computer.aoc2019.OpCode.*;

import java.util.*;
import java.util.concurrent.*;

public class Computer implements Runnable {
    public Map<Long, Long> memory;
    private long pointer = 0L;
    private long relativeBase = 0L;
    private final boolean modes;
    private final BlockingQueue<Long> in;
    private final BlockingQueue<Long> out;
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    protected static final boolean PRINT_HALT = false;

    public static Future<?> runComputer(final List<Long> program, final BlockingQueue<Long> in,                                        final BlockingQueue<Long> out, final boolean async ) {
        final Computer computer = new Computer( program, in, out );
        final Future<?> future;
        if ( async ) {
            future = computer.runAsync();
        } else {
            computer.run();
            future = CompletableFuture.completedFuture( null );
        }
        return future;
    }

    public Computer( final List<Long> program ) {
        this( false, program, null, null );
    }

    public Computer( final List<Long> program, final BlockingQueue<Long> in, final BlockingQueue<Long> out ) {
        this( true, program, in, out );
    }

    private Computer( final boolean modes, final List<Long> program,                          final BlockingQueue<Long> in, final BlockingQueue<Long> out ) {
        this.modes = modes;
        this.in = in;
        this.out = out;
        loadProgram( program );
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

    protected int getMode( final long value, final int param ) {
        int position = 10;
        for ( int i = 0; i < param; i++ ) {
            position *= 10;
        }
        return (int) ( ( value / position ) % 10 );
    }

    private Long getValueIndirect( final Long index, final boolean relative ) {
        final long offSet = relative ? relativeBase : 0;
        return getValueDirect( getValueDirect( index ) + offSet );
    }

    private Long getValueDirect( final Long address ) {
        return memory.getOrDefault( address, 0L );
    }

    private Long setValueIndirect( final Long index, final Long val, final boolean relative ) {
        final long offSet = relative ? relativeBase : 0;
        return setValueDirect( getValueDirect( index ) + offSet, val );
    }

    private Long setValueDirect( final Long address, final Long val ) {
        Objects.requireNonNull( val );
        return memory.put( address, val );
    }

    protected Long getNextParameter( final int mode ) {
        if ( mode == 1 ) {
            return getValueDirect( advancePointer() );
        } else {
            return getValueIndirect( advancePointer(), mode == 2 );
        }
    }

    protected Long setNextParameter( final Long val, final int mode ) {
        return setValueIndirect( advancePointer(), val, mode == 2 );
    }

    protected String printParameter( final int mode, final int param ) {
        final Long address = getValueDirect( pointer + param );
        if ( mode == 0 ) {
            return String.format("m[%s]", address);
        } else {
            return Long.toString( address );
        }
    }

    public Long advancePointer() {
        return ++pointer;
    }

    private void loadProgram( final List<Long> program ) {
        memory = new HashMap<>();
        for ( long i = 0L; i < program.size(); i++ ) {
            memory.put( i, program.get( (int) i ) );
        }
    }

    private void setPointer( final int pointer ) {
        this.pointer = pointer;
    }

    public void reset() {
        this.pointer = 0L;
        this.relativeBase = 0L;
    }

    private Optional<OpCode> getCurrentOpCode() {
        long opCode = currentInstruction();
        if ( modes ) {
            opCode %= 100;
        }
        return Optional.ofNullable( OpCode.valueOf( opCode ) );
    }

    protected Long currentInstruction() {
        return getValueDirect( pointer );
    }

    public boolean executeOneStep() {
        final Optional<OpCode> opCode = getCurrentOpCode();
        opCode.orElse( NOP ).accept( this );
        advancePointer();
        return opCode.orElse( HALT ) != HALT;
    }

    @Override
    public void run() {
        while ( true ) {
            if ( !executeOneStep() ) {
                return;
            }
        }
    }

    public Future<?> runAsync() {
        return EXECUTOR.submit( this );
    }

    public static void shutdownAll() {
        EXECUTOR.shutdown();
    }

    public Optional<OpCode> printOneStep() {
        final Optional<OpCode> opCode = getCurrentOpCode();
        System.out.println( opCode.orElse( NOP ).toString( this ) );
        return opCode;
    }

    public void printProgram() {
        System.out.println( "=======BEGIN PROGRAM=======" );
        final long origPointer = pointer;

        setPointer( 0 );
        while (memory.containsKey(pointer)) {
            final Optional<OpCode> opCode = printOneStep();
            pointer += opCode.orElse( NOP ).getNParams() + 1;
        }
        System.out.println( "========END PROGRAM========" );

        this.pointer = origPointer;
    }
}
