package com.github.webpage_statistician.dao;

import java.io.IOException;

public interface DAOReader {

    public String readText(String source) throws IOException;
}