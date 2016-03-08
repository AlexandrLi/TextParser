package com.epam.alexandrli.textparser.service;

import com.epam.alexandrli.textparser.entity.Component;
import com.epam.alexandrli.textparser.entity.CompositeText;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.epam.alexandrli.textparser.entity.CompositeText.Type;

public class TextService {
    public List<Component> sortSentencesByWordsCount(CompositeText text) {
        List<Component> sentences = getComponentsList(text, Type.SENTENCE);
        sentences.sort((firstSentence, secondSentence) -> {
            if (firstSentence instanceof CompositeText && secondSentence instanceof CompositeText) {
                return Integer.compare(((CompositeText) firstSentence).numberOfChildComponents(), ((CompositeText) secondSentence).numberOfChildComponents());
            }
            throw new TypeMismatchException("Incorrect component type");
        });
        return sentences;
    }

    public List<Component> getComponentsList(CompositeText text, Type componentsType) {
        Iterator<Component> iterator = text.iterator(componentsType);
        List<Component> components = new ArrayList<>();
        while (iterator.hasNext()) {
            components.add(iterator.next());
        }
        return components;
    }

    public List<Component> getAllWordsFromSentence(CompositeText text) {
        if (text.getType() != Type.SENTENCE) {
            throw new TypeMismatchException("CompositeText type must be SENTENCE");
        }
        Iterator<Component> iterator = text.iterator();
        List<Component> words = new ArrayList<>();
        Component component;
        while (iterator.hasNext()) {
            component = iterator.next();
            if (component instanceof CompositeText) {
                if (((CompositeText) component).getType() == Type.WORD) {
                    words.add(component);
                }
            }
        }
        return words;
    }
}
