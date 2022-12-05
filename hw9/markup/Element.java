package markup;

import java.lang.StringBuilder;
import java.util.List;

public abstract class Element implements TexHtml {
    protected final List<TexHtml> list;

    protected Element(List<TexHtml> list) {
        this.list = list;
    }

    @Override
    public void toHtml(StringBuilder res) {
        toHtmlBegin(res);
        for (TexHtml text : list) {
            text.toHtml(res);
        }
        toHtmlEnd(res);
    }
    @Override
    public void value(StringBuilder res) {
        toHtmlBegin(res);
        for (TexHtml text : list) {
            text.value(res);
        }
        toHtmlEnd(res);
    }


    protected abstract void toHtmlBegin(StringBuilder res);

    protected abstract void toHtmlEnd(StringBuilder res);
}