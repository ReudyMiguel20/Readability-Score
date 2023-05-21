package readability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ReadabilityAnalyzer {
    private String text;
    private final String sentenceRegex;
    private Scanner scanner;

    public ReadabilityAnalyzer() {
        this.sentenceRegex = "(\\w+[!?.])";
        this.scanner = new Scanner(System.in);
        this.text = "";
    }

    public void add(String userInput) {
        this.text = userInput;
    }

    public int sentenceCounter() {
        int counterSentence = 0;
        String[] splitter = this.text.split(this.sentenceRegex);

        for (String x : splitter) {
            counterSentence++;
        }

        return counterSentence;
    }

    public int characterCounter() {
        StringBuilder sb = new StringBuilder();
        int counterCharacter = 0;
        String[] splitterText = this.text.split(" ");

        for (String x : splitterText) {
            sb.append(x);
        }

        char[] charText = sb.toString().toCharArray();

        for (char x : charText) {
            counterCharacter++;
        }

        return counterCharacter;
    }

    public int wordCounter() {
        int counterWords = 0;
        String[] splitter = this.text.split(" ");

        for (String x : splitter) {
            counterWords++;
        }

        return counterWords;
    }

    public double calculateScore() {
        return 4.71 * characterCounter() / wordCounter() + 0.5 * wordCounter() / sentenceCounter() - 21.43;
    }

    public String recommendedAge(double calculateScore) {
        double score = Math.ceil(calculateScore);

        switch ((int) score) {
            case 1:
                return "5-6";
            case 2:
                return "6-7";
            case 3:
                return "7-8";
            case 4:
                return "8-9";
            case 5:
                return "9-10";
            case 6:
                return "10-11";
            case 7:
                return "11-12";
            case 8:
                return "12-13";
            case 9:
                return "13-14";
            case 10:
                return "14-15";
            case 11:
                return "15-16";
            case 12:
                return "16-17";
            case 13:
                return "17-18";
            default:
                return "17-18";
        }

    }

    public void printStats() {
        System.out.printf("""
                The text is:
                %s
                
                Words: %d
                Sentences: %d
                Characters: %d
                The score is: %.2f
                This test should be understood by %s year-olds.""", this.text, wordCounter(), sentenceCounter(), characterCounter(), calculateScore(), recommendedAge(calculateScore()));
    }

    public void importFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                this.text = sb.toString();
            }

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
