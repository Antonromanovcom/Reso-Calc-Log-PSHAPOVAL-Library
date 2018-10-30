package ru.reso.resocalc.Utils.Factories.ConcreteFactories;

import org.apache.commons.lang3.reflect.FieldUtils;
import ru.reso.resocalc.Entity.*;
import ru.reso.resocalc.Service.DBConnection;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;

import javax.sql.rowset.WebRowSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BonusFactory implements EntitiesUtils {


    @Override
    public CalcEntity getEntityByCalcId(long calcid) {
        WsPremium bonuses = null;

        WebRowSet rs = DAOUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_BONUSES_BY_ID, calcid);
        bonuses = webRowSet2Entity(rs);

        return bonuses;
    }

    @Override
    public WsPremium webRowSet2Entity(WebRowSet rs) {

        Integer step = 0;
        WsPremium bonuses = new WsPremium();

        if (rs == null) {
            return bonuses;
        }

        try {
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()) {

                WsBonusUnit bonusUnit = new WsBonusUnit();

                for (int i = 1; i <= count; i++) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String fieldName = rsmd.getColumnName(i);

                    if (DAOUtils.searchInClassFields(new WsBonusUnit(), fieldName)) { // если нашли поле в классе, то...
                        Integer fieldLocalType = DAOUtils.getLocalFieldType(new WsBonusUnit(), fieldName); // определяем тип поля

                        switch (fieldLocalType) { // в зависимости от определенного локального типа пишем в класс через рефлексию ...
                            case Types.DOUBLE:
                                FieldUtils.writeField(bonusUnit, DAOUtils.getRealFieldName(new WsBonusUnit(), fieldName), rs.getDouble(i), true);
                                break;

                            case Types.BIGINT:
                                FieldUtils.writeField(bonusUnit, DAOUtils.getRealFieldName(new WsBonusUnit(), fieldName), rs.getLong(i), true);
                                break;

                            case Types.INTEGER:
                                FieldUtils.writeField(bonusUnit, DAOUtils.getRealFieldName(new WsBonusUnit(), fieldName), rs.getInt(i), true);
                                break;

                            default:
                                FieldUtils.writeField(bonusUnit, DAOUtils.getRealFieldName(new WsBonusUnit(), fieldName), null, true);
                                break;
                        }
                    }

                }


                bonuses.getBonusList().add(bonusUnit);
                bonuses.addToHash(String.valueOf(rs.getInt("PREMIUMTYPE")), String.valueOf(rs.getDouble("DEDUCTIBLE_SUM")) + "-" + String.valueOf(rs.getDouble("PREMIUM_SUM")));

            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to convert webRowSet to Entity", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", ex);
            }
        }


        for (String name : bonuses.getHash().keySet()) {

            String key = name;
            String value = bonuses.getHash().get(name);
  //          Logger.getLogger("").log(Level.SEVERE, key + " -  " + value, "Инфо");
        }

   //     Logger.getLogger("").log(Level.SEVERE, "А теперь распечатаем получившийся Лист", "Инфо");

        for (WsBonusUnit temp : bonuses.getBonusList()) {
     //       Logger.getLogger("").log(Level.SEVERE, temp.getCalcid() + " -  " + temp.getDeductible_sum() + " - " + temp.getPremiumtype() + " - " + temp.getPremium_sum(), "Инфо");
        }


        return bonuses;


    }

    @Override
    public Integer getReordsCount(String sql, long calcid) {
        DAOUtils daoUtils = new DAOUtils();
        return daoUtils.getReordsCountByIdAndSQL(sql, calcid);
    }

    @Override
    public CalcEntity checkNonMatching(long calcid1st, long calcid2d) {

        Integer result = null;
        WsPremium additionalBonuses = null;

        try {
            String sql = sqlLogging.SQL_GET_NON_MATCHING_PREMIUMS;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid1st));
            paramList.add(new StmtParam(Types.BIGINT, calcid2d));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);
            Integer iterate = rs.size();

            if (iterate>0) {

               /* if (rs == null) {
                    return null;
                } else {

                    if (rs.next()) {
                        result = rs.getInt("COUNT");
                    }

                } */

                additionalBonuses = webRowSet2Entity(rs);

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return additionalBonuses;


    }

    public String getBonusDescription(int id) {

        String result = null;

        try {
            String sql = sqlLogging.SQL_GET_PREMIUM_DESC_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.INTEGER, id));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                return null;
            } else {
                if (rs.next()) {
                    result = rs.getString("DESCR");
                }

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return result;

    }
}
