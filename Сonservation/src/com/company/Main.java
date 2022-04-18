package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void saveGame(String string, GameProgress gameProgress) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(string);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String path, List<String> list) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String fileZip : list) {
                File file = new File(fileZip);
                String zName = file.getName();
                FileInputStream fis = new FileInputStream(fileZip);
                ZipEntry entry = new ZipEntry("packed_" + zName);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(File file) {
        if (file.delete())
            System.out.println("The file " + file.getName() + " was deleted");
    }

    public static void main(String[] args) throws IOException {

        GameProgress prog1 = new GameProgress(15, 4, 3, 5.5);
        GameProgress prog2 = new GameProgress(10, 6, 6, 7.8);
        GameProgress prog3 = new GameProgress(20, 7, 10, 14.4);

        File save1 = new File("C:/Games/savegames/save1.dat");
        File save2 = new File("C:/Games/savegames/save2.dat");
        File save3 = new File("C:/Games/savegames/save3.dat");
        File saveZip = new File("C:/Games/savegames/zip.zip");

        saveGame(save1.getAbsolutePath(), prog1);
        saveGame(save2.getAbsolutePath(), prog2);
        saveGame(save3.getAbsolutePath(), prog3);

        List<String> saveFiles = new ArrayList<>();
        saveFiles.add(save1.getAbsolutePath());
        saveFiles.add(save2.getAbsolutePath());
        saveFiles.add(save3.getAbsolutePath());

        zipFiles(saveZip.getAbsolutePath(), saveFiles);

        deleteFile(save1);
        deleteFile(save2);
        deleteFile(save3);
    }
}

