package com.nikitsin.handling.information.entity.impl;

import com.nikitsin.handling.information.entity.TextComponent;
import com.nikitsin.handling.information.entity.TextComponentType;

import java.util.Objects;

public class SignImpl implements TextComponent {
    private char sign;
    public SignImpl(char sign) {
        this.sign = sign;
    }

    @Override
    public int countOfOrderedSymbol(String symbol) {
        if(String.valueOf(sign).equalsIgnoreCase(symbol)){
            return 1;
        }
        return 0;
    }
    @Override
    public String getTextMessage() {
        return String.valueOf(sign);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignImpl)) return false;
        SignImpl signImpl = (SignImpl) o;
        return Objects.equals(this.sign, signImpl.sign);
    }

    @Override
    public int hashCode() {
        return sign;
    }
    @Override
    public String toString() {
        return String.valueOf(sign);
    }

}
