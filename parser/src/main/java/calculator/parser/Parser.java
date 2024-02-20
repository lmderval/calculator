package calculator.parser;

import calculator.ast.ASTNode;
import calculator.ast.NumberASTNode;
import calculator.ast.binary.*;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.token.exception.InvalidTokenException;
import calculator.parser.exception.UnexpectedTokenException;
import calculator.token.ITokenProvider;
import calculator.token.Token;

public class Parser {
    private final ITokenProvider tokenProvider;

    public Parser(ITokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    private Token peekToken() throws InvalidTokenException {
        if (tokenProvider.availableTokens() < 1) {
            tokenProvider.processTokens(1);
        }

        return tokenProvider.peek(0);
    }

    private Token popToken() throws InvalidTokenException {
        if (tokenProvider.availableTokens() < 1) {
            tokenProvider.processTokens(1);
        }

        return tokenProvider.pop();
    }

    private BinaryASTNode createBinaryASTNodeFrom(Token operator, ASTNode left, ASTNode right) throws UnexpectedTokenException {
        switch (operator.getType()) {
            case PLUS -> {
                return new AddBinaryASTNode(left, right);
            }
            case MINUS -> {
                return new SubBinaryASTNode(left, right);
            }
            case MULTIPLY -> {
                return new MulBinaryASTNode(left, right);
            }
            case DIVIDE -> {
                return new DivBinaryASTNode(left, right);
            }
            default -> throw new UnexpectedTokenException(operator);
        }
    }

    public ASTNode parseExpr() throws InvalidTokenException, UnexpectedTokenException {
        Token token = peekToken();
        if (token.getType() == Token.Type.END_OF_INPUT) {
            return new NumberASTNode(0);
        }

        if (token.getType() != Token.Type.NUMBER
                && token.getType() != Token.Type.LEFT_PARENTHESIS
                && token.getType() != Token.Type.MINUS) {
            throw new UnexpectedTokenException(token);
        }

        ASTNode node = parseSum();
        token = peekToken();
        if (token.getType() != Token.Type.RIGHT_PARENTHESIS
                && token.getType() != Token.Type.END_OF_INPUT) {
            throw new UnexpectedTokenException(token);
        }

        return node;
    }

    public ASTNode parseSum() throws InvalidTokenException, UnexpectedTokenException {
        Token token = peekToken();
        if (token.getType() != Token.Type.NUMBER
                && token.getType() != Token.Type.LEFT_PARENTHESIS
                && token.getType() != Token.Type.MINUS) {
            throw new UnexpectedTokenException(token);
        }

        ASTNode node = parseProd();
        token = peekToken();
        while (token.getType() == Token.Type.PLUS || token.getType() == Token.Type.MINUS) {
            Token operator = token;
            tokenProvider.pop();

            token = peekToken();
            if (token.getType() != Token.Type.NUMBER
                    && token.getType() != Token.Type.LEFT_PARENTHESIS
                    && token.getType() != Token.Type.MINUS) {
                throw new UnexpectedTokenException(token);
            }

            ASTNode right = parseProd();
            token = peekToken();
            node = createBinaryASTNodeFrom(operator, node, right);
        }

        if (token.getType() != Token.Type.RIGHT_PARENTHESIS
                && token.getType() != Token.Type.END_OF_INPUT) {
            throw new UnexpectedTokenException(token);
        }

        return node;
    }

    public ASTNode parseProd() throws InvalidTokenException, UnexpectedTokenException {
        Token token = peekToken();
        if (token.getType() != Token.Type.NUMBER
                && token.getType() != Token.Type.LEFT_PARENTHESIS
                && token.getType() != Token.Type.MINUS) {
            throw new UnexpectedTokenException(token);
        }

        ASTNode node = parseTerm();
        token = peekToken();
        while (token.getType() == Token.Type.MULTIPLY || token.getType() == Token.Type.DIVIDE) {
            Token operator = token;
            tokenProvider.pop();

            token = peekToken();
            if (token.getType() != Token.Type.NUMBER
                    && token.getType() != Token.Type.LEFT_PARENTHESIS
                    && token.getType() != Token.Type.MINUS) {
                throw new UnexpectedTokenException(token);
            }

            ASTNode right = parseTerm();
            token = peekToken();
            node = createBinaryASTNodeFrom(operator, node, right);
        }

        if (token.getType() != Token.Type.PLUS
                && token.getType() != Token.Type.MINUS
                && token.getType() != Token.Type.RIGHT_PARENTHESIS
                && token.getType() != Token.Type.END_OF_INPUT) {
            throw new UnexpectedTokenException(token);
        }

        return node;
    }

    public ASTNode parseTerm() throws InvalidTokenException, UnexpectedTokenException {
        Token token = popToken();
        if (token.getType() != Token.Type.NUMBER
                && token.getType() != Token.Type.LEFT_PARENTHESIS
                && token.getType() != Token.Type.MINUS) {
            throw new UnexpectedTokenException(token);
        }

        if (token.getType() == Token.Type.NUMBER) {
            return new NumberASTNode(token.getValue());
        }

        if (token.getType() == Token.Type.LEFT_PARENTHESIS) {
            ASTNode node = parseExpr();
            token = popToken();
            if (token.getType() != Token.Type.RIGHT_PARENTHESIS) {
                throw new UnexpectedTokenException(token);
            }

            return node;
        }

        // token.getType() == Token.Type.MINUS
        return new MinusUnaryASTNode(parseNegTerm());
    }

    public ASTNode parseNegTerm() throws InvalidTokenException, UnexpectedTokenException {
        Token token = popToken();
        if (token.getType() != Token.Type.NUMBER
                && token.getType() != Token.Type.LEFT_PARENTHESIS) {
            throw new UnexpectedTokenException(token);
        }

        if (token.getType() == Token.Type.NUMBER) {
            return new NumberASTNode(token.getValue());
        }

        // token.getType() == Token.Type.LEFT_PARENTHESIS
        ASTNode node = parseExpr();
        token = popToken();
        if (token.getType() != Token.Type.RIGHT_PARENTHESIS) {
            throw new UnexpectedTokenException(token);
        }

        return node;
    }
}
