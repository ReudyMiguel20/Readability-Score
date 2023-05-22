package readability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadabilityAnalyzer {
    private String text;
    private final String sentenceRegex;
    private Scanner scanner;

    public ReadabilityAnalyzer() {
        //This regex is for counting the sentences: a sentence stops when at the end is '!', '?' or '.'
        this.sentenceRegex = "(\\w+[!?.])";
        this.scanner = new Scanner(System.in);
        this.text = "";
    }

    public void add(String userInput) {
        this.text = userInput;
    }

    //Counts and return the number of sentences found in the text
    public int sentenceCounter() {

        int counterSentence = 0;
        String[] splitter = this.text.split(this.sentenceRegex);

        for (String x : splitter) {
            counterSentence++;
        }

        return counterSentence;
    }

    //Counts and return the number of characters found in the text
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


    public int syllableCounter() {
        int counterSyllables = 0;
        boolean lastCharWasVowel = false;
        String[] splitter = this.text.split(" ");

        //Counting vowels and avoiding counting double vowels
        for (String x : splitter) {
            char[] wordSplitter = x.toCharArray();
            char lastLetter = x.charAt(x.length() - 1);
            int counterVowels = 0;
            int counter = 0;

            for (char z : wordSplitter) {

                /*This is to meet the following conditions:
                 * 1- If the last letter in the word is 'e', do not count the vowel
                 * 2- If at the end of the word there were no vowels, then consider this as a 1-syllable one*/
                if (counter == wordSplitter.length - 1) {
                    if (counterVowels == 0 && z != 'e') {
                        counterVowels++;
                        counterSyllables += counterVowels;
                        lastCharWasVowel = false;
                        break;
                    } else if (lastLetter == 'e') {
                        if (counterVowels == 0) {
                            lastCharWasVowel = false;
                            counterSyllables += 1;
                            break;
                        } else {
                            counterSyllables += counterVowels;
                            lastCharWasVowel = false;
                            break;
                        }
                    } else if (!lastCharWasVowel && (z == 'a' || z == 'i' || z == 'o' || z == 'u' || z == 'y')) {
                        counterVowels++;
                        counterSyllables += counterVowels;
                        break;
                    } else if (lastCharWasVowel && (z != 'a' || z != 'i' || z != 'o' || z != 'u' || z != 'y')) {
                        counterSyllables += counterVowels;
                        lastCharWasVowel = false;
                        break;
                    } else {
                        counterSyllables += counterVowels;
                        lastCharWasVowel = false;
                        break;
                    }
                }

                //To avoid counting double vowels
                if (lastCharWasVowel) {
                    lastCharWasVowel = false;
                    counter++;
                    continue;
                }

                //If the word is a vowel, counter goes up
                switch (z) {
                    case 'a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y' -> {
                        lastCharWasVowel = true;
                        counterVowels++;
                        counter++;
                    }
                    default -> counter++;
                }
            }

        }

        return counterSyllables;
    }

    public int syllableCounter(String word) {
        int counterSyllables = 0;
        boolean lastCharWasVowel = false;
        String[] splitter = word.split(" ");

        //Counting vowels and avoiding counting double vowels
        for (String x : splitter) {
            char[] wordSplitter = x.toCharArray();
            char lastLetter = x.charAt(x.length() - 1);
            int counterVowels = 0;
            int counter = 0;

            for (char z : wordSplitter) {

                /*This is to meet the following conditions:
                 * 1- If the last letter in the word is 'e', do not count the vowel
                 * 2- If at the end of the word there were no vowels, then consider this as a 1-syllable one*/
                if (counter == wordSplitter.length - 1) {
                    if (counterVowels == 0 && z != 'e') {
                        counterVowels++;
                        counterSyllables += counterVowels;
                        lastCharWasVowel = false;
                        break;
                    } else if (lastLetter == 'e') {
                        if (counterVowels == 0) {
                            lastCharWasVowel = false;
                            counterSyllables += 1;
                            break;
                        } else {
                            counterSyllables += counterVowels;
                            lastCharWasVowel = false;
                            break;
                        }
                    } else if (!lastCharWasVowel && (z == 'a' || z == 'i' || z == 'o' || z == 'u' || z == 'y')) {
                        counterVowels++;
                        counterSyllables += counterVowels;
                        break;
                    } else if (lastCharWasVowel && (z != 'a' || z != 'i' || z != 'o' || z != 'u' || z != 'y')) {
                        counterSyllables += counterVowels;
                        lastCharWasVowel = false;
                        break;
                    } else {
                        counterSyllables += counterVowels;
                        lastCharWasVowel = false;
                        break;
                    }
                }

                //To avoid counting double vowels
                if (lastCharWasVowel) {
                    lastCharWasVowel = false;
                    counter++;
                    continue;
                }

                //If the word is a vowel, counter goes up
                switch (z) {
                    case 'a', 'e', 'i', 'o', 'u', 'y', 'A', 'E', 'I', 'O', 'U', 'Y' -> {
                        lastCharWasVowel = true;
                        counterVowels++;
                        counter++;
                    }
                    default -> counter++;
                }
            }

        }

        return counterSyllables;
    }

    public int polysyllableCounter() {
        int counterPolysyllable = 0;

        String[] splitterWord = this.text.split(" ");

        for (String word : splitterWord) {
            if (syllableCounter(word) >= 3) {
                counterPolysyllable++;
            }
        }

        return counterPolysyllable;
    }

    //Counts and return the number of words found in the text
    public int wordCounter() {
        int counterWords = 0;
        String[] splitter = this.text.split(" ");

        for (String x : splitter) {
            counterWords++;
        }

        return counterWords;
    }

    //Calculate the score based on the Automated readability index (ARI)
    public double calculateScore() {
        return 4.71 * characterCounter() / wordCounter() + 0.5 * wordCounter() / sentenceCounter() - 21.43;
    }

    public void printARIStats() {
        double score = 4.71 * characterCounter() / wordCounter() + 0.5 * wordCounter() / sentenceCounter() - 21.43;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        System.out.printf("\nAutomated Readability Index: %.2f (about %d-year-olds).", score, ageFinal);
    }

    public double getAgeARIStats() {
        double score = 4.71 * characterCounter() / wordCounter() + 0.5 * wordCounter() / sentenceCounter() - 21.43;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        return ageFinal;
    }

    public void printFKStats() {
        double score = 0.39 * wordCounter() / sentenceCounter() + 11.8 * syllableCounter() / wordCounter() - 15.59;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        System.out.printf("\nFlesch–Kincaid readability tests: %.2f (about %d-year-olds).", score, ageFinal);
    }

    public double getAgeFKStats() {
        double score = 0.39 * wordCounter() / sentenceCounter() + 11.8 * syllableCounter() / wordCounter() - 15.59;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        return ageFinal;
    }

    public void printSMOGStats() {
        double score = 1.043 * Math.sqrt(1.0 * polysyllableCounter() * 30 / sentenceCounter()) + 3.1291;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        System.out.printf("\nSimple Measure of Gobbledygook: %.2f (about %d-year-olds).", score, ageFinal);
    }

    public double getAgeSMOGStats() {
        double score = 1.043 * Math.sqrt(1.0 * polysyllableCounter() * 30 / sentenceCounter()) + 3.1291;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        return ageFinal;
    }

    public void printCLStats() {
        double score = 0.0588 * 1.0 * characterCounter() / wordCounter() * 100 - 0.296 * sentenceCounter() / wordCounter() * 100 - 15.8;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        System.out.printf("\nColeman–Liau index: %.2f (about %d-year-olds).", score, ageFinal);
    }

    public double getAgeCLStats() {
        double score = 0.0588 * 1.0 * characterCounter() / wordCounter() * 100 - 0.296 * sentenceCounter() / wordCounter() * 100 - 15.8;
        String age = recommendedAge(score);
        String[] ageSplitter = age.split("\\-");
        int[] ages = new int[2];

        for (int i = 0; i < ages.length; i++) {
            ages[i] = Integer.parseInt(ageSplitter[i]);
        }

        int ageFinal = 0;
        for (int x : ages) {
            if (ageFinal < x) {
                ageFinal = x;
            }
        }

        return ageFinal;
    }

    public void printAllStats() {
        printARIStats();
        printFKStats();
        printSMOGStats();
        printCLStats();

        double sumAge = getAgeARIStats() + getAgeCLStats() + getAgeSMOGStats() + getAgeFKStats();
        double averageAge = sumAge / 4.0;

        System.out.println("\n");
        System.out.printf("This text should be understood in average by %.2f-year-olds.", averageAge);
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
                        Syllables: %d
                        Polysyllables: %d""",
                this.text, wordCounter(), sentenceCounter(), characterCounter(), syllableCounter(), polysyllableCounter(), recommendedAge(calculateScore()));
    }

    public void printScoreMethod() {
        System.out.println("\nEnter the score you want to calculate (ARI, FK, SMOG, CL, all)");

        switch (scanner.nextLine()) {
            case "ARI" -> printARIStats();
            case "FK" -> printFKStats();
            case "SMOG" -> printSMOGStats();
            case "CL" -> printCLStats();
            case "all" -> printAllStats();
        }
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
