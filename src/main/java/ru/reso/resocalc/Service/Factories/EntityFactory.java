package ru.reso.resocalc.Service.Factories;

import ru.reso.resocalc.Entity.CalcEntity;

import javax.sql.rowset.WebRowSet;

public abstract class EntityFactory {

    public abstract CalcEntity getEntityByCalcId(long calcid);
    //public abstract CalcEntity webRowSet2Entity(WebRowSet rs);
    public abstract CalcEntity webRowSet2Entity(WebRowSet rs);
    public void addToHash(CalcEntity calcEntity, String key, String value){
        calcEntity.addToHash(key, value);
    }
    public abstract Integer getReordsCount(long calcid);

}
