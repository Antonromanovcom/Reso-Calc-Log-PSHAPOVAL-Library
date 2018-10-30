/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utils.WsLogDictonary;
import java.io.Serializable;

/**
 *
 * @author SHAPPN
 */
public class WsPremium implements Serializable  {
    /**ИД расчета*/
    private long calcid;
    /**Размер франщизы */
    private double deductible_sum;
    /**ИД премии  */
    private int premiumtype;
    /**Сумма премии */
    private double premium_sum;

    public WsPremium(long calcid) {
        this.calcid = calcid;
    }
    
    public WsPremium() {

    }

    public WsPremium(Long calcID, double deductible_sum, WsLogDictonary.CalcPremium type, double premium) {
        this.calcid = calcID;
        this.deductible_sum= deductible_sum;
        this.premiumtype = type.getIndex();
        this.premium_sum = premium;
    }

    public long getCalcid() {
        return calcid;
    }

    public double getDeductible_sum() {
        return deductible_sum;
    }

    public int getPremiumtype() {
        return premiumtype;
    }

    public double getPremium_sum() {
        return premium_sum;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    public void setDeductible_sum(double deductible_sum) {
        this.deductible_sum = deductible_sum;
    }

    public void setPremiumtype(int premiumtype) {
        this.premiumtype = premiumtype;
    }

    public void setPremium_sum(double premium_sum) {
        this.premium_sum = premium_sum;
    }
    
}
