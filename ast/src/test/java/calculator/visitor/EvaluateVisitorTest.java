package calculator.visitor;

import calculator.ast.ASTNode;
import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.visitor.EvaluateVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EvaluateVisitorTest {
    @Test
    void accept_simpleNumberASTNode() {
        ASTNode node = new NumberASTNode(42);
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }

    @Test
    void accept_simpleAddBinaryASTNode() {
        ASTNode node = new AddBinaryASTNode(
                new NumberASTNode(32),
                new NumberASTNode(10)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }

    @Test
    void accept_simpleSubBinaryASTNode() {
        ASTNode node = new SubBinaryASTNode(
                new NumberASTNode(52),
                new NumberASTNode(10)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }

    @Test
    void accept_simpleMulASTNode() {
        ASTNode node = new MulBinaryASTNode(
                new NumberASTNode(21),
                new NumberASTNode(2)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }

    @Test
    void accept_simpleDivASTNode() {
        ASTNode node = new DivBinaryASTNode(
                new NumberASTNode(84),
                new NumberASTNode(2)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }

    @Test
    void accept_divisionByZero() {
        ASTNode node = new DivBinaryASTNode(
                new NumberASTNode(42),
                new NumberASTNode(0)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertThrows(ArithmeticException.class, () -> node.accept(visitor));
    }

    @Test
    void accept_simpleMinusUnaryASTNode() {
        ASTNode node = new MinusUnaryASTNode(
                new NumberASTNode(42)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(-42, node.accept(visitor));
    }

    @Test
    void accept_subtractionWithMinusUnary() {
        ASTNode node = new SubBinaryASTNode(
                new NumberASTNode(32),
                new MinusUnaryASTNode(
                        new NumberASTNode(10)
                )
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }
}
