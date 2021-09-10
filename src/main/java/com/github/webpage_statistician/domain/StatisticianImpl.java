package com.github.webpage_statistician.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.webpage_statistician.dao.DAOFileReader;

@Component
public class StatisticianImpl implements Statistician {

    Map<String, Integer> wordsMap = new HashMap<>();
    List<String> wordsList;
    @Autowired
    TextParser parser;

    @Override
    public Map<String, Integer> getStatistics(String fileName) throws IOException {
        wordsList = parser.parse(new DAOFileReader().readText(fileName));
        for (String word : wordsList) {
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
        for (Entry<String, Integer> word : wordsMap.entrySet()) {
            System.out.println("[word: " + word.getKey() + ", value: " + word.getValue() + "]");
        }
        return wordsMap;
    }
}