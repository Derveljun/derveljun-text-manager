package com.derveljun.textmanager.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileUtils {

    final static String TXT = ".txt";

    public static List<String> readDirectories(String strPath) throws IOException {
        return Files.walk(Paths.get(strPath))
                .filter(Files::isDirectory)
                .map(f -> f.toString())
                .collect(Collectors.toList());
    }

    public static List<String> readFiles(String strPath) throws IOException {
        return Files.walk(Paths.get(strPath))
                .filter(Files::isRegularFile)
                .map(f -> f.getFileName().toString().replace(TXT, ""))
                .collect(Collectors.toList());
    }

    public static List<String> readFilteringFiles(String strPath, String fileName, String extensionFilter) throws IOException {
        return Files.walk(Paths.get(strPath, fileName))
                .filter(Files::isRegularFile)
                .filter(f -> f.endsWith(extensionFilter))
                .map(f -> f.toString())
                .collect(Collectors.toList());
    }

    public static List<String> readDirAndFiles(String strPath, String fileName) throws IOException {
        return Files.walk(Paths.get(strPath, fileName))
                .map(f -> f.toString())
                .collect(Collectors.toList());
    }

    public static String readTextFile(String strPath, String fileName) throws IOException {
        return Files.readString(Paths.get(strPath + fileName + TXT), StandardCharsets.UTF_8);
    }

    public static boolean writeTextFile(String strPath, String fileName, String content) {

        try {
            Path path = Paths.get(strPath + fileName + TXT);

            Files.deleteIfExists(path);

            FileChannel fileChannel = FileChannel.open(Files.createFile(path), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

            ByteBuffer bf = StandardCharsets.UTF_8.encode(content);
            fileChannel.write(bf);
            fileChannel.close();

            return true;
        } catch (IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) throws IOException {
        String path = "D:\\", fileName = "test";

        writeTextFile(path, fileName, "한글 data STring");
        String txt = readTextFile(path, fileName);
        System.out.println(txt);
    }
}