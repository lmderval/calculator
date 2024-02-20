package calculator.visitor;

import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;

public class PrintVisitor implements IVisitor<Void> {
    @Override
    public Void visit(NumberASTNode node) {
        System.out.print(node.getValue());
        return null;
    }

    @Override
    public Void visit(AddBinaryASTNode node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print("+");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(SubBinaryASTNode node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print("-");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(MulBinaryASTNode node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print("*");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(DivBinaryASTNode node) {
        System.out.print("(");
        node.getLeft().accept(this);
        System.out.print("/");
        node.getRight().accept(this);
        System.out.print(")");
        return null;
    }

    @Override
    public Void visit(MinusUnaryASTNode node) {
        System.out.print("-");
        node.getNode().accept(this);
        return null;
    }
}
