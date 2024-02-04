package calculator.ast;

import calculator.App;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.BinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.ast.unary.UnaryASTNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ASTNodeTest {
    @Test
    void getValue_singleNumberASTNode() {
        NumberASTNode node = new NumberASTNode(42);
        assertEquals(42, node.getValue());
    }

    @Test
    void getLeft_singleAddBinaryASTNode() {
        BinaryASTNode node = new AddBinaryASTNode(
                new NumberASTNode(42),
                new NumberASTNode(21)
        );
        assertEquals(42, ((NumberASTNode) node.getLeft()).getValue());
    }

    @Test
    void getRight_singleMulBinaryASTNode() {
        BinaryASTNode node = new MulBinaryASTNode(
                new NumberASTNode(42),
                new NumberASTNode(21)
        );
        assertEquals(21, ((NumberASTNode) node.getRight()).getValue());
    }

    @Test
    void getNode_singleMinusUnaryASTNode() {
        UnaryASTNode node = new MinusUnaryASTNode(
                new NumberASTNode(42)
        );
        assertEquals(42, ((NumberASTNode) node.getNode()).getValue());
    }
}
