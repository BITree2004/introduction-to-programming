package expression;

import java.util.Map;

public class Add extends Operation {

    public Add(MathExpression left, MathExpression right) {
        super(left, right, MathPriority.ADD, Map.of());
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x, y, z) + super.right.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) + super.right.evaluate(x);
    }

    @Override
    public String getCharacter() {
        return "+";
    }
}
