package expression;


import java.util.Map;

public class Divide extends Operation {

    public Divide(MathExpression left, MathExpression right) {

        super(left, right, MathPriority.DIVIDE, Map.of(
                MathPriority.ADD, true,
                MathPriority.SUBTRACT, true
                ),
                Map.of(
                MathPriority.ADD, true,
                MathPriority.SUBTRACT, true,
                MathPriority.DIVIDE, true,
                MathPriority.MULTIPLY, true
        ));
    }

    @Override
    public int evaluate(int x) {
        return super.left.evaluate(x) / super.right.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return super.left.evaluate(x, y, z) / super.right.evaluate(x, y, z);
    }
    @Override
    public double evaluate(double x) {
        return super.left.evaluate(x) / super.right.evaluate(x);
    }
    @Override
    public String getCharacter() {
        return "/";
    }
}
