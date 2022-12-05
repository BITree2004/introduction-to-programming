package markup;

import java.util.List;

public class Link extends DoubleElement {

    public Link (List<TexHtml> list1, List<TexHtml> list2) {
        super(list1, list2);
    }
    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<a href='");
    }
    @Override
    protected void toHtmlMiddle(StringBuilder res) {
        res.append("'>");
    }
    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</a>");
    }
}
