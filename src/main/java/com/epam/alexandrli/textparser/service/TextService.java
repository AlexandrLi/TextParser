package com.epam.alexandrli.textparser.service;

import com.epam.alexandrli.textparser.entity.CompositeText;

import java.util.Comparator;
import java.util.List;

import static com.epam.alexandrli.textparser.entity.CompositeText.Type;

public class TextService {
    public static final Comparator<CompositeText> COMPONENTS_COUNT_ORDER = new ComponentsCountComparator();

    public List<CompositeText> sortComponentsByCondition(CompositeText text, Type componentType, Comparator<CompositeText> condition) {
        List<CompositeText> compositeList = text.getCompositeList(componentType);
        compositeList.sort(condition);
        return compositeList;
    }

    private static class ComponentsCountComparator implements Comparator<CompositeText> {

        @Override
        public int compare(CompositeText firstComposite, CompositeText secondComposite) {
            return Integer.compare(firstComposite.numberOfChildComponents(), secondComposite.numberOfChildComponents());
        }
    }

}
