package markup;

import java.lang.StringBuilder;

public class Text implements TextElementTex {
    private final String text;
    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toTex(StringBuilder res) {
        res.append(text);
    }
}