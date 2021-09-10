package com.github.webpage_statistician.dao;

import java.io.IOException;

public interface StatAppWriter {

    public void write(String text, String fileName) throws IOException;
}