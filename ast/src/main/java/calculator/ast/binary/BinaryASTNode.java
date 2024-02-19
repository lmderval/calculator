package calculator.ast.binary;

import calculator.ast.ASTNode;

public abstract class BinaryASTNode extends ASTNode {
    protected final ASTNode left;
    protected final ASTNode right;

    public BinaryASTNode(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
}
