package calculator.lexer;

import calculator.complex.Complex;
import calculator.token.exception.InvalidTokenException;
import calculator.token.Token;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class LexerTest {
    @Test
    void processTokens_oneNumber() {
        assertDoesNotThrow(() -> {
            String input = "42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
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
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
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
            assertEquals(new Token(Token.Type.NUMBER, new Complex(2, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.MULTIPLY), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(21, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_smallDivision() {
        assertDoesNotThrow(() -> {
            String input = "84/2";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(84, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.DIVIDE), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(2, 0)), lexer.pop());
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
            assertEquals(new Token(Token.Type.NUMBER, new Complex(32, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(10, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.RIGHT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_subtractionWithParentheses() {
        assertDoesNotThrow(() -> {
            String input = "84-(32+10)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(8);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(84, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.MINUS), lexer.pop());
            assertEquals(new Token(Token.Type.LEFT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(32, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(10, 0)), lexer.pop());
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
            assertEquals(new Token(Token.Type.NUMBER, new Complex(2, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.MULTIPLY), lexer.pop());
            assertEquals(new Token(Token.Type.LEFT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(11, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(10, 0)), lexer.pop());
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
            assertEquals(new Token(Token.Type.NUMBER, new Complex(14, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(28, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_notValidNumber() {
        assertThrows(InvalidTokenException.class, () -> {
            String input = "a";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
        });
    }

    @Test
    void processTokens_notValidNumberInsideCalculation() {
        assertThrows(InvalidTokenException.class, () -> {
            String input = "42+a";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
        });
    }

    @Test
    void processTokens_notEnoughProcessedToken() {
        assertThrows(InvalidTokenException.class, () -> {
            String input = "14+28";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(14, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(28, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_invalidTokenNotProcessed() {
        assertDoesNotThrow(() -> {
            String input = "42*a";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.MULTIPLY), lexer.pop());
        });
    }

    @Test
    void processTokens_processAfterEOI() {
        assertDoesNotThrow(() -> {
            String input = "42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(5);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_operatorAtTheEnd() {
        assertDoesNotThrow(() -> {
            String input = "42+";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(3);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_numberInsideParentheses() {
        assertDoesNotThrow(() -> {
            String input = "(42)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            assertEquals(new Token(Token.Type.LEFT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.RIGHT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_hugeNumber() {
        assertThrows(InvalidTokenException.class, () -> {
            String input = "546736573825856837268";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
        });
    }

    @Test
    void peek_firstToken() {
        assertDoesNotThrow(() -> {
            String input = "42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.peek(0));
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void peek_fifthToken() {
        assertDoesNotThrow(() -> {
            String input = "2*(11+10)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(8);
            assertEquals(new Token(Token.Type.PLUS), lexer.peek(4));
            assertEquals(new Token(Token.Type.NUMBER, new Complex(2, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.MULTIPLY), lexer.pop());
            assertEquals(new Token(Token.Type.LEFT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(11, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.PLUS), lexer.pop());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(10, 0)), lexer.pop());
            assertEquals(new Token(Token.Type.RIGHT_PARENTHESIS), lexer.pop());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
        });
    }

    @Test
    void processTokens_negativePeek() {
        assertThrows(InvalidParameterException.class, () -> {
            String input = "(42)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            lexer.peek(-1);
        });
    }

    @Test
    void processTokens_outOfBoundsPeek() {
        assertThrows(InvalidTokenException.class, () -> {
            String input = "2*(11+10)";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            lexer.peek(6);
        });
    }

    @Test
    void availableTokens_oneToken() {
        assertDoesNotThrow(() -> {
            String input = "42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(1);
            assertEquals(1, lexer.availableTokens());
        });
    }

    @Test
    void availableTokens_fourTokens() {
        assertDoesNotThrow(() -> {
            String input = "30 + 12";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(4);
            assertEquals(4, lexer.availableTokens());
        });
    }

    @Test
    void availableTokens_twoTokensThenPop() {
        assertDoesNotThrow(() -> {
            String input = "42";
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Lexer lexer = new Lexer(bais);
            lexer.processTokens(2);
            assertEquals(2, lexer.availableTokens());
            assertEquals(new Token(Token.Type.NUMBER, new Complex(42, 0)), lexer.pop());
            assertEquals(1, lexer.availableTokens());
            assertEquals(new Token(Token.Type.END_OF_INPUT), lexer.pop());
            assertEquals(0, lexer.availableTokens());
        });
    }
}
