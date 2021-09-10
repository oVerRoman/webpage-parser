package com.github.webpage_statistician.domain;

import java.io.IOException;
import java.util.Map;

public interface Statistician {

    public Map<String, Integer> getStatistics(String fileName) throws IOException;
}