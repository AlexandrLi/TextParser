package com.epam.alexandrli.textparser;

import java.util.ArrayList;
import java.util.List;

public class CompositeText implements Printable {
    private List<Printable> childList = new ArrayList<>();

    public CompositeText() {
    }

    @Override
    public void print() {
        System.out.println(childList);
    }


    public void add(Printable printable) {
        childList.add(printable);
    }

    public void remove(Printable printable) {
        childList.remove(printable);
    }

    public Printable getChild(int index) {
        return childList.get(index);
    }
}
