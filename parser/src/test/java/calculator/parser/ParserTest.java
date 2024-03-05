package calculator.parser;

import calculator.ast.ASTNode;
import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.complex.Complex;
import calculator.token.exception.InvalidTokenException;
import calculator.token.ITokenProvider;
import calculator.token.Token;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    void parseExpr_endOfInput() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(NumberASTNode.class, node);
            assertEquals(new Complex(), ((NumberASTNode) node).getValue());
        });
    }

    @Test
    void parseExpr_singleNumber() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(42, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(NumberASTNode.class, node);
            assertEquals(new Complex(42, 0), ((NumberASTNode) node).getValue());
        });
    }

    @Test
    void parseExpr_simpleAddition() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(30, 0)),
                new Token(Token.Type.PLUS),
                new Token(Token.Type.NUMBER, new Complex(12, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(AddBinaryASTNode.class, node);
            AddBinaryASTNode add = (AddBinaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, add.getLeft());
            assertInstanceOf(NumberASTNode.class, add.getRight());
            assertEquals(new Complex(30, 0), ((NumberASTNode) add.getLeft()).getValue());
            assertEquals(new Complex(12, 0), ((NumberASTNode) add.getRight()).getValue());
        });
    }

    @Test
    void parseExpr_simpleSubtraction() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(54, 0)),
                new Token(Token.Type.MINUS),
                new Token(Token.Type.NUMBER, new Complex(12, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(SubBinaryASTNode.class, node);
            SubBinaryASTNode sub = (SubBinaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, sub.getLeft());
            assertInstanceOf(NumberASTNode.class, sub.getRight());
            assertEquals(new Complex(54, 0), ((NumberASTNode) sub.getLeft()).getValue());
            assertEquals(new Complex(12, 0), ((NumberASTNode) sub.getRight()).getValue());
        });
    }

    @Test
    void parseExpr_simpleMultiplication() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(2, 0)),
                new Token(Token.Type.MULTIPLY),
                new Token(Token.Type.NUMBER, new Complex(21, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(MulBinaryASTNode.class, node);
            MulBinaryASTNode mul = (MulBinaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, mul.getLeft());
            assertInstanceOf(NumberASTNode.class, mul.getRight());
            assertEquals(new Complex(2, 0), ((NumberASTNode) mul.getLeft()).getValue());
            assertEquals(new Complex(21, 0), ((NumberASTNode) mul.getRight()).getValue());
        });
    }

    @Test
    void parseExpr_simpleDivision() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(84, 0)),
                new Token(Token.Type.DIVIDE),
                new Token(Token.Type.NUMBER, new Complex(2, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(DivBinaryASTNode.class, node);
            DivBinaryASTNode div = (DivBinaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, div.getLeft());
            assertInstanceOf(NumberASTNode.class, div.getRight());
            assertEquals(new Complex(84, 0), ((NumberASTNode) div.getLeft()).getValue());
            assertEquals(new Complex(2, 0), ((NumberASTNode) div.getRight()).getValue());
        });
    }

    @Test
    void parseExpr_simpleExpression() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(12, 0)),
                new Token(Token.Type.PLUS),
                new Token(Token.Type.NUMBER, new Complex(2, 0)),
                new Token(Token.Type.MULTIPLY),
                new Token(Token.Type.NUMBER, new Complex(15, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(AddBinaryASTNode.class, node);
            AddBinaryASTNode add = (AddBinaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, add.getLeft());
            assertInstanceOf(MulBinaryASTNode.class, add.getRight());
            assertEquals(new Complex(12, 0), ((NumberASTNode) add.getLeft()).getValue());
            MulBinaryASTNode mul = (MulBinaryASTNode) add.getRight();
            assertInstanceOf(NumberASTNode.class, mul.getLeft());
            assertInstanceOf(NumberASTNode.class, mul.getRight());
            assertEquals(new Complex(2, 0), ((NumberASTNode) mul.getLeft()).getValue());
            assertEquals(new Complex(15, 0), ((NumberASTNode) mul.getRight()).getValue());
        });
    }

    @Test
    void parseExpr_negativeNumber() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.MINUS),
                new Token(Token.Type.NUMBER, new Complex(42, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(MinusUnaryASTNode.class, node);
            MinusUnaryASTNode neg = (MinusUnaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, neg.getNode());
            assertEquals(new Complex(42, 0), ((NumberASTNode) neg.getNode()).getValue());
        });
    }

    @Test
    void parseExpr_subtractionWithNegativeNumber() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.NUMBER, new Complex(30, 0)),
                new Token(Token.Type.MINUS),
                new Token(Token.Type.MINUS),
                new Token(Token.Type.NUMBER, new Complex(12, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(SubBinaryASTNode.class, node);
            SubBinaryASTNode sub = (SubBinaryASTNode) node;
            assertInstanceOf(NumberASTNode.class, sub.getLeft());
            assertInstanceOf(MinusUnaryASTNode.class, sub.getRight());
            MinusUnaryASTNode neg = (MinusUnaryASTNode) sub.getRight();
            assertEquals(new Complex(30, 0), ((NumberASTNode) sub.getLeft()).getValue());
            assertEquals(new Complex(12, 0), ((NumberASTNode) neg.getNode()).getValue());
        });
    }

    @Test
    void parseExpr_expressionWithParentheses() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.LEFT_PARENTHESIS),
                new Token(Token.Type.NUMBER, new Complex(12, 0)),
                new Token(Token.Type.PLUS),
                new Token(Token.Type.NUMBER, new Complex(4, 0)),
                new Token(Token.Type.RIGHT_PARENTHESIS),
                new Token(Token.Type.MULTIPLY),
                new Token(Token.Type.NUMBER, new Complex(2, 0)),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(MulBinaryASTNode.class, node);
            MulBinaryASTNode mul = (MulBinaryASTNode) node;
            assertInstanceOf(AddBinaryASTNode.class, mul.getLeft());
            assertInstanceOf(NumberASTNode.class, mul.getRight());
            AddBinaryASTNode add = (AddBinaryASTNode) mul.getLeft();
            assertInstanceOf(NumberASTNode.class, add.getLeft());
            assertInstanceOf(NumberASTNode.class, add.getRight());
            assertEquals(new Complex(12, 0), ((NumberASTNode) add.getLeft()).getValue());
            assertEquals(new Complex(4, 0), ((NumberASTNode) add.getRight()).getValue());
            assertEquals(new Complex(2, 0), ((NumberASTNode) mul.getRight()).getValue());
        });
    }

    @Test
    void parseExpr_complexExpression() {
        TestTokenProvider tokenProvider = TestTokenProvider.of(
                new Token(Token.Type.LEFT_PARENTHESIS),
                new Token(Token.Type.NUMBER, new Complex(2, 0)),
                new Token(Token.Type.PLUS),
                new Token(Token.Type.NUMBER, new Complex(4, 0)),
                new Token(Token.Type.RIGHT_PARENTHESIS),
                new Token(Token.Type.MULTIPLY),
                new Token(Token.Type.MINUS),
                new Token(Token.Type.LEFT_PARENTHESIS),
                new Token(Token.Type.NUMBER, new Complex(3, 0)),
                new Token(Token.Type.PLUS),
                new Token(Token.Type.NUMBER, new Complex(4, 0)),
                new Token(Token.Type.RIGHT_PARENTHESIS),
                new Token(Token.Type.END_OF_INPUT)
        );
        Parser parser = new Parser(tokenProvider);
        assertDoesNotThrow(() -> {
            ASTNode node = parser.parseExpr();
            assertInstanceOf(MulBinaryASTNode.class, node);
            MulBinaryASTNode mul = (MulBinaryASTNode) node;
            assertInstanceOf(AddBinaryASTNode.class, mul.getLeft());
            assertInstanceOf(MinusUnaryASTNode.class, mul.getRight());
            AddBinaryASTNode addLeft = (AddBinaryASTNode) mul.getLeft();
            assertInstanceOf(NumberASTNode.class, addLeft.getLeft());
            assertInstanceOf(NumberASTNode.class, addLeft.getRight());
            assertEquals(new Complex(2, 0), ((NumberASTNode) addLeft.getLeft()).getValue());
            assertEquals(new Complex(4, 0), ((NumberASTNode) addLeft.getRight()).getValue());
            MinusUnaryASTNode neg = (MinusUnaryASTNode) mul.getRight();
            assertInstanceOf(AddBinaryASTNode.class, neg.getNode());
            AddBinaryASTNode addRight = (AddBinaryASTNode) neg.getNode();
            assertInstanceOf(NumberASTNode.class, addRight.getLeft());
            assertInstanceOf(NumberASTNode.class, addRight.getRight());
            assertEquals(new Complex(3, 0), ((NumberASTNode) addRight.getLeft()).getValue());
            assertEquals(new Complex(4, 0), ((NumberASTNode) addRight.getRight()).getValue());
        });
    }

    private record TestTokenProvider(LinkedList<Token> tokens) implements ITokenProvider {

        public static TestTokenProvider of(Token... tokens) {
            return new TestTokenProvider(new LinkedList<>(List.of(tokens)));
        }

        @Override
        public void processTokens(int n) throws InvalidTokenException {
            if (tokens.size() < n) {
                throw new InvalidTokenException("not enough tokens to process");
            }
        }

        @Override
        public int availableTokens() {
            return tokens.size();
        }

        @Override
        public Token peek(int n) throws InvalidTokenException {
            if (tokens.size() < n) {
                throw new InvalidTokenException("not enough tokens processed");
            }

            return tokens.get(n);
        }

        @Override
        public Token pop() throws InvalidTokenException {
            if (tokens.isEmpty()) {
                throw new InvalidTokenException("not enough tokens processed");
            }

            return tokens.poll();
        }
    }
}
