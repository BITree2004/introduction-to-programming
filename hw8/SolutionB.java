import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


class MyScannerForB {
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int BLOCK_SIZE = 256;
    char[] buffer = new char[BLOCK_SIZE];
    Reader reader;

    MyScannerForB(InputStream input) throws IOException {
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


public class SolutionB {
    public static void main (String args[]) throws IOException {
        int n;
        final int delta = 710;
        final int start = -25_000;
        try {
            MyScannerForB reader = new MyScannerForB(System.in);
            try {
                n = reader.nextInt();
                for (int i = 0; i < n; i++) {
                    System.out.println(delta * (start + i));
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e + ":" + "InputStream problem");
        }
    }
}
