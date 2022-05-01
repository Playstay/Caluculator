package jp.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Accumulator {
    private static final MathContext DEAFAULT_CONTEXT = MathContext.DECIMAL128;

    private MathContext context;

    public Accumulator() {
        this(DEAFAULT_CONTEXT);
    }

    public Accumulator(MathContext context) {
        if (context == null) {
            throw new NullPointerException();
        }

        this.context = context;
    }

    public BigDecimal caluculate(List<Token> expression) {
        Deque<BigDecimal> stack = new ArrayDeque<>();
        for (Token token : expression) {
            if (token.isNumber()) {
                stack.push(token.getValue());
                continue;
            }

            BigDecimal result = execute(stack, token);
            stack.push(result);
        }

        if (stack.size() != 1) {
            throw new RuntimeException();
        }
        return stack.pop();
    }

    private BigDecimal execute(Deque<BigDecimal> stack, Token operand) {
        // TODO: 除数、被除数に当たる汎用的な単語がわからん...
        BigDecimal b = stack.pop();
        BigDecimal a = stack.pop();
        BigDecimal result = null;

        switch (operand.getType()) {
            case ADD:
                result = add(a, b);
                break;
            case SUB:
                result = subtract(a, b);
                break;
            case MUL:
                result = multiply(a, b);
                break;
            case DIV:
                result = divide(a, b);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return result;
    }

    private BigDecimal add(BigDecimal addend, BigDecimal augend) {
        return addend.add(augend);
    }

    private BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        return minuend.subtract(subtrahend);
    }

    private BigDecimal multiply(BigDecimal multiplier, BigDecimal multiplicand) {
        return multiplier.multiply(multiplicand);
    }

    private BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return dividend.divide(divisor, context);
    }
}
