package game;

public class SequencePlayer implements Player {
    public SequencePlayer() {}
    @Override
    public Move move(Position position, Cell cell) {
        System.err.println("Sequence move!");
        for (int i = 0; i < position.getN(); i++) {
            for (int j = 0; j < position.getM(); j++) {
                final Move move = new Move(i, j, position.getTurn());
                if (position.isValidMove(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("Full border");
    }
}
