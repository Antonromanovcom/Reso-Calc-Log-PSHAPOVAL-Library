package ru.reso.resocalc.Service.Factories.ConcreteFactories;

import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.WsCoeff;
import ru.reso.resocalc.Entity.WsCoeffCalc;
import ru.reso.resocalc.Service.DBConnection;
import ru.reso.resocalc.Service.Factories.EntityFactory;
import org.apache.commons.lang3.reflect.FieldUtils;
import ru.reso.resocalc.Utils.DAOUtils;
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

public class WsCoeffCalcFactory extends EntityFactory {


    @Override
    public WsCoeff getEntityByCalcId(long calcid) {
        WsCoeff wsCoeff = null;

        try {
            String sql = sqlLogging.SQL_GET_CALC_COEFF_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);
            wsCoeff = webRowSet2Entity(rs);

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

/*
        return calcLog;
        */

        return wsCoeff;
    }

    @Override
    public WsCoeff webRowSet2Entity(WebRowSet rs) {


        Integer iterate = 0;
        Integer step = 0;
        WsCoeff coeffList = new WsCoeff();


        if (rs == null) {
            return coeffList;
        }

        try {

            int count = rs.getMetaData().getColumnCount();
            iterate = rs.size();


            while (rs.next()) {
                step = step + 1;
                WsCoeffCalc wsCoeff = new WsCoeffCalc();

                for (int i = 1; i <= count; i++) {

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int type = rsmd.getColumnType(i);
                    String fieldName = rsmd.getColumnName(i);

                    if (DAOUtils.searchInClassFields(new WsCoeffCalc(), fieldName)) {


                        Integer fieldLocalType = DAOUtils.getLocalFieldType(new WsCoeffCalc(), fieldName); // Мы нашли и определили тип поля анализируя поля в классе
                        FieldUtils.writeField(wsCoeff, "test", "1451139", true);

                        /** ...тип переменной мы определили ранее. Теперь нам надо по определенному типу произвести соответствующеесчитывание из ResulSet'а
                         *     rs.getТИП(х).
                         */

                        switch (fieldLocalType) {
                            case Types.DOUBLE:
                                FieldUtils.writeField(wsCoeff, DAOUtils.getRealFieldName(new WsCoeffCalc(), fieldName), rs.getDouble(i), true);
                                break;

                            case Types.BIGINT:
                                FieldUtils.writeField(wsCoeff, DAOUtils.getRealFieldName(new WsCoeffCalc(), fieldName), rs.getLong(i), true);
                                break;

                            case Types.INTEGER:
                                FieldUtils.writeField(wsCoeff, DAOUtils.getRealFieldName(new WsCoeffCalc(), fieldName), rs.getInt(i), true);
                                break;

                            default:
                                FieldUtils.writeField(wsCoeff, DAOUtils.getRealFieldName(new WsCoeffCalc(), fieldName), null, true);
                                break;
                        }
                    }
                }

                coeffList.getCoeffCalcList().add(wsCoeff);
                this.addToHash(coeffList, String.valueOf(rs.getInt("COEFID")), String.valueOf(rs.getDouble("VALUE")));
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




        for (String name : coeffList.getHash().keySet()) {

            String key = name;
            String value = coeffList.getHash().get(name);
        }

        for (WsCoeffCalc temp : coeffList.getCoeffCalcList()) {
            System.out.println(temp);
            Logger.getLogger("").log(Level.SEVERE, temp.getCalcid() + " -  " + temp.getCoefid() + " - " + temp.getValue() + " - " + temp.getTest(), "Инфо");
        }


        return coeffList;
    }

    @Override
    public void addToHash(CalcEntity calcEntity, String key, String value) {
        super.addToHash(calcEntity, key, value);
    }

    @Override
    public Integer getReordsCount(long calcid) {
        Integer result = null;

        try {
            String sql = sqlLogging.SQL_GET_CALC_COEFF_COUNT_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                return null;
            } else {
                    //result = rs.size();
                if (rs.next()) {
                    result = rs.getInt("COUNT");
                    Logger.getLogger("").log(Level.SEVERE, "КОЛИЧЕСТВО ЗАПИСЕЙ   ------   " + String.valueOf(result), "COUNT");
                }

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return result;
    }

    public String getCoeffDescription(int coefid){

        String result = null;

        try {
            String sql = sqlLogging.SQL_GET_COEFF_DESC_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.INTEGER, coefid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            Logger.getLogger("").log(Level.SEVERE, "Ща будем доставать дескрипшн - " + String.valueOf(coefid), "ntcn");
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                return null;
            } else {
                //result = rs.size();
                if (rs.next()) {
                    result = rs.getString("DESCR");
                    Logger.getLogger("").log(Level.SEVERE, "DESCRIPTION ------   " + result, "COUNT");
                }

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return result;

    }


}

