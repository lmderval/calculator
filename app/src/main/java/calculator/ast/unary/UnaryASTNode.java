package calculator.ast.unary;

import calculator.ast.ASTNode;

public abstract class UnaryASTNode extends ASTNode {

    protected final ASTNode node;

    public UnaryASTNode(ASTNode node) {
        this.node = node;
    }

    public ASTNode getNode() {
        return node;
    }
}
