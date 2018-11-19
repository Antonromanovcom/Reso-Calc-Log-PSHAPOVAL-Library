package ru.reso.resocalc.Factories.ConcreteFactories;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.WsCoeff;
import ru.reso.resocalc.Service.DBConnection;
import ru.reso.resocalc.Service.DBConnectionLocal;
import ru.reso.resocalc.Utils.DAOUtils;
import ru.reso.resocalc.Factories.EntitiesUtils;
import ru.reso.resocalc.Utils.sqlLogging;
import ru.reso.wp.srv.ResoObject;
import ru.reso.wp.srv.consts.ResoSrvTypeConsts;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;

import javax.sql.rowset.WebRowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.reso.resocalc.Utils.DAOUtils.parseWebRowSet2;

public class WsCoeffCalcFactory extends ResoObject implements EntitiesUtils {
    @Override
    public CalcEntity getEntityByCalcId(long calcid) {

        WsCoeff wsCoeff = null;
        DAOUtils daoUtils = new DAOUtils();
        WebRowSet rs = daoUtils.getWebRowSetByCalcId(sqlLogging.SQL_GET_CALC_COEFF_BY_ID, calcid);
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
        Connection conn = null;
        ResultSet rs = null;
        WebRowSet wrs = null;

        conn = resobj_ResoDBConnection.getConnectionInit(ResoSrvTypeConsts.TDataBase.PRIMARY);

        try {
            String sql = sqlLogging.SQL_GET_COEFF_DESC_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.INTEGER, coefid));
           // conn = resobj_ResoDBConnection.getConnectionOsago();
            rs = ResoDatabaseInvoke.prepareStatementExecuteQuery(conn, sql, paramList);
            String rsStr = ResoDatabaseInvoke.encodeWebRowSet(rs);
            wrs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (wrs == null) {
                return null;
            } else {
                if (wrs.next()) {
                    result = wrs.getString("DESCR");
                }

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        finally {
            try {
                if (wrs != null)
                    wrs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;


    }
}
