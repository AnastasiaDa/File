package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void exist(File file, StringBuilder builder) {
        if (file.isDirectory())
            builder.append("The folder " + file.getName() + " exists \n");
    }

    public static void createFolder(File[] folders, StringBuilder builder) {
        for (File file : folders) {
            if (file.mkdir())
                builder.append("The folder " + file.getName() + " was created \n");
        }
    }

    public static void createFile(File[] files, StringBuilder builder) {
        for (File file : files) {
            try {
                if (file.createNewFile())
                    builder.append("The file " + file.getName() + " was created \n");
            } catch (IOException ex) {
                builder.append(ex.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {

        StringBuilder log = new StringBuilder();

        File gamesFolder = new File("C:/Games/");
        exist(gamesFolder, log);

        File[] folders = {
                new File(gamesFolder, "src"),
                new File(gamesFolder, "res"),
                new File(gamesFolder, "saveGames"),
                new File(gamesFolder, "temp"),
                new File(gamesFolder + "/src", "main"),
                new File(gamesFolder + "/src", "test"),
                new File(gamesFolder + "/res", "drawables"),
                new File(gamesFolder + "/res", "vectors"),
                new File(gamesFolder + "/res", "icons"),
        };

        File[] files = {
                new File(folders[4], "Main.java"),
                new File(folders[4], "Utils.java"),
                new File(folders[3], "temp.txt"),
        };

        createFolder(folders, log);
        createFile(files, log);

        String logResult = log.toString();
        try (FileWriter writer = new FileWriter(files[2])) {
            writer.write(logResult);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

