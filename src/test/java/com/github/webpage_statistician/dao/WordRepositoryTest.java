package com.github.webpage_statistician.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.webpage_statistician.dao.entity.Word;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class WordRepositoryTest {

    List<Word> wordsList;
    List<Word> wordsListFromDB;
    @Autowired
    WordRepository wordRepository;

    @Test
    void wordRepository_saveAndFindWords_whenListOfWordsGiven() {
        Word firstWord = new Word("SQLWORD", 15);
        Word secondWord = new Word("REPOSITORY", 3);
        Word thirdWord = new Word("ENTITY", 7);
        wordsList = new ArrayList<>();
        wordsList.add(firstWord);
        wordsList.add(secondWord);
        wordsList.add(thirdWord);
        wordRepository.saveAll(wordsList);
        wordsListFromDB = new ArrayList<>();
        wordsListFromDB = wordRepository.findAll();
        assertEquals(wordsList, wordsListFromDB);
    }
}