package com.goit.javacore.module10.pshcherba;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Task3 {
    private static final String FILE_PATH = "src/main/resources/words.txt";

    public static void main(String[] args) {
        printWordsCount(readStringFromFile(FILE_PATH));
    }

    private static String readStringFromFile(String path) {
        String string = null;
        Path filePath = Path.of(path);

        if (!Files.exists(filePath)) {
            throw new RuntimeException("File with path " + filePath + " does not exist");
        }

        try {
            string = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }

    private static void printWordsCount(String string) {
        String[] words = string.trim().split("[\\p{Punct}\\s]+");
        Map<String, Integer> wordsCount = new LinkedHashMap<>();

        for (String word : words) {
            if (wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else {
                wordsCount.put(word, 1);
            }
        }

        wordsCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ))
                .forEach((key, value) -> System.out.println(key + " " + value));
    }
}
