package jp.example;

import java.math.BigDecimal;

public class Symbol {
    enum Type {
        NUMBER,
        ADD("+", 1),
        SUB("-", 1),
        MUL("*", 2),
        DIV("/", 2),
        RRACKET("("),
        LBRACKET(")");

        private String value;
        private int priority;

        private Type() {
            this(null, -1);
        }

        private Type(String value) {
            this(value, -1);
        }

        private Type(String value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        public Type getType(String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            for (Type type : Type.values()) {
                if (type == NUMBER) {
                    continue;
                }
                if (type.value.equals(value)) {
                    return type;
                }
            }

            try {
                new BigDecimal(value);
                return NUMBER;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
        
        public int getPriority() {
            return this.priority;
        }

    }
}
