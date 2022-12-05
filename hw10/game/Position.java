package game;

public interface Position {
    boolean isValidMove(Move move);
    int getN();
    int getM();
    Cell getTurn();
}
