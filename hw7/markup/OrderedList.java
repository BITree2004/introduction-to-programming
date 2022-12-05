package markup;

import java.lang.StringBuilder;
import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> text) {
        super(text);
    }

    @Override
    protected void toTexBegin(StringBuilder res) {
        res.append("\\begin{enumerate}");
    }

    @Override
    protected void toTexEnd(StringBuilder res) {
        res.append("\\end{enumerate}");
    }
}