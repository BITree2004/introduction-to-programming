package markup;

import java.util.List;

public class Strong extends StyleElement {
    public Strong(List<TextElementTex> text) {
        super(text);
    }

    @Override
    protected void toTexBegin(StringBuilder res) {
        res.append("\\textbf{");
    }

    @Override
    protected void toTexEnd(StringBuilder res) {
        res.append("}");
    }
}