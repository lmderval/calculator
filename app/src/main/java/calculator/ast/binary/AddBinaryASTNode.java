package calculator.ast.binary;

import calculator.ast.ASTNode;
import calculator.visitor.IVisitor;

public class AddBinaryASTNode extends BinaryASTNode {
    public AddBinaryASTNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
