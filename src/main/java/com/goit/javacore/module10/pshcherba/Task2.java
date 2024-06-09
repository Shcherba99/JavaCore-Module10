package com.goit.javacore.module10.pshcherba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final String INPUT_FILE_PATH = "src/main/resources/File2.txt";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/user.json";

    public static void main(String[] args) {
        createJson(createUserList(readStringLinesFromFile(INPUT_FILE_PATH)));
    }

    private static List<String> readStringLinesFromFile(String path) {
        List<String> stringLines = new ArrayList<>();
        Path filePath = Path.of(path);

        if (!Files.exists(filePath)) {
            throw new RuntimeException("File with path " + filePath + " does not exist");
        }

        try {
            stringLines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringLines;
    }

    private static List<User> createUserList(List<String> stringLines) {
        List<User> userList = new ArrayList<>();

        for (int i = 1; i < stringLines.size(); i++) {
            String[] line = stringLines.get(i).trim().split("\\s+");
            String name = line[0].trim();
            int age = Integer.parseInt(line[1].trim());
            userList.add(new User(name, age));
        }

        return userList;
    }

    private static void createJson(List<User> userList) {
        ObjectMapper mapper = new JsonMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File(OUTPUT_FILE_PATH), userList);
            System.out.println("The serialization operation was completed successfully.");
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AllArgsConstructor
    @Getter
    private static class User {
        private String name;
        private int age;
    }
}


