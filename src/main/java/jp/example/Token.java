package jp.example;

import java.math.BigDecimal;

public class Token {
    private BigDecimal value;
    private Type type;

    public Token(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }

        try {
            this.value = new BigDecimal(value);
            type = Type.NUMBER;
            return;
        } catch (NumberFormatException e) {
            // 数値に変換できないことはあるけど何かする必要はない。
            // 基本的に例外を処理フローに含めるのはよろしくないが仕方ない。
        }

        this.value = null;
        type = Type.getOperand(value);
    }

    protected void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getValue() {
        if (!isNumber()) {
            throw new RuntimeException();
        }
        return value;
    }

    public boolean isNumber() {
        return type == Type.NUMBER;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Token)) {
            return false;
        }

        Token a = (Token) obj;
        if (this.isNumber()) {
            return a.isNumber() && (this.getValue().equals(a.getValue()));
        } else {
            return this.getType() == a.getType();
        }
    }

    @Override
    public String toString() {
        StringBuffer message = new StringBuffer();
        message.append("Type:")
                .append(getType());
        if(isNumber()){
            message.append(",value:")
            .append(getValue().toPlainString());
        }
        return message.toString();
    }
}
