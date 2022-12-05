package markup;

import java.util.List;

public class Strong extends Element {
    public Strong (List<TexHtml> list) {
        super(list);
    }
    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<strong>");
    }

    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</strong>");
    }
}
