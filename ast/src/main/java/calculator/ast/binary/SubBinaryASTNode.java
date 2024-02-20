package calculator.ast.binary;

import calculator.ast.ASTNode;
import calculator.visitor.IVisitor;

public class SubBinaryASTNode extends BinaryASTNode {
    public SubBinaryASTNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
