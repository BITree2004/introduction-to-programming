package expression;

public class Variable extends MathExpression {
    private final String var;

    public Variable(String var) {
        super(MathPriority.VALUE);
        this.var = var;
    }

    public String getValue() {
        return var;
    }
    @Override
    public String toString() {
        return var;
    }
    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch(var) {
            case "x" -> x;
            case "y" -> y;
            default -> z;
        };
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj != null && obj.getClass() == Variable.class) {
            return this.getValue().equals(((Variable) obj).getValue());
        }
        return false;
    }
}
