package com.nikitsin.handling.information.entity.impl;

import com.nikitsin.handling.information.entity.TextComponent;
import com.nikitsin.handling.information.entity.TextComponentType;

public class LetterImpl implements TextComponent {
    private char letter;
    public LetterImpl(char letter) {
        this.letter = letter;
    }

    @Override
    public int countOfOrderedSymbol(String symbol) {
        if(String.valueOf(letter).equalsIgnoreCase(symbol)){
            return 1;
        }
        return 0;
    }
    @Override
    public String getTextMessage() {
        return String.valueOf(letter);
    }
    @Override
    public boolean addComponent(TextComponent textComponent) {
        return false;
    }
    @Override
    public TextComponent getComponent(int index) {
        throw new UnsupportedOperationException("That method is not for leaf object");
    }
    @Override
    public int getComponentsSize() {
        throw new UnsupportedOperationException("That method is not for leaf object");
    }
    @Override
    public TextComponentType getTypeOfTextComponent() {
        throw new UnsupportedOperationException("That method is not for leaf object");
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TextLetter{");
        sb.append("letter=").append(letter);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        try {
            LetterImpl other = (LetterImpl) o;
            return this.letter == other.letter;
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return letter;
    }
}
