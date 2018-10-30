package ru.reso.resocalc.Utils.Factories.ConcreteFactories;


import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.DriverUnit;
import ru.reso.resocalc.Entity.WsDriver;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;

import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.reso.resocalc.Utils.DAOUtils.dateToString;
import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet;

public class DriverFactory implements EntitiesUtils {

    @Override
    public CalcEntity getEntityByCalcId(long calcid) {

        WsDriver drivers = null;

        WebRowSet rs = DAOUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_DRIVERS, calcid);
        drivers = webRowSet2Entity(rs);

        return drivers;

    }

    @Override
    public WsDriver webRowSet2Entity(WebRowSet rs) {

        Integer temporary = 1;
        WsDriver drivers = new WsDriver();

        if (rs == null) {
            return drivers;
        }

        try {
            int count = rs.getMetaData().getColumnCount();

            Logger.getLogger("").log(Level.SEVERE, "Количество записей - " + rs.size(), "Count");
            while (rs.next()) {

                DriverUnit driverUnit = new DriverUnit();
                parseWebRowSet(count, rs, new DriverUnit(), driverUnit);
                drivers.getDriversList().add(driverUnit);
                drivers.addToHash(this.rowsToKeyString(temporary, rs), this.rowsToString(rs));
                temporary += 1;

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


        for (String name : drivers.getHash().keySet()) {

            String key = name;
            String value = drivers.getHash().get(name);
            Logger.getLogger("").log(Level.SEVERE, key + " ->  " + value, "Инфо");
        }
/*
        Logger.getLogger("").log(Level.SEVERE, "А теперь распечатаем получившийся Лист", "Инфо");

        for (WsBonusUnit temp : drivers.getBonusList()) {
            Logger.getLogger("").log(Level.SEVERE, temp.getCalcid() + " -  " + temp.getDeductible_sum() + " - " + temp.getPremiumtype() + " - " + temp.getPremium_sum(), "Инфо");
        }

*/
        return drivers;
    }

    @Override
    public Integer getReordsCount(String sql, long calcid) {
        return null;
    }

    @Override
    public CalcEntity checkNonMatching(long calcid1st, long calcid2d) {
        return null;
    }


    private String rowsToString(WebRowSet rs) throws SQLException {

        String result = String.valueOf(rs.getInt("KBMCLASSDRIVEROSAGO"))
                + "-" + String.valueOf(rs.getInt("KVS"))
                + "-" + String.valueOf(rs.getInt("KVSOSAGO"))
                + "-" + String.valueOf(rs.getInt("STAGE"))
                + "-" + String.valueOf(rs.getInt("LICENCEDATE"))
                + "-" + String.valueOf(rs.getInt("LICENCESERIA"))
                + "-" + String.valueOf(rs.getInt("LICENCENUMBER"))
                + "-" + String.valueOf(rs.getInt("KBMCOEFFDRIVEROSAGO"))
                + "-" + String.valueOf(rs.getInt("KBMCOEFFDRIVERCASCO"))
                + "-" + String.valueOf(rs.getInt("RELATE_TO_RISK"))
                + "-" + String.valueOf(rs.getInt("KBMIDREQUESTRSADRIVEROSAGO"));

        return result;
    }

    private String rowsToKeyString(Integer id, WebRowSet rs) throws SQLException {

        String fio = "";
        if ((rs.getString("FIO")) == null) {
            fio = "н/д";
        } else {
            fio = rs.getString("FIO");
            //fio = "д/д";
        }

        String result = String.valueOf(id)
                + "-" + fio
                + "-" + dateToString(rs.getDate("BIRTHDATE"))
                + "-" + String.valueOf(rs.getInt("AGE"))
                + "-" + rs.getString("SEX")
                + "-" + String.valueOf(rs.getInt("LICENCEDATE"))
                + "-" + String.valueOf(rs.getInt("LICENCESERIA"))
                + "-" + String.valueOf(rs.getInt("LICENCENUMBER"))
                + "-" + String.valueOf(rs.getInt("KBMCOEFFDRIVEROSAGO"))
                + "-" + String.valueOf(rs.getInt("KBMCOEFFDRIVERCASCO"))
                + "-" + String.valueOf(rs.getInt("RELATE_TO_RISK"))
                + "-" + String.valueOf(rs.getInt("KBMIDREQUESTRSADRIVEROSAGO"));


        return result;
    }


}
