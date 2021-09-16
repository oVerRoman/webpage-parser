package com.github.webpage_statistician.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.webpage_statistician.dao.entity.Word;

class StatAppConsolePrinterTest {

    List<Word> wordsList;
    StatAppConsolePrinter printer;

    @Test
    void statAppConsolePrinter_printWordsTable_whenWordsListGiven() {
        Word firstWord = new Word("WORDS", 2);
        Word secondWord = new Word("UNDER", 5);
        Word thirdWord = new Word("COUNT", 15);
        Word forthWord = new Word("LONGESTWORD", 1);
        wordsList = new ArrayList<>();
        wordsList.add(firstWord);
        wordsList.add(secondWord);
        wordsList.add(thirdWord);
        wordsList.add(forthWord);
        String expected = "Word        | Value\r\nWORDS       | 2    \r\nUNDER       | 5    \r\n"
                + "COUNT       | 15   \r\nLONGESTWORD | 1    \r\n";
        printer = new StatAppConsolePrinter();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(printStream);
        printer.print(wordsList);
        System.out.flush();
        System.setOut(old);
        assertEquals(expected, baos.toString());
    }
}