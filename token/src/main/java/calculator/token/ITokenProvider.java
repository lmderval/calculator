package calculator.token;

import calculator.token.exception.InvalidTokenException;

public interface ITokenProvider {
    void processTokens(int n) throws InvalidTokenException;

    int availableTokens();

    Token peek(int n) throws InvalidTokenException;

    Token pop() throws InvalidTokenException;
}
