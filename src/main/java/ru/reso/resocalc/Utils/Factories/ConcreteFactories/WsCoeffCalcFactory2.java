package ru.reso.resocalc.Utils.Factories.ConcreteFactories;

import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.WsCoeff;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet2;
import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet4GettingHash;

public class WsCoeffCalcFactory2 implements EntitiesUtils {
    @Override
    public CalcEntity getEntityByCalcId(long calcid) {

        WsCoeff wsCoeff = null;
        WebRowSet rs = DAOUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_CALC_COEFF_BY_ID, calcid);
        wsCoeff = webRowSet2Entity(rs);

        return wsCoeff;
    }

    @Override
    public WsCoeff webRowSet2Entity(WebRowSet rs) {
        Integer temporary = 1;
        WsCoeff wsCoeff = new WsCoeff();

        if (rs == null) {
            return wsCoeff;
        }

        try {
            int count = rs.getMetaData().getColumnCount();

            Logger.getLogger("").log(Level.SEVERE, "Количество записей в WsCoeff - " + rs.size(), "Count");

            while (rs.next()) {
                parseWebRowSet2(count, rs, new WsCoeff(), wsCoeff);
                //wsCoeff.addToHashAll(parseWebRowSet4GettingHash(count, rs, new WsCoeff()));
                //this.addToHash(coeffList, String.valueOf(rs.getInt("COEFID")), String.valueOf(rs.getDouble("VALUE")));
                wsCoeff.getHash().put(String.valueOf(rs.getInt("COEFID")), String.valueOf(rs.getDouble("VALUE")));
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

        Logger.getLogger("").log(Level.SEVERE, "ПЕЧАТАЕМ WsCalcLogsNew2", "КПАЛУМТОМПЛ");

        for (String name : wsCoeff.getHash().keySet()) {
            String key = name;
            String value = wsCoeff.getHash().get(name);
            Logger.getLogger("").log(Level.SEVERE, key + " ->  " + value, "Инфо");
        }

        return wsCoeff;
    }

    @Override
    public Integer getReordsCount(String sql, long calcid) {
        return null;
    }

    @Override
    public CalcEntity checkNonMatching(long calcid1st, long calcid2d) {
        return null;
    }
}
