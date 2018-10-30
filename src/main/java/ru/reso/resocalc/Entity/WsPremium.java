/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ROMAB
 */
public class WsPremium implements Serializable, CalcEntity {


    private ArrayList<WsBonusUnit> bonusList = new ArrayList<>();
    private LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения



    @Override
    public String getTest() {
        return null;
    }

    @Override
    public void addToHash(String key, String value) {
        this.hash.put(key, value);
    }

    @Override
    public Map<String, String> getHash() {
        return hash;
    }




    public ArrayList<WsBonusUnit> getBonusList() {
        return bonusList;
    }

    public void setBonusList(ArrayList<WsBonusUnit> bonusList) {
        this.bonusList = bonusList;
    }
}
