package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileUtils {

    public static final String DEFAULT_ZIP_FILE_NAME = "/zipFile.zip";

    public static void save(String targetPath, byte[] source) throws IOException {
        String savePath = targetPath.concat(DEFAULT_ZIP_FILE_NAME);
        Files.deleteIfExists(Path.of(savePath));

        try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
            outputStream.write(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(String targetPath, String fileName, byte[] source) throws IOException {
        String savePath = targetPath.concat("%s/%s".formatted(targetPath, fileName));
        Files.deleteIfExists(Path.of(savePath));

        try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
            outputStream.write(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void unZip(String targetPath, String fileName) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(targetPath.concat(fileName)))) {
            ZipEntry zipEntry = null;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Path savePath = Path.of("%s/%s".formatted(targetPath, zipEntry.getName()));
                Files.deleteIfExists(savePath);

                Files.copy(zipInputStream, savePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
