import java.lang.Character;
import java.lang.StringBuilder;
import java.lang.String;

public class SumOctal{
    public static void main(String[] args){
        int sum = 0;
        StringBuilder number = new StringBuilder();
        int radix = 10;
        for (int j = 0; j < args.length; j++) {
            int i = 0;
            for (char s : args[j].toCharArray()) {
                i++;
                if (Character.toLowerCase(s) == 'o') {
                    radix = 8;
                } else if (s == '-' || Character.isDigit(s)) {
                    number.append(s);
                }
                if (number.length() > 0 && (Character.isWhitespace(s) || i == args[j].length())) {
                    sum += Long.parseLong(number.toString(), radix);
                    number.setLength(0);
                    radix = 10;
                }
            }
        }
        System.out.println(sum);
    }
}