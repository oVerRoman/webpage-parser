package com.github.webpage_statistician.domain;

import java.io.IOException;
import java.util.List;

import com.github.webpage_statistician.dao.entity.Word;

public interface Statistician {

    public List<Word> getStatistics(String fileName) throws IOException;
}