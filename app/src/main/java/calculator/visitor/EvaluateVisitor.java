package calculator.visitor;

import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;

public class EvaluateVisitor implements IVisitor<Integer> {
    @Override
    public Integer visit(NumberASTNode node) {
        return node.getValue();
    }

    @Override
    public Integer visit(AddBinaryASTNode node) {
        return node.getLeft().accept(this) + node.getRight().accept(this);
    }

    @Override
    public Integer visit(MulBinaryASTNode node) {
        return node.getLeft().accept(this) * node.getRight().accept(this);
    }

    @Override
    public Integer visit(MinusUnaryASTNode node) {
        return -node.getNode().accept(this);
    }
}
