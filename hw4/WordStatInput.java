import java.io.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class WordStatInput{
    public static void main(String[] args){
        try{
            Reader r = new BufferedReader(new InputStreamReader(
            new FileInputStream(args[0]), 
            "UTF8")
            );
            String[] words = new String[1];
            try {
                char[] buffer = new char[512];
                int read = r.read(buffer);
                StringBuilder word = new StringBuilder();
                int sz = 0;
                while (read >= 0) {
                    for (int i = 0; i < read; i++) {
                        if (buffer[i] == '\'' || Character.getType(buffer[i]) == Character.DASH_PUNCTUATION) {
                            word.append(buffer[i]);
                        } else if (Character.isLetter(buffer[i])) {
                            word.append(Character.toLowerCase(buffer[i]));
                        } else if (word.length() > 0) {
                            if (!(sz + 1 < words.length)) {
                                words = Arrays.copyOf(words, words.length * 2);
                            }
                            words[sz++]=word.toString();
                            word.setLength(0);
                        }
                    }
                    read = r.read(buffer);
                }
                if (word.length() > 0) {
                    words[sz++]=word.toString();
                }
                words = Arrays.copyOf(words, sz);
            } finally {
                r.close();
            }    
            try {
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), 
                    "UTF8")
                );
                try {
                    String[] arr = new String[words.length];
                    for (int i = 0; i < words.length; i++) {
                        arr[i] = words[i];
                    }
                    final int n = words.length;
                    Arrays.sort(arr);
                    int[] cnt = new int[n];
                    Long [] answer2 = new Long[1];
                    int sz2 = 0; 
                    for (int i = 0; i < n; i++) {
                    int l2 = Arrays.binarySearch(arr, words[i]);
                    if (cnt[l2] == 1) {
                        continue;
                    }            
                    cnt[l2] = 1;
                    int r2 = l2;
                        while (l2 > 0 && arr[l2 - 1].equals(arr[l2])) {
                            l2--;
                        }   
                        while (r2 + 1 < n && arr[r2 + 1].equals(arr[r2])) {
                            r2++;
                        }   
                        if (sz2 == answer2.length) {
                            answer2 = Arrays.copyOf(answer2, answer2.length * 2);
                        }
                        answer2[sz2++] = i * (1L << 30) + r2 - l2 + 1; 
                    }
                    //Arrays.sort(answer, (a, b) -> Long.compare(a[0], b[0]));
                    answer2 = Arrays.copyOf(answer2, sz2);
                    Arrays.sort(answer2);
                    int index;
                    int count;
                    for (int i = 0; i < sz2; i++) {
                        index = (int) (answer2[i] / (1L << 30));
                        count = (int) (answer2[i] - (1L << 30) * index);
                        w.write(words[index] + " " + count);
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
/*
import java.io.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class WordStatInput{
    public static void sort2(int[] arr, String[] arr2, int l, int r) {
        if (r - l <= 1) {
            return ;
        }
        int m = (l + r) / 2;
        sort2(arr, arr2, l, m);
        sort2(arr, arr2, m, r);
        int sz = r - l, left = l, right = m;
        int[] b = new int[sz];
        for (int i = 0; i < sz; i++) {
            if (right == r || (left < m && arr2[arr[left]].compareTo(arr2[arr[right]]) <= 0)){
                b[i] = arr[left++];    
            } else {
                b[i] = arr[right++];
            }
        }
        for (int i = l; i < r; i++) {
            arr[i] = b[i - l];
        }
    }
    
    public static void sort3(int[][] arr, int l, int r) {
        if (r - l <= 1) {
            return ;
        }
        int m = (l + r) / 2;
        sort3(arr, l, m);
        sort3(arr, m, r);
        int sz = r - l, left = l, right = m;
        int[][] b = new int[sz][2];
        for (int i = 0; i < sz; i++) {
            if (right == r || (left < m && arr[left][0] <= arr[right][0])){
                b[i] = arr[left++];    
            } else {
                b[i] = arr[right++];
            }
        }
        for (int i = l; i < r; i++) {
            arr[i] = b[i - l];
        }
    }

    public static void main(String[] args){
        try{
            Reader r = new BufferedReader(new InputStreamReader(
            new FileInputStream(args[0]), 
            "UTF8")
            );
            try {
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), 
                    "UTF8")
                );
                try {
                    char[] buffer = new char[512];
                    int read = r.read(buffer);
                    StringBuilder word = new StringBuilder();
                    String[] words = new String[1];
                    int sz = 0;
                    while (read >= 0) {
                        for (int i = 0; i < read; i++) {
                            if (buffer[i] == '\'' || Character.getType(buffer[i]) == Character.DASH_PUNCTUATION) {
                                word.append(buffer[i]);
                            } else if (Character.isLetter(buffer[i])) {
                                word.append(Character.toLowerCase(buffer[i]));
                            } else if (word.length() > 0) {
                                if (!(sz + 1 < words.length)) {
                                    words = Arrays.copyOf(words, words.length * 2);
                                }
                                words[sz++]=word.toString();
                                word.setLength(0);
                            }
                        }
                        read = r.read(buffer);
                    }
                    if (word.length() > 0) {
                        words[sz++]=word.toString();
                    }
                    words = Arrays.copyOf(words, sz);
                    int[] arr = new int [words.length];
                    for (int i = 0; i < words.length; i++) {
                        arr[i] = i;
                    }
                    final int n = words.length;
                    sort2(arr, words, 0, n);
                    int[][] answer = new int[1][2];
                    sz = 1;
                    answer[0][1] = 1;
                    answer[0][0] = arr[0];
                    for (int i = 1; i < n; i++) {
                        if (!words[arr[i - 1]].equals(words[arr[i]])) {
                            if (!(sz< answer.length)) {
                                answer = Arrays.copyOf(answer, answer.length * 2);
                            }
                            answer[sz] = new int [2];
                            answer[sz][0] = arr[i];
                            answer[sz++][1] = 1;
                        } else {
                            answer[sz - 1][0] = Math.min(answer[sz - 1][0], arr[i]);
                            answer[sz - 1][1]++;
                        }
                    }
                    answer = Arrays.copyOf(answer, sz);
                    Arrays.sort(answer, (a, b) -> Long.compare(a[0], b[0]));
                    //sort3(answer, 0, sz);
                    //Arrays.sort(answer);
                    for (int i = 0; i < sz; i++) {
                        w.write(words[answer[i][0]] + " " + answer[i][1]);
                        w.newLine();    
                    }  
                } finally {
                    w.close();
                }
            } finally {
                r.close();
            }    
        }catch (IOException e){
            System.out.println(e);
        }    
    }
}
*/
/*
import java.io.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class WordStatInput{
    public static void main(String[] args){
        try{
            Reader r = new BufferedReader(new InputStreamReader(
            new FileInputStream(args[0]), 
            "UTF8")
            );
            try {
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(args[1]), 
                    "UTF8")
                );
                try {
                    char[] buffer = new char[512];
                    int read = r.read(buffer);
                    StringBuilder word = new StringBuilder();
                    String[] words = new String[1];
                    int sz = 0;
                    while (read >= 0) {
                        for (int i = 0; i < read; i++) {
                            if (buffer[i] == '\'' || Character.getType(buffer[i]) == Character.DASH_PUNCTUATION) {
                                word.append(buffer[i]);
                            } else if (Character.isLetter(buffer[i])) {
                                word.append(Character.toLowerCase(buffer[i]));
                            } else if (word.length() > 0) {
                                if (!(sz + 1 < words.length)) {
                                    words = Arrays.copyOf(words, words.length * 2);
                                }
                                words[sz++]=word.toString();
                                word.setLength(0);
                            }
                        }
                        read = r.read(buffer);
                    }
                    if (word.length() > 0) {
                        words[sz++]=word.toString();
                    //    word.setLength(0);
                    }
                    words = Arrays.copyOf(words, sz);
                    Map<String, Long> mapa = new HashMap<String, Long>();
                    for (int i = 0; i < words.length; i++) {
                        Long pre = mapa.getOrDefault(words[i], 0L);
                        if (pre > 0) {
                            mapa.put(words[i], pre + 1);
                        } else {
                            mapa.put(words[i], i * (1L<<32) + 1);
                        }    
                    }
                    for (int i = 0; i < words.length; i++) {
                        long value = (mapa.get(words[i]) - i * (1L<<32));
                        if (value > 0) {
                            w.write(words[i] + " " + value);
                            w.newLine();    
                        }
                    }
                } finally {
                    w.close();
                }
            } finally {
                r.close();
            }    
        }catch (IOException e){
            System.out.println(e);
        }    
    }
}
*/
