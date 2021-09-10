package com.github.webpage_statistician.domain;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.github.webpage_statistician.dao.StatAppWriter;

@Controller
public class StatisticianController implements CommandLineRunner {

    private static String fileName = "webtext.txt";
    private static Map<String, Integer> countedWords;
    @Autowired
    private static StatAppReader reader;
    @Autowired
    private static StatAppWriter writer;
    @Autowired
    private static Statistician stat;

    public StatisticianController(StatAppReader reader, StatAppWriter writer, Statistician stat) {
        StatisticianController.reader = reader;
        StatisticianController.writer = writer;
        StatisticianController.stat = stat;
    }

    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    public static void main(String[] args) {
        final String URI = args[0];
        String text = null;
        try {
            text = reader.readText(URI);
            writer.write(text, fileName);
            countedWords = stat.getStatistics(fileName);
        } catch (IOException e) {
            System.out.println("Can't read/write file " + fileName);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Can't read text from website " + URI);
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}