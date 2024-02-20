package calculator.visitor;

import calculator.ast.ASTNode;
import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.visitor.PrintVisitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class PrintVisitorTest {
    private final PrintStream DEFAULT_STREAM = System.out;
    private final ByteArrayOutputStream BAOS = new ByteArrayOutputStream();

    @BeforeEach
    void redirectSTDOUT() {
        System.setOut(new PrintStream(BAOS));
    }

    @AfterEach
    void restoreSTDOUT() {
        System.setOut(DEFAULT_STREAM);
    }

    @Test
    void accept_simpleNumberASTNode() {
        ASTNode node = new NumberASTNode(42);
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("42", BAOS.toString());
    }

    @Test
    void accept_simpleAddBinaryASTNode() {
        ASTNode node = new AddBinaryASTNode(
                new NumberASTNode(32),
                new NumberASTNode(10)
        );
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("(32+10)", BAOS.toString());
    }

    @Test
    void accept_simpleSubBinaryASTNode() {
        ASTNode node = new SubBinaryASTNode(
                new NumberASTNode(52),
                new NumberASTNode(10)
        );
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("(52-10)", BAOS.toString());
    }

    @Test
    void accept_simpleMulASTNode() {
        ASTNode node = new MulBinaryASTNode(
                new NumberASTNode(21),
                new NumberASTNode(2)
        );
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("(21*2)", BAOS.toString());
    }

    @Test
    void accept_simpleDivASTNode() {
        ASTNode node = new DivBinaryASTNode(
                new NumberASTNode(84),
                new NumberASTNode(2)
        );
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("(84/2)", BAOS.toString());
    }

    @Test
    void accept_divisionByZero() {
        ASTNode node = new DivBinaryASTNode(
                new NumberASTNode(42),
                new NumberASTNode(0)
        );
        PrintVisitor visitor = new PrintVisitor();
        assertDoesNotThrow(() -> {
            node.accept(visitor);
            assertEquals("(42/0)", BAOS.toString());
        });
    }

    @Test
    void accept_simpleMinusUnaryASTNode() {
        ASTNode node = new MinusUnaryASTNode(
                new NumberASTNode(42)
        );
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("-42", BAOS.toString());
    }

    @Test
    void accept_subtractionWithMinusUnary() {
        ASTNode node = new SubBinaryASTNode(
                new NumberASTNode(32),
                new MinusUnaryASTNode(
                        new NumberASTNode(10)
                )
        );
        PrintVisitor visitor = new PrintVisitor();
        node.accept(visitor);
        assertEquals("(32--10)", BAOS.toString());
    }
}
