package calculator.lexer;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {
    @Test
    void processTokens_oneNumber() {
        assertDoesNotThrow(() -> {
            String input = "42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(new Token(Token.Type.NUMBER, 42), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_onePlus() {
        assertDoesNotThrow(() -> {
            String input = "+";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_oneNegativeNumber() {
        assertDoesNotThrow(() -> {
            String input = "-42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(3);
            assertEquals(new Token(Token.Type.MINUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 42), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_smallMultiplication() {
        assertDoesNotThrow(() -> {
            String input = "2*21";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            assertEquals(new Token(Token.Type.NUMBER, 2), lexer.pop());
            assertEquals(new Token(Token.Type.MULTIPLY), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 21), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_sumWithParentheses() {
        assertDoesNotThrow(() -> {
            String input = "(32+10)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(6);
            assertEquals(new Token(Token.Type.LEFT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 32), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 10), lexer.pop());
            assertEquals(new Token(Token.Type.RIGHT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_simpleCalculation() {
        assertDoesNotThrow(() -> {
            String input = "2*(11+10)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(8);
            assertEquals(new Token(Token.Type.NUMBER, 2), lexer.pop());
            assertEquals(new Token(Token.Type.MULTIPLY), lexer.pop());
            assertEquals(new Token(Token.Type.LEFT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 11), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 10), lexer.pop());
            assertEquals(new Token(Token.Type.RIGHT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_additionWithSpaces() {
        assertDoesNotThrow(() -> {
            String input = " 14 + 28 ";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            assertEquals(new Token(Token.Type.NUMBER, 14), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, 28), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }
}
