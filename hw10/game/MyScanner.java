package game;

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

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.nio.charset.StandardCharsets;

public class MyScanner{
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int SIZE_BLOCK = 256;
    char[] buffer = new char[SIZE_BLOCK];
    Reader reader;
    String newline = System.getProperty("line.separator");

    public MyScanner(InputStream input) throws IOException{
        reader = new InputStreamReader(input);
        countCharacter = reader.read(buffer);
    }

    public MyScanner(File input) throws IOException {
        try {
            reader = new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8);
            countCharacter = reader.read(buffer);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    public MyScanner(String input) throws IOException {
        reader = new StringReader(input);
        countCharacter = reader.read(buffer);
    }

    public void close() throws IOException {
        if (countCharacter == -1) {
            reader.close();
            closeScanner = true;
        }
    }

    private boolean spaceChecker(char c) {
        return !Character.isWhitespace(c) && c != '\n' && c != '\r';
    }

    public boolean hasNext() {
        if (closeScanner) {
            return false;
        }
        while (l < countCharacter && !spaceChecker(buffer[l])) {
            l++;
            if (l == countCharacter) {
                try {
                    countCharacter = reader.read(buffer);
                } catch (IOException e) {
                    System.out.println(e + "IOException in start");
                }
                l = 0;
            }
        }
        return countCharacter >= 0;
    }

    public int countBreakLine() throws IOException, IllegalStateException {
        if (closeScanner) {
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

    public String next() throws IOException, NoSuchElementException, IllegalStateException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        return basicNext();
    }

    public Integer nextInt() throws IOException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        int answer;
        try {
            answer = Integer.parseInt(basicNext());
        } catch (NoSuchElementException e) {
            System.out.println("Lose signal with file or inputstream!");
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("It's not integer. Program wait only integer now");
            return nextInt();
        }
        return answer;
    }

    public Integer nextUnsignedInt() throws IOException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        int answer;
        try {
            answer = Integer.parseInt(basicNext());
            if (answer <= 0) {
                System.out.println("It's not positive integer. Program wait only positive integer now");
                return nextUnsignedInt();
            }
        } catch (NumberFormatException e) {
            System.out.println("It's not positive integer. Program wait only positive integer now");
            return nextUnsignedInt();
        }
        return answer;
    }

    public Long nextLong() throws IOException, NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        long answer;
        try {
            answer = Long.parseLong(basicNext());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("next token isn't Integer, or is out of range");
        }
        return answer;
    }
}
