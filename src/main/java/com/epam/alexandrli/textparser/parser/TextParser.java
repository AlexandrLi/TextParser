package com.epam.alexandrli.textparser.parser;

import com.epam.alexandrli.textparser.entity.CharLeaf;
import com.epam.alexandrli.textparser.entity.CompositeText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static com.epam.alexandrli.textparser.PropertyManager.REGEX_PROPERTIES;
import static com.epam.alexandrli.textparser.PropertyManager.getProperties;
import static com.epam.alexandrli.textparser.entity.CompositeText.Type;

public class TextParser {
    private static final String PARAGRAPH = "paragraph";
    private static final String SENTENCE = "sentence";
    private static final String WORD = "word";
    private final static Logger logger = LoggerFactory.getLogger(TextParser.class);
    private Properties regexProperties = getProperties(REGEX_PROPERTIES);

    public TextParser() {
    }

    public CompositeText parseText(String stringText) {
        CompositeText text = new CompositeText();
        text.setType(Type.TEXT);
        String[] textParagraphs = stringText.split(regexProperties.getProperty(PARAGRAPH));
        for (String textParagraph : textParagraphs) {
            text.add(parseParagraphs(textParagraph));
        }
        logger.debug("The result of {} parsing is: {}", Type.TEXT, text.toString());
        return text;
    }

    public CompositeText parseParagraphs(String text) {
        logger.info("Parse {}", Type.PARAGRAPH);
        CompositeText paragraph = new CompositeText();
        paragraph.setType(Type.PARAGRAPH);
        String[] textSentences = text.split(regexProperties.getProperty(SENTENCE));
        for (String textSentence : textSentences) {
            paragraph.add(parseSentence(textSentence));
        }
        logger.debug("The result of {} parsing is: {}", Type.PARAGRAPH, paragraph.toString());
        return paragraph;
    }

    public CompositeText parseSentence(String text) {
        logger.info("Parse {}", Type.SENTENCE);
        CompositeText sentence = new CompositeText();
        sentence.setType(Type.SENTENCE);
        String[] textWords = text.split(regexProperties.getProperty(WORD));
        for (String textWord : textWords) {
            sentence.add(parseWord(textWord));
        }
        logger.debug("The result of {} parsing is: {}", Type.SENTENCE, sentence.toString());
        return sentence;
    }

    public CompositeText parseWord(String text) {
        logger.info("Parse {}", Type.WORD);
        CompositeText word = new CompositeText();
        word.setType(Type.WORD);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            CharLeaf symbol = CharLeaf.valueOf(text.charAt(i));
            logger.debug("Parsed symbol is: {}", symbol.toString());
            word.add(symbol);
        }
        logger.debug("The result of {} parsing is: {}", Type.WORD, word.toString());
        return word;
    }

}
