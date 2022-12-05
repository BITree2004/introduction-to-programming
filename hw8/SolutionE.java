import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


class MyScannerForE {
    int countCharacter = -1;
    boolean closeScanner = false;
    int l = 0;
    private final int BLOCK_SIZE = 256;
    char[] buffer = new char[BLOCK_SIZE];
    Reader reader;

    MyScannerForE(InputStream input) throws IOException {
        reader = new InputStreamReader(input);
        countCharacter = reader.read(buffer);
    }


    void close() throws IOException {
        if (countCharacter == -1) {
            reader.close();
            closeScanner = true;
        }
    }

    private boolean spaceChecker(char c) {
        return !Character.isWhitespace(c) && c != '\n' && c != '\r';
    }

    boolean hasNext() throws IOException, IllegalStateException {
        if (closeScanner == true) {
            throw new IllegalStateException("this scanner is closed");
        }
        while (l < countCharacter && !spaceChecker(buffer[l])) {
            l++;
            if (l == countCharacter) {
                countCharacter = reader.read(buffer);
                l = 0;
            }
        }
        if (countCharacter < 0) {
            return false;
        } else {
            return true;
        }
    }

    private String basicNext() throws IOException {
        StringBuilder answer = new StringBuilder();
        while (l < countCharacter && spaceChecker(buffer[l])) {
            answer.append(buffer[l]);
            l++;
            if (l == countCharacter) {
                countCharacter = reader.read(buffer);
                l = 0;
            }
        }
        return answer.toString();
    }

    Integer nextInt() throws IOException, NoSuchElementException, InputMismatchException {
        if (!this.hasNext()) {
            throw new NoSuchElementException("no more tokens are available");
        }
        int answer;
        try {
            answer = Integer.parseInt(basicNext());
        } catch (NumberFormatException e) {
            throw new InputMismatchException("next token isn't Integer, or is out of range");
        }
        return answer;
    }
}


public class SolutionE {
    static void dfs(int v, int graph[][], boolean used[], int height[], int tin[], int tout[], int timer) {
        used[v] = true;
        tin[v] = timer++;
        for (int i = 0; i < graph[v].length; i++) {
            int to = graph[v][i];
            if (!used[to]) {
                height[to] = height[v] + 1;
                dfs(to, graph, used, height, tin, tout, timer);
                timer = tout[to] + 1;
            }
        }
        tout[v] = timer++;
    }

    static boolean isParent(int u, int v, int []tin, int []tout) {
        return tin[u] <= tin[v] && tout[v] <= tout[u];
    }

    public static void main (String args[]) throws IOException {
        try {
            MyScannerForE reader = new MyScannerForE(System.in);
            try {
                int n = reader.nextInt();
                int m = reader.nextInt();
                int [][] graph = new int[n][1];
                int []numberChildren = new int[n];
                for (int i = 0; i < n - 1; i++) {
                    int v = reader.nextInt();
                    int u = reader.nextInt();
                    v--;
                    u--;
                    if (numberChildren[u] == graph[u].length) {
                        graph[u] = Arrays.copyOf(graph[u], graph[u].length * 2);
                    }
                    if (numberChildren[v] == graph[v].length) {
                        graph[v] = Arrays.copyOf(graph[v], graph[v].length * 2);
                    }
                    graph[u][numberChildren[u]++] = v;
                    graph[v][numberChildren[v]++] = u;
                }
                for (int i = 0; i < n; i++) {
                    graph[i] = Arrays.copyOf(graph[i], numberChildren[i]);
                }
                int c[] = new int[m];
                for (int i = 0; i < m; i++) {
                    c[i] = reader.nextInt();
                    c[i]--;
                }
                boolean used[] = new boolean[n];
                int height[] = new int[n]; // height of the vertex in the tree
                int tin[] = new int[n]; // time to enter the vertex
                int tout[] = new int[n]; // exit time from the vertex
                int timer = 0;
                dfs(c[0], graph, used, height, tin, tout, timer);
                int mx = c[0];
                for (int i = 1; i < m; i++) {
                    if (height[mx] < height[c[i]]) {
                        mx = c[i];
                    }
                }
                int answer = -1;
                for (int i = 0; i < n; i++) {
                    if (height[i] == height[mx] - height[i]
                            && isParent(c[0], i, tin, tout)
                            && isParent(i, mx, tin, tout)) {
                        answer = i;
                    }
                }
                if (answer != -1) {
                    // check solution
                    Arrays.fill(height, 0);
                    Arrays.fill(used, false);
                    dfs(answer, graph, used, height, tin, tout, timer);
                    for (int i = 1; i < m; i++) {
                        if (height[c[i]] != height[c[0]]) {
                            answer = -1;
                        }
                    }
                }
                if (answer != -1) {
                    System.out.println("YES\n" + (answer + 1));
                } else {
                    System.out.println("NO");
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println(e + ":" + "InputStream problem");
        }
    }
}
