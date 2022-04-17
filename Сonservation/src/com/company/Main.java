package com.company;

import java.io.*;
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

    public static void zipFiles(String path, String string) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));
             FileInputStream fis = new FileInputStream(string)) {
            ZipEntry entry = new ZipEntry(string);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
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

        GameProgress prog1 = new GameProgress(15,4, 3, 5.5);
        GameProgress prog2 = new GameProgress(10, 6, 6, 7.8);
        GameProgress prog3 = new GameProgress(20, 7, 10, 14.4);

        saveGame("C:/Games/savegames/save1.dat", prog1);
        saveGame("C:/Games/savegames/save2.dat", prog2);
        saveGame("C:/Games/savegames/save3.dat", prog3);

        zipFiles("C:/Games/savegames/zip.zip","C:/Games/savegames/save1.dat");
        zipFiles("C:/Games/savegames/zip.zip","C:/Games/savegames/save2.dat");
        zipFiles("C:/Games/savegames/zip.zip","C:/Games/savegames/save3.dat");




    }
}
