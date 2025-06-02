package com.nikitsin.handling.information.textParser.impl;

import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.entity.TextComponentType;
import com.nikitsin.handling.information.textParser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphParserImpl implements TextParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParserImpl.class);
    private static final String SPLIT_ON_SENTENCES_REGEXP = "(?<=[.{1}!?])\\s";
    private SentenceParserImpl sentenceParserImpl = new SentenceParserImpl();

    @Override
    public TextComponentComponentImpl parse(String paragraph) {
        TextComponentComponentImpl textComponentImpl = new TextComponentComponentImpl(TextComponentType.PARAGRAPH);

        List<String> allStr = new ArrayList<>(Arrays.asList(paragraph.split(SPLIT_ON_SENTENCES_REGEXP)));

        for(String s : allStr){
            textComponentImpl.addComponent(sentenceParserImpl.parse(s));
        }

        logger.info("Paragraph was parsed on sentences");

        return textComponentImpl;
    }

}
