package ru.reso.resocalc.Factories.ConcreteFactories;


import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.SubEntities.DriverUnit;
import ru.reso.resocalc.Entity.WsDriver;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ru.reso.resocalc.Utils.DAOUtils.dateToString;
import static ru.reso.resocalc.Utils.DAOUtils.getJSONfromMap;
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

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("KBMCLASSDRIVEROSAGO", String.valueOf(rs.getInt("KBMCLASSDRIVEROSAGO")));
        map.put("KVS", String.valueOf(rs.getInt("KVS")));
        map.put("KVSOSAGO", String.valueOf(rs.getInt("KVSOSAGO")));
        map.put("STAGE", String.valueOf(rs.getInt("STAGE")));
        map.put("LICENCEDATE", String.valueOf(rs.getInt("LICENCEDATE")));
        map.put("LICENCESERIA", String.valueOf(rs.getInt("LICENCESERIA")));
        map.put("LICENCENUMBER", String.valueOf(rs.getInt("LICENCENUMBER")));
        map.put("KBMCOEFFDRIVEROSAGO", String.valueOf(rs.getInt("KBMCOEFFDRIVEROSAGO")));
        map.put("KBMCOEFFDRIVERCASCO", String.valueOf(rs.getInt("KBMCOEFFDRIVERCASCO")));
        map.put("RELATE_TO_RISK", String.valueOf(rs.getInt("RELATE_TO_RISK")));
        map.put("KBMIDREQUESTRSADRIVEROSAGO", String.valueOf(rs.getInt("KBMIDREQUESTRSADRIVEROSAGO")));

        String result = getJSONfromMap(map);

        return result;
    }

    private String rowsToKeyString(Integer id, WebRowSet rs) throws SQLException {

        String fio = "";
        if ((rs.getString("FIO")) == null) {
            fio = "н/д";
        } else {
            fio = rs.getString("FIO");
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
