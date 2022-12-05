package markup;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Paragraph implements ParagraphOrListTex {
    private final List<TextElementTex> text;

    public Paragraph(List<TextElementTex> text) {
        this.text = text;
    }

    @Override
    public void toTex(StringBuilder res) {
        for (TextElementTex textElement : text) {
            textElement.toTex(res);
        }
    }
}