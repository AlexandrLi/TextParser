package com.epam.alexandrli.textparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public TextParser() {
    }

    public CompositeText parseText(String stringText) {
        CompositeText text = new CompositeText();
        text.setType(Type.TEXT);
        String[] textParagraphs = stringText.split(RegexType.getRegex(RegexType.PARAGRAPH));
        for (String textParagraph : textParagraphs) {
            text.add(parseParagraphs(textParagraph));
        }
        return text;
    }

    public CompositeText parseParagraphs(String text) {
        CompositeText paragraph = new CompositeText();
        paragraph.setType(Type.PARAGRAPH);
        String[] textSentences = text.split(RegexType.getRegex(RegexType.SENTENCE));
        for (String textSentence : textSentences) {
            paragraph.add(parseSentence(textSentence));
        }
        return paragraph;
    }

    public CompositeText parseSentence(String text) {
        CompositeText sentence = new CompositeText();
        sentence.setType(Type.SENTENCE);
        String[] textWords = text.split(RegexType.getRegex(RegexType.WORD));
        for (String textWord : textWords) {
            sentence.add(parseWord(textWord));
        }
        return sentence;
    }

    public CompositeText parseWord(String text) {
        CompositeText word = new CompositeText();
        word.setType(Type.WORD);
        for (int i = 0; i < text.length(); i++) {
            CharLeaf symbol = CharLeaf.valueOf(text.charAt(i));
            word.add(symbol);
        }

        return word;
    }

    private Matcher getMatcher(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text);
    }

    private static class RegexType {
        private static final String WORD = "word";
        private static final String SYMBOL = "symbol";
        private static final String SENTENCE = "sentence";
        private static final String PARAGRAPH = "paragraph";

        private static String getRegex(String propertyKey) {
            InputStream in = TextParser.class.getClassLoader().getResourceAsStream("regexType.properties");
            Properties regexProperties = new Properties();
            try {
                regexProperties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return regexProperties.getProperty(propertyKey);
        }
    }
}
