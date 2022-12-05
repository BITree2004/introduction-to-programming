import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;

import java.nio.charset.StandardCharsets;

public class Wspp{
    public static void main(String[] args){
        try{
            MyScanner r = new MyScanner(new File(args[0]));
            //Map<String, ArrayList<Integer>> words = new LinkedHashMap<>();
            Map<String, IntList> words = new LinkedHashMap<>();
            IntList arr;
            try {
                StringBuilder word = new StringBuilder();
                //ArrayList<Integer> arr;
                int sz = 0, index = 1;
                while (r.hasNext()) {
                    String buffer = r.next(); 
                    for (int i = 0; i <= buffer.length(); i++) {
                        if (i < buffer.length() && (buffer.charAt(i) == '\'' || Character.getType(buffer.charAt(i)) == Character.DASH_PUNCTUATION)) {
                            word.append(buffer.charAt(i));
                        } else if (i < buffer.length() && Character.isLetter(buffer.charAt(i))) {
                            word.append(Character.toLowerCase(buffer.charAt(i)));
                        } else if (word.length() > 0) {
                            String push = word.toString();
                            arr = words.getOrDefault(push, new IntList());
                            //arr = words.getOrDefault(push, new ArrayList<>());
                            arr.add(index++);
                            words.put(push, arr);
                            word.setLength(0);
                        }
                    }
                }
                if (word.length() > 0) {
                    String push = word.toString();
                    arr = words.getOrDefault(push, new IntList());
                    //arr = words.getOrDefault(push, new ArrayList<>());
                    arr.add(index++);
                    words.put(push, arr);
                }
            } finally {
                r.close();
            }    
            try {
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), 
                    StandardCharsets.UTF_8.name())
                );
                try {
                    //for (Map.Entry<String, ArrayList<Integer>> x : words.entrySet()) {
                    for (Map.Entry<String, IntList> x : words.entrySet()) {
                        /*w.write(x.getKey() + " " + x.getValue().size());
                        for (Iterator<Integer> it = x.getValue().iterator(); it.hasNext(); ) {
                            w.write(" " + it.next());
                        } */
                        x.getValue().clearMemory();
                        arr = x.getValue();
                        w.write(x.getKey() + " " + arr.size());
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