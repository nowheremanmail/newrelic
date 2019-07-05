package com.newrelic.codingchallenge.server;

import java.net.DatagramPacket;
import java.util.logging.Logger;

public class UDPWorker implements Runnable {
    private static Logger logger = Logger.getLogger("Worker");

    private DatagramPacket data;
    private Callback callback;

    public UDPWorker(DatagramPacket data, Callback callback) {
        this.data = data;
        this.callback = callback;
    }

    @Override
    public void run() {
        int n = data.getLength();
        byte[] source = data.getData();
        char[] buffer = new char[n];

        for (int i = 0; i < n; i++) buffer[i] = (char) source[i];
        callback.addNumber(buffer, data.getLength());
    }
}
