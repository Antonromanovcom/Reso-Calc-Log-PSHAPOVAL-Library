/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Service;


import ru.reso.wp.srv.ResoRemoteObject;
import ru.reso.wp.srv.db.models.StmtParamList;
import java.sql.SQLException;


/**
 * Класс, инкапсулирующий работу с БД через РесоСерверИнтеракт. Тут без комментариев. Как бэ все понятно.
 * @author SHAPPN
 */
public class DBConnection extends ResoRemoteObject {
    private  String currentDB = "OSAGO"; // БД с которой работаем.

    /**
     * Вызов хранимых процедур   
     * @param aSQL запрос SQL
     * @param aStmtParamList - список параметров
     * @return возвращает список выходных параметров
     * @throws SQLException
     */
    public StmtParamList callableStatementExecute(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return getResobj_EjbDatabaseInteraction().callableStatementExecute(aSQL, aStmtParamList);

    }

    /**
     *
     * @param aSQL запрос
     * @param aStmtParamList
     * @return возвращает список выходных параметров закодированных в строку
     * @throws SQLException
     */
    public String callableStatementExecuteQuery(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return getResobj_EjbDatabaseInteraction().callableStatementExecuteQuery(aSQL, aStmtParamList);
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
        return getResobj_EjbDatabaseInteraction().prepareStatementExecute(aSQL, aStmtParamList);
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
        return getResobj_EjbDatabaseInteraction().prepareStatementExecuteQuery(aSQL, aStmtParamList);
        //return getResobj_EjbDatabaseInteraction().prepareStatementExecuteQuery(currentDB, aSQL, aStmtParamList);
    }

    public Integer prepareStatementExecuteUpdate(String aSQL, StmtParamList aStmtParamList) throws SQLException {
        return getResobj_EjbDatabaseInteraction().prepareStatementExecuteUpdate(aSQL, aStmtParamList);
    }

    public String prepareStatementExecuteQueryWOParametrs(String aSQL) throws SQLException {
        return getResobj_EjbDatabaseInteraction().prepareStatementExecuteQueryWOParametrs(aSQL);
    }



}
