package com.github.webpage_statistician.ui;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.webpage_statistician.dao.entity.Word;

@Component
public class StstAppConsolePrinter implements StatAppPrinter {

    @Override
    public void print(List<Word> wordsList) {
        int wordMaxLength = wordsList.stream().map(word -> word.getWord())
                .max(Comparator.comparingInt(word -> word.length())).orElse("").length();
        String stringFormat = "%-" + (wordMaxLength + 1) + "s| %-5s";
        System.out.println(String.format(stringFormat, "Word", "Value"));
        for (Word word : wordsList) {
            System.out.println(String.format(stringFormat, word.getWord(), word.getValue()));
        }
    }
}