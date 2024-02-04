package calculator.ast;

import calculator.visitor.IVisitor;

public abstract class ASTNode {
    public abstract <T> T accept(IVisitor<T> visitor);
}
