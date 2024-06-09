package com.goit.javacore.module10.pshcherba;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Task1 {
    private static final String FILE_PATH = "src/main/resources/File1.txt";

    public static void main(String[] args) {
        printValidPhonesFromFile(FILE_PATH);
    }

    private static void printValidPhonesFromFile(String absolutePath) {
        Path filePath = Path.of(absolutePath);
        String pattern1 = "\\(\\d{3}\\) \\d{3}-\\d{4}";
        String pattern2 = "\\d{3}-\\d{3}-\\d{4}";

        if (!Files.exists(filePath)) {
            throw new RuntimeException("File with path " + filePath + " does not exist");
        }

        try {
            Set<String> phoneNumbers = new HashSet<>(Files.readAllLines(filePath));
            for (String phoneNumber : phoneNumbers) {
                if (Pattern.matches(pattern1, phoneNumber.trim()) || Pattern.matches(pattern2, phoneNumber.trim())) {
                    System.out.println(phoneNumber.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
