/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;
import ru.reso.wp.srv.ResoRemoteObject;
import ru.reso.wp.srv.db.models.StmtParamList;

/**
 *
 * @author SHAPPN
 */
public class DBConnection extends ResoRemoteObject implements Serializable {

    public DBConnection() throws NamingException, SQLException, ClassNotFoundException {
        super();
    }

    /**
     * Вызов хранимых процедур   
     * @param aSQL запрос SQL
     * @param aStmtParamList - список параметров
     * @return возвращает список выходных параметров
     * @throws SQLException
     */
    public StmtParamList callableStatementExecute(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return super.getResobj_EjbDatabaseInteraction().callableStatementExecute(aSQL, aStmtParamList);
    }

    /**
     *
     * @param aSQL запрос
     * @param aStmtParamList
     * @return возвращает список выходных параметров закодированных в строку
     * @throws SQLException
     */
    public String callableStatementExecuteQuery(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return super.getResobj_EjbDatabaseInteraction().callableStatementExecuteQuery(aSQL, aStmtParamList);
    }

    /**
     * Выполенени Insert
     *
     * @param aSQL Запрос SQL
     * @param aStmtParamList список параметров
     * @return
     * @throws SQLException
     */
    public boolean prepareStatementExecute(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return super.getResobj_EjbDatabaseInteraction().prepareStatementExecute(aSQL, aStmtParamList);
    }

    /**
     * Выполнение Select
     *
     * @param aSQL Запрос SQL
     * @param aStmtParamList список параметров
     * @return
     * @throws SQLException
     */
    public String prepareStatementExecuteQuery(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return super.getResobj_EjbDatabaseInteraction().prepareStatementExecuteQuery(aSQL, aStmtParamList);
    }
}
