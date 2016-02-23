package com.epam.alexandrli.textparser;

import java.util.ArrayList;

public class CharLeaf implements Component {

    private final char value;

    private CharLeaf(char value) {
        this.value = value;
    }

    @Override
    public String toPlainString() {
        return String.valueOf(value);
    }

    public static CharLeaf valueOf(char value) {
        return CharCache.getCachedValue(value);
    }

    public char getValue() {
        return value;
    }

    private static class CharCache {
        private static ArrayList<CharLeaf> charCache = new ArrayList<>();

        public static CharLeaf getCachedValue(char value) {
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
