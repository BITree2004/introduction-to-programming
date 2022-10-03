import java.io.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.TreeMap;

public class WordStatWordsShingles{
    public static void main(String[] args){
        try {
            Map<String, Integer> mapa = new TreeMap<String, Integer>();
            Reader r = new BufferedReader(new InputStreamReader(
            new FileInputStream(args[0]), 
            "UTF8")
            );
            try {
                char[] buffer = new char[1];
                int read = r.read(buffer);
                StringBuilder word = new StringBuilder();
                int flag = 1;
                int len = 0;
                String push;
                while (read >= 0) {
                    for (int i = 0; i < read; i++) {
                        if (buffer[i] == '\'' || Character.getType(buffer[i]) == Character.DASH_PUNCTUATION) {
                            word.append(buffer[i]);
                            len++;
                        } else if (Character.isLetter(buffer[i])) {
                            word.append(Character.toLowerCase(buffer[i]));
                            len++;
                        } else if (len > 0) {
                            if (flag > 0) {
                                push = word.toString();
                                mapa.put(push, mapa.getOrDefault(push, 0) + 1);
                            }
                            flag = 1;
                            len = 0;
                            word.setLength(0);
                        }
                        if (len == 3) {
                            push = word.toString();
                            mapa.put(push, mapa.getOrDefault(push, 0) + 1);
                            flag = 0;
                            len--;
                            word.deleteCharAt(0);
                        }
                        
                    }
                    read = r.read(buffer);
                }
                if (flag > 0 && len > 0) {
                    push = word.toString();
                    mapa.put(push, mapa.getOrDefault(push, 0) + 1);
                } 
            } finally {      
                r.close();
            }
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]), 
                "UTF8")
            );
            try{
                try {
                    for (Map.Entry<String, Integer> x : mapa.entrySet()) {
                        w.write(x.getKey() + " " + x.getValue());
                        w.newLine();
                    }
                } finally {
                    w.close();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e + ":" + "Name output file is undefined");    
            } catch (FileNotFoundException e) {
                System.out.println(e + ":" + "Output file not found");
            } catch (IOException e) {
                System.out.println(e + ":" + "Output file writing error");
            }        
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e + ":" + "Name input file is undefined");    
        } catch (FileNotFoundException e) {
            System.out.println(e + ":" + "Input file not found");
        } catch (IOException e) {
            System.out.println(e + ":" + "Input file reading error");
        }     
    }
}