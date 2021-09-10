package com.github.webpage_statistician.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

@Component
public class StatAppFileWriter implements StatAppWriter {

    @Override
    public void write(String text, String fileName) throws IOException {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8, false))) {
            fileWriter.write(text);
        }
    }
}