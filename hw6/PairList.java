import java.util.Arrays;

public class PairList {
    private int sz = 0;
    private Pair[] arr = new Pair[1];
 
    PairList() {
    
    }
    
    
    private void resize() {
        arr = Arrays.copyOf(arr, sz * 2);
    }
    
    int size() {
        return sz;
    }

    void add(int x, String y) {
        if (sz == arr.length) {
            resize();
        }
        arr[sz++] = new Pair(x, y);
    }


    Pair get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= sz) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        return arr[i];
    }

    void change(int i, int x, String y) {
        if (i < 0 || i >= sz) {
            return;
        }
        arr[i] = new Pair(x, y);
    }

    void clearMemory() {
        arr = Arrays.copyOf(arr, sz);
    }

    void sort() {
        if (sz > 0) {
            arr = Arrays.copyOf(arr, sz);
            Arrays.sort(arr);
        }
    }
}
                                