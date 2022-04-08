import java.math.BigDecimal;

public class Symbol {
   private BigDecimal value;
   private String operator;
   private Type type;

   private enum Type{
       OPERATOR("+-*/"),
       NUMBER,
       LEFT_BRACKET("("),
       RIGHT_BRACKET(")");

       private String operands;
       private Type(){
           operands = null;
       }
       private Type(String operand){
           this.operands = operand;
       }
   }

   Symbol(BigDecimal value){
       if(value==null){
           throw new IllegalArgumentException("value must not be null.");
       }

       this.value = value;
       this.operator = null;
   }

   Symbol(String operator){
       if(operator==null){
           throw new IllegalArgumentException("value must not be null.");
       }
       this.operator = operator;
       this.value = null;
   }
}
