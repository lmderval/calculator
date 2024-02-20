package calculator.ast.binary;

import calculator.ast.ASTNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class BinaryASTNode extends ASTNode {
    protected final ASTNode left;
    protected final ASTNode right;
}
