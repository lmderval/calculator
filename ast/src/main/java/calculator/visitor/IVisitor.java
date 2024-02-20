package calculator.visitor;

import calculator.ast.NumberASTNode;
import calculator.ast.binary.AddBinaryASTNode;
import calculator.ast.binary.DivBinaryASTNode;
import calculator.ast.binary.MulBinaryASTNode;
import calculator.ast.binary.SubBinaryASTNode;
import calculator.ast.unary.MinusUnaryASTNode;

public interface IVisitor<T> {
    T visit(NumberASTNode node);

    T visit(AddBinaryASTNode node);

    T visit(SubBinaryASTNode node);

    T visit(MulBinaryASTNode node);

    T visit(DivBinaryASTNode node);

    T visit(MinusUnaryASTNode node);
}
