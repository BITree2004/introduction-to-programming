package game;

import java.util.Arrays;

public class Game {

    private final boolean log;
    private int numberActivePlayers;

    private final Player[] players;
    private final Result[] results;
    private final int N;
    public Game (final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.players = new Player[]{player1, player2};
        N = 2;
        results = new Result[N];
        numberActivePlayers = N;
        Arrays.fill(results, Result.UNKNOWN);
    }
    public Game (final boolean log, final Player[] players) {
        this.log = log;
        this.players = players;
        N = players.length;
        results = new Result[N];
        numberActivePlayers = N;
        Arrays.fill(results, Result.UNKNOWN);
    }

    public Result[] play(Board board) {
        TicTacToePosition gamePosition = new TicTacToePosition(board);
        while (numberActivePlayers > 0) {
            for (int i = 0; i < N; i++) {
                final int result = move(board, gamePosition, players[i], i + 1);
                if (result != 0) {
                    if (result < 0) {
                        results[-result - 1] = Result.LOSE;
                        board.setPlayerLost(board.getTurn());
                        --numberActivePlayers;
                    } else {
                        if (result <= N) {
                            results[result - 1] = Result.WIN;
                            --numberActivePlayers;
                            for (int j = 0; j < N; j++) {
                                if (results[j] == Result.UNKNOWN) {
                                    results[j] = Result.LOSE;
                                }
                            }
                        } else {
                            for (int j = 0; j < N; j++) {
                                if (results[j] == Result.UNKNOWN) {
                                    results[j] = Result.DRAW;
                                }
                            }
                        }
                        return results;
                    }
                }
            }
        }
        throw new AssertionError("Error, no valid moves or not moves");
    }

    private int move(final Board board, final Position position, final Player player, final int no) {
        final Move move;
        try {
            move = player.move(position, position.getTurn());
        } catch(Exception e) {
            log("Player " + no + " lost and threw a exception:" + e);
            return -no;
        }
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("Position:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " win");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return -no;
        } else if (result == Result.DRAW) {
            log("Draw!");
            return N + 1;
        } else {
            return 0;
        }
    }

    private void log(String s) {
        if (log) {
            System.err.println(s);
        }
    }

}
