import java.util.Scanner;
import java.util.Arrays;

import java.lang.Character;
import java.lang.StringBuilder;
import java.lang.Integer;
import java.io.*;

public class ReverseOctAbc {
    public static int parse(String s, int radix) {
        int res = 0;
        int sign = 1; 
        if (s.charAt(0) == '-') {
            sign = -1;
        } else {
            res = s.charAt(0) - '0';
        }
        for (int i = 1; i < s.length(); i++) {
            res *= radix;
            res += (s.charAt(i) - '0');
        }
        return res * sign;
    }

    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(System.in);
            int[][] list = new int[1][1];
            int sz = 0;
            int [] last = new int[1];
            String[] b = new String[10];
            for (int i = 0; i < 10; i++) {
                b[i] = String.valueOf(i);
            }
            StringBuilder push = new StringBuilder();
            String number;
            int radix;
            // :NOTE: считывать линиями - плохая идея, можно получить ML, лучше читать токенами
            int lines = in.countBreakLine();
            while (lines > 0) {
                if (!(sz + 1 < list.length)) {
                    list = Arrays.copyOf(list, list.length * 2);    
                    last = Arrays.copyOf(last, last.length * 2);    
                }
                last[sz + 1] = 0;
                list[sz + 1] = new int[1];
                sz++;
                lines--;
            }  
            while (in.hasNext()) {
                if (!(last[sz] < list[sz].length)) {
                    list[sz] = Arrays.copyOf(list[sz], list[sz].length * 2);    
                }
                number = in.next();
                push = new StringBuilder();
                if (Character.toLowerCase(number.charAt(number.length() - 1)) == 'o') {
                    radix = 8;
                } else {
                    radix = 10;
                }
                for (int i = 0; i < number.length() - (radix == 8 ? 1 : 0); i++) {
                    char c = Character.toLowerCase(number.charAt(i));
                    if ('a' <= c && c - 'a' < radix) {
                        push.append(b[number.charAt(i) - (Character.isLowerCase(number.charAt(i)) ? 'a' : 'A')]);
                    } else {
                        push.append(c);
                    }
                }
                //list[sz][last++] = (int)Long.parseLong(push.toString(), radix);
                list[sz][last[sz]++] = parse(push.toString(), radix);
                lines += in.countBreakLine();
                while (lines > 0) {
                    if (!(sz + 1 < list.length)) {
                        list = Arrays.copyOf(list, list.length * 2);    
                        last = Arrays.copyOf(last, last.length * 2);    
                    }
                    last[sz + 1] = 0;
                    list[sz + 1] = new int[1];
                    sz++;
                    lines--;
                }
            }
            if (last[sz] > 0) {
                list = Arrays.copyOf(list, sz + 1);
            } else {
                list = Arrays.copyOf(list, sz);
            }
            in.close();                              
            for (int i = list.length - 1; i >= 0; i--) {
                for (int j = last[i] - 1; j >= 0; j--) {
                    System.out.print(list[i][j] + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
                System.out.println(e + ":" + "Where is input stream?)");    
        }
    }
}
                                        