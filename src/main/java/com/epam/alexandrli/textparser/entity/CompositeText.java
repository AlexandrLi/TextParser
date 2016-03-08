package com.epam.alexandrli.textparser.entity;

import java.util.*;

public class CompositeText implements Component, Iterable<Component> {
    private static List<Type> typeHierarchy = new ArrayList<>();

    static {
        Collections.addAll(typeHierarchy, Type.values());
    }

    private List<Component> components = new ArrayList<>();
    private Type type;

    public CompositeText() {

    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public StringBuilder toPlainString(StringBuilder sb) {
        for (Component component : components) {
            component.toPlainString(sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return "CompositeText{" +
                "type=" + type +
                ", number of components=" + components.size() +
                '}';
    }

    public void add(Component component) {
        components.add(component);
    }

    public boolean isNestedType(Type type) {
        for (int i = typeHierarchy.indexOf(this.type) + 1; i < typeHierarchy.size(); i++) {
            if (type == typeHierarchy.get(i)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfChildComponents() {
        int count = 0;
        Iterator<Component> iterator = components.iterator();
        if (iterator.hasNext()) {
            count++;
        }
        return count;
    }

    public List<CompositeText> getCompositeList(Type componentType) {
        Iterator<Component> iterator = this.iterator(componentType);
        List<CompositeText> components = new ArrayList<>();
        Component component;
        while (iterator.hasNext()) {
            component = iterator.next();
            if (component instanceof CompositeText) {
                components.add(((CompositeText) component));
            }
        }
        return components;
    }

    public boolean isNearestNestedType(Type type) {
        return (typeHierarchy.indexOf(type) - typeHierarchy.indexOf(this.type)) == 1;
    }

    public Iterator<Component> iterator(Type type) {
        if (isNearestNestedType(type)) {
            return iterator();
        } else {
            if (isNestedType(type)) {
                return new DeepIterator(type);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public Iterator<Component> iterator() {
        return components.iterator();
    }

    public enum Type {
        TEXT, PARAGRAPH, SENTENCE, SENTENCE_PART, WORD, PUNCTUATION
    }

    private class DeepIterator implements Iterator<Component> {
        Deque<Iterator<Component>> stack = new ArrayDeque<>();
        Iterator<Component> currentIterator;
        Type deepType;

        public DeepIterator(Type type) {
            currentIterator = components.iterator();
            stack.push(currentIterator);
            deepType = type;
        }


        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if (currentIterator.hasNext()) {
                    return true;
                }
                stack.pop();
                currentIterator = stack.peek();
            }
            return false;
        }

        @Override
        public Component next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Component component = currentIterator.next();
            if (component instanceof CompositeText) {
                while (((CompositeText) component).getType() != deepType) {
                    currentIterator = ((CompositeText) component).iterator();
                    stack.push(currentIterator);
                    component = currentIterator.next();
                }
            }
            return component;
        }
    }
}
