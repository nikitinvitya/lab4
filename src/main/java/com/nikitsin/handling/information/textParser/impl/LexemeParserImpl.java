package com.nikitsin.handling.information.textParser.impl;

import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.entity.TextComponentType;
import com.nikitsin.handling.information.entity.impl.SignImpl;
import com.nikitsin.handling.information.textParser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParserImpl implements TextParser {
    private static final String WORD_REGEXP = "[\\w)('][\\w-)(']*";
    private WordParserImpl wordParserImpl = new WordParserImpl();

    @Override
    public TextComponentComponentImpl parse(String lexeme) {
        TextComponentComponentImpl textComponentImpl = new TextComponentComponentImpl(TextComponentType.LEXEME);

        Pattern pattern = Pattern.compile(WORD_REGEXP);
        Matcher matcher = pattern.matcher(lexeme);
        String word = "";
        while(matcher.find()){
            word = matcher.group();
            textComponentImpl.addComponent(wordParserImpl.parse(word));
        }
        if((lexeme.length() - word.length()) == 1){
           textComponentImpl.addComponent(new SignImpl(lexeme.charAt(lexeme.length() - 1)));
        }

        return textComponentImpl;
    }

}
