package calculator.token;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Token {
    private final Type type;
    private final int value;

    public Token(Type type) {
        this(type, 0);
    }

    public Token(Type type, int value) {
        this.type = type;
        this.value = value;
    }

    public enum Type {
        END_OF_INPUT,
        PLUS,
        MINUS,
        MULTIPLY,
        LEFT_PARENTHESIS,
        RIGHT_PARENTHESIS,
        NUMBER
    }
}