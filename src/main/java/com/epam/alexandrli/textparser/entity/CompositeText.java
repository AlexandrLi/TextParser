package com.epam.alexandrli.textparser.entity;

import java.util.ArrayList;
import java.util.List;

public class CompositeText implements Component {
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
    public String toPlainString(StringBuilder sb) {
        for (Component component : components) {
            component.toPlainString(sb);
        }
        return sb.toString();
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

    public enum Type {
        WORD, SENTENCE, PARAGRAPH, TEXT
    }
}
