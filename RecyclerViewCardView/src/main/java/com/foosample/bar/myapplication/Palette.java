package com.foosample.bar.myapplication;

/**
 * Created by obaro on 15/03/2015.
 */
public class Palette {
    private String name;
    private String hexValue;
    private int intValue;

    public Palette(String name, String hexValue, int intValue) {
        this.name = name;
        this.hexValue = hexValue;
        this.intValue = intValue;
    }

    public String getName() {
        return name;
    }

    public String getHexValue() {
        return hexValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
