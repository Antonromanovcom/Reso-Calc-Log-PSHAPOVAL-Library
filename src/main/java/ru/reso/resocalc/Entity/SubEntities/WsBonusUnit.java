package ru.reso.resocalc.Entity.SubEntities;


/**
 * Подкласс. Тот самый, что будет представлен в ArrayList'е класса WsPremium
 */

public class WsBonusUnit {

    /**ИД расчета*/
    private long calcid;
    /**Размер франщизы */
    private double deductible_sum;
    /**ИД премии  */
    private int premiumtype;
    /**Сумма премии */
    private double premium_sum;


    public long getCalcid() {
        return calcid;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    public double getDeductible_sum() {
        return deductible_sum;
    }

    public void setDeductible_sum(double deductible_sum) {
        this.deductible_sum = deductible_sum;
    }

    public int getPremiumtype() {
        return premiumtype;
    }

    public void setPremiumtype(int premiumtype) {
        this.premiumtype = premiumtype;
    }

    public double getPremium_sum() {
        return premium_sum;
    }

    public void setPremium_sum(double premium_sum) {
        this.premium_sum = premium_sum;
    }
}
