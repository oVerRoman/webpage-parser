package com.github.webpage_statistician.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

class TextParserImplTest {

    final String fileName = "src/test/resources/testparsingfile.txt";
    String text;
    List<String> wordsList;
    TextParserImpl parser;

    @Test
    void textParserImpl_returnListOfWordStrings_whenTextGiven() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get(fileName)).forEach(stringBuilder::append);
        text = stringBuilder.toString();
        parser = new TextParserImpl();
        wordsList = parser.parse(text);
        assertThat(wordsList).contains("EXAMPLES").contains("SENTENCE").contains("WORD-WITH-HYPHEN")
                .doesNotContain("SCRIPT").doesNotContain("STYLE").doesNotContain("DOCTYPE").doesNotContain("(")
                .doesNotContain(";").doesNotContain(" ");
    }
}