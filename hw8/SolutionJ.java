import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


class MyScannerForJ {
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int BLOCK_SIZE = 256;
    char[] buffer = new char[BLOCK_SIZE];
    Reader reader;

    MyScannerForJ(InputStream input) throws IOException {
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

    String next() throws IOException, NoSuchElementException, InputMismatchException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        String answer;
        try {
            answer = basicNext();
        } catch (NumberFormatException e) {
            throw new InputMismatchException("next token isn't Integer, or is out of range");
        }
        return answer;
    }
}


public class SolutionJ {
    public static int sub(int x, int y, int mod) {
        return ((x - y) % mod + mod) % mod;
    }

    public static void main (String args[]) throws IOException {
        try {
            MyScannerForJ reader = new MyScannerForJ(System.in);
            try {
                int n = reader.nextInt();
                int cnt[][] = new int[n][n];
                String row;
                for (int i = 0; i < n; i++) {
                    row = reader.next();
                    for (int j = 0; j < row.length(); j++) {
                        cnt[i][j] = row.charAt(j) - '0';
                    }
                }
                int graph[][] = new int[n][n];
                for (int v = 0; v < n; v++) {
                    for (int to = v + 1; to < n; to++) {
                        if (cnt[v][to] == 0) {
                            graph[v][to] = 0;
                        } else {
                            graph[v][to] = 1;
                            for (int i = to + 1; i < n; i++) {
                                cnt[v][i] = sub(cnt[v][i], cnt[to][i], 10);
                            }
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(graph[i][j]);
                    }
                    System.out.println();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e + ":" + "InputStream problem");
        }
    }
}
