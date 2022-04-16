package jp.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {
    
    @Test
    public void 足し算の記号が返ってくること(){
        assertEquals(Type.getOperand("+"),Type.ADD);
    }

    @Test
    public void 引き算の記号が返ってくること(){
        assertEquals(Type.getOperand("-"),Type.SUB);
    }

    @Test
    public void 掛け算の記号が返ってくること(){
        assertEquals(Type.getOperand("*"),Type.MUL);
    }
    
    @Test
    public void 割り算の記号が返ってくること(){
        assertEquals(Type.getOperand("/"),Type.DIV);
    }
    
    @Test
    public void 左カッコの記号が返ってくること(){
        assertEquals(Type.getOperand("("),Type.LBRACKET);
    }

    @Test
    public void 右カッコの記号が返ってくること(){
        assertEquals(Type.getOperand(")"),Type.RBRACKET);
    }
    
    @Test
    public void 数字の列挙型が返ってこないこと(){
        assertThrows(IllegalArgumentException.class, ()->Type.getOperand("112"));
    }

    @Test
    public void 正しくない値で例外が発生すること(){
        assertThrows(IllegalArgumentException.class, ()->Type.getOperand("＋"));
    }
    
    @Test
    public void nullで例外が発生すること(){
        assertThrows(IllegalArgumentException.class, ()->Type.getOperand(null));
    }
}
