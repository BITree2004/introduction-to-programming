package expression;

import java.util.Map;

public class Subtract extends Operation {

    public Subtract(MathExpression left, MathExpression right) {
        super(left, right, MathPriority.SUBTRACT, Map.of(), Map.of(
                MathPriority.ADD, true,
                MathPriority.SUBTRACT, true
        ));
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) - super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x, y, z) - super.right.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) - super.right.evaluate(x);
    }

    @Override
    public String getCharacter() {
        return "-";
    }
}
