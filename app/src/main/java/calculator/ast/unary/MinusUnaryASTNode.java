package calculator.ast.unary;

import calculator.ast.ASTNode;
import calculator.visitor.IVisitor;

public class MinusUnaryASTNode extends UnaryASTNode {
    public MinusUnaryASTNode(ASTNode node) {
        super(node);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
