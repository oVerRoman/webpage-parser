package com.github.webpage_statistician.domain;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.github.webpage_statistician.dao.StatAppWriter;
import com.github.webpage_statistician.dao.entity.Word;
import com.github.webpage_statistician.ui.StatAppPrinter;

@Controller
public class StatisticianController implements CommandLineRunner {

    private static final String FILE_NAME = "webtext.txt";
    private List<Word> countedWords;
    @Autowired
    private StatAppReader reader;
    @Autowired
    private StatAppWriter writer;
    @Autowired
    private Statistician stat;
    @Autowired
    private WordService wordService;
    @Autowired
    private StatAppPrinter printer;

    @Override
    public void run(String... args) {
        if (args.length != 1) {
            System.out.println("Input website URL with protocol.");
            System.out.println("For example: https://www.google.com.");
            return;
        }
        final String URI = args[0];
        String text = null;
        try {
            text = reader.readText(URI);
        } catch (IOException e) {
            System.out.println("Can't read text from website " + URI + ".");
            return;
        } catch (InterruptedException e) {
            System.out.println("Can't read text from website " + URI + ".");
            Thread.currentThread().interrupt();
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("Can't read text from website " + URI + ".");
            System.out.println("Input website URL with protocol.");
            System.out.println("For example: https://www.google.com.");
            return;
        }
        try {
            writer.write(text, FILE_NAME);
            countedWords = stat.getStatistics(FILE_NAME);
        } catch (IOException e) {
            System.out.println("Can't read/write file " + FILE_NAME + ".");
            return;
        }
        wordService.saveAllWords(countedWords);
        printer.print(wordService.getAllWords());
    }

    public StatAppReader getReader() {
        return reader;
    }

    public void setReader(StatAppReader reader) {
        this.reader = reader;
    }

    public StatAppWriter getWriter() {
        return writer;
    }

    public void setWriter(StatAppWriter writer) {
        this.writer = writer;
    }

    public Statistician getStatistician() {
        return stat;
    }

    public void setStatistician(Statistician stat) {
        this.stat = stat;
    }

    public WordService getWordService() {
        return wordService;
    }

    public void setWordService(WordService wordService) {
        this.wordService = wordService;
    }

    public StatAppPrinter getPrinter() {
        return printer;
    }

    public void setPrinter(StatAppPrinter printer) {
        this.printer = printer;
    }
}