package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class ZipFileUtils {

    public static final String DEFAULT_ZIP_FILE_NAME = "/zip-file.zip";

    public static void save(String targetPath, byte[] source) throws IOException {
        Path savePath = Paths.get(targetPath, DEFAULT_ZIP_FILE_NAME);
        Files.deleteIfExists(savePath);
        Files.write(savePath, source, CREATE_NEW);
    }

    public static void save(String targetPath, String fileName, byte[] source) throws IOException {
        Path savePath = Paths.get(targetPath, fileName);
        Files.deleteIfExists(savePath);
        Files.write(savePath, source, CREATE_NEW);
    }

    public static void unZip(String targetPath) throws IOException {
        Path zipPath = Paths.get(targetPath, DEFAULT_ZIP_FILE_NAME);
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath.toFile()))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Path savePath = Paths.get(targetPath, zipEntry.getName());
                Files.deleteIfExists(savePath);
                Files.copy(zipInputStream, savePath);
            }
        }
    }

    public static void unZip(String targetPath, String fileName) throws IOException {
        Path zipPath = Paths.get(targetPath, fileName);
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath.toFile()))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Path savePath = Paths.get(targetPath, zipEntry.getName());
                Files.deleteIfExists(savePath);
                Files.copy(zipInputStream, savePath);
            }
        }
    }
}
