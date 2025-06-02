package com.nikitsin.handling.information.service;

import com.nikitsin.handling.information.entity.TextComponent;
import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.entity.TextComponentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public class TextOperation {
    private static final Logger logger = LogManager.getLogger(TextOperation.class);

    public static TextComponentComponentImpl removeSentencesWithLessWords(TextComponentComponentImpl text, int minWords) {
        TextComponentComponentImpl result = new TextComponentComponentImpl(TextComponentType.TEXT);

        if (text == null) {
            logger.error("text can't be null");
            return result;
        }

        for (int i = 0; i < text.getComponentsSize(); i++) {
            TextComponent paragraph = text.getComponent(i);
            TextComponentComponentImpl resultParagraph = new TextComponentComponentImpl(TextComponentType.PARAGRAPH);

            for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                TextComponent sentence = paragraph.getComponent(j);
                int wordCount = countWords(sentence);
                
                if (wordCount >= minWords) {
                    resultParagraph.addComponent(sentence);
                }
            }

            if (resultParagraph.getComponentsSize() > 0) {
                result.addComponent(resultParagraph);
            }
        }

        logger.info("Removed sentences with less than " + minWords + " words");
        return result;
    }


    public static Map<String, Integer> countDuplicateWords(TextComponentComponentImpl text) {
        Map<String, Integer> wordCount = new HashMap<>();

        if (text == null) {
            logger.error("text can't be null");
            return wordCount;
        }

        // Подсчитываем вхождения каждого слова
        for (int i = 0; i < text.getComponentsSize(); i++) {
            TextComponent paragraph = text.getComponent(i);
            for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                TextComponent sentence = paragraph.getComponent(j);
                for (int k = 0; k < sentence.getComponentsSize(); k++) {
                    TextComponent lexeme = sentence.getComponent(k);
                    for (int l = 0; l < lexeme.getComponentsSize(); l++) {
                        if (lexeme.getComponent(l) instanceof TextComponentComponentImpl &&
                            lexeme.getComponent(l).getTypeOfTextComponent().equals(TextComponentType.WORD)) {
                            String word = lexeme.getComponent(l).getTextMessage().toLowerCase();
                            wordCount.merge(word, 1, Integer::sum);
                        }
                    }
                }
            }
        }

        wordCount.entrySet().removeIf(entry -> entry.getValue() <= 1);

        logger.info("Found " + wordCount.size() + " duplicate words");
        return wordCount;
    }

    public static TextComponentComponentImpl sortParagraphsByNumOFSentences(TextComponentComponentImpl text) {
        TextComponentComponentImpl result = new TextComponentComponentImpl(TextComponentType.TEXT);

        if (text == null) {
            logger.error("text can't be null");
            return result;
        }

        List<TextComponent> paragraphs = new ArrayList<>();
        for (int i = 0; i < text.getComponentsSize(); i++) {
            paragraphs.add(text.getComponent(i));
        }
        paragraphs = paragraphs.stream().sorted(Comparator.comparingInt(TextComponent::getComponentsSize)).collect(Collectors.toList());
        for (TextComponent paragraph : paragraphs) {
            result.addComponent(paragraph);
        }

        logger.info("Paragraphs was sorted by number of sentences");

        return result;
    }

    private static int countWords(TextComponent sentence) {
        int count = 0;
        for (int i = 0; i < sentence.getComponentsSize(); i++) {
            TextComponent lexeme = sentence.getComponent(i);
            for (int j = 0; j < lexeme.getComponentsSize(); j++) {
                if (lexeme.getComponent(j) instanceof TextComponentComponentImpl &&
                    lexeme.getComponent(j).getTypeOfTextComponent().equals(TextComponentType.WORD)) {
                    count++;
                }
            }
        }
        return count;
    }
}













