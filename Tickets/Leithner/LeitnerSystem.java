import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LeitnerSystem {
    public static void main(String[] args) {
        try {
            Map<String, String> words = readDataFromFile("words.txt"); // создаем карту слов и их переводов, где ключ
                                                                         // - английское слово
            Map<String, String> bins = initializeBins(words.keySet()); // получаем список ключей из words (все
                                                                       // английские слова)

            while (true) {
                String currentWord = getNextWord(words, bins, false); // получаем из метода getNextWord новое слово для
                                                                      // перевода

                if (currentWord == null) {
                    System.out.println("Congradulations! You've learned all the words");
                    break;
                }

                String userTranlation = getUserTranslation(currentWord);

                if (userTranlation.isEmpty()) {
                    saveStateToFile(words, bins, "state.txt");
                    System.out.println("The Program is finished. The state is saved");
                    break;
                }

                if (userTranlation.equalsIgnoreCase(words.get(currentWord))) {
                    moveWordToNextBin(currentWord, bins);
                    System.out.println("Correct! The word is moved to the next bin");
                } else {
                    moveWordToFirstBin(currentWord, bins, words);
                    System.out.println("Incorrect! The word is moved to the first bin");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> readDataFromFile(String filename) throws IOException {
        Map<String, String> words = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                words.put(parts[0].trim(), parts[1].trim());
            } else {
                System.out.println("String format error:" + line);
            }
        }

        reader.close();
        return words;
    }

    private static Map<String, String> initializeBins(Set<String> wordSet) {
        Map<String, String> bins = new HashMap<>();
        for (String word : wordSet) {
            bins.put(word, "1");
        }

        return bins;
    }

    private static String getNextWord(Map<String, String> words, Map<String, String> bins, boolean isFirstRun) {
        double totalProbability = 0.0;
        String currentWord = null;
    
        for (String bin : bins.keySet()) {
            totalProbability += 1.5 * Math.pow(1.5, Integer.parseInt(bins.get(bin)));
        }
    
        double randomValue = Math.random() * totalProbability; // randomValue используется для случайного выбора слова из коллекции, 
                                                               // с учетом их вероятностей. Чем выше вероятность для слова, тем больше шансов, 
                                                               // что randomValue попадет в диапазон этого слова, и оно будет выбрано для изучения.
        double currentProbability = 0.0;
    
        for (String word : words.keySet()) {
            if (isFirstRun || Integer.parseInt(bins.get(word)) < 10) {
                currentProbability += 1.5 * Math.pow(1.5, Integer.parseInt(bins.get(word)));
    
                if (randomValue <= currentProbability + 0.000001) {
                    currentWord = word;
                    break;
                }
            }
        }
    
        return currentWord;
    }
    
    private static String getUserTranslation(String word) {
        System.out.print("Translation for \"" + word + "\":");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        return scanner.nextLine();
    }

    private static void moveWordToNextBin(String word, Map<String, String> bins){
        int currentBin = Integer.parseInt(bins.get(word));
        int nextBin = Math.min(currentBin + 1, 10); // Если currentBin + 1 меньше или равно 10, то результатом будет currentBin + 1. 
                                                      // Если currentBin + 1 больше 10, то результатом будет 10.
        bins.put(word, Integer.toString(nextBin));
    }
    
    private static void moveWordToFirstBin(String word, Map<String, String>bins, Map<String, String>words){
        System.out.println("Error. Correct answer: " + words.get(word));
        bins.put(word, "1");
    }

    private static void saveStateToFile(Map<String, String>words, Map<String, String>bins, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8));

        for (String word : words.keySet()) {
            writer.write(word + ": " + words.get(word) + " " + bins.get(word) + "\n");
        }

        writer.close();
    }
    
}
