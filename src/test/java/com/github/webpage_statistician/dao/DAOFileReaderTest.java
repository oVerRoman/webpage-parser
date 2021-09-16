package com.github.webpage_statistician.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class DAOFileReaderTest {

    String fileName;
    String fileContext;
    String result;
    DAOFileReader reader;

    @Test
    void daoFileReader_returnFileContextString_whenFileNameGiven() throws IOException {
        fileName = "src/test/resources/testreadingfile.txt";
        fileContext = "Test text for reading file.";
        reader = new DAOFileReader();
        result = reader.readText(fileName);
        assertEquals(fileContext, result);
    }
}