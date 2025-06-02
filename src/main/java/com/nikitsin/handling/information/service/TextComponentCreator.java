package com.nikitsin.handling.information.service;

import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.textParser.impl.TextParserImpl;
import com.nikitsin.handling.information.io.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextComponentCreator {
    private static final Logger logger = LogManager.getLogger(TextComponentCreator.class);

    public static TextComponentComponentImpl create(){

        TextParserImpl parser = new TextParserImpl();
        String text = TextReader.readText(TextReader.INPUT_DATA_PATH);

        ExpressionService converter = new ExpressionService();
        String changeText = converter.replaceExpressionsByNumber(text);

        logger.info("TextComponent Object is created");

        return parser.parse(changeText);
    }
}
