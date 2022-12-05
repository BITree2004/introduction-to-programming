import expression.*;

public class Main {
    public static void main(String[] args) {
        Const a = new Const(10.0);
        Const b = new Const(10);
        System.out.println(a.equals(b));
    }
}
