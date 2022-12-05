package markup;

import java.lang.StringBuilder;
import java.util.List;

public abstract class DoubleElement implements TexHtml {
    protected final List<TexHtml> list1;
    protected final List<TexHtml> list2;

    protected DoubleElement(List<TexHtml> list1, List<TexHtml> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    @Override
    public void toHtml(StringBuilder res) {
        toHtmlBegin(res);
        for (TexHtml text : list1) {
            text.toHtml(res);
        }
        toHtmlMiddle(res);
        for (TexHtml text : list2) {
            text.toHtml(res);
        }
        toHtmlEnd(res);
    }
    @Override
    public void value(StringBuilder res) {
        toHtmlBegin(res);
        for (TexHtml text : list1) {
            text.value(res);
        }
        toHtmlMiddle(res);
        for (TexHtml text : list2) {
            text.value(res);
        }
        toHtmlEnd(res);
    }

    protected abstract void toHtmlMiddle(StringBuilder res);
    protected abstract void toHtmlBegin(StringBuilder res);

    protected abstract void toHtmlEnd(StringBuilder res);
}