package calculator.ast;

import calculator.visitor.IVisitor;

public class NumberASTNode extends ASTNode {
    private final int value;

    public NumberASTNode(int value) {
        this.value = value;
    }

    @Override
    public <T> T accept(IVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public int getValue() {
        return value;
    }
}
