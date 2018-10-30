package ru.reso.resocalc.Entity;


/**
 * Класс, который мы пихаем в ХэшМеп, который мы и передаем на клиента.
 */
public class ComparedParam {

    private String key; //ключ. Как правило это название поля
    private String val1; // значение для calcid #1
    private String val2; // значение для calcid #2
    private Boolean compare; // В последней версии сервера мы теперь для некоторых таблиц сравнение делаем на сервере. как и должно быть.

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
