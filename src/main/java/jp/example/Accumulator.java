package jp.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * 逆ポーランド記法をもとに計算処理を実行するクラスです。
 * コンストラクタで{@link MathContext}を指定することで丸めモードを使用できますが、モードに従って丸めるのは除算の時のみです。
 */
public class Accumulator {
    private static final MathContext DEAFAULT_CONTEXT = MathContext.DECIMAL128;

    private MathContext context;

    /**
     * 丸めモードに{@link MathContext#DECIMAL128}を使用するように設定します。
     */
    public Accumulator() {
        this(DEAFAULT_CONTEXT);
    }

    /**
     * 引数の{@link MathContext}を利用して丸めを行うように設定します。
     * 
     * @param context
     * @throws NullPointerException contextがnullの場合
     */
    public Accumulator(MathContext context) {
        if (context == null) {
            throw new NullPointerException();
        }

        this.context = context;
    }

    /**
     * 計算処理を実行します。
     * 
     * @param expression 逆ポーランド記法順に格納されているリスト
     * @return 計算結果のBigDecimal
     * @throws ArithmeticException 計算の途中で0除算した場合。
     */
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
            throw new ArithmeticException();
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
        // 除算については丸めがないモード(MathContext.UNNECESSARY)では無限小数になるものは例外が発生する
        // そのため、contextを設定して起こらないようにする。
        return dividend.divide(divisor, context);
    }
}
