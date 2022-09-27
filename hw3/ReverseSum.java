import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class ReverseSum{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] list = new int[1][1];
        int [] p = new int[1];
        int sz = 0;
        int last;
        int maxsz = 0;
        while (in.hasNextLine()) {
             if (!(sz < list.length)) {
                list = Arrays.copyOf(list, list.length * 2);    
             }
             Scanner line = new Scanner(in.nextLine());
             last = 0;
             list[sz] = new int[1];
             while (line.hasNextInt()) {
                if (!(last < list[sz].length)) {
                    list[sz] = Arrays.copyOf(list[sz], list[sz].length * 2);    
                }
                list[sz][last++] = line.nextInt();
                maxsz = Math.max(maxsz, last);
             }
             list[sz] = Arrays.copyOf(list[sz], last);
             sz++;
        }
        list = Arrays.copyOf(list, sz);
        //
        int [] column = new int[maxsz];
        int [] number = new int[maxsz];
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < list[i].length; j++) {
                column[j] += list[i][j];
                number[j]++;           
            }
        }
        //
        int s;
        for (int i = 0; i < sz; i++) {
            s = 0;
            for (int j = 0; j < list[i].length; j++) {
                s = s + list[i][j];
            }
            for (int j = 0; j < list[i].length; j++) {
                System.out.print((s + column[j] - list[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
                                        