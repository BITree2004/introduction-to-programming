package game;

import java.io.IOException;

public class TestGameWithOccupiedCells {
    public static void main(String[] args) {
        System.out.println("You started game 11x11 with occupied cells on the main diagonal");
        final int n = 11;
        final int m = 11;
        final int k = 3;
        final int count = 2;
        final Player[] players = new Player[count];
        try {
            MyScanner reader = new MyScanner(System.in);
            try {
                for (int i = 0; i < count; i++) {
                    players[i] = new HumanPlayer(reader);
                }
                Game myGame = new Game(true, players);
                Board myBoard = new TicTacToeBoard(n, m, k, count);
                for (int i = 0; i < n; i++) {
                    myBoard.blockCell(i, i);
                }
                for (int i = 0; i < n; i++) {
                    myBoard.blockCell(i, n - 1 - i);
                }
                myGame.play(myBoard);
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
