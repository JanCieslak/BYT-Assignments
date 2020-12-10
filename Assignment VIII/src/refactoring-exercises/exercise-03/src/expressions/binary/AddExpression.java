package expressions.binary;

import expressions.BinaryOperatorExpression;
import expressions.Expression;

public class AddExpression extends BinaryOperatorExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate() {
        return left.evaluate() + right.evaluate();
    }
}
