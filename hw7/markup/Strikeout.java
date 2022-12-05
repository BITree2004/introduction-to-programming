package markup;

import java.util.List;

public class Strikeout extends StyleElement {
    public Strikeout(List<TextElementTex> text) {
        super(text);
    }

    @Override
    protected void toTexBegin(StringBuilder res) {
        res.append("\\textst{");
    }

    @Override
    protected void toTexEnd(StringBuilder res) {
        res.append("}");
    }
}