package ru.reso.resocalc.Entity;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author ROMAB
 *
 * Логгирование коэфициентов. Основной родительский класс
 *
 *
 */
public class WsCoeff implements Serializable, CalcEntity {

    /**ИД расчета     */
    private long calcid;

    private ArrayList<WsCoeffCalc>coeffCalcList = new ArrayList<>();

    private LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения

    public WsCoeff() {
    }

    public long getCalcid() {
        return calcid;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    @Override
    public void addToHash(String key, String value) {
        this.hash.put(key, value);
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

    public void addToHashAll(LinkedHashMap<String, String> map) {
        this.hash.putAll(map);
    }
}
