package markup;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public abstract class StyleElement implements TextElementTex {
    private final List<TextElementTex> text;

    protected StyleElement(List<TextElementTex> text) {
        this.text = text;
    }


    @Override
    public void toTex(StringBuilder res) {
        toTexBegin(res);
        for (TextElementTex textElement : text) {
            textElement.toTex(res);
        }
        toTexEnd(res);
    }

    protected abstract void toTexBegin(StringBuilder res);

    protected abstract void toTexEnd(StringBuilder res);
}