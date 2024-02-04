package calculator.ast;

public class NumberASTNode extends ASTNode {

    private final int value;

    public NumberASTNode(int value) {
        this.value = value;
    }

    @Override
    public void printAST() {
        System.out.print(value);
    }

    public int getValue() {
        return value;
    }
}
