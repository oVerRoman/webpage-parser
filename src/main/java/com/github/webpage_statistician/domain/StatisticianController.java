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
        final String URI = args[0];
        String text = null;
        try {
            text = reader.readText(URI);
        } catch (IOException e) {
            System.out.println("Can't read text from website " + URI + ".");
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Can't read text from website " + URI + ".");
            Thread.currentThread().interrupt();
            System.exit(1);
        }
        try {
            writer.write(text, FILE_NAME);
            countedWords = stat.getStatistics(FILE_NAME);
        } catch (IOException e) {
            System.out.println("Can't read/write file " + FILE_NAME + ".");
            System.exit(1);
        }
        wordService.saveAllWords(countedWords);
        printer.print(wordService.getAllWords());
    }
}