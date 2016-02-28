package com.epam.alexandrli.textparser;


import com.epam.alexandrli.textparser.entity.CompositeText;
import com.epam.alexandrli.textparser.factory.TextReader;
import com.epam.alexandrli.textparser.parser.TextParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {
    final static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        logger.info("App started!");
        TextReader textReader = new TextReader();
        String textFromFile = textReader.createTextFromFile("text.txt");
        logger.info("Text read from file");
        TextParser textParser = new TextParser();
        logger.info("Start text parsing");
        CompositeText compositeText = textParser.parseText(textFromFile);
        logger.info("Text has been parsed successfully");
        System.out.println();
    }
}
