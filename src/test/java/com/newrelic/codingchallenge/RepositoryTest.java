package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.data.Repository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepositoryTest {


    @Test
    public void testEmpty() {
        Repository repository = new Repository();

        assertEquals(repository.getNumbers(), 0);
        assertEquals(repository.getUniques(), 0);
    }


    @Test
    public void testFirst() {
        Repository repository = new Repository();

        // is new
        assertEquals(repository.addNumber("000000000".toCharArray()), true);

        // there are only 1
        assertEquals(repository.getNumbers(), 1);

        // and is unique
        assertEquals(repository.getUniques(), 1);

        // no duplicates
        assertEquals(repository.getDuplicates(), 0);
    }


    @Test
    public void testTwoDifferent() {
        Repository repository = new Repository();

        assertEquals(repository.addNumber("000000000".toCharArray()), true);
        assertEquals(repository.addNumber("000010000".toCharArray()), true);

        // received 2
        assertEquals(repository.getNumbers(), 2);

        // and both uniques
        assertEquals(repository.getUniques(), 2);

        // no duplicates
        assertEquals(repository.getDuplicates(), 0);
    }


    @Test
    public void testTwoDuplicate() {
        Repository repository = new Repository();

        assertEquals(repository.addNumber("000000000".toCharArray()), true);
        assertEquals(repository.addNumber("000000000".toCharArray()), false);

        // received 2
        assertEquals(repository.getNumbers(), 2);

        // but only 1
        assertEquals(repository.getUniques(), 1);

        // no duplicates
        assertEquals(repository.getDuplicates(), 1);
    }

}
