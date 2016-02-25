package com.epam.alexandrli.textparser;


import com.epam.alexandrli.textparser.entity.CompositeText;
import com.epam.alexandrli.textparser.factory.NewLoggerFactory;
import com.epam.alexandrli.textparser.factory.TextFactory;
import com.epam.alexandrli.textparser.parser.TextParser;
import org.slf4j.Logger;

public class Runner {
    final static Logger logger = NewLoggerFactory.createLoggerWithConfigFromFile(Runner.class, "logback.xml");

    public static void main(String[] args) {
        logger.info("App started!");
        TextFactory textFactory = new TextFactory();
        String textFromFile = textFactory.createTextFromFile("text.txt");
        logger.info("Text read from file");
        TextParser textParser = new TextParser();
        logger.info("Start text parsing");
        CompositeText compositeText = textParser.parseText(textFromFile);
        logger.info("Text has been parsed successfully");
        String s = compositeText.toPlainString(new StringBuilder());
    }
}
