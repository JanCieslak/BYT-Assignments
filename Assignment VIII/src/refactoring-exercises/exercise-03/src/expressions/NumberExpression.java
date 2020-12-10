package expressions;

public class NumberExpression extends Expression {
    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}
