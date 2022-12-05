package markup;

import java.util.List;

public class Strikeout extends Element {
    public Strikeout (List<TexHtml> list) {
        super(list);
    }
    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<s>");
    }

    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</s>");
    }
}
