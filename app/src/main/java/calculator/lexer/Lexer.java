package calculator.lexer;

import calculator.exception.EOIException;
import calculator.exception.InvalidTokenException;
import calculator.token.ITokenProvider;
import calculator.token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.LinkedList;

public class Lexer implements ITokenProvider {
    private final InputStream is;
    private final LinkedList<Token> tokenList;
    private Character current;

    public Lexer(InputStream is) {
        this.is = is;
        tokenList = new LinkedList<>();
        current = null;
    }

    private void readChar() throws IOException, EOIException {
        int nextByte = is.read();
        if (nextByte == -1) {
            current = null;
            throw new EOIException("end of input");
        }

        current = (char) nextByte;
    }

    private boolean isOperator() {
        char[] delimiters = new char[]{'+', '-', '*', '(', ')'};
        for (char delimiter : delimiters) {
            if (current == delimiter)
                return true;
        }

        return false;
    }

    private boolean isDelimiter() {
        return isOperator() || current == ' ';
    }

    private Token processOperator() throws InvalidTokenException {
        switch (current) {
            case '+' -> {
                return new Token(Token.Type.PLUS);
            }
            case '-' -> {
                return new Token(Token.Type.MINUS);
            }
            case '*' -> {
                return new Token(Token.Type.MULTIPLY);
            }
            case '(' -> {
                return new Token(Token.Type.LEFT_PARENTHESIS);
            }
            case ')' -> {
                return new Token(Token.Type.RIGHT_PARENTHESIS);
            }
            default -> throw new InvalidTokenException(current + " is not a valid token"); // Should not be reached
        }
    }

    private Token processNumber() throws IOException, InvalidTokenException {
        StringBuilder sb = new StringBuilder();
        try {
            while (!isDelimiter()) {
                sb.append(current);
                readChar();
            }

        } catch (EOIException e) {
            if (sb.isEmpty()) // Should not be reached
                throw new InvalidTokenException("empty number");
        }

        try {
            return new Token(Token.Type.NUMBER, Integer.parseInt(sb.toString()));
        } catch (NumberFormatException e) {
            throw new InvalidTokenException(sb + " is not a valid number");
        }
    }

    private Token processToken() throws InvalidTokenException, IOException {
        try {
            while (current == null || current == ' ') // Skip spaces
                readChar();
        } catch (EOIException e) {
            return new Token(Token.Type.END_OF_INPUT);
        }

        if (isOperator()) {
            Token operator = processOperator();
            current = null; // The lexer must read a new character the next time processToken is called
            return operator;
        }

        return processNumber();
    }

    @Override
    public void processTokens(int n) throws InvalidTokenException {
        for (int i = 0; i < n; i++) {
            try {
                tokenList.add(processToken());
            } catch (IOException e) {
                throw new InvalidTokenException("unable to process another token: " + i + " were processed");
            }
        }
    }

    @Override
    public int availableTokens() {
        return tokenList.size();
    }

    @Override
    public Token peek(int n) throws InvalidTokenException {
        if (n < 0)
            throw new InvalidParameterException("must be positive");

        if (n >= tokenList.size())
            throw new InvalidTokenException("not enough token processed");

        return tokenList.get(n);
    }

    @Override
    public Token pop() throws InvalidTokenException {
        if (tokenList.isEmpty())
            throw new InvalidTokenException("unable to pop a token");

        return tokenList.poll();
    }
}
