package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

@SuppressWarnings("javaarchitecture:S7027")
public enum OpCode implements Consumer<IntComputer> {
    ADD(IntComputer.getDataFor("ADD")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            computer.setNextParameter(first + second, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = %s + %s", padZero(computer.getPointer()), third, first, second);
        }

    },
    MULTIPLY(IntComputer.getDataFor("MULTIPLY")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            computer.setNextParameter(first * second, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = %s * %s", padZero(computer.getPointer()), third, first, second);
        }
    },
    HALT(IntComputer.getDataFor("HALT")) {
        @Override
        public void accept(IntComputer computer) {
            if (IntComputer.getPrintHalt()) {
                LOGGER.severe("HALT");
            }
        }

        @Override
        public String toString(IntComputer computer) {
            return String.format("%s: HALT", padZero(computer.getPointer()));
        }
    },
    NOP(IntComputer.getDataFor("NOP")) {
        @Override
        public String toString(IntComputer computer) {
            String first = computer.printParameter(1, 0);

            return String.format("%s: %s", padZero(computer.getPointer()), first);
        }
    },
    IN(IntComputer.getDataFor("IN")) {
        @Override
        @SuppressWarnings("squid:S2142")
        public void accept(IntComputer computer) {
            Long first;

            try {
                Long instruction = computer.currentInstruction();

                first = computer.getIn().take();
                computer.setNextParameter(first, computer.getMode(instruction, 1));
            } catch (InterruptedException e) {
                throw new AdventOfCodeException(e);
            }
        }

        @Override
        public String toString(IntComputer computer) {
            String first = computer.printParameter(0, 1);

            return String.format("%s: %s = user input", padZero(computer.getPointer()), first);
        }
    },
    OUT(IntComputer.getDataFor("OUT")) {
        @Override
        @SuppressWarnings("squid:S2142")
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));

            try {
                computer.getOut().put(first);
            } catch (InterruptedException e) {
                throw new AdventOfCodeException(e);
            }
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);

            return String.format("%s: print: %s", padZero(computer.getPointer()), first);
        }
    },
    JUMP_TRUE(IntComputer.getDataFor("JUMP_TRUE")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            if (first != 0) {
                computer.setPointer(second.intValue() - 1L);
            }
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);

            return String.format("%s: if ( %s != 0 ) jump to %s", padZero(computer.getPointer()), first, second);
        }
    },
    JUMP_FALSE(IntComputer.getDataFor("JUMP_FALSE")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            if (first == 0) {
                computer.setPointer(second.intValue() - 1L);
            }
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);

            return String.format("%s: if ( %s == 0 ) jump to %s", padZero(computer.getPointer()), first, second);
        }
    },
    LESS_THAN(IntComputer.getDataFor("LESS_THAN")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));
            long val = first < second ? 1L : 0L;

            computer.setNextParameter(val, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = ( %s < %s ) ? 1 : 0", padZero(computer.getPointer()), third, first, second);
        }
    },
    EQUALS(IntComputer.getDataFor("EQUALS")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));
            long val = first.equals(second) ? 1L : 0L;

            computer.setNextParameter(val, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = ( %s == %s ) ? 1 : 0", padZero(computer.getPointer()), third, first, second);
        }
    },
    RELATIVE_BASE(IntComputer.getDataFor("RELATIVE_BASE")) {
        @Override
        public void accept(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));

            computer.setRelativeBase(computer.getRelativeBase() + first);
        }

        @Override
        public String toString(IntComputer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);

            return String.format("%s: base += %s", padZero(computer.getPointer()), first);
        }
    };

    private static final Logger LOGGER = Logger.getLogger(OpCode.class.getName());
    private static final Map<Long, OpCode> OP_CODES = new HashMap<>();

    static {
        for (final OpCode op : OpCode.values()) {
            OP_CODES.put(op.getValue(), op);
        }
    }

    private final OpCodeData data;

    OpCode(OpCodeData data) {
        this.data = data;
    }

    public long getValue() {
        return data.opcode();
    }

    protected int getNumberOfParameters() {
        return data.numberParameters();
    }

    public abstract String toString(final IntComputer computer);

    @Override
    public void accept(final IntComputer computer) {
        // simply accept.
    }

    public static OpCode valueOf(long code) {
        return OP_CODES.get(code);
    }

    private static String padZero(long num) {
        int pad = 4;

        return StringUtils.leftPad(Long.toString(num), pad, '0');
    }
}
