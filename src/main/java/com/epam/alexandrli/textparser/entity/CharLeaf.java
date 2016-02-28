package com.epam.alexandrli.textparser.entity;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public String toString() {
        return "CharLeaf{" +
                "value=" + value +
                " type=" + type +
                '}';
    }

    public Type getType() {
        return type;
    }

    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        return sb.append(value);
    }

    public char getValue() {
        return value;
    }

    private enum Type {
        WORDCHAR, PUNСTUATION, WHITESPACE, SYMBOL
    }

    private static class CharCache {
        static Map<Character, CharLeaf> charCache = new HashMap<>();

        static CharLeaf getCachedValue(char value) {
            if (charCache.get(value) == null) {
                cacheNewValue(value);
            }
            return charCache.get(value);
        }

        static void cacheNewValue(char value) {
            Type valueType = getValueType(value);
            CharLeaf nonCachedChar = new CharLeaf(value, valueType);
            charCache.put(value, nonCachedChar);
        }

        static Type getValueType(char value) {
            if (value == '!' || value == '?' || value == '.' || value == ',' || value == ':' || value == ';' || value == '—') {
                return Type.PUNСTUATION;
            }
            if (((int) value > 64 && (int) value < 91) || ((int) value > 96 && (int) value < 123) || ((int) value > 1039 && (int) value < 1104) || (int) value == 1105 || (int) value == 1025) {
                return Type.WORDCHAR;
            }
            if (value == ' ' || value == '\n' || value == '\r' || value == '\t') {
                return Type.WHITESPACE;
            }
            return Type.SYMBOL;
        }
    }
}
