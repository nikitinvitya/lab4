package com.nikitsin.handling.information.textParser.impl;

import com.nikitsin.handling.information.entity.TextComponent;
import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.entity.TextComponentType;
import com.nikitsin.handling.information.entity.impl.LetterImpl;
import com.nikitsin.handling.information.entity.impl.SignImpl;
import com.nikitsin.handling.information.textParser.TextParser;


public class WordParserImpl implements TextParser {
    private static final String CHECK_LETTER_REGEXP = "\\w";

    @Override
    public TextComponent parse(String word) {
        TextComponentComponentImpl textComponentImpl = new TextComponentComponentImpl(TextComponentType.WORD);

        for (int i = 0; i < word.length(); i++) {
            if(String.valueOf(word.charAt(i)).matches(CHECK_LETTER_REGEXP)){
                textComponentImpl.addComponent(new LetterImpl(word.charAt(i)));
            }
            else {
                textComponentImpl.addComponent(new SignImpl(word.charAt(i)));
            }
        }

        return textComponentImpl;
    }

}
