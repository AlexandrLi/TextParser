package com.epam.alexandrli.textparser;

import java.util.ArrayList;

public class CharLeaf implements Printable {

    private char value;

    public CharLeaf() {
    }

    public CharLeaf(char value) {
        valueOf(value);
    }

    private CharLeaf valueOf(char value) {
        return CharCache.getCachedValue(value);
    }

    @Override
    public void print() {
        System.out.println(value);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    private static class CharCache {
        private static ArrayList<CharLeaf> charCache = new ArrayList<>();

        private static CharLeaf getCachedValue(char value) {
            return charCache.get(findCachedValueIndex(value));
        }

        private static int findCachedValueIndex(char value) {
            for (int i = 0; i < charCache.size(); i++) {
                if (charCache.get(i).value == value) {
                    return i;
                }
            }
            cacheNewValue(value);
            return charCache.size() - 1;
        }

        private static void cacheNewValue(char value) {
            CharLeaf nonCachedChar = new CharLeaf();
            nonCachedChar.setValue(value);
            charCache.add(nonCachedChar);
        }
    }
}
