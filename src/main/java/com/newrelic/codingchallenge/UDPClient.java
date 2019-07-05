package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.others.Utils;

import java.io.IOException;
import java.net.*;
import java.util.Random;
import java.util.logging.Logger;

public class UDPClient {
    private static Logger logger = Logger.getLogger("Client");

    private String hostName = "localhost";
    private int port = 4000;
    private boolean isStopped = false;

    public UDPClient(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void run() throws SocketException, UnknownHostException {

        Utils utils = new Utils();
        Random rnd = new Random();

        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(hostName);

        int n = 0;
        while (!isStopped) {
            // just to see that is doing something
            if (++n % 100 == 0)
                System.out.println(".");
            else
                System.out.print(".");
            //
            try {
                byte[] message = (utils.convert(rnd.nextInt(100_000_000)) + System.lineSeparator()).getBytes();
                DatagramPacket packet = new DatagramPacket(message, message.length, address, port);

                udpSocket.send(packet);
            } catch (IOException ex) {
                ex.printStackTrace();
                try {
                    // In case of error, wait 1 second before continue
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
