package jp.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccumulatorTest {
    
    @Test
    public void 簡単な計算が可能なこと() {
        Accumulator accumulator = new Accumulator();
        List<Token> exp = new ArrayList<>();
        exp.add(new Token("1"));
        exp.add(new Token("2"));
        exp.add(new Token("+"));
        
        assertEquals(new BigDecimal(3), accumulator.caluculate(exp));
    }
    
    @Test
    public void 減算の順序があっていること(){
        Accumulator accumulator = new Accumulator();
        List<Token> exp = new ArrayList<>();
        exp.add(new Token("1"));
        exp.add(new Token("2"));
        exp.add(new Token("-"));
        
        assertEquals(new BigDecimal(-1), accumulator.caluculate(exp));
    }
    
    @Test
    public void 除算の順序があっていること(){
        Accumulator accumulator = new Accumulator();
        List<Token> exp = new ArrayList<>();
        exp.add(new Token("1"));
        exp.add(new Token("3"));
        exp.add(new Token("/"));
        
        assertEquals(new BigDecimal(1).divide(new BigDecimal(3),MathContext.DECIMAL128), accumulator.caluculate(exp));
    }
    
    
    @Test
    public void 計算処理が連続する場合でも処理ができること() {
        Accumulator accumulator = new Accumulator();
        List<Token> exp = Arrays.stream("1 2 + 4 5 - *".split(" ")).map(x -> new Token(x)).toList();
        
        assertEquals(new BigDecimal("-3"), accumulator.caluculate(exp));
    }
    
    @Test
    public void 逆ポーランド記法がおかしい場合例外が発生すること(){
        Accumulator accumulator = new Accumulator();
        List<Token> exp = Arrays.stream("1 3 2 + 4 5 - *".split(" ")).map(x -> new Token(x)).toList();
        
        assertThrows(ArithmeticException.class, ()-> accumulator.caluculate(exp));
    }
}
