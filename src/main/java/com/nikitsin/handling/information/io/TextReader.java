package com.nikitsin.handling.information.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Objects;
import java.util.stream.Stream;

public class TextReader {
    private static final Logger logger = LogManager.getLogger(TextReader.class);
    private static final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    public static final String INPUT_DATA_PATH = "text/text.txt";

    public static String readText(String path){
        StringBuilder allText = new StringBuilder();

        File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());

        if(!file.exists()){
            logger.fatal("File doesn't exist");
            throw new RuntimeException("File doesn't exist");
        }

        try(InputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> linesStream = reader.lines()){

                linesStream.filter( s -> !s.isEmpty()).forEach(allText::append);
                logger.info("File was correctly read");
            }

        catch (IOException e) {
            logger.fatal("Problems with reading file, on the next path: " + path, e);
            throw new RuntimeException("Problems with reading file, on the next path: " + path, e);
        }

        return allText.toString();
    }

}
