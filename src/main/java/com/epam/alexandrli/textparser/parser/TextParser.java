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
    private static final String SENTENCE_PART = "sentence.part";
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
        String[] textSentenceParts = text.split(regexProperties.getProperty(SENTENCE_PART));
        for (String sentencePart : textSentenceParts) {
            sentence.add(parseSentencePart(sentencePart));
        }
        logger.debug("The result of {} parsing is: {}", Type.SENTENCE, sentence.toString());
        return sentence;
    }

    public CompositeText parseSentencePart(String text) {
        logger.info("Parse {}", Type.SENTENCE_PART);
        CompositeText sentencePart = new CompositeText();
        for (int i = 0; i < text.length(); i++) {
            CharLeaf symbol = CharLeaf.valueOf(text.charAt(i));
            if (symbol.getType() == CharLeaf.Type.WORDCHAR) {
                sentencePart.setType(Type.WORD);
            } else {
                sentencePart.setType(Type.PUNCTUATION);
            }
            logger.debug("Parsed symbol is: {}", symbol.toString());
            sentencePart.add(symbol);
        }
        logger.debug("The result of {} parsing is: {}", Type.SENTENCE_PART, sentencePart.toString());
        return sentencePart;
    }

}
