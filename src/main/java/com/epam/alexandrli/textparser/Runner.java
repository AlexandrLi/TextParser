package com.epam.alexandrli.textparser;


import com.epam.alexandrli.textparser.entity.CompositeText;
import com.epam.alexandrli.textparser.factory.TextFactory;
import com.epam.alexandrli.textparser.parser.TextParser;

public class Runner {
    public static void main(String[] args) {
        TextFactory textFactory = new TextFactory();
        String textFromFile = textFactory.createTextFromFile("text.txt");
        TextParser textParser = new TextParser();
        CompositeText compositeText = textParser.parseText(textFromFile);
        String s = compositeText.toPlainString(new StringBuilder());
        System.out.println(s);
    }
}
