package markup;

import java.util.List;

public class ListItem {
    private final List<ParagraphOrListTex> text;
    public ListItem(List<ParagraphOrListTex> text) {
        this.text = text;
    }
    public void toTex(StringBuilder res) {
        toTexBegin(res);
        for (ParagraphOrListTex x: text) {
            x.toTex(res);
        }
    }

    private void toTexBegin(StringBuilder res) {
        res.append("\\item ");
    }
}