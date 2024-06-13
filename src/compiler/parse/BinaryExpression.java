package compiler.parse;

public class BinaryExpression implements Expression {
    public final Expression left, right;
    public final char operator;

    public BinaryExpression(Expression left, Expression right, char operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
}
