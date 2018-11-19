package ru.reso.resocalc.Service;


import ru.reso.wp.srv.ResoObject;
import ru.reso.wp.srv.consts.ResoSrvTypeConsts;
import ru.reso.wp.srv.db.InteractionInterceptor;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;
import ru.reso.wp.srv.db.models.StmtParamList;

import javax.interceptor.Interceptors;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionLocal  extends ResoObject {

    Connection conn = null;

    public DBConnectionLocal() {

        super(ResoSrvTypeConsts.TWorkMode.PRODUCTION, ResoSrvTypeConsts.TDataBase.OSAGO);
        conn = resobj_ResoDBConnection.getConnectionInit(ResoSrvTypeConsts.TDataBase.PRIMARY);

    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


    public void close() throws SQLException {
        this.conn.close();
    }


    public String prepareStatementExecuteQuery(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        String result = "";
        ResultSet rs = null;
        //Connection conn = this.getResobj_ResoDBConnection().getConnectionPrimary();
        //Connection connLocal = this.getResobj_ResoDBConnection().getConnectionPrimary();

        Logger.getGlobal().log(Level.WARNING, "SQL = " +  aSQL, "SQL");

        try {
            rs = ResoDatabaseInvoke.prepareStatementExecuteQuery(conn, aSQL, aStmtParamList);
            Logger.getGlobal().log(Level.WARNING, "prepareStatementExecuteQuery - OK" , "ITS OK");
            result = ResoDatabaseInvoke.encodeWebRowSet(rs);
            Logger.getGlobal().log(Level.WARNING, "encodeWebRowSet - OK" , "ITS OK");
        } catch (Exception ex) {
            Logger.getGlobal().log(Level.WARNING, ex.toString() ,ex);
        }
        finally {
            try {
                if (rs != null)
                    rs.close();
            }
            finally {
            if (!ResoDatabaseInvoke.isClosedConn(conn)) {
                conn.close();
                Logger.getGlobal().log(Level.WARNING, "Закрыли конекшн", "ITS OK");
            }
            }
        }

        return result;
    }


}
