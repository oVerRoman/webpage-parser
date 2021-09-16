package com.github.webpage_statistician.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class StatAppWebpageReaderTest {

    String source;
    String text;
    StatAppWebpageReader reader;

    @Test
    void statAppWebpageReader_returnWebpageBodyTet_whenCallReadTextMethod() throws IOException, InterruptedException {
        reader = new StatAppWebpageReader();
        source = "https://www.google.com/";
        text = reader.readText(source);
        assertThat(text).contains("doctype").contains("script").contains("body");
    }
}