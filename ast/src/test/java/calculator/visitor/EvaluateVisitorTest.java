package calculator.visitor;

import calculator.ast.ASTNode;
import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.complex.Complex;
import calculator.visitor.EvaluateVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EvaluateVisitorTest {
    @Test
    void accept_simpleNumberASTNode() {
        ASTNode node = new NumberASTNode(new Complex(42, 0));
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(42, 0), node.accept(visitor));
    }

    @Test
    void accept_simpleAddBinaryASTNode() {
        ASTNode node = new AddBinaryASTNode(
                new NumberASTNode(new Complex(32, 0)),
                new NumberASTNode(new Complex(10, 0))
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(42, 0), node.accept(visitor));
    }

    @Test
    void accept_simpleSubBinaryASTNode() {
        ASTNode node = new SubBinaryASTNode(
                new NumberASTNode(new Complex(52, 0)),
                new NumberASTNode(new Complex(10, 0))
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(42, 0), node.accept(visitor));
    }

    @Test
    void accept_simpleMulASTNode() {
        ASTNode node = new MulBinaryASTNode(
                new NumberASTNode(new Complex(21, 0)),
                new NumberASTNode(new Complex(2, 0))
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(42, 0), node.accept(visitor));
    }

    @Test
    void accept_simpleDivASTNode() {
        ASTNode node = new DivBinaryASTNode(
                new NumberASTNode(new Complex(84, 0)),
                new NumberASTNode(new Complex(2, 0))
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(42, 0), node.accept(visitor));
    }

    @Test
    void accept_divisionByZero() {
        ASTNode node = new DivBinaryASTNode(
                new NumberASTNode(new Complex(42, 0)),
                new NumberASTNode(new Complex())
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertThrows(ArithmeticException.class, () -> node.accept(visitor));
    }

    @Test
    void accept_simpleMinusUnaryASTNode() {
        ASTNode node = new MinusUnaryASTNode(
                new NumberASTNode(new Complex(42, 0))
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(-42, 0), node.accept(visitor));
    }

    @Test
    void accept_subtractionWithMinusUnary() {
        ASTNode node = new SubBinaryASTNode(
                new NumberASTNode(new Complex(32, 0)),
                new MinusUnaryASTNode(
                        new NumberASTNode(new Complex(10, 0))
                )
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(new Complex(42, 0), node.accept(visitor));
    }
}
