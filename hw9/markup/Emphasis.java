package markup;

import java.util.List;

public class Emphasis extends Element {
    public Emphasis (List<TexHtml> list) {
        super(list);
    }
    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<em>");
    }

    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</em>");
    }
}
