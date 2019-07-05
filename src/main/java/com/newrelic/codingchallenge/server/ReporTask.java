package com.newrelic.codingchallenge.server;

import com.newrelic.codingchallenge.data.Repository;

import java.util.TimerTask;
import java.util.logging.Logger;

public class ReporTask extends TimerTask {
    private static Logger logger = Logger.getLogger("Report");

    private Repository repository;

    private int lastUniques = 0, lastDuplicates = 0, lastTotalUniques = 0;

    public ReporTask(Repository repository) {
        this.repository = repository;
    }


    @Override
    public void run() {
        int currentUniques = repository.getUniques(), currentDuplicates = repository.getDuplicates(), currentTotalUniques = repository.getNumbers();

        logger.info(String.format("Received %d unique numbers, %d duplicates. Unique total: %d", currentUniques - lastUniques, currentDuplicates - lastDuplicates, currentTotalUniques));

        lastUniques = currentUniques;
        lastDuplicates = currentDuplicates;
        lastTotalUniques = currentTotalUniques;
    }
}
