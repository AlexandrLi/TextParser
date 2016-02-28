package com.epam.alexandrli.textparser.parser;

import com.epam.alexandrli.textparser.entity.CharLeaf;
import com.epam.alexandrli.textparser.entity.CompositeText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TextParser {
    private final static Logger logger = LoggerFactory.getLogger(TextParser.class);

    public TextParser() {
    }

    public CompositeText parseText(String stringText) {
        CompositeText text = new CompositeText();
        text.setType(CompositeText.Type.TEXT);
        String[] textParagraphs = stringText.split(RegexType.getRegex(RegexType.PARAGRAPH));
        for (String textParagraph : textParagraphs) {
            text.add(parseParagraphs(textParagraph));
        }
        logger.debug("The result of {} parsing is: {}", CompositeText.Type.TEXT, text.toString());
        return text;
    }

    public CompositeText parseParagraphs(String text) {
        logger.info("Parse {}", CompositeText.Type.PARAGRAPH);
        CompositeText paragraph = new CompositeText();
        paragraph.setType(CompositeText.Type.PARAGRAPH);
        String[] textSentences = text.split(RegexType.getRegex(RegexType.SENTENCE));
        for (String textSentence : textSentences) {
            paragraph.add(parseSentence(textSentence));
        }
        logger.debug("The result of {} parsing is: {}", CompositeText.Type.PARAGRAPH, paragraph.toString());
        return paragraph;
    }

    public CompositeText parseSentence(String text) {
        logger.info("Parse {}", CompositeText.Type.SENTENCE);
        CompositeText sentence = new CompositeText();
        sentence.setType(CompositeText.Type.SENTENCE);
        String[] textWords = text.split(RegexType.getRegex(RegexType.WORD));
        for (String textWord : textWords) {
            sentence.add(parseWord(textWord));
        }
        logger.debug("The result of {} parsing is: {}", CompositeText.Type.SENTENCE, sentence.toString());
        return sentence;
    }

    public CompositeText parseWord(String text) {
        logger.info("Parse {}", CompositeText.Type.WORD);
        CompositeText word = new CompositeText();
        word.setType(CompositeText.Type.WORD);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            CharLeaf symbol = CharLeaf.valueOf(text.charAt(i));
            logger.debug("Parsed symbol is: {}", symbol.toString());
            word.add(symbol);
        }
        logger.debug("The result of {} parsing is: {}", CompositeText.Type.WORD, word.toString());
        return word;
    }

    private static class RegexType {
        private static final String WORD = "word";
        private static final String SENTENCE = "sentence";
        private static final String PARAGRAPH = "paragraph";

        private static String getRegex(String propertyKey) {
            InputStream in = TextParser.class.getClassLoader().getResourceAsStream("properties/regexType.properties");
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
