package ru.reso.resocalc.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author ROMAB
 */
public class WsCoeff implements Serializable, CalcEntity {

    /**ИД расчета     */
    private long calcid;
    private String test;
    private ArrayList<WsCoeffCalc>coeffCalcList = new ArrayList<>();


    /*Значение коэффициента*/
    private double value;
    private LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения


    public WsCoeff() {
    }

    public long getCalcid() {
        return calcid;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    public String getTest() {
        return test;
    }

    @Override
    public void addToHash(String key, String value) {
        this.hash.put(key, value);
    }

    public void setTest(String test) {
        this.test = test;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LinkedHashMap<String, String> getHash() {
        return hash;
    }

    public ArrayList<WsCoeffCalc> getCoeffCalcList() {
        return coeffCalcList;
    }

    public void setCoeffCalcList(ArrayList<WsCoeffCalc> coeffCalcList) {
        this.coeffCalcList = coeffCalcList;
    }
}
