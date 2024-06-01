package org.example.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

public class ZipFileUtils {

    private static final String DEFAULT_ZIP_FILE_NAME = "/zip-file.zip";

    public static void save(String targetPath, byte[] source) throws IOException {
        save(targetPath, DEFAULT_ZIP_FILE_NAME, source);
    }

    public static void save(String targetPath, String fileName, byte[] source) throws IOException {
        Path savePath = Paths.get(targetPath, fileName);
        Files.deleteIfExists(savePath);
        Files.write(savePath, source, CREATE_NEW);
    }

    public static void unZip(String targetPath) throws IOException {
        unZip(targetPath, DEFAULT_ZIP_FILE_NAME);
    }

    public static void unZip(String targetPath, String fileName) throws IOException {
        try (ZipInputStream zipInputStream = parse(targetPath, fileName)) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Path savePath = Paths.get(targetPath, zipEntry.getName());
                Files.deleteIfExists(savePath);
                Files.copy(zipInputStream, savePath);
            }
        }
    }

    private static ZipInputStream parse(String zipFilePath, String fileName) throws FileNotFoundException {
        Path zipPath = Paths.get(zipFilePath, fileName);

        return new ZipInputStream(new FileInputStream(zipPath.toFile()));
    }
}
