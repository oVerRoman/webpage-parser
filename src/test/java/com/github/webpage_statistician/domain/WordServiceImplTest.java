package com.github.webpage_statistician.domain;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.webpage_statistician.dao.WordRepository;
import com.github.webpage_statistician.dao.entity.Word;

class WordServiceImplTest {

    WordServiceImpl wordService;
    List<Word> wordsList;
    @Mock
    WordRepository wordRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void wordServiceImpl_callWordRepositoryMethods_whenCallWordServiceImplMethods() {
        wordService = new WordServiceImpl();
        wordsList = new ArrayList<>();
        wordService.setWordRepository(wordRepository);
        when(wordRepository.saveAll(wordsList)).thenReturn(wordsList);
        when(wordRepository.findAll()).thenReturn(wordsList);
        wordService.saveAllWords(wordsList);
        wordService.getAllWords();
        verify(wordRepository, times(1)).saveAll(wordsList);
        verify(wordRepository, times(1)).findAll();
    }
}