package calculator.visitor;

import calculator.ast.ASTNode;
import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.visitor.EvaluateVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void accept_simpleMulASTNode() {
        ASTNode node = new MulBinaryASTNode(
                new NumberASTNode(21),
                new NumberASTNode(2)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(42, node.accept(visitor));
    }

    @Test
    void accept_simpleMinusUnaryASTNode() {
        ASTNode node = new MinusUnaryASTNode(
                new NumberASTNode(42)
        );
        EvaluateVisitor visitor = new EvaluateVisitor();
        assertEquals(-42, node.accept(visitor));
    }
}
