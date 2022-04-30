package jp.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccumulatorTest {
    
    @Test
    public void 簡単な計算が可能なこと() {
        List<Token> exp = new ArrayList<>();
        exp.add(new Token("1"));
        exp.add(new Token("2"));
        exp.add(new Token("+"));
        
        assertEquals(new BigDecimal(3), Accumulator.caluculate(exp));
    }
    
    @Test
    public void 減算の順序があっていること(){
        List<Token> exp = new ArrayList<>();
        exp.add(new Token("1"));
        exp.add(new Token("2"));
        exp.add(new Token("-"));
        
        assertEquals(new BigDecimal(-1), Accumulator.caluculate(exp));
    }
    
    @Test
    public void 除算の順序があっていること(){
        List<Token> exp = new ArrayList<>();
        exp.add(new Token("1"));
        exp.add(new Token("3"));
        exp.add(new Token("/"));
        
        assertEquals(new BigDecimal(1).divide(new BigDecimal(3)), Accumulator.caluculate(exp));
    }
}
