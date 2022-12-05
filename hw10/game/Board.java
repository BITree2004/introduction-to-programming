package game ;

public interface Board {
    Result makeMove(Move move);
    boolean isValidMove(Move move);
    int getN();
    int getM();
    Cell getTurn();
    boolean blockCell(int x, int y);
    void setPlayerLost(Cell index);
}
