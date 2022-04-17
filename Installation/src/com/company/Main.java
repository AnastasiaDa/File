package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void exist(File file, StringBuilder builder) {
        if (file.isDirectory())
            builder.append("The folder " + file.getName() + " exists \n");
    }

    public static void createFolder(File file, StringBuilder builder) {
        if (file.mkdir())
            builder.append("The folder " + file.getName() + " was created \n");
    }

    public static void createFile(File file, StringBuilder builder) {
        try {
            if (file.createNewFile())
                builder.append("The file " + file.getName() + " was created \n");
        } catch (IOException ex) {
            builder.append(ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) throws IOException {

        StringBuilder log = new StringBuilder();

        File gamesFolder = new File("C:/Games/");
        exist(gamesFolder, log);

        File srcFolder = new File("C:/Games/src/");
        createFolder(srcFolder, log);

        File resFolder = new File("C:/Games/res/");
        createFolder(resFolder, log);

        File savegamesFolder = new File("C:/Games/savegames/");
        createFolder(savegamesFolder, log);

        File tempFolder = new File("C:/Games/temp/");
        createFolder(tempFolder, log);

        File mainSrcFolder = new File("C:/Games/src/main/");
        createFolder(mainSrcFolder, log);

        File testSrcFolder = new File("C:/Games/src/test/");
        createFolder(testSrcFolder, log);

        File mainFile = new File("C:/Games/src/main/Main.java");
        createFile(mainFile, log);

        File utilsFile = new File("C:/Games/src/main/Utils.java");
        createFile(utilsFile, log);

        File drawablesResFolder = new File("C:/Games/res/drawables/");
        createFolder(drawablesResFolder, log);

        File vectorsResFolder = new File("C:/Games/res/vectors/");
        createFolder(vectorsResFolder, log);

        File iconsResFolder = new File("C:/Games/res/icons/");
        createFolder(iconsResFolder, log);

        File textTempFolder = new File("C:/Games/temp/temp.txt");
        createFile(textTempFolder, log);

        String logResult = log.toString();
        try (FileWriter writer = new FileWriter(textTempFolder)) {
            writer.write(logResult);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
