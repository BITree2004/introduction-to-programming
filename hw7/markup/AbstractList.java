package markup;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractList implements ParagraphOrListTex {
    protected final List<ListItem> text;

    protected AbstractList(List<ListItem> text) {
        this.text = text;
    }

    @Override
    public void toTex(StringBuilder res) {
        toTexBegin(res);
        for (ListItem textElement : text) {
            textElement.toTex(res);
        }
        toTexEnd(res);
    }

    protected abstract void toTexBegin(StringBuilder res);

    protected abstract void toTexEnd(StringBuilder res);
}