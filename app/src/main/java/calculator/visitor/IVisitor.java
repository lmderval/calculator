package calculator.visitor;

import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;

public interface IVisitor<T> {
    T visit(NumberASTNode node);

    T visit(AddBinaryASTNode node);

    T visit(MulBinaryASTNode node);

    T visit(MinusUnaryASTNode node);
}
