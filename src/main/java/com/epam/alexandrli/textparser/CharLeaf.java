package com.epam.alexandrli.textparser;

import java.util.ArrayList;

public class CharLeaf implements Printable {

    private char value;

    public CharLeaf() {
    }

    private CharLeaf(char value) {
        this.value = value;
    }

    private CharLeaf valueOf(char value) {
        return CharCache.valueOf(value);
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

    public static class CharCache {
        private static ArrayList<CharLeaf> charCache = new ArrayList<>();

        public static CharLeaf valueOf(char value) {
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
            CharLeaf nonCachedChar = new CharLeaf(value);
            charCache.add(nonCachedChar);
        }
    }
}
