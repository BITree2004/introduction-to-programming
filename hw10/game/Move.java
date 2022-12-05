package game;

import java.util.Map;

public class Move {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.ONE, 'X',
            Cell.TWO, 'O',
            Cell.FREE, '|',
            Cell.FOUR, '-',
            Cell.E, '.'
    );
    private final int row;
    private final int column;

    private final Cell value;

    public Move (final int row, final int column, final Cell value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "row: " + (getRow() + 1) + ", column: " + (getColumn() + 1) + ", value:" + SYMBOLS.get(getValue());
    }
}
