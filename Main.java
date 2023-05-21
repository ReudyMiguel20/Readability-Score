package readability;

import java.sql.Array;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ReadabilityAnalyzer readabilityAnalyzer = new ReadabilityAnalyzer();

        if (args.length >= 1) {
            readabilityAnalyzer.importFile(args[0]);
            readabilityAnalyzer.characterCounter();
            readabilityAnalyzer.printStats();
        }

    }
}
