package calculator.ast;

import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.BinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.ast.unary.UnaryASTNode;
import calculator.complex.Complex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ASTNodeTest {
    @Test
    void getValue_singleNumberASTNode() {
        NumberASTNode node = new NumberASTNode(new Complex(42, 0));
        assertEquals(new Complex(42, 0), node.getValue());
    }

    @Test
    void getLeft_singleAddBinaryASTNode() {
        BinaryASTNode node = new AddBinaryASTNode(
                new NumberASTNode(new Complex(42, 0)),
                new NumberASTNode(new Complex(21, 0))
        );
        assertEquals(new Complex(42, 0), ((NumberASTNode) node.getLeft()).getValue());
    }

    @Test
    void getRight_singleSubBinaryASTNode() {
        BinaryASTNode node = new SubBinaryASTNode(
                new NumberASTNode(new Complex(42, 0)),
                new NumberASTNode(new Complex(21, 0))
        );
        assertEquals(new Complex(21, 0), ((NumberASTNode) node.getRight()).getValue());
    }

    @Test
    void getRight_singleMulBinaryASTNode() {
        BinaryASTNode node = new MulBinaryASTNode(
                new NumberASTNode(new Complex(42, 0)),
                new NumberASTNode(new Complex(21, 0))
        );
        assertEquals(new Complex(21, 0), ((NumberASTNode) node.getRight()).getValue());
    }

    @Test
    void getLeft_singleDivBinaryASTNode() {
        BinaryASTNode node = new MulBinaryASTNode(
                new NumberASTNode(new Complex(42, 0)),
                new NumberASTNode(new Complex(21, 0))
        );
        assertEquals(new Complex(42, 0), ((NumberASTNode) node.getLeft()).getValue());
    }

    @Test
    void getNode_singleMinusUnaryASTNode() {
        UnaryASTNode node = new MinusUnaryASTNode(
                new NumberASTNode(new Complex(42, 0))
        );
        assertEquals(new Complex(42, 0), ((NumberASTNode) node.getNode()).getValue());
    }
}
