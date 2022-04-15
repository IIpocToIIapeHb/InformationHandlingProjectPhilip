package epam.com.infohandling;

public class Lexeme implements Component {

    private String value;
    private LexemeType type;

    private Lexeme(String value, LexemeType type) {
        this.value = value;
        this.type = type;
    }

    public static Lexeme word(String value) {
        return new Lexeme(value, LexemeType.WORD);
    }

    public static Lexeme expression(String value) {
        return new Lexeme(value, LexemeType.EXPRESSION);
    }

    public String getValue() {
        return value;
    }

    public LexemeType getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setType(LexemeType type) {
        this.type = type;
    }

    /*    @Override
    public String toString() {
        return "Lexeme{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }*/

    @Override
    public String toString() {
        return value;
    }
}
