package markup;

import java.util.List;

public class Paragraph extends Element {
    public Paragraph (List<TexHtml> list) {
        super(list);
    }
    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<p>");
    }
    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</p>");
    }
}
