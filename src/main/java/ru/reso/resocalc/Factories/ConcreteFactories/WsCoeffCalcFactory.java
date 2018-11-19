package ru.reso.resocalc.Factories.ConcreteFactories;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.WsCoeff;
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

public class WsCoeffCalcFactory implements EntitiesUtils {
    @Override
    public CalcEntity getEntityByCalcId(long calcid) {

        WsCoeff wsCoeff = null;
        WebRowSet rs = DAOUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_CALC_COEFF_BY_ID, calcid);
        wsCoeff = webRowSet2Entity(rs);

        return wsCoeff;
    }

    @Override
    public WsCoeff webRowSet2Entity(WebRowSet rs) {

        WsCoeff wsCoeff = new WsCoeff();

        if (rs == null) {
            return wsCoeff;
        }

        try {
            int count = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                parseWebRowSet2(count, rs, new WsCoeff(), wsCoeff);
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

        return wsCoeff;
    }

    @Override
    public Integer getReordsCount(String sql, long calcid) {
        DAOUtils daoUtils = new DAOUtils();
        return daoUtils.getReordsCountByIdAndSQL(sql, calcid);
    }

    @Override
    public CalcEntity checkNonMatching(long calcid1st, long calcid2d) {
        return null;
    }

    public String getCoeffDescription(int coefid) {

        String result = null;

        try {
            String sql = sqlLogging.SQL_GET_COEFF_DESC_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.INTEGER, coefid));
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
