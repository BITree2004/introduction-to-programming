package game;

public class TicTacToePosition implements Position {
    private final Board board;
    public TicTacToePosition(final Board board) {
        this.board = board;
    }
    @Override
    public int getN() {
        return board.getN();
    }

    @Override
    public int getM() {
        return board.getM();
    }

    @Override
    public Cell getTurn() {
        return board.getTurn();
    }
    @Override
    public boolean isValidMove(Move move) {
        return board.isValidMove(move);
    }
}
