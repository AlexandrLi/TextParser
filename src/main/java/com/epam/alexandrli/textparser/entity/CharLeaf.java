package com.epam.alexandrli.textparser.entity;

import java.util.ArrayList;

public class CharLeaf implements Component {

    private final char value;
    private final Type type;

    private CharLeaf(char value, Type type) {
        this.value = value;
        this.type = type;
    }

    public static CharLeaf valueOf(char value) {
        return CharCache.getCachedValue(value);
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toPlainString(StringBuilder sb) {
        return sb.append(value).toString();
    }

    public char getValue() {
        return value;
    }

    private enum Type {
        WORDCHAR, PUNKTUATION, WHITESPACE, SYMBOL
    }

    private static class CharCache {
        private static ArrayList<CharLeaf> charCache = new ArrayList<>();

        static CharLeaf getCachedValue(char value) {
            for (CharLeaf charLeaf : charCache) {
                if (charLeaf.value == value) {
                    return charLeaf;
                }
            }
            cacheNewValue(value);
            return charCache.get(charCache.size()-1);
        }

        static void cacheNewValue(char value) {
            Type valueType = getValueType(value);
            CharLeaf nonCachedChar = new CharLeaf(value, valueType);
            charCache.add(nonCachedChar);
        }

        static Type getValueType(char value) {
            if (value == '!' || value == '?' || value == '.' || value == ',' || value == ':' || value == ';') {
                return Type.PUNKTUATION;
            }
            if (((int) value > 64 && (int) value < 91) || ((int) value > 96 && (int) value < 123) || ((int) value > 191 && (int) value < 256) || (int) value > 168 || (int) value == 184) {
                return Type.WORDCHAR;
            }
            if (value == ' ' || value == '\n' || value == '\r' || value == '\t') {
                return Type.WHITESPACE;
            }
            return Type.SYMBOL;
        }
    }
}
