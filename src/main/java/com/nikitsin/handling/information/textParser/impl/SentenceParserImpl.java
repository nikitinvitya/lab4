package com.nikitsin.handling.information.textParser.impl;

import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.entity.TextComponentType;
import com.nikitsin.handling.information.textParser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceParserImpl implements TextParser {
    private static final Logger logger = LogManager.getLogger(SentenceParserImpl.class);
    private static final String SPLIT_ON_LEXEME_REGEXP = "\\s+(?=[\\w-(])";
    private LexemeParserImpl lexemeParserImpl = new LexemeParserImpl();

    @Override
    public TextComponentComponentImpl parse(String sentence) {
        TextComponentComponentImpl textComponentImpl = new TextComponentComponentImpl(TextComponentType.SENTENCE);

        List<String> allStr = new ArrayList<>(Arrays.asList(sentence.split(SPLIT_ON_LEXEME_REGEXP)));
        allStr.removeIf(o -> o.equals(""));

        for(String s : allStr){
            textComponentImpl.addComponent(lexemeParserImpl.parse(s));
        }

        logger.info("Sentence was parsed on lexemes");

        return textComponentImpl;
    }

}
