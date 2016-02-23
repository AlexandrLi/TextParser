package com.epam.alexandrli.textparser;


import static com.epam.alexandrli.textparser.CharLeaf.CharCache;

public class Runner {
    public static void main(String[] args) {
        TextFactory textFactory = new TextFactory();
        String textFromFile = textFactory.createTextFromFile("text.txt");
        System.out.println(textFromFile);

        CharLeaf char1 = CharCache.valueOf('A');
        CharLeaf char2 = CharCache.valueOf('B');
        CharLeaf char3 = CharCache.valueOf('C');
        CharLeaf char4 = CharCache.valueOf('A');
        CharLeaf char5 = CharCache.valueOf('C');


    }
}
