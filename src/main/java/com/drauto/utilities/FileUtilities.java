package com.drauto.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtilities {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtilities.class);

    public static File readFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Unable to find the file '%s'", path));
        }
        return file;
    }

    /**
     * @param file
     * @return list of lines of the given file
     */
    public static List<String> returnFileAsListOfLines(String filePath) {
        try {
            return Files.readAllLines(readFile(filePath).toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            log.error("Exception occured while reading the file", e);
        }
        return null;

    }

}
