package com.nikitsin.handling.information.entity;

public interface TextComponent {

    String getTextMessage();
    boolean addComponent(TextComponent textComponent);
    TextComponent getComponent(int index);
    int getComponentsSize();
    TextComponentType getTypeOfTextComponent();
    int countOfOrderedSymbol(String symbol);

}
