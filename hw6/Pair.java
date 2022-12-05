import java.util.Arrays;

public class Pair implements Comparable<Pair> {
    int first;
    String second;
    
    Pair() {
    
    }
    
    Pair(int x, String y) {
        first = x;
        second = y;
    }
    
    public int compareTo(Pair anotherPair) {
        if (this.first == anotherPair.first) {
            return 0;
        } else if (this.first < anotherPair.first) {
            return -1;
        } else {
            return 1;
        }
    }
}
                                