package com.github.webpage_statistician.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TextParserImpl implements TextParser {

    private List<String> wordsList;

    @Override
    public List<String> parse(String text) {
        wordsList = new ArrayList<>();
        String[] wordsArray = text.split("<title>.*?</title>|<style.*?(\n.*?)*</style>|"
                + "<script.*?(\n.*?)*</script>|<.*?([\n\r\s\t].*?)*?>|\s*^*[—-]\s+|\s+|\t+|"
                + "&#160;|&nbsp;|u+00a0|&#37|&#x27|&#8209|[.,!\\?:;_…\\+\\*\\/–—=\'\"«»“”(){}\\[\\]]");
        for (String word : wordsArray) {
            if (!word.equals("")) {
                wordsList.add(word.toUpperCase());
            }
        }
        return wordsList;
    }
}