package md2html;

import markup.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {

    // symbols of selection
    public static final String[] BORDER_STYLE = new String[]{"**", "__", "--", "*", "_", "`"};
    public static final String[] BORDER_LINK = new String[]{"[", "](", ")"};

    private static void pushBlock(List<TexHtml> answer, List<Integer> blockStarts, StringBuilder res, int startBlock) {
        if (res.isEmpty()) {
            return;
        }
        answer.add(new Text(res));
        res.setLength(0);
        blockStarts.add(startBlock + 1);
    }

    static List<TexHtml> findYoungerElement(final List<TexHtml> answer, final List<Integer> blockStarts, final int x) {
        final Deque<TexHtml> res = new ArrayDeque<>();
        while (!answer.isEmpty() && blockStarts.get(answer.size() - 1) >= x) {
            res.addFirst(answer.get(answer.size() - 1));
            answer.remove(answer.size() - 1);
            blockStarts.remove(blockStarts.size() - 1);
        }
        return new ArrayList<>(res);
    }

    static TexHtml subText(TexHtml s, int start) {
        StringBuilder originalVersion = new StringBuilder();
        s.value(originalVersion);
        if (originalVersion.length() <= start) {
            return new Text("");
        } else {
            return new Text(originalVersion.substring(start));
        }
    }

    static int sizeBorder(int index) {
        return (index <= 2 ? 2 : 1);
    }

    static int sizeBorderLink(int index) {
        return (index == 1 ? 2 : 1);
    }

    static void pushStyle(List<TexHtml> answer, List<TexHtml> arr, int indexType) {
        switch (indexType) {
            case 0, 1 -> answer.add(new Strong(arr));
            case 2 -> answer.add(new Strikeout(arr));
            case 3, 4 -> answer.add(new Emphasis(arr));
            case 5 -> answer.add(new Code(arr));
        }
    }

    private static List<TexHtml> myConvertor(String text, int left, int right) {
        if (left > right) {
            return List.of(new Text(""));
        }
        Map<String, Integer> lastEntryStyle = new HashMap<>();
        // 0 strong **
        // 1 strong __
        // 2 strikeout --
        // 3 emphasis *
        // 4 emphasis _
        // 5 code `
        // link [xxx](xxx)
        List<TexHtml> answer = new ArrayList<>();
        List<Integer> startBlocks = new ArrayList<>();
        int startBlock = 0;
        char lastSymbol = '/'; // neutral symbol

        StringBuilder story = new StringBuilder();
        for (int i = left; i <= right; i++) {
            char character = text.charAt(i);
            char nextCharacter = '/';
            if (i + 1 < text.length()) {
                nextCharacter = text.charAt(i + 1);
            }
            if (lastSymbol == '\\') {
                story.append(character);
                continue;
            }
            boolean simpleSymbol = true;
            boolean isLink = lastEntryStyle.getOrDefault("](", 0) > 0;
            // смотрю html-элементы
            for (int j = 0; j < BORDER_STYLE.length; j++) {
                if (!isLink && text.startsWith(BORDER_STYLE[j], i)) {
                    simpleSymbol = false;
                    String styleBorder = String.valueOf(character);
                    if (sizeBorder(j) == 2) {
                        styleBorder += nextCharacter;
                    }
                    pushBlock(answer, startBlocks, story, startBlock);
                    if (lastEntryStyle.getOrDefault(styleBorder, 0) == 0) {
                        lastEntryStyle.put(styleBorder, i + 1);
                        story.append(character);
                        if (sizeBorder(j) == 2) {
                            story.append(nextCharacter);
                        }
                        startBlock = i;
                    } else {
                        startBlock = i + sizeBorder(j);
                        List<TexHtml> arr = findYoungerElement(answer, startBlocks, lastEntryStyle.getOrDefault(styleBorder, 0));
                        arr.set(0, subText(arr.get(0), sizeBorder(j)));
                        pushStyle(answer, arr, j);
                        startBlocks.add(lastEntryStyle.getOrDefault(styleBorder, 0));
                        lastEntryStyle.put(styleBorder, 0);
                    }
                    if (sizeBorder(j) == 2) {
                        i++;
                    }
                    break;
                }
            }
            //обрабатываю начало и середину ссылки
            for (int k = 0; k < 2; k++) {
                if (text.startsWith(BORDER_LINK[k], i)) {
                    simpleSymbol = false;
                    String styleBorder = String.valueOf(character);
                    if (sizeBorderLink(k) == 2) { // is ]( not [
                        styleBorder += nextCharacter;
                    }
                    pushBlock(answer, startBlocks, story, startBlock);
                    lastEntryStyle.put(styleBorder, i + 1);
                    story.append(character);
                    startBlock = i;
                    if (sizeBorderLink(k) == 2) {
                        story.append(nextCharacter);
                        i++;
                    }
                }
            }
            //обрабатываю конец ссылки
            if (text.startsWith(BORDER_LINK[2], i) && lastEntryStyle.getOrDefault(BORDER_LINK[1], 0) > 0 ) {
                simpleSymbol = false;
                pushBlock(answer, startBlocks, story, startBlock);
                startBlock = i + 1;
                List<TexHtml> arr = findYoungerElement(answer, startBlocks, lastEntryStyle.getOrDefault("](", 0));
                arr.set(0, subText(arr.get(0), 2));
                List<TexHtml> arr2 = findYoungerElement(answer, startBlocks, lastEntryStyle.getOrDefault("[", 0));
                arr2.set(0, subText(arr2.get(0), 1));
                answer.add(new Link(arr, arr2));
                startBlocks.add(lastEntryStyle.getOrDefault("[", 0));
                lastEntryStyle.put(BORDER_LINK[0], 0);
                lastEntryStyle.put(BORDER_LINK[1], 0);
            }
            if (simpleSymbol) {
                story.append(character);
            }
            lastSymbol = character;
        }
        answer.add(new Text(story));
        return answer;
    }

    public static void main(final String[] args) {
        try {
            final MyScanner reader = new MyScanner(new File(args[0]));
            final List<TexHtml> res = new ArrayList<>(0);
            try {
                StringBuilder section = new StringBuilder();
                while (reader.hasNextLine()) {
                    final String line = reader.nextLine();
                    if (!line.isEmpty()) {
                        section.append(line).append('\n');
                    }

                    if (!section.isEmpty() && (line.isEmpty() || !reader.hasNextLine())) {
                        section.deleteCharAt(section.length() - 1);
                        int level = 0;
                        while (level + 1 < section.length() && section.charAt(level) == '#') {
                            level++;
                        }
                        if (0 < level && level < section.length() && Character.isWhitespace(section.charAt(level))) {
                            res.add(new Header(myConvertor(section.toString(), level + 1, section.length() - 1), level));
                        } else {
                            res.add(new Paragraph(myConvertor(section.toString(), 0, section.length() - 1)));
                        }
                        section = new StringBuilder();
                    }
                }
            } finally {
                reader.close();
            }
            try {
                final PrintWriter writer = new PrintWriter(args[1], StandardCharsets.UTF_8);
                try {
                    for (final TexHtml x : res) {
                        final StringBuilder res2 = new StringBuilder();
                        x.toHtml(res2);
                        writer.println(res2);
                    }
                } finally {
                    writer.close();
                }
            } catch (final ArrayIndexOutOfBoundsException e) {
                System.out.println(e + ":" + "Name input or output file is undefined");
            } catch (final FileNotFoundException e) {
                System.out.println(e + ":" + "Output file not found");
            } catch (final IOException e) {
                System.out.println(e + ":" + "Output file writing error");
            }
        } catch (final ArrayIndexOutOfBoundsException e) {
            System.out.println(e + ":" + "Name input or output file is undefined");
        } catch (final FileNotFoundException e) {
            System.out.println(e + ":" + "Input file not found");
        } catch (final IOException e) {
            System.out.println(e + ":" + "Input file reading error");
        }
    }
}
