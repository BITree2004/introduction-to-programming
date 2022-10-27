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
//import java.lang.Character;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.nio.charset.StandardCharsets;

// :NOTE: ÐºÐ¾Ð½ÑÑ‚Ð°Ð½Ñ‚Ñ‹ Ð¿Ñ€Ð¸Ð½ÑÑ‚Ð¾ Ð½Ð°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ ÐºÐ°Ð¿ÑÐ¾Ð¼, Ð½Ð°Ð¿Ñ€Ð¸Ð¼ÐµÑ€, BLOCK_SIZE

public class MyScanner{
    // :NOTE: Ð»ÑƒÑ‡ÑˆÐµ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ñ„Ð»Ð°Ð³Ð¸ Ñ Ð³Ð¾Ð²Ð¾Ñ€ÑÑ‰Ð¸Ð¼Ð¸ Ð½Ð°Ð·Ð²Ð°Ð½Ð¸ÑÐ¼Ð¸, Ð° Ð½Ðµ -1 Ð¸ -2
    int countCharacter = -1;
    boolean closeScanner = false; 
    int l = 0;
    private final int SIZE_BLOCK = 256;
    // :NOTE: Ð²Ñ‹Ð½ÐµÑÑ‚Ð¸ ÐºÐ¾Ð½ÑÑ‚Ð°Ð½Ñ‚Ñƒ
    char[] buffer = new char[SIZE_BLOCK];
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

    // :NOTE: Ð»ÑƒÑ‡ÑˆÐµ spaceChecker
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
                                
