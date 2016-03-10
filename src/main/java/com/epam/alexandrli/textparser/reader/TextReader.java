package com.epam.alexandrli.textparser.reader;
import java.io.InputStream;
import java.util.Scanner;

public class TextReader {

    public String createTextFromFile(String fileName) {
        InputStream in = TextReader.class.getClassLoader().getResourceAsStream("text/" + fileName);
        return new Scanner(in).useDelimiter("\\A").next();
    }

}
