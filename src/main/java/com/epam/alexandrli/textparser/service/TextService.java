package com.epam.alexandrli.textparser.service;

import com.epam.alexandrli.textparser.entity.Component;
import com.epam.alexandrli.textparser.entity.CompositeText;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static com.epam.alexandrli.textparser.entity.CompositeText.Type;

public class TextService {
    public static final Comparator<CompositeText> ELEMENTS_COUNT_ORDER = new ComponentsCountComparator();

    public List<CompositeText> sortSentencesByWordsCount(CompositeText text) {
        List<CompositeText> sentences = text.getCompositeList(Type.SENTENCE);
        sentences.sort(ELEMENTS_COUNT_ORDER);
        return sentences;
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

    private static class ComponentsCountComparator implements Comparator<CompositeText> {

        @Override
        public int compare(CompositeText firstComposite, CompositeText secondComposite) {
            return Integer.compare(firstComposite.numberOfChildComponents(), secondComposite.numberOfChildComponents());
        }
    }

}
