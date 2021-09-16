package com.github.webpage_statistician.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.webpage_statistician.dao.WordRepository;
import com.github.webpage_statistician.dao.entity.Word;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordRepository wordRepository;

    public void saveAllWords(List<Word> wordsList) {
        wordRepository.saveAll(wordsList);
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public WordRepository getWordRepository() {
        return wordRepository;
    }

    public void setWordRepository(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }
}