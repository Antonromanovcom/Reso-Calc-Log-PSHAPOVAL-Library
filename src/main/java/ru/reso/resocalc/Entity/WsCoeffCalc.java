package ru.reso.resocalc.Entity;


import ru.reso.resocalc.Utils.WsLogDictonary;

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

    public WsCoeffCalc(long calcid) {
        this.calcid = calcid;
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
    
    public void WsCoeffCalc() {
        
    }
    /**
     * Конструктор 
     * @param calcid идентификатор расчета
     * @param coeff коэффициент
     * @param value значение
     */
    public void WsCoeffCalc(long calcid, WsLogDictonary.CalcCoeff coeff, double value ) {
        this.calcid = calcid;
        this.coefid = coeff.getIndex();
        this.value = value;
    }
}
