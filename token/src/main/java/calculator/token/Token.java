package calculator.token;

import calculator.complex.Complex;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class Token {
    private final Type type;
    private final Complex value;

    public Token(Type type) {
        this(type, new Complex());
    }

    public Token(Type type, @NonNull Complex value) {
        this.type = type;
        this.value = value;
    }

    public enum Type {
        END_OF_INPUT,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        LEFT_PARENTHESIS,
        RIGHT_PARENTHESIS,
        NUMBER
    }
}