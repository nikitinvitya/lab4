package com.nikitsin.handling.information.entity.impl;

import com.nikitsin.handling.information.entity.TextComponent;
import com.nikitsin.handling.information.entity.TextComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComponentComponentImpl implements TextComponent {
    private static final String TO_STRING_MESSAGE = "\n\t";
    private final List<TextComponent> components = new ArrayList<>();
    private final TextComponentType type;

    public TextComponentComponentImpl(TextComponentType type) {
        this.type = type;
    }

    @Override
    public int countOfOrderedSymbol(String symbol) {
        int counter = 0;
        for(TextComponent textComponent : components){
            counter += textComponent.countOfOrderedSymbol(symbol);
        }

        return counter;
    }
    @Override
    public TextComponentType getTypeOfTextComponent() {
        return type;
    }
    @Override
    public TextComponent getComponent(int index) {
        return components.get(index);
    }
    @Override
    public int getComponentsSize() {
        return components.size();
    }
    @Override
    public String getTextMessage() {
        StringBuilder stringBuilder = new StringBuilder();

        components.removeIf(Objects::isNull);
        switch (type){
            case TEXT:
                components.forEach(o -> stringBuilder.append("\n\t").append(o.getTextMessage()));
                break;
            case SENTENCE:
                components.forEach(o -> stringBuilder.append(o.getTextMessage()).append(" "));
                break;
            default:
                components.forEach(o -> stringBuilder.append(o.getTextMessage()));
        }

        return stringBuilder.toString();
    }
    @Override
    public boolean addComponent(TextComponent textComponent) {
        return components.add(textComponent);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        // Проверка на null и сравнение типов через Class
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TextComponentComponentImpl that = (TextComponentComponentImpl) o;

        if (this.type != that.type) {
            return false;
        }

        if (this.components.size() != that.components.size()) {
            return false;
        }

        for (int i = 0; i < this.components.size(); i++) {
            TextComponent thisComponent = this.components.get(i);
            TextComponent thatComponent = that.components.get(i);

            if (thisComponent == null) {
                if (thatComponent != null) {
                    return false;
                }
            } else {
                if (!thisComponent.equals(thatComponent)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = type.hashCode();
        for(TextComponent textComponent : components){
            hashCode += textComponent.hashCode() * 31;
        }

        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(TextComponent paragraph : components){
            stringBuilder.append(TO_STRING_MESSAGE);
            stringBuilder.append(paragraph.getTextMessage());
        }
        return stringBuilder.toString();
    }

}
