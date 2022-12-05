package expression;

import java.util.Map;

public class Multiply extends Operation {

    public Multiply(MathExpression left, MathExpression right) {

        super(left, right, MathPriority.MULTIPLY, Map.of(
                MathPriority.ADD, true,
                MathPriority.SUBTRACT, true
        ),
                Map.of(
                MathPriority.ADD, true,
                MathPriority.SUBTRACT, true,
               MathPriority.DIVIDE, true
        ));
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) * super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x, y, z) * super.right.evaluate(x, y, z);
    }

    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) * super.right.evaluate(x);
    }
    @Override
    public String getCharacter() {
        return "*";
    }
}
