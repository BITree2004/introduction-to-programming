import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


class MyScannerForI {
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int BLOCK_SIZE = 256;
    char[] buffer = new char[BLOCK_SIZE];
    Reader reader;

    MyScannerForI(InputStream input) throws IOException {
        reader = new InputStreamReader(input);
        countCharacter = reader.read(buffer);
    }


    void close() throws IOException {
        if (countCharacter == -1) {
            reader.close();
            closeScanner = true;
        }
    }

    private boolean spaceChecker(char c) {
        return !Character.isWhitespace(c) && c != '\n' && c != '\r';
    }

    boolean hasNext() throws IOException, IllegalStateException {
        if (closeScanner == true) {
            throw new IllegalStateException("this scanner is closed");
        }
        while (l < countCharacter && !spaceChecker(buffer[l])) {
            l++;
            if (l == countCharacter) {
                countCharacter = reader.read(buffer);
                l = 0;
            }
        }
        if (countCharacter < 0) {
            return false;
        } else {
            return true;
        }
    }

    private String basicNext() throws IOException {
        StringBuilder answer = new StringBuilder();
        while (l < countCharacter && spaceChecker(buffer[l])) {
            answer.append(buffer[l]);
            l++;
            if (l == countCharacter) {
                countCharacter = reader.read(buffer);
                l = 0;
            }
        }
        return answer.toString();
    }

    Integer nextInt() throws IOException, NoSuchElementException, InputMismatchException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        int answer;
        try {
            answer = Integer.parseInt(basicNext());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("next token isn't Integer, or is out of range");
        }
        return answer;
    }
}


public class SolutionI {
    static int div(int x , int y) {
        return (x + y - 1) / y;
    }

    public static void main (String args[]) throws IOException {
        int n;
        int []x;
        int []y;
        int []h;
        try {
            MyScannerForI reader = new MyScannerForI(System.in);
            try {
                n = reader.nextInt();
                x = new int[n];
                y = new int[n];
                h = new int[n];
                for (int i = 0; i < n; i++) {
                    x[i] = reader.nextInt();
                    y[i] = reader.nextInt();
                    h[i] = reader.nextInt();
                }
                int xl = x[0] - h[0];
                int xr = x[0] + h[0];
                int yl = y[0] - h[0];
                int yr = y[0] + h[0];
                for (int i = 1; i < n; i++) {
                    xl = Math.min(xl, x[i] - h[i]);
                    xr = Math.max(xr, x[i] + h[i]);
                    yl = Math.min(yl, y[i] - h[i]);
                    yr = Math.max(yr, y[i] + h[i]);
                }
                int answerh = div(Math.max(xr - xl, yr - yl), 2);
                int answerx = (xl + xr) / 2;
                int answery = (yl + yr) / 2;
                System.out.println(answerx + " " + answery + " " + answerh);
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e + ":" + "InputStream problem");
        }
    }
}
