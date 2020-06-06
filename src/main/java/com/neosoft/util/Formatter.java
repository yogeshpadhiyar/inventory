package com.neosoft.util;

import java.io.Serializable;

public class Formatter {
    public static int convertToInt(Serializable serializable){
        return Integer.parseInt(String.valueOf(serializable));
    }
}
