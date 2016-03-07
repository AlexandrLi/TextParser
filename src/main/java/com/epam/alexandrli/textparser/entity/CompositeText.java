package com.epam.alexandrli.textparser.entity;

import java.util.*;

public class CompositeText implements Component, Iterable<Component> {
    private static List<Type> typeHierarchy = new ArrayList<>();
    private List<Component> components = new ArrayList<>();
    private Type type;


    public CompositeText() {
        Collections.addAll(typeHierarchy, Type.values());
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

    public void remove(Component component) {
        components.remove(component);
    }

    public Component getChild(int index) {
        return components.get(index);
    }

    public boolean isNestedType(Type type) {
        for (int i = typeHierarchy.indexOf(type); i < typeHierarchy.size(); i++) {
            if (type == typeHierarchy.get(i)) {
                return true;
            }
        }
        return false;
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
        TEXT, PARAGRAPH, SENTENCE, SENTENCE_PART
    }

    private class DeepIterator implements Iterator<Component> {
        Stack<Iterator<Component>> stack = new Stack<>();
        Type deepType;

        public DeepIterator(Type type) {
            stack.push(components.iterator());
            deepType = type;
        }


        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }
            Iterator<Component> componentIterator = stack.peek();
            if (componentIterator.hasNext()) {
                return true;
            }
            stack.pop();
            return hasNext();
        }

        @Override
        public Component next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Iterator<Component> componentIterator = stack.peek();
            Component component = componentIterator.next();
            if (component instanceof CompositeText) {
                if (((CompositeText) component).getType() == deepType) {
                    return component;
                }
                stack.push(((CompositeText) component).iterator());
            }
            return next();
        }
    }
}
