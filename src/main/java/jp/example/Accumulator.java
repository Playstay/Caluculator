package jp.example;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Accumulator {
    public static BigDecimal caluculate(List<Token> expression) {
        Deque<BigDecimal> stack = new ArrayDeque<>();
        for (Token token : expression) {
            if(token.isNumber()){
                stack.push(token.getValue());
                continue;
            }
            
            BigDecimal result = execute(stack, token);
            stack.push(result);
        }
        
        if(stack.size()!=1){
            throw new RuntimeException();
        }
        return stack.pop();
    }
    
    private static BigDecimal execute(Deque<BigDecimal> stack, Token operand){
        // TODO: 除数、被除数に当たる汎用的な単語がわからん...
        BigDecimal b = stack.pop();
        BigDecimal a = stack.pop();
        BigDecimal result = null;
        
        switch(operand.getType()){
            case ADD:
            result = a.add(b);
            break;
            case SUB:
            result = a.subtract(b);
            break;
            case MUL:
            result = a.multiply(b);
            break;
            case DIV:
            result = a.divide(b);
            break;
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }
}
