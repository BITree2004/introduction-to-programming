import markup.*;
import markup.Text;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //ListItem a = new ListItem ((List<Emphasis>) new Emphasis((List<TextElement>) new Text("a")));
        Strong a = new Strong(List.of(new Strong(List.of(new Text("abc")))));
        StringBuilder res = new StringBuilder();
        a.toTex(res);
        System.out.println(res);
    }
}
