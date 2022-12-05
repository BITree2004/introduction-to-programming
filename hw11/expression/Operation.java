package expression;

import java.util.Map;

public abstract class Operation extends MathExpression {
    protected final MathExpression left;
    protected final MathExpression right;

    protected final Map<MathPriority, Boolean> lowPriorityForLeftPart;
    protected final Map<MathPriority, Boolean> lowPriorityForRightPart;

    protected Operation(final MathExpression left, final MathExpression right,
                        final MathPriority priority, Map<MathPriority, Boolean> lowPriorityForLeftPart,
                        Map<MathPriority, Boolean> lowPriorityForRightPart) {
        super(priority);
        this.left = left;
        this.right = right;
        this.lowPriorityForLeftPart = lowPriorityForLeftPart;
        this.lowPriorityForRightPart = lowPriorityForRightPart;
    }

    protected Operation(final MathExpression left, final MathExpression right,
                        final MathPriority priority, Map<MathPriority, Boolean> lowPriority) {
        super(priority);
        this.left = left;
        this.right = right;
        this.lowPriorityForLeftPart = lowPriority;
        this.lowPriorityForRightPart = lowPriority;
    }

    public abstract String getCharacter();

    private String operationToString() {
        return " " + getCharacter() + " ";
    }

    public MathExpression getLeft () {
        return left;
    }

    public MathExpression getRight () {
        return right;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Operation) {
            return left.equals(((Operation)obj).getLeft()) &&
                    right.equals(((Operation)obj).getRight()) &&
                    getCharacter().equals(((Operation)obj).getCharacter());
        }
        return false;
    }
    @Override
    public String toMiniString() {
        return left.toStringWithBracket(lowPriorityForLeftPart)
                + operationToString()
                + right.toStringWithBracket(lowPriorityForRightPart);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + getCharacter() + " " + right.toString() + ")";
    }

}
