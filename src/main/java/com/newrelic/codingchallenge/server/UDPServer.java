package com.newrelic.codingchallenge.server;

import com.newrelic.codingchallenge.data.LogService;
import com.newrelic.codingchallenge.data.Repository;
import com.newrelic.codingchallenge.others.Constants;
import com.newrelic.codingchallenge.others.Utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that implements Http server
 *
 * @author david
 */
public class UDPServer implements Callback {
    private static Logger logger = Logger.getLogger("Server");

    static final long TEN_MINUTES = 10 * 1000 * 60l;
    private static final String LOG_FILE_NAME = "numbers.log";

    private int port = 4000, nThreads = 5;
    private String hostName;

    private AtomicBoolean isStopped;
    private ExecutorService threadPool;
    private Repository repository;
    private Utils utils;
    private LogService logService;

    public UDPServer(String hostName, int port, int nThreads) {
        this.hostName = hostName;
        this.port = port;
        this.nThreads = nThreads;
    }

    public void run() {
        logger.log(Level.INFO, "Starting Server");

        isStopped = new AtomicBoolean(false);
        threadPool = Executors.newFixedThreadPool(nThreads);
        repository = new Repository();
        utils = new Utils();
        logService = new LogService(LOG_FILE_NAME);
        //
        // create task to show statistics every 10 minutes
        Timer timer = new Timer();
        timer.schedule(new ReporTask(repository), TEN_MINUTES, TEN_MINUTES);

        //
        try (DatagramSocket  serverSocket = new DatagramSocket(port)) {
            logService.start();

            logger.log(Level.INFO, "Server Started");

            while (!isStopped.get()) {
                DatagramPacket packet = new DatagramPacket(new byte[Constants.DATA_SIZE+Constants.LINE_SEPARATOR_LENGTH], Constants.DATA_SIZE+Constants.LINE_SEPARATOR_LENGTH);
                serverSocket.receive(packet);
                threadPool.execute(new UDPWorker(packet, this));
            }

            logger.log(Level.INFO, "Waiting to stop processes");
            threadPool.shutdown();

            logService.end();

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error starting server", ex);
        }

        logger.log(Level.INFO, "Server Stopped");
    }

    @Override
    public void terminate() {
        isStopped.set(true);
    }

    @Override
    public void addNumber(char[] buffer, int readedChars) {
        if (utils.isValidData(buffer, readedChars)) {
            if (utils.isTerminate(buffer)) {
                terminate();
            } else if (utils.isNumber(buffer)) {
                boolean added = repository.addNumber(buffer);
                if (added) {
                    // new number log to file
                    logService.addNumber(buffer);
                } else {
                    // number already exists
                }
            }
        } else {
            // invalid data ignore
        }
    }
}