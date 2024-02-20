package calculator.ast.binary;

import calculator.ast.ASTNode;
import calculator.visitor.IVisitor;

public class DivBinaryASTNode extends BinaryASTNode {
    public DivBinaryASTNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
