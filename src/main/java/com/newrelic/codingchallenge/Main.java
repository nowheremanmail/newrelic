package com.newrelic.codingchallenge;


import com.newrelic.codingchallenge.server.UDPServer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger("Main");

    public static void main(String[] argv) {
        try {
            String hostName = "localhost";
            int port = 4000;

            if (argv.length < 1 || (argv.length >= 1 && (!argv[0].equalsIgnoreCase("server") && !argv[0].equalsIgnoreCase("client")))) {
                System.err.println("Main server/client <hostName> (optional default localhost) <port> (optional default 4000)\n");

                return;
            }

            String mode = argv[0];

            if (argv.length > 1) {
                hostName = argv[1];
            }
            if (argv.length > 2) {
                port = Integer.parseInt(argv[2]);
            }

            if (mode.equalsIgnoreCase("server")) {
                UDPServer server = new UDPServer(hostName, port, 5);
                server.run();
            }
            else if (mode.equalsIgnoreCase("client")) {
                UDPClient client = new UDPClient(hostName, port);
                client.run();
            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error starting Server", ex);

        }
    }
}