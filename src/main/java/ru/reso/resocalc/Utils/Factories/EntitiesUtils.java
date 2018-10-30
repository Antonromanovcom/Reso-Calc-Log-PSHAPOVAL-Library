package ru.reso.resocalc.Utils.Factories;

import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.WsCoeff;

import javax.sql.rowset.WebRowSet;

public interface EntitiesUtils {

    CalcEntity getEntityByCalcId(long calcid);

    CalcEntity webRowSet2Entity(WebRowSet rs);

    Integer getReordsCount(String sql, long calcid);

    CalcEntity checkNonMatching(long calcid1st, long calcid2d);
}
