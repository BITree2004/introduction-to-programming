package expression;

import java.util.Map;

public abstract class MathExpression implements Expression, TripleExpression, DoubleExpression {
    protected final MathPriority priority;
    protected MathExpression(MathPriority priority) {
        this.priority = priority;
    }

    public MathPriority getPriority() {
        return this.priority;
    }

    public String toStringWithBracket(Map<MathPriority, Boolean> lowPriority) {
        if (lowPriority.getOrDefault(this.priority, false)) {
            return "(" + toMiniString() + ")";
        } else {
            return toMiniString();
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
