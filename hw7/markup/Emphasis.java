package markup;

import java.util.List;

public class Emphasis extends StyleElement {
    public Emphasis(List<TextElementTex> text) {
        super(text);
    }

    @Override
    protected void toTexBegin(StringBuilder res) {
        res.append("\\emph{");
    }

    @Override
    protected void toTexEnd(StringBuilder res) {
        res.append("}");
    }
}