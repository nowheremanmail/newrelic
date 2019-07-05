package com.newrelic.codingchallenge.others;

public class Utils {


    public String convert(int number) {
        String str = Integer.toString(number);
        int lenght = str.length();

        return ("000000000" + str).substring(lenght);
    }

    public boolean isTerminate(char[] buffer) {
        for (int i = 0; i < Constants.DATA_SIZE; i++) {
            if (Constants.TERMINATE[i] != buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumber(char[] buffer) {
        for (int i = 0; i < Constants.DATA_SIZE; i++) {
            if (buffer[i] < '0' || buffer[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public boolean hasLineSeparator(char[] buffer) {
        // validate ends with separator
        for (int i = 0; i < Constants.LINE_SEPARATOR_LENGTH; i++) {
            if (buffer[Constants.DATA_SIZE + i] != Constants.LINE_SEPARATOR.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidData(char[] buffer, int readedBytes) {
        if (readedBytes != Constants.DATA_SIZE + Constants.LINE_SEPARATOR_LENGTH) {
            // too short or too long
            return false;
        }
        return true;
    }
}
