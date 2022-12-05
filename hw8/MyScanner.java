import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.lang.Integer;
import java.lang.Long;
import java.lang.Double;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.nio.charset.StandardCharsets;


public class MyScanner {
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int BLOCK_SIZE = 256;
    char[] buffer = new char[BLOCK_SIZE];
    Reader reader;
    String newline = System.getProperty("line.separator");

    MyScanner(InputStream input) throws IOException {
        reader = new InputStreamReader(input);
        countCharacter = reader.read(buffer);
    }

    MyScanner(File input) throws IOException, FileNotFoundException {
        try {
            reader = new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8.name());
            countCharacter = reader.read(buffer);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    MyScanner(String input) throws IOException {
        reader = new StringReader(input);
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

    int countBreakLine() throws IOException, IllegalStateException {
        if (closeScanner == true) {
            throw new IllegalStateException("this scanner is closed");
        }
        int res = 0;
        while (l < countCharacter && !spaceChecker(buffer[l])) {
            if (buffer[l] == '\r' || buffer[l] == '\n') {
                res++;
            }
            l++;
            if (l == countCharacter) {
                countCharacter = reader.read(buffer);
                l = 0;
            }
        }
        return res / newline.length();
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

    String next() throws IOException, NoSuchElementException, IllegalStateException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        return basicNext();
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

    Long nextLong() throws IOException, NoSuchElementException, InputMismatchException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        Long answer;
        try {
            answer = Long.parseLong(basicNext());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("next token isn't Integer, or is out of range");
        }
        return answer;
    }

    Double nextDouble() throws IOException, NoSuchElementException, InputMismatchException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        double answer;
        try {
            answer = Double.parseDouble(basicNext());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("next token isn't Integer, or is out of range");
        }
        return answer;
    }

    boolean hasNextLine() throws IOException, IllegalStateException {
        if (closeScanner == true) {
            throw new IllegalStateException("this scanner is closed");
        }
        if (countCharacter < 0) {
            return false;
        } else {
            return true;
        }
    }

    String nextLine() throws IOException, IllegalStateException, NoSuchElementException {
        if (!this.hasNextLine()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        StringBuilder answer = new StringBuilder();

        while (l < countCharacter && buffer[l] != '\r' && buffer[l] != '\n') {
            answer.append(buffer[l]);
            l++;
            if (l == countCharacter) {
                countCharacter = reader.read(buffer);
                l = 0;
            }
        }
        if (l < countCharacter) {
            for (int i = 0; i < newline.length() ; i++) {
                l++;
                if (l == countCharacter) {
                    countCharacter = reader.read(buffer);
                    l = 0;
                }
            }
        }
        return answer.toString();
    }

}
