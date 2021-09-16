package com.github.webpage_statistician.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class DAOFileReader implements DAOReader {

    Stream<String> textStream;
    List<String> notCountedWords;
    StringBuilder stringBuilder;

    @Override
    public String readText(String source) throws IOException {
        stringBuilder = new StringBuilder();
        textStream = Files.lines(Paths.get(source));
        textStream.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}