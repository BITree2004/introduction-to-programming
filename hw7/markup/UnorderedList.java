package markup;

import java.lang.StringBuilder;
import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> text) {
        super(text);
    }

    @Override
    protected void toTexBegin(StringBuilder res) {
        res.append("\\begin{itemize}");
    }

    @Override
    protected void toTexEnd(StringBuilder res) {
        res.append("\\end{itemize}");
    }
}