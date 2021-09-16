package com.github.webpage_statistician.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.github.webpage_statistician.dao.StatAppFileWriter;

class StatAppFileWriterTest {

    String text;
    String fileName;
    StatAppFileWriter writer;
    String textFromFile;

    @Test
    void statAppFileWriter_writeFileWithText_whenTextGiven() throws IOException {
        text = "Test text.";
        fileName = "src/test/resources/testwritingwebtext.txt";
        writer = new StatAppFileWriter();
        writer.write(text, fileName);
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get(fileName)).forEach(stringBuilder::append);
        textFromFile = stringBuilder.toString();
        assertEquals(text, textFromFile);
    }
}