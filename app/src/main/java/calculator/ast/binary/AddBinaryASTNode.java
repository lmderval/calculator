package calculator.ast.binary;

import calculator.ast.ASTNode;

public class AddBinaryASTNode extends BinaryASTNode {
    public AddBinaryASTNode(ASTNode left, ASTNode right) {
        super(left, right);
    }

    @Override
    public void printAST() {
        System.out.print("(");
        left.printAST();
        System.out.print("+");
        right.printAST();
        System.out.print(")");
    }
}
