import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;


import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

import java.nio.charset.StandardCharsets;

public class WsppCountLastL{
    public static void main(String[] args){
        try{                        
            MyScanner r = new MyScanner(new File(args[0]));
            Map<String, IntList> words = new LinkedHashMap<>();
            Map<String, Integer> count = new LinkedHashMap<>();
            IntList arr;
            try {
                StringBuilder word = new StringBuilder();
                int sz = 0;
                int index = 1;
                Map<String, Integer> lastRow = new LinkedHashMap<>();
                while (r.hasNext()) {
                    for (Map.Entry<String, Integer> x : lastRow.entrySet()) {
                        arr = words.getOrDefault(x.getKey(), new IntList());
                        arr.add(x.getValue());
                        words.put(x.getKey(), arr);        
                    }
                    lastRow.clear();
                    index = 1;
                    String buffer = r.nextLine(); 
                    for (int i = 0; i <= buffer.length(); i++) {
                        if (i < buffer.length() && (buffer.charAt(i) == '\'' || Character.getType(buffer.charAt(i)) == Character.DASH_PUNCTUATION)) {
                            word.append(buffer.charAt(i));
                        } else if (i < buffer.length() && Character.isLetter(buffer.charAt(i))) {
                            word.append(Character.toLowerCase(buffer.charAt(i)));
                        } else if (word.length() > 0) {
                            String push = word.toString();
                            lastRow.put(push, index++);
                            count.put(push, count.getOrDefault(push, 0) + 1);
                            word.setLength(0);
                        }
                    }
                }
                if (word.length() > 0) {
                    String push = word.toString();
                    lastRow.put(push, index++);
                    count.put(push, count.getOrDefault(push, 0) + 1);
                    word.setLength(0);
                }
                for (Map.Entry<String, Integer> x : lastRow.entrySet()) {
                    arr = words.getOrDefault(x.getKey(), new IntList());
                    arr.add(x.getValue());
                    words.put(x.getKey(), arr);        
                }
                lastRow.clear();
            } finally {
                r.close();
            }    
            try {
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), 
                    StandardCharsets.UTF_8.name())
                );
                try {
                    PairList order = new PairList();
                    for (String x : words.keySet()) {
                        order.add(count.getOrDefault(x, 0), x);  
                    }
                    order.sort();
                    for (int j = 0; j < order.size(); j++) {
                        String x = order.get(j).second;
                        arr = words.getOrDefault(x, new IntList());
                        w.write(x + " " + count.getOrDefault(x, 0));
                        for (int i = 0; i < arr.size(); i++) {
                            try {
                                w.write(" " + arr.get(i));
                            } catch(IndexOutOfBoundsException e) {
                                System.out.println(e + ":" + "index is out of range");
                            }
                        }
                        w.newLine();
                    } 
                } finally {
                    w.close();
                }   
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e + ":" + "Name input or output file is undefined");    
            } catch (FileNotFoundException e) {
                System.out.println(e + ":" + "Output file not found");
            } catch (IOException e) {
                System.out.println(e + ":" + "Output file writing error");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e + ":" + "Name input or output file is undefined");    
        } catch (FileNotFoundException e) {
            System.out.println(e + ":" + "Input file not found");
        } catch (IOException e) {
            System.out.println(e + ":" + "Input file reading error");
        }    
    }
}