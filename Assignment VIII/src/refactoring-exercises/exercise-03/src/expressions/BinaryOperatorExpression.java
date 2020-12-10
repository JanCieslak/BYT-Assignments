package expressions;

public abstract class BinaryOperatorExpression extends Expression {
    protected final Expression left;
    protected final Expression right;

    public BinaryOperatorExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
