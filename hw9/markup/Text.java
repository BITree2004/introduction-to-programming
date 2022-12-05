package markup;

import java.util.Map;

public class Text implements TexHtml {
    private final String s;

    private final Map<Character, Integer> IS_SPECIAL_SYMBOL= Map.of(
            '<', 1,
            '>', 2,
            '&', 3,
            '\\', 4
    );
    private final String[] HTML_VERSION_SPECIAL_SYMBOLS = {"&lt;", "&gt;", "&amp;", ""};
    public Text (final String s) {
        this.s = s;
    }

    public Text(StringBuilder s) {
        this.s = String.valueOf(s);
    }

    public void value(StringBuilder res) {
        res.append(s);
    }

    @Override
    public void toHtml(StringBuilder res) {
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            int typeChar = IS_SPECIAL_SYMBOL.getOrDefault(character, 0);
            if (typeChar > 0) {
                res.append(HTML_VERSION_SPECIAL_SYMBOLS[typeChar - 1]);
            } else {
                // it is simple character
                res.append(character);
            }
        }
    }
}
