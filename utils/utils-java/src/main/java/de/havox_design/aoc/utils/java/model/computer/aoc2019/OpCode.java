package de.havox_design.aoc.utils.java.model.computer.aoc2019;

import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static de.havox_design.aoc.utils.java.model.computer.aoc2019.Computer.PRINT_HALT;

public enum OpCode implements Consumer<Computer> {
    ADD( 1, 3 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            final Long second = computer.getNextParameter( computer.getMode( instruction, 2 ) );
            computer.setNextParameter( first + second, computer.getMode( instruction, 3 ) );
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            final String second = computer.printParameter( computer.getMode( instruction, 2 ),
                    2 );
            final String third = computer.printParameter( 0, 3 );
            return padZero( computer.getPointer() ) + ": " + third + " = " + first + " + " + second;
        }

    },
    MULTIPLY( 2, 3 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            final Long second = computer.getNextParameter( computer.getMode( instruction, 2 ) );
            computer.setNextParameter( first * second, computer.getMode( instruction, 3 ) );
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            final String second = computer.printParameter( computer.getMode( instruction, 2 ),
                    2 );
            final String third = computer.printParameter( 0, 3 );
            return padZero( computer.getPointer() ) + ": " + third + " = " + first + " * " + second;
        }
    },
    IN( 3, 1 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long first;
            try {
                final Long instruction = computer.currentInstruction();
                first = computer.getIn().take();
                computer.setNextParameter( first, computer.getMode( instruction, 1 ) );
            } catch ( InterruptedException e ) {
                throw new RuntimeException( e );
            }
        }

        @Override
        public String toString( final Computer computer ) {
            final String first = computer.printParameter( 0, 1 );
            return padZero( computer.getPointer() ) + ": " + first + " = user input";
        }
    },
    OUT( 4, 1 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            try {
                computer.getOut().put( first );
            } catch ( InterruptedException e ) {
                throw new RuntimeException( e );
            }
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            return padZero( computer.getPointer() ) + ": print: " + first;
        }
    },
    JUMP_TRUE( 5, 2 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            final Long second = computer.getNextParameter( computer.getMode( instruction, 2 ) );
            if ( first != 0 ) {
                computer.setPointer(second.intValue() - 1L); //after executing an instruction, computer always advances the pointer
            }
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            final String second = computer.printParameter( computer.getMode( instruction, 2 ),
                    2 );
            return padZero(
                    computer.getPointer() ) + ": if ( " + first + " != 0 ) jump to " + second;
        }
    },
    JUMP_FALSE( 6, 2 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            final Long second = computer.getNextParameter( computer.getMode( instruction, 2 ) );
            if ( first == 0 ) {
                computer.setPointer(second.intValue() - 1L); //after executing an instruction, computer always advances the pointer
            }
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            final String second = computer.printParameter( computer.getMode( instruction, 2 ),
                    2 );
            return padZero(
                    computer.getPointer() ) + ": if ( " + first + " == 0 ) jump to " + second;
        }
    },
    LESS_THAN( 7, 3 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            final Long second = computer.getNextParameter( computer.getMode( instruction, 2 ) );
            final long val = first < second ? 1L : 0L;
            computer.setNextParameter( val, computer.getMode( instruction, 3 ) );
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            final String second = computer.printParameter( computer.getMode( instruction, 2 ),
                    2 );
            final String third = computer.printParameter( 0, 3 );
            return padZero(
                    computer.getPointer() ) + ": " + third + " = ( " + first + " < " + second + " ) ? 1 : 0";
        }
    },
    EQUALS( 8, 3 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            final Long second = computer.getNextParameter( computer.getMode( instruction, 2 ) );
            final long val = first.equals( second ) ? 1L : 0L;
            computer.setNextParameter( val, computer.getMode( instruction, 3 ) );
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            final String second = computer.printParameter( computer.getMode( instruction, 2 ),
                    2 );
            final String third = computer.printParameter( 0, 3 );
            return padZero(
                    computer.getPointer() ) + ": " + third + " = ( " + first + " == " + second + " ) ? 1 : 0";
        }
    },
    RELATIVE_BASE( 9, 1 ) {
        @Override
        public void accept( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final Long first = computer.getNextParameter( computer.getMode( instruction, 1 ) );
            computer.setRelativeBase(computer.getRelativeBase() + first);
        }

        @Override
        public String toString( final Computer computer ) {
            final Long instruction = computer.currentInstruction();
            final String first = computer.printParameter( computer.getMode( instruction, 1 ),
                    1 );
            return padZero( computer.getPointer() ) + ": " + "base += " + first;
        }
    },
    HALT( 99, 0 ) {
        @Override
        public void accept( final Computer computer ) {
            if (PRINT_HALT) {
                System.err.println("HALT");
            }
        }

        @Override
        public String toString( final Computer computer ) {
            return padZero( computer.getPointer() ) + ": " + "HALT";
        }
    },
    NOP( -1, 0 ) {
        @Override
        public String toString( final Computer computer ) {
            final String first = computer.printParameter( 1, 0 );
            return padZero( computer.getPointer() ) + ": " + first;
        }
    };

    private final long code;
    private final int nParams;

    OpCode( final long code, int nParams ) {
        this.code = code;
        this.nParams = nParams;
    }

    private static final Map<Long, OpCode> OP_CODES = new HashMap<>();

    static {
        for ( final OpCode op : OpCode.values() ) {
            OP_CODES.put( op.code, op );
        }
    }

    static OpCode valueOf(long code ) {
        return OP_CODES.get( code );
    }

    long getValue() {
        return code;
    }

    protected int getNParams() {
        return nParams;
    }

    abstract String toString( final Computer computer );

    @Override
    public void accept( final Computer computer ) {}

    private static String padZero( final long num ) {
        final int PAD = 4;
        return Strings.padStart( Long.toString( num ), PAD, '0' );
    }
}
