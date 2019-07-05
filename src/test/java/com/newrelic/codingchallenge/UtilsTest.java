package com.newrelic.codingchallenge;

import com.newrelic.codingchallenge.others.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    Utils utils;

    @Before
    public void init() {
        utils = new Utils();
    }

    @Test
    public void testZero() {

        assertEquals(utils.convert(0), "000000000");
    }

    @Test
    public void testNumber() {

        assertEquals(utils.convert(1_231_230), "001231230");
    }

    @Test
    public void testFull() {

        assertEquals(utils.convert(999_999_999), "999999999");
    }


    @Test
    public void testIsNumber() {
        assertTrue(utils.isNumber("123456789".toCharArray()));
    }

    @Test
    public void testIsTerminate() {
        assertTrue(utils.isNumber("terminate".toCharArray()));
    }

    @Test
    public void testIsValidTerminate() {
        assertTrue(utils.isValidData("terminate".toCharArray(), "terminate".length()));
    }

    @Test
    public void testIsValidData() {
        assertTrue(utils.isValidData("123456789".toCharArray(), "123456789".length()));
    }

    @Test
    public void testIsInvalidData1() {
        assertTrue(utils.isValidData("1234567".toCharArray(), "1234567".length()));
    }

    @Test
    public void testIsInvalidData2() {
        assertTrue(utils.isValidData("0123456789".toCharArray(), "0123456789".length()));
    }

}
