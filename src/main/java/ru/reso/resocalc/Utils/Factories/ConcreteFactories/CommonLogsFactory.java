package ru.reso.resocalc.Utils.Factories.ConcreteFactories;


import ru.reso.resocalc.Entity.*;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet2;
import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet4GettingHash;


public class CommonLogsFactory implements EntitiesUtils {


    @Override
    public CalcEntity getEntityByCalcId(long calcid) {
        WsCommonLogs wsCommonLogs = null;

        WebRowSet rs = DAOUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_COMMON_LOGS, calcid);
        wsCommonLogs = webRowSet2Entity(rs);

        return wsCommonLogs;
    }

    @Override
    public WsCommonLogs webRowSet2Entity(WebRowSet rs) {
        Integer temporary = 1;
        WsCommonLogs wsCommonLogs = new WsCommonLogs();

        if (rs == null) {
            return wsCommonLogs;
        }

        try {
            int count = rs.getMetaData().getColumnCount();

            Logger.getLogger("").log(Level.SEVERE, "Количество записей в КоммонЛогс - " + rs.size(), "Count");
            while (rs.next()) {
                parseWebRowSet2(count, rs, new WsCommonLogs(), wsCommonLogs);
                wsCommonLogs.addToHashAll(parseWebRowSet4GettingHash(count, rs, new WsCommonLogs()));
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

        Logger.getLogger("").log(Level.SEVERE, "ПЕЧАТАЕМ КОММОНН ЛОГ", "КПАЛУМТОМПЛ");
        for (String name : wsCommonLogs.getHash().keySet()) {

            String key = name;
            String value = wsCommonLogs.getHash().get(name);
            Logger.getLogger("").log(Level.SEVERE, key + " ->  " + value, "Инфо");
        }
        return wsCommonLogs;
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
