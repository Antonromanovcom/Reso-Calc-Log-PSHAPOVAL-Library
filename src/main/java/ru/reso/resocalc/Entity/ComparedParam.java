package ru.reso.resocalc.Entity;

public class ComparedParam {

    private String key;
    private String val1;
    private String val2;



    public ComparedParam(String name, String s, String s1) {
        this.key = name;
        this.val1 = s;
        this.val2 = s1;
    }

    public String getKey() {
        return key;
    }

    public String getVal1() {
        return val1;
    }

    public String getVal2() {
        return val2;
    }
}
