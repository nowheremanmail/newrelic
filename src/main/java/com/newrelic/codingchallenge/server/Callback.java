package com.newrelic.codingchallenge.server;

public interface Callback {
    void terminate ();

    void addNumber(char[] buffer, int readedChars);
}
