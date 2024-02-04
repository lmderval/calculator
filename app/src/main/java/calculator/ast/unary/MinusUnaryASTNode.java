package calculator.ast.unary;

import calculator.ast.ASTNode;

public class MinusUnaryASTNode extends UnaryASTNode {
    public MinusUnaryASTNode(ASTNode node) {
        super(node);
    }

    @Override
    public void printAST() {
        System.out.print("-");
        node.printAST();
    }
}
