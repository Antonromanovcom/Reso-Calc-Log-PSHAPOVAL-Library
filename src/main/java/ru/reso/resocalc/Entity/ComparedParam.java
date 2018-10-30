package ru.reso.resocalc.Entity;

public class ComparedParam {

    private String key;
    private String val1;
    private String val2;
    private Boolean compare;



    public ComparedParam(String name, String s, String s1) {
        this.key = name;
        this.val1 = s;
        this.val2 = s1;
    }

    public ComparedParam(String name, String s, String s1, Boolean compare) {
        this.key = name;
        this.val1 = s;
        this.val2 = s1;
        this.compare = compare;
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

    public Boolean getCompare() {
        return compare;
    }

    public void setCompare(Boolean compare) {
        this.compare = compare;
    }
}
