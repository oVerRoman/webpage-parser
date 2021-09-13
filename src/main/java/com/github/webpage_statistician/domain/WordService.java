package com.github.webpage_statistician.domain;

import java.util.List;

import com.github.webpage_statistician.dao.entity.Word;

public interface WordService {

    public void saveAllWords(List<Word> wordsList);

    public List<Word> getAllWords();
}