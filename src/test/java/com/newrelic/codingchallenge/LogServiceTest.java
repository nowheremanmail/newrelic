package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.data.LogService;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LogServiceTest {
    final static String TEST_FILE_NAME = "test.log";

    @Test
    public void creationTest() throws IOException {
        LogService logService = new LogService(TEST_FILE_NAME);

        logService.start();
        try {
            List<String> lines = Files.readAllLines(Paths.get(TEST_FILE_NAME), StandardCharsets.US_ASCII);
            assertEquals(lines.size(), 0);
        } finally {
            logService.end();
        }

    }

    @Test
    public void addOneTest() throws IOException {
        LogService logService = new LogService(TEST_FILE_NAME);
        logService.start();

        try {
            logService.addNumber(("0123456789" + System.lineSeparator()).toCharArray());

            List<String> lines = Files.readAllLines(Paths.get(TEST_FILE_NAME), StandardCharsets.US_ASCII);
            assertEquals(lines.size(), 1);
            assertEquals(lines.get(0), "0123456789");

        } finally {
            logService.end();
        }
    }

}
