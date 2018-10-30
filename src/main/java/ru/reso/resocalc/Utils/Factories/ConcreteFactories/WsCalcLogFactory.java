package ru.reso.resocalc.Utils.Factories.ConcreteFactories;

import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Service.DBConnection;
import ru.reso.resocalc.Utils.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;

import javax.sql.rowset.WebRowSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WsCalcLogFactory implements EntitiesUtils {
    @Override
    public CalcEntity getEntityByCalcId(long calcid) {
        return null;
    }

    @Override
    public CalcEntity webRowSet2Entity(WebRowSet rs) {
        return null;
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
            String sql = sqlLogging.SQL_GET_WS_CALC_LOG_DESCRIPTION;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.VARCHAR, columnName));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                Logger.getLogger("").log(Level.SEVERE, "rs для поиска Дескрипшена НОЛЬ", "111");
                return null;
            } else {
                if (rs.next()) {
                    result = rs.getString("COMMENTS");
                    Logger.getLogger("").log(Level.SEVERE, "Нашли Дескрипшен - " + result, "111");
                }

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return result;

    }



}
