package markup;

import java.util.List;

public class Code extends Element {
    public Code (List<TexHtml> list) {
        super(list);
    }
    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<code>");
    }

    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</code>");
    }
}
