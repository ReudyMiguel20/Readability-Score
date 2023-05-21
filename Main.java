package readability;


public class Main {
    public static void main(String[] args) {
        ReadabilityAnalyzer readabilityAnalyzer = new ReadabilityAnalyzer();

        if (args.length >= 1) {
            readabilityAnalyzer.importFile(args[0]);
            readabilityAnalyzer.printStats();
            readabilityAnalyzer.printScoreMethod();
        }

    }
}
