package game;

import java.io.IOException;
import java.util.NoSuchElementException;

public class TestGameWithTwoHumanPlayers {
    public static void main(String[] args) {
        final int n;
        final int m;
        final int k;
        final int count; // count players
        final Player[] players;
        try {
            System.out.println("please enter the numbers n, m, k, count players");
            MyScanner reader = new MyScanner(System.in);
            n = reader.nextUnsignedInt();
            m = reader.nextUnsignedInt();
            k = reader.nextUnsignedInt();
            count = reader.nextUnsignedInt();
            players = new Player[count];
            for (int i = 0; i < count; i++) {
                players[i] = new HumanPlayer(reader);
            }

        } catch (NoSuchElementException e) {
            throw new AssertionError("Lose signal with file or InputStream");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        Game first = new Game(true, players);
        Result[] result = first.play(new TicTacToeBoard(n, m, k, count));
        System.out.println("final result:");
        for (int i = 0; i < count; i++) {
            System.out.println("player " + (i + 1) + ": " + result[i]);
        }
        System.out.println();
    }
}