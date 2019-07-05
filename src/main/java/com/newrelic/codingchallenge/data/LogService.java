package com.newrelic.codingchallenge.data;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogService {
    private static Logger logger = Logger.getLogger("LogService");


    private FileChannel fileChannel;
    private String fileName;

    public LogService(String fileName) {
        this.fileName = fileName;
    }

    public void start() throws IOException {
        Set options = new HashSet();
        options.add(StandardOpenOption.CREATE);
        options.add(StandardOpenOption.TRUNCATE_EXISTING);
        options.add(StandardOpenOption.WRITE);

        Path path = Paths.get(fileName);

        fileChannel = FileChannel.open(path, options);

    }

    public void end() {
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException e) {

            }
        }
    }

    public void addNumber(char[] chars) {
        // chars already contains line separator
        try {
            CharBuffer charBuffer = CharBuffer.wrap(chars);
            fileChannel.write(Charset.forName("ascii").encode(charBuffer));
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error starting Server", ex);
        }

    }
}
