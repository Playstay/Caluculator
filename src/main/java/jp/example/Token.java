package jp.example;

import java.math.BigDecimal;

public class Token {
    private BigDecimal value;
    private Type type;

    public Token(String value){
        if(value ==null){
            throw new IllegalArgumentException();
        }

        try{
            this.value = new BigDecimal(value);
            type = Type.NUMBER;
            return;
        }catch(NumberFormatException e){
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
        if(!isNumber()){
            throw new RuntimeException();
        }
        return value;
    }

    public boolean isNumber() {
        return type == Type.NUMBER;
    }
}
