package com.newrelic.codingchallenge.server;

import com.newrelic.codingchallenge.others.Constants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements Runnable {
    private static Logger logger = Logger.getLogger("Worker");

    private Socket clientSocket;
    private Callback callback;

    public Worker(Socket clientSocket, Callback callback) {
        this.clientSocket = clientSocket;
        this.callback = callback;
    }

    @Override
    public void run() {
        //
        char[] buffer = new char[Constants.DATA_SIZE + Constants.LINE_SEPARATOR_LENGTH];
        //
        try (InputStreamReader input = new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.US_ASCII)) {
            int readedBytes = input.read(buffer);

            callback.addNumber(buffer, readedBytes);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error starting server", ex);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {

            }
        }
    }
}
