package homework6.dao;

import java.util.*;
import java.util.stream.Collectors;

public class Dao {
    public static List<Map.Entry<String, Long>> processInput(String input) {
        String[] words = input.toLowerCase().split("\\W+");

        Map<String, Long> wordCountMap = Arrays.stream(words)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> sortedEntries = wordCountMap.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());

        return sortedEntries;
    }

    public void displayStatistics(List<Map.Entry<String, Long>> sortedEntries, String input) {
        int maxWordLength = calculateMaxWordLength(sortedEntries);
        int tableWidth = 35 + maxWordLength;
        printLine(tableWidth);
        System.out.println("| Rating | Word" + repeatSpaces(maxWordLength - 4) + " | Count | Percentage |");
        printLine(tableWidth);
        int rating = 1;
        for (Map.Entry<String, Long> entry : sortedEntries) {
            String word = entry.getKey();
            long count = entry.getValue();
            double percentage = (count * 100.0) / input.split("\\W+").length;
            System.out.printf("| %-6d | %-"+maxWordLength+"s | %-5d | %9.0f%% |%n", rating, word, count, percentage);
            rating++;
        }
        printLine(tableWidth);
    }

    private static int calculateMaxWordLength(List<Map.Entry<String, Long>> sortedEntries) {
        int maxWordLength = 0;
        for (Map.Entry<String, Long> entry : sortedEntries) {
            int wordLength = entry.getKey().length();
            if (wordLength > maxWordLength) {
                maxWordLength = wordLength;
            }
        }
        return maxWordLength;
    }

    private static void printLine(int width) {
        System.out.println("-".repeat(width));
    }

    private static String repeatSpaces(int count) {
        return " ".repeat(Math.max(0, count));
    }
}

