import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


class MyScannerForM {
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int BLOCK_SIZE = 256;
    char[] buffer = new char[BLOCK_SIZE];
    Reader reader;

    MyScannerForM(InputStream input) throws IOException {
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


public class SolutionM {
    public static void main (String args[]) throws IOException {
        HashMap<Integer, Integer> values = new HashMap<>();
        int t;
        int n;
        int a[];
        int answer;
        try {
            MyScannerForM reader = new MyScannerForM(System.in);
            try {
                t = reader.nextInt();
                for (int test = 0; test < t; test++) {
                    n = reader.nextInt();
                    a = new int[n];
                    for (int j = 0; j < n; j++) {
                        a[j] = reader.nextInt();
                    }
                    answer = 0;
                    for (int j = n - 1; j >= 1; j--) {
                        for (int i = 0; i < j; i++) {
                            answer += values.getOrDefault((a[j] << 1) - a[i], 0);
                        }
                        values.put(a[j], values.getOrDefault(a[j], 0) + 1);
                    }
                    System.out.println(answer);
                    values.clear();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e + ":" + "InputStream problem");
        }
    }
}
