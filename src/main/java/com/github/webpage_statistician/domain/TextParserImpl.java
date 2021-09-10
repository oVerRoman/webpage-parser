package com.github.webpage_statistician.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TextParserImpl implements TextParser {

    private List<String> words;

    @Override
    public List<String> parse(String text) {
        words = new ArrayList<>();
        String[] strings = text.toLowerCase()
                .split("<title>.*?</title>|<style.*?(\n.*?)*</style>|"
                        + "<script.*?(\n.*?)*</script>|<.*?([\n\r\s\t].*?)*?>|"
                        + "\s*^*[—-]\s+|\s+|\t+|&#160;|&nbsp;|u+00a0|&#37|&#x27|[.,!?:;_\\+\\*\\/—\'\"«»“”(){}\\[\\]]");
        for (String string : strings) {
            if (!string.equals("")) {
                words.add(string);
            }
        }
        return words;
    }
}