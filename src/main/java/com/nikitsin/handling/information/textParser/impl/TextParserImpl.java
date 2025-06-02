package com.nikitsin.handling.information.textParser.impl;

import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.entity.TextComponentType;
import com.nikitsin.handling.information.textParser.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextParserImpl implements TextParser {
    private static final Logger logger = LogManager.getLogger(TextParserImpl.class);
    private static final String SPLIT_ON_PARAGRAPHS_REGEXP = "\\s{4}";
    private ParagraphParserImpl paragraphParserImpl = new ParagraphParserImpl();

    @Override
    public TextComponentComponentImpl parse(String text) {
        TextComponentComponentImpl textComponentImpl = new TextComponentComponentImpl(TextComponentType.TEXT);

        List<String> allStr = new ArrayList<>(Arrays.asList(text.split(SPLIT_ON_PARAGRAPHS_REGEXP)));

        for(String s : allStr){
            textComponentImpl.addComponent(paragraphParserImpl.parse(s));
        }

        logger.info("Text was parsed on paragraphs");
        return textComponentImpl;
    }

}
