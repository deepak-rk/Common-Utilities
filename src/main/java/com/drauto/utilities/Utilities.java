package com.drauto.utilities;

import java.util.Date;

public class Utilities {
    public static String getTime() {
        Date today = new Date();
        return String.valueOf(today.getTime());
    }
}
