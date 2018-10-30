package ru.reso.resocalc.Factories.ConcreteFactories;

import ru.reso.resocalc.Entity.*;
import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Service.DBConnection;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;
import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;
import java.sql.Types;
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
        WsCommonLogs wsCommonLogs = new WsCommonLogs();

        if (rs == null) {
            return wsCommonLogs;
        }

        try {
            int count = rs.getMetaData().getColumnCount();
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


    public String getDescription4Column(String columnName) {

        String result = null;

        try {
            String sql = sqlLogging.SQL_GET_WS_COMMON_LOGS_DESCRIPTION;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.VARCHAR, columnName));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                return null;
            } else {
                if (rs.next()) {
                    result = rs.getString("COMMENTS");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return result;

    }
}
