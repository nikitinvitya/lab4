package com.nikitsin.handling.information.main;

import com.nikitsin.handling.information.entity.impl.TextComponentComponentImpl;
import com.nikitsin.handling.information.service.TextComponentCreator;
import com.nikitsin.handling.information.service.TextOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final int MIN_WORDS = 5;

    public static void main(String[] args) {
        try {

            TextComponentComponentImpl textComponentImpl = TextComponentCreator.create();

            logger.info("Sorting paragraphs by number of sentences\n");
            TextComponentComponentImpl sortedParagraphs = TextOperation.sortParagraphsByNumOFSentences(textComponentImpl);
            logger.info(sortedParagraphs.getTextMessage());


            logger.info("Removing sentences with less than " + MIN_WORDS + " words\n");
            TextComponentComponentImpl filteredText = TextOperation.removeSentencesWithLessWords(textComponentImpl, MIN_WORDS);
            logger.info(filteredText.getTextMessage());
            

            logger.info("Finding duplicate words\n");
            Map<String, Integer> duplicateWords = TextOperation.countDuplicateWords(textComponentImpl);
            for (Map.Entry<String, Integer> entry : duplicateWords.entrySet()) {
                logger.info("Word: '{}' appears {} times", entry.getKey(), entry.getValue());
            }

        } catch (Exception e) {
            logger.error("Error occurred while processing text: {}", e.getMessage());
            logger.error("Stack trace:", e);
        }
    }
} 