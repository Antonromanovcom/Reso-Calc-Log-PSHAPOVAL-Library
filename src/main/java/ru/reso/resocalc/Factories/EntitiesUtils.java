package ru.reso.resocalc.Factories;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import javax.sql.rowset.WebRowSet;


/**
 * Интерфейс для всех фабрик
 */

public interface EntitiesUtils {

    CalcEntity getEntityByCalcId(long calcid); //Вернуть заполненный Энтити по calcid

    CalcEntity webRowSet2Entity(WebRowSet rs); //Преобразовать полученный из БД РоуСет в Энтити

    Integer getReordsCount(String sql, long calcid); //Количесьво записей в РоуСете

    CalcEntity checkNonMatching(long calcid1st, long calcid2d); //проверить совпадение кол-ва записей/полей по calcid 1 относительно  calcid 2
}
