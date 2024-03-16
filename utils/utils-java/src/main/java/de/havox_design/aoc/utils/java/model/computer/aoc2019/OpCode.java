package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import com.google.common.base.Strings;
import de.havox_design.aoc.utils.java.exceptions.AdventOfCodeException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

public enum OpCode implements Consumer<Computer> {
    ADD(Computer.getDataFor("ADD")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            computer.setNextParameter(first + second, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = %s + %s", padZero(computer.getPointer()), third, first, second);
        }

    },
    MULTIPLY(Computer.getDataFor("MULTIPLY")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            computer.setNextParameter(first * second, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = %s * %s", padZero(computer.getPointer()), third, first, second);
        }
    },
    HALT(Computer.getDataFor("HALT")) {
        @Override
        public void accept(Computer computer) {
            if (Computer.getPrintHalt()) {
                LOGGER.severe("HALT");
            }
        }

        @Override
        public String toString(Computer computer) {
            return String.format("%s: HALT", padZero(computer.getPointer()));
        }
    },
    NOP(Computer.getDataFor("NOP")) {
        @Override
        public String toString(Computer computer) {
            String first = computer.printParameter(1, 0);

            return String.format("%s: %s", padZero(computer.getPointer()), first);
        }
    },
    IN(Computer.getDataFor("IN")) {
        @Override
        @SuppressWarnings("squid:S2142")
        public void accept(Computer computer) {
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
        public String toString(Computer computer) {
            String first = computer.printParameter(0, 1);

            return String.format("%s: %s = user input", padZero(computer.getPointer()), first);
        }
    },
    OUT(Computer.getDataFor("OUT")) {
        @Override
        @SuppressWarnings("squid:S2142")
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));

            try {
                computer.getOut().put(first);
            } catch (InterruptedException e) {
                throw new AdventOfCodeException(e);
            }
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);

            return String.format("%s: print: %s", padZero(computer.getPointer()), first);
        }
    },
    JUMP_TRUE(Computer.getDataFor("JUMP_TRUE")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            if (first != 0) {
                computer.setPointer(second.intValue() - 1L);
            }
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);

            return String.format("%s: if ( %s != 0 ) jump to %s", padZero(computer.getPointer()), first, second);
        }
    },
    JUMP_FALSE(Computer.getDataFor("JUMP_FALSE")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));

            if (first == 0) {
                computer.setPointer(second.intValue() - 1L);
            }
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);

            return String.format("%s: if ( %s == 0 ) jump to %s", padZero(computer.getPointer()), first, second);
        }
    },
    LESS_THAN(Computer.getDataFor("LESS_THAN")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));
            long val = first < second ? 1L : 0L;

            computer.setNextParameter(val, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = ( %s < %s ) ? 1 : 0", padZero(computer.getPointer()), third, first, second);
        }
    },
    EQUALS(Computer.getDataFor("EQUALS")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));
            Long second = computer.getNextParameter(computer.getMode(instruction, 2));
            long val = first.equals(second) ? 1L : 0L;

            computer.setNextParameter(val, computer.getMode(instruction, 3));
        }

        @Override
        public String toString(Computer computer) {
            Long instruction = computer.currentInstruction();
            String first = computer.printParameter(computer.getMode(instruction, 1), 1);
            String second = computer.printParameter(computer.getMode(instruction, 2), 2);
            String third = computer.printParameter(0, 3);

            return String.format("%s: %s = ( %s == %s ) ? 1 : 0", padZero(computer.getPointer()), third, first, second);
        }
    },
    RELATIVE_BASE(Computer.getDataFor("RELATIVE_BASE")) {
        @Override
        public void accept(Computer computer) {
            Long instruction = computer.currentInstruction();
            Long first = computer.getNextParameter(computer.getMode(instruction, 1));

            computer.setRelativeBase(computer.getRelativeBase() + first);
        }

        @Override
        public String toString(Computer computer) {
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

    public abstract String toString(final Computer computer);

    @Override
    public void accept(final Computer computer) {
        // simply accept.
    }

    public static OpCode valueOf(long code) {
        return OP_CODES.get(code);
    }

    private static String padZero(long num) {
        int pad = 4;

        return Strings.padStart(Long.toString(num), pad, '0');
    }
}
