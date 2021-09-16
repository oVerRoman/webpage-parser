package com.github.webpage_statistician.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.webpage_statistician.dao.DAOReader;
import com.github.webpage_statistician.dao.entity.Word;

@Component
public class StatisticianImpl implements Statistician {

    Map<String, Integer> wordsMap = new HashMap<>();
    List<String> wordsList;
    List<Word> countedWordsList;
    @Autowired
    TextParser parser;
    @Autowired
    DAOReader reader;

    @Override
    public List<Word> getStatistics(String fileName) throws IOException {
        wordsList = parser.parse(reader.readText(fileName));
        for (String word : wordsList) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
        countedWordsList = new ArrayList<>();
        for (Entry<String, Integer> word : wordsMap.entrySet()) {
            countedWordsList.add(new Word(word.getKey(), word.getValue()));
        }
        return countedWordsList;
    }

    public TextParser getParser() {
        return parser;
    }

    public void setParser(TextParser parser) {
        this.parser = parser;
    }

    public DAOReader getReader() {
        return reader;
    }

    public void setReader(DAOReader reader) {
        this.reader = reader;
    }
}