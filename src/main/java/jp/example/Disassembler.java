package jp.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Disassembler {
    // 正規表現で意味がある文字はバックスラッシュでエスケープする必要がある。
    // が、バックスラッシュ単体ではJava側でエスケープしていると判断されるのでバックスラッシュもエスケープする。
    // なので、バックスラッシュをヒットする正規表現を書く際には、\\\\になる。
    private final static String REGEXP_DIGIT ="\\d(\\.\\d)?";
    private final static String REGEXP_OPERATOR ="[+-/*]";
    private final static String REGEXP_LBRACKET ="\\(";
    private final static String REGEXP_RBRACKET ="\\)";
    
    // まず [数値][オペランド][数値]ができるようにする。
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("");

    public static List<Token> execute(String expression){
        List<Token> result = new ArrayList<>();
        return result;
    }
    
    private static String createRegex(String... exps){
        StringBuffer result = new StringBuffer();
        
    }
}
