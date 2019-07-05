package com.newrelic.codingchallenge.data;

import com.newrelic.codingchallenge.others.Constants;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository {
    //
    // we will keep a hash with all uniques values, keeping key == values could save some data?
    ConcurrentHashMap<String, String> numbers = new ConcurrentHashMap<String, String>();
    //
    // total numbers received
    AtomicInteger totalNumbers = new AtomicInteger(0);
    //
    // total unique numbers detected
    AtomicInteger totalUniques = new AtomicInteger(0);

    // total duplicated numbers detected
    AtomicInteger totalDuplicates = new AtomicInteger(0);

    /**
     * @return Add number to list return true or false if this number is new or no
     */
    public boolean addNumber(char[] numberDigits) {
        String number = new String(numberDigits, 0, Constants.DATA_SIZE);
        String existing = numbers.put(number, number);
        totalNumbers.incrementAndGet();
        if (existing == null) {
            totalUniques.incrementAndGet();
            return true;
        } else {
            totalDuplicates.incrementAndGet();
            return false;
        }
    }

    /**
     * @return number of uniques entries processed
     */
    public int getUniques() {
        return totalUniques.get();
    }

    /**
     * @return number of total entries processed
     */
    public int getNumbers() {
        return totalNumbers.get();
    }

    /**
     * @return total duplicated detected
     */
    public int getDuplicates() {
        return totalDuplicates.get();
    }
}
