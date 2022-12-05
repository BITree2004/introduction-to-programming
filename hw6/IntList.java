
import java.util.Arrays;


public class IntList {
    private int sz = 0;
    private int[] arr = new int[1];
 
    IntList() {
    
    }
    
    
    private void resize() {
        arr = Arrays.copyOf(arr, sz * 2);
    }
    
    int size() {
        return sz;
    }

    void add(int x) {
        if (sz == arr.length) {
            resize();
        }
        arr[sz++] = x;
    }

    int get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= sz) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        return arr[i];
    }

    void change(int i, int x) {
        if (i < 0 || i >= sz) {
            return;
        }
        arr[i] = x;
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
                                
