package game;

import java.io.IOException;

public class TestCyclicTournament {
    public static void main(String[] args) {
        try {
            System.out.println("You run CyclicTournament with only HumanPlayers. Give N - number of players");
            MyScanner reader = new MyScanner(System.in);
            try {
                final int N = reader.nextUnsignedInt();
                final Player[] players = new Player[N];
                for (int i = 0; i < N; i++) {
                    players[i] = new HumanPlayer(reader);
                    //players[i] = new SequencePlayer();
                }
                new CyclicTournament(true, N, players).play();
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("You try to run TestCyclicTournament. But scanner throws InputProblem: " + e);
        }
    }
}
