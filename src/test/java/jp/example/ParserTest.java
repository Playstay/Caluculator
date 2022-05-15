package jp.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void 単体でパースできること() {
        String expression = "2";
        List<Token> exp = List.of(new Token(expression));
        assertEquals(exp, Parser.execute(exp));
    }

    @Test
    public void 記号のみでもパース出来ること() {
        String expression = "+";
        List<Token> exp = List.of(new Token(expression));
        assertEquals(exp, Parser.execute(exp));
    }

    @Test
    public void 簡単な式をパース出来ること() {
        List<Token> exp = Arrays.stream(new String[] { "1", "+", "2" }).map(x -> new Token(x)).toList();
        List<Token> rpexp = Arrays.stream(new String[] { "1", "2", "+" }).map(x -> new Token(x)).toList();
        assertEquals(rpexp, Parser.execute(exp));
    }

    @Test
    public void 複数項の式をパース出来ること() {
        List<Token> exp = Arrays.stream(new String[] { "1", "+", "2", "-", "4" }).map(x -> new Token(x)).toList();
        List<Token> rpexp = Arrays.stream(new String[] { "1", "2", "+", "4", "-" }).map(x -> new Token(x)).toList();
        assertEquals(rpexp, Parser.execute(exp));
    }
    
    @Test 
    public void 優先順位を処理できること() {
        List<Token> exp = Arrays.stream(new String[] { "1", "+", "2", "*", "4" }).map(x -> new Token(x)).toList();
        List<Token> rpexp = Arrays.stream(new String[] { "1", "2", "4", "*", "+" }).map(x -> new Token(x)).toList();
        assertEquals(rpexp, Parser.execute(exp));
    }
}
