package game;

public class CyclicTournament {
    private final int N;
    private final Player[] players;
    private final boolean log;
    public CyclicTournament (final boolean log, final int N, final Player[] players) {
        this.N = N;
        this.players = players;
        this.log = log;
    }

    private int point(Result result) {
        return switch (result) {
            case LOSE, UNKNOWN -> 0;
            case DRAW -> 1;
            case WIN -> 3;
        };
    }

    public void play() {
        final int [][]finishResult = new int [N][N];
        for (int i = 0; i < N; i++) {
            int local_i = i + 1, local_j = 0;
            while (local_i < N) {
                Game myGame = new Game(true, players[local_j], players[local_i]);
                log("players: " + (local_j + 1) + " " + (local_i + 1) + " start game\n");
                Result[] result = myGame.play(new TicTacToeBoard(3, 3, 3, 2));
                finishResult[local_j][local_i] = point(result[0]);
                finishResult[local_i][local_j] = point(result[1]);
                log("player number " + (local_j + 1) + " in tournament " + result[0] + " game\n");
                log("player number " + (local_i + 1) + " in tournament " + result[1] + " game\n");
                local_i++;
                local_j++;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    log("$");
                } else {
                    log(String.valueOf(finishResult[i][j]));
                }
            }
            log("\n");
        }
    }
    private void log (String s) {
        if (log) {
            System.err.print(s);
        }
    }
}
