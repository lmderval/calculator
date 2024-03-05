package calculator.visitor;

import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;
import calculator.complex.Complex;

public class EvaluateVisitor implements IVisitor<Complex> {
    @Override
    public Complex visit(NumberASTNode node) {
        return node.getValue();
    }

    @Override
    public Complex visit(AddBinaryASTNode node) {
        return node.getLeft().accept(this).add(node.getRight().accept(this));
    }

    @Override
    public Complex visit(SubBinaryASTNode node) {
        return node.getLeft().accept(this).sub(node.getRight().accept(this));
    }

    @Override
    public Complex visit(MulBinaryASTNode node) {
        return node.getLeft().accept(this).mul(node.getRight().accept(this));
    }

    @Override
    public Complex visit(DivBinaryASTNode node) throws ArithmeticException {
        return node.getLeft().accept(this).div(node.getRight().accept(this));
    }

    @Override
    public Complex visit(MinusUnaryASTNode node) {
        return node.getNode().accept(this).neg();
    }
}
