import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Reverse{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] list = new int[1][1];
        int sz = 0;
        int last;
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
             }
             list[sz] = Arrays.copyOf(list[sz], last);
             sz++;
        }
        list = Arrays.copyOf(list, sz);
        
        for (int i = sz - 1; i >= 0; i--) {
            for (int j = list[i].length - 1; j >= 0; j--) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
    }
}
                                        
