package com.example.lab16_ex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class GenerateTasks {

    public static void main(String[] args) throws IOException {

        String username = getUser();

        if (username != null) {

            String ex = getExercises(username);
            Files.writeString(Paths.get("tasks.html"), ex);

            String jsp = Files.readString(Paths.get("src/main/resources/template.jsp"));
            Files.writeString(Paths.get("src/main/webapp/WEB-INF/views/start.jsp"), jsp);
        }
    }

    public static String getUser() throws IOException, FileNotFoundException {
        Properties p = new Properties();
        p.load(new FileReader("src/main/resources/application.properties"));
        String username = p.getProperty("username");
        return username;
    }

    private static String getExercises(String username) throws IOException {
        String content = Files.readString(Paths.get("src/main/resources/template.html"));
        content = content.replaceAll("\\$username", username);
        return content;
    }

}