package jp.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class SymbolTest {

    @Test
    public void 足し算で生成できること() {
        Symbol symbol = new Symbol("+");
        assertEquals(Type.ADD, symbol.getType());
    }

    @Test
    public void 引き算で生成できること() {
        Symbol symbol = new Symbol("-");
        assertEquals(Type.SUB, symbol.getType());
        assertEquals(false, symbol.isNumber());
    }

    @Test
    public void 数値で生成できること() {
        String valueString = "1.23";
        Symbol symbol = new Symbol(valueString);
        assertEquals(Type.NUMBER, symbol.getType());
        assertEquals(true, symbol.isNumber());
        assertEquals(new BigDecimal(valueString), symbol.getValue());
    }

    @Test
    public void nullで例外が発生すること() {
        assertThrows(IllegalArgumentException.class, () -> new Symbol(null));
    }
    
    @Test
    public void 変換できない文字列で例外が発生すること(){
        assertThrows(IllegalArgumentException.class, () -> new Symbol("hogehoge"));
    }
    
    @Test
    public void 記号でgetValueができないこと(){
        Symbol symbol = new Symbol("+");
        assertThrows(RuntimeException.class, () -> symbol.getValue());
    }
}
