package ru.reso.resocalc.Entity;

import java.io.Serializable;

/**
 *
 * @author SHAPPN
 */
public class WsCoeffCalc implements Serializable {
    /**ИД расчета     */
    private long calcid;
    /**Ид коэффициента    */
    private int coefid;
    /**Значение коэффициента*/
    private double value;

    private String test;

    public WsCoeffCalc(long calcid) {
        this.calcid = calcid;
    }

    public WsCoeffCalc() {

    }

    public long getCalcid() {
        return calcid;
    }

    public int getCoefid() {
        return coefid;
    }

    public double getValue() {
        return value;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    public void setCoefid(int coefid) {
        this.coefid = coefid;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
