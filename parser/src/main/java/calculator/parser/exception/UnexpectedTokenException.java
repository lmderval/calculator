package calculator.parser.exception;

import calculator.token.Token;

public class UnexpectedTokenException extends Exception {
    private final Token token;

    public UnexpectedTokenException(Token token) {
        super("unexpected token: " + token.getType());
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
