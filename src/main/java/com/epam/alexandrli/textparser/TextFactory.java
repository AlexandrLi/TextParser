package com.epam.alexandrli.textparser;

import java.io.FileInputStream;
import java.io.IOException;

public class TextFactory {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public String createTextFromFile(String path) {
        String text = "";
        try {
            FileInputStream textFile = new FileInputStream(path);
            byte[] string = new byte[textFile.available()];
            textFile.read(string);
            text = new String(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
