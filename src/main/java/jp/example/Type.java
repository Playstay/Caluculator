package jp.example;


public enum Type {
    /** 数値 */
    NUMBER,
    /**加算記号 */
    ADD("+", 1),
    /** 減算記号 */
    SUB("-", 1),
    /**　乗算記号 */
    MUL("*", 2),
    /** 除算記号 */
    DIV("/", 2),
    /**　開きカッコ */
    RRACKET("("),
    /**　閉じカッコ */
    LBRACKET(")");

    private String value;
    private int priority;

    private Type() {
        this(null, -1);
    }

    private Type(String value) {
        this(value, -1);
    }

    private Type(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    /**
     * valueに対応するEnumを返します。このメソッドではNUMBERを返すことはありません。
     * @param value 対象の文字列
     * @return valueに対応する列挙型
     * @throws IllegalArgumentException valueに対応する列挙型が存在しない場合
     */
    public Type getOperand(String value) {
        if (value == null) {
            throw new NullPointerException("value should not be null.");
        }

        for (Type type : Type.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }

        throw new IllegalArgumentException(value +" is not valid token.");
    }

    /**
     * この記号の優先度を返します。
     * @return 優先度
     */
    public int getPriority() {
        return this.priority;
    }

}
