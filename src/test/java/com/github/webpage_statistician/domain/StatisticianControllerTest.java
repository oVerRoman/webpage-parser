package com.github.webpage_statistician.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.webpage_statistician.dao.StatAppWriter;
import com.github.webpage_statistician.dao.entity.Word;
import com.github.webpage_statistician.ui.StatAppPrinter;

class StatisticianControllerTest {

    StatisticianController controller;
    List<Word> wordsList;
    @Mock
    private StatAppWebpageReader reader;
    @Mock
    private StatAppWriter writer;
    @Mock
    private Statistician stat;
    @Mock
    private WordService wordService;
    @Mock
    private StatAppPrinter printer;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void statisticianController_checkExecution_whenReadWebsiteAndGetStatistics()
            throws IOException, InterruptedException {
        wordsList = new ArrayList<>();
        final String URI = "uri";
        final String file = "webtext.txt";
        String text = "text";
        controller = new StatisticianController();
        controller.setPrinter(printer);
        controller.setReader(reader);
        controller.setStatistician(stat);
        controller.setWordService(wordService);
        controller.setWriter(writer);
        when(reader.readText(URI)).thenReturn(text);
        doNothing().when(writer).write(text, file);
        when(stat.getStatistics(file)).thenReturn(wordsList);
        doNothing().when(wordService).saveAllWords(wordsList);
        when(wordService.getAllWords()).thenReturn(wordsList);
        doNothing().when(printer).print(wordsList);
        controller.run(URI);
        verify(reader, times(1)).readText(URI);
        verify(writer, times(1)).write(text, file);
        verify(stat, times(1)).getStatistics(file);
        verify(wordService, times(1)).saveAllWords(wordsList);
        verify(wordService, times(1)).getAllWords();
        verify(printer, times(1)).print(wordService.getAllWords());
        assertEquals(wordsList, wordService.getAllWords());
    }

    @Test
    void statisticianController_printExplainingText_whenArgumentIsMissed() throws IOException, InterruptedException {
        wordsList = new ArrayList<>();
        final String URI = "uri";
        controller = new StatisticianController();
        String expected = "Input website URL with protocol.\r\nFor example: https://www.google.com.\r\n";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(printStream);
        controller.run();
        System.out.flush();
        System.setOut(old);
        assertEquals(expected, baos.toString());
        verify(reader, never()).readText(URI);
    }

    @Test
    void statisticianController_printExplainingText_whenArgumentsAreMoreThanOne()
            throws IOException, InterruptedException {
        wordsList = new ArrayList<>();
        final String URI = "uri";
        final String URI2 = "uri2";
        controller = new StatisticianController();
        String expected = "Input website URL with protocol.\r\nFor example: https://www.google.com.\r\n";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(printStream);
        controller.run(URI, URI2);
        System.out.flush();
        System.setOut(old);
        assertEquals(expected, baos.toString());
        verify(reader, never()).readText(URI);
    }
}
