/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Entity;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.SubEntities.PartnerUnit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ROMAB
 *
 * Логгирование Партнеров
 *
 */

public class WsPartner  implements Serializable, CalcEntity {


    private ArrayList<PartnerUnit> partnerList = new ArrayList<>();
    private LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения

    public WsPartner() { }

    @Override
    public void addToHash(String key, String value) {
        this.hash.put(key, value);
    }

    @Override
    public Map<String, String> getHash() {
        return hash;
    }

    public ArrayList<PartnerUnit> getPartnerList() {
        return partnerList;
    }

    public void setDriversList(ArrayList<PartnerUnit> driversList) {
        this.partnerList = driversList;
    }
}
