package com.github.webpage_statistician.domain;

import java.io.IOException;

public interface StatAppReader {

    public String readText(String source) throws IOException, InterruptedException;
}