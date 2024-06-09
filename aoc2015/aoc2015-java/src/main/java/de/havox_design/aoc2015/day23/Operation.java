package de.havox_design.aoc2015.day23;

public class Operation {
    public static final String OPERATION_HALF = "hlf";
    public static final String OPERATION_INCREMENT = "inc";
    public static final String OPERATION_JUMP = "jmp";
    public static final String OPERATION_JUMP_ON_EQUAL = "jie";
    public static final String OPERATION_JUMP_ON_ONE = "jio";
    public static final String OPERATION_TRIPLE = "tpl";

    public static final String REGISTER_A = "a";
    public static final String REGISTER_B = "b";

    String op;
    Integer value;
    String register;

    @SuppressWarnings("squid:S3776")
    public void operate(State state) {
        switch (op) {
            case OPERATION_HALF:
                if (register.equals(REGISTER_A)) {
                    state.registerA /= 2;
                } else {
                    state.registerB /= 2;
                }

                state.processCount++;
                break;
            case OPERATION_TRIPLE:
                if (register.equals(REGISTER_A)) {
                    state.registerA *= 3;
                } else {
                    state.registerB *= 3;
                }

                state.processCount++;
                break;
            case OPERATION_INCREMENT:
                if (register.equals(REGISTER_A)) {
                    state.registerA++;
                } else {
                    state.registerB++;
                }

                state.processCount++;
                break;
            case OPERATION_JUMP:
                state.processCount += value;
                break;
            case OPERATION_JUMP_ON_EQUAL:
                if (register.equals(REGISTER_A) && (state.registerA % 2 == 0)) {
                    state.processCount += value;
                } else if (register.equals(REGISTER_B) && (state.registerB % 2 == 0)) {
                    state.processCount += value;
                } else {
                    state.processCount++;
                }

                break;
            case OPERATION_JUMP_ON_ONE:
                if (register.equals(REGISTER_A) && state.registerA == 1) {
                    state.processCount += value;
                } else if (register.equals(REGISTER_B) && state.registerB == 1) {
                    state.processCount += value;
                } else {
                    state.processCount++;
                }

                break;
            default:
                throw new IllegalStateException("unknown operation: " + op);
        }
    }
}
