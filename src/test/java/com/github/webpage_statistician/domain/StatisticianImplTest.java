package com.github.webpage_statistician.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.webpage_statistician.dao.DAOFileReader;
import com.github.webpage_statistician.dao.entity.Word;

class StatisticianImplTest {

    String fileName = "src/test/resources/testreadingfile.txt";
    String textFromFile = "Text from file.";
    StatisticianImpl stat;
    List<String> wordsList;
    List<Word> countedWordsList;
    Map<String, Integer> wordsMap;
    @Mock
    TextParserImpl parser;
    @Mock
    DAOFileReader reader;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void statisticianImpl_returnListOfWordsFromFile_whenFileNameGiven() throws IOException {
        stat = new StatisticianImpl();
        wordsList = new ArrayList<>();
        stat.setParser(parser);
        stat.setReader(reader);
        wordsList.add("TEXT");
        wordsList.add("TEST");
        wordsList.add("NEXT");
        wordsList.add("TEXT");
        wordsList.add("NEXT");
        wordsList.add("NEXT");
        wordsList.add("TEST");
        wordsList.add("BEST");
        Word test = new Word("TEST", 2);
        Word next = new Word("NEXT", 3);
        when(reader.readText(fileName)).thenReturn(textFromFile);
        when(parser.parse(reader.readText(fileName))).thenReturn(wordsList);
        countedWordsList = stat.getStatistics(fileName);
        assertThat(countedWordsList).contains(test).contains(next);
    }
}