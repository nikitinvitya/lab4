package com.nikitsin.handling.information.textParser;

import com.nikitsin.handling.information.entity.TextComponent;

@FunctionalInterface
public interface TextParser {
    TextComponent parse(String text);
}
