package jp.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Parser {

    public static List<Token> execute(List<Token> expression) {
        Deque<Token> stack = new ArrayDeque<>();
        List<Token> buffer = new ArrayList<>();
        // アルゴリズム参考サイト
        // http://shopping2.gmobb.jp/htdmnr/www08/c-tips/rpn/rpn02.html
        for (Token token : expression) {
            if (token.isNumber()) {
                buffer.add(token);
                continue;
            }

            if (token.getType() == Type.RBRACKET) {
                moveToBuffer(stack, buffer);
            } else if (token.getType() == Type.LBRACKET) {
                stack.push(token);
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        stack.addFirst(token);
                        break;
                    }
                    // peekはスタックトップを取得するが削除しない
                    Token top = stack.peek();
                    if (token.getType().getPriority() > top.getType().getPriority()) {
                        stack.addFirst(token);
                        break;
                    }

                    buffer.add(stack.pop());
                }
            }
        }

        while (!stack.isEmpty()) {
            Token token = stack.removeFirst();
            if(token.getType() == Type.RBRACKET){
                throw new ArithmeticException("カッコの対応が取れていません。");
            }

            buffer.add(token);
        }
        return buffer;
    }

    /**
     * 現在のスタックの中身を{@link Type#LBRACKET}が出現するまでバッファに移動します。
     * {@linkplain Type#LBRACKET}自体はバッファに移動せず削除します。
     * 
     * @param stack
     * @param buffer
     * @throws ArithmeticException {@linkplain Type#LBRACKET}が出現せずにスタックが空になった場合。
     */
    private static void moveToBuffer(Deque<Token> stack, List<Token> buffer) {
        while (!stack.isEmpty()) {
            Token popdToken = stack.pop();
            if (popdToken.getType() == Type.LBRACKET) {
                return;
            }
            buffer.add(popdToken);
        }

        throw new ArithmeticException("開きカッコと閉じカッコの数が一致しません");
    }
}
