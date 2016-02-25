package com.epam.alexandrli.textparser.entity;

import org.slf4j.Logger;

import java.util.ArrayList;

import static com.epam.alexandrli.textparser.factory.NewLoggerFactory.createLoggerWithConfigFromFile;

public class CharLeaf implements Component {
    private final static Logger logger = createLoggerWithConfigFromFile(CharLeaf.class, "Logback.xml");
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
    public String toPlainString(StringBuilder sb) {
        return sb.append(value).toString();
    }

    public char getValue() {
        return value;
    }

    private enum Type {
        WORDCHAR, PUNСTUATION, WHITESPACE, SYMBOL
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
            return charCache.get(charCache.size() - 1);
        }

        static void cacheNewValue(char value) {
            Type valueType = getValueType(value);
            CharLeaf nonCachedChar = new CharLeaf(value, valueType);
            charCache.add(nonCachedChar);
        }

        static Type getValueType(char value) {
            if (value == '!' || value == '?' || value == '.' || value == ',' || value == ':' || value == ';') {
                return Type.PUNСTUATION;
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
