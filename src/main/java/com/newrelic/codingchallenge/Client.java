package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.others.Utils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.logging.Logger;

public class Client {
    private static Logger logger = Logger.getLogger("Client");

    private String hostName = "localhost";
    private int port = 4000;
    private boolean isStopped = false;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void run() {

        Utils utils = new Utils();
        Random rnd = new Random();

        int n = 0;
        while (!isStopped) {
            // just to see that is doing something
            if (++n % 100 == 0)
                System.out.println(".");
            else
                System.out.print(".");
            //
            try (Socket clientSocket = new Socket(hostName, port)) {
                try (OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.US_ASCII)) {
                    out.write(utils.convert(rnd.nextInt(100_000_000)) + System.lineSeparator());
                    out.flush();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    // In case of error, wait 1 second before continue
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
/*            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }


}
