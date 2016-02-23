package com.epam.alexandrli.textparser;


public class Runner {
    public static void main(String[] args) {
        TextFactory textFactory = new TextFactory();
        String textFromFile = textFactory.createTextFromFile("text.txt");
        System.out.println(textFromFile);

    }
}
