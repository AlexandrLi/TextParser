package com.epam.alexandrli.textparser.factory;
import java.io.InputStream;
import java.util.Scanner;

public class TextFactory {

    public String createTextFromFile(String fileName) {
        InputStream in = TextFactory.class.getClassLoader().getResourceAsStream("text/" + fileName);
        return new Scanner(in).useDelimiter("\\A").next();
    }

}
