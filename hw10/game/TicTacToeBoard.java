package game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TicTacToeBoard implements Board {
    private final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.ONE, 'X',
            Cell.TWO, 'O',
            Cell.FREE, '|',
            Cell.FOUR, '-',
            Cell.E, '.',
            Cell.OCCUPIED, '$'
    );
    private final Map<Cell, Status> statusPlayers = new HashMap<>();
    private final Cell[][] board;
    private final int N;
    private final int M;
    private final int K;
    private Cell turn = Cell.ONE;
    private int notEmpty;
    private final int[][] DIRECTIONS = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    /*
     Задал направление для отрезков
     ********************\****|****?*************************
     *********************\***|***?**************************
     **********************\**|**?***************************
     ***********************\*|*?****************************
     ************************\|?*****************************
     -------------------------X------------------------------
     ************************?|\*****************************
     ***********************?*|*\****************************
     **********************?**|**\***************************
     *********************?***|***\**************************
     ********************?****|****\*************************
     *******************?*****|*****\************************
    */
    public TicTacToeBoard(final int N, final int M, final int K, final int NUMBER_PLAYERS) {
        this.N = N;
        this.M = M;
        this.K = K;
        board = new Cell [N][M];
        for (Cell[] row : board) {
            Arrays.fill(row, Cell.E);
        }
        for (int i = 0; i < NUMBER_PLAYERS; i++, turn = incrementTurn(turn)) {
            statusPlayers.put(turn, Status.ACTIVE);
        }
        turn = Cell.ONE;
        notEmpty = N * M;
    }

    @Override
    public boolean isValidMove(Move move) {
        return 0 <= move.getRow() && move.getRow() < N &&
                0 <= move.getColumn() && move.getColumn() < M &&
                board[move.getRow()][move.getColumn()] == Cell.E && turn == move.getValue();
    }
    @Override
    public int getN() {
        return N;
    }
    @Override
    public int getM() {
        return M;
    }
    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public boolean blockCell(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M && board[x][y] == Cell.E) {
            board[x][y] = Cell.OCCUPIED;
            return true;
        }
        return false;
    }

    @Override
    public void setPlayerLost(Cell index) {
        statusPlayers.put(index, Status.LOSER);
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValidMove(move)) {
            return Result.UNKNOWN;
        }
        board[move.getRow()][move.getColumn()] = move.getValue();
        notEmpty--;
        if (checkResult(move)) {
            return Result.WIN;
        }
        if (notEmpty == 0) {
            return Result.DRAW;
        }
        turn = nextTurn();
        return Result.UNKNOWN;
    }

    private Cell incrementTurn(Cell number) {
        return switch(number) {
            case ONE -> Cell.TWO;
            case TWO -> Cell.FREE;
            case FREE -> Cell.FOUR;
            default -> Cell.ONE;
        };
    }

    public Cell nextTurn() {
        turn = incrementTurn(turn);
        while (statusPlayers.getOrDefault(turn, Status.LOSER) == Status.LOSER) {
            turn = incrementTurn(turn);
        }
        return turn;
    }

    public boolean checkResult(Move move) {
        for (int i = 0; i < 4; i++) {
            if (runningCount(move.getRow(), move.getColumn(), DIRECTIONS[i][0], DIRECTIONS[i][1], move.getValue())
                    + runningCount(move.getRow(), move.getColumn(), DIRECTIONS[i + 4][0], DIRECTIONS[i + 4][1], move.getValue())
                    >= K + 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder res = new StringBuilder("\\");
        res.append("*".repeat(M)).append('\n');
        for (int i = 0; i < N; i++) {
            res.append("*");
            for (int j = 0; j < M; j++) {
                res.append(SYMBOLS.get(board[i][j]));
            }
            res.append('\n');
        }
        return res.toString();
    }
    private boolean correctIndexCell (int row, int column) {
        return 0 <= row && row < N && 0 <= column && column < M;
    }
    private int runningCount (int startRow, int startColumn, int deltaR, int deltaC, Cell value) {
        int res = 0;
        for (int i = 0; i < K && correctIndexCell(startRow, startColumn); i++, startRow += deltaR, startColumn += deltaC) {
            if (board[startRow][startColumn] == value) {
                res++;
            } else {
                return res;
            }
        }
        return res;
    }
}
