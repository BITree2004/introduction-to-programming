package markup;

import java.util.List;

public class Header extends Element {

    private final int level;
    public Header (List<TexHtml> list, int level) {
        super(list);
        this.level = level;
    }

    @Override
    protected void toHtmlBegin(StringBuilder res) {
        res.append("<h").append((char) (level + '0')).append(">");
    }
    @Override
    protected void toHtmlEnd(StringBuilder res) {
        res.append("</h").append((char) (level + '0')).append(">");
    }
}
