package com.demo.tools;

import java.text.DateFormat;
import java.util.Date;

public class CurrentlyTime {
    public static String currentlyTime() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        return dateFormat.format(date);
    }
}
