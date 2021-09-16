package com.github.webpage_statistician.ui;

import java.util.List;

import com.github.webpage_statistician.dao.entity.Word;

public interface StatAppPrinter {

    public void print(List<Word> wordsList);
}