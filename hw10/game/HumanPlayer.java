package game;

import java.io.IOException;
public class HumanPlayer implements Player {
    MyScanner reader;
    public HumanPlayer(MyScanner reader) {
        this.reader = reader;
    }
    @Override
    public Move move(Position position, Cell cell) {
        System.out.println("Your move! Please give me two integers");
        while (true) {
            int x = -1;
            try {
                x = reader.nextUnsignedInt() - 1;
            } catch (Exception e) {
                break;
            }
            int y = -1;
            try {
                y = reader.nextInt() - 1;
            } catch (Exception e) {
                break;
            }
            final Move move = new Move(x, y, cell);
            if (position.isValidMove(move)) {
                return move;
            } else {
                System.out.println("Move " + move + " is invalid");
            }
        }
        throw new AssertionError("Lose signal with File or InputStream");
    }
}
