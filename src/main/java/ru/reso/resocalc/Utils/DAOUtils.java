package ru.reso.resocalc.Utils;


import org.apache.commons.lang3.reflect.FieldUtils;
import ru.reso.resocalc.Entity.CalcEntity;
import ru.reso.resocalc.Entity.MyStmtParamList;
import ru.reso.resocalc.Entity.Unit;
import ru.reso.resocalc.Entity.WsBonusUnit;
import ru.reso.resocalc.Service.DBConnection;
import ru.reso.resocalc.Service.FileLog;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;
import javax.sql.rowset.WebRowSet;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOUtils {

    public static String getClassName(Object cls) {
        String res = "";
        res = cls.getClass().getName();
        return res;
    }

    /*public static Boolean setValueInEntity(Object anyClass, String name) {
    }*/

    public static Boolean searchInClassFields(Object anyClass, String name) {

        Boolean result = false;

        String className = getClassName(anyClass); //Вытаскиваем имя класса
        try {
            Class<?> clazz = Class.forName(className); //Берем сам класс
            for (Field field : clazz.getDeclaredFields()) { //Бегаем по полям класса
                if (name != null && (field.getName()) != null) { // Проверяем, что у нас не ноль и переданное в параметре метода имя и текунщее пробегаемое в цикле поле класса

                    /**
                     * Тут момент спорный. В методе searchAndGet, который основан на этом, мы используем equals, потому что некоторые поля в классах совпадают и
                     * отличаются одной буквой. И возможно тут надо будет переделать тоже на equals.
                     */
                    if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static StmtParamList paramListGenerator2(String sql, Object cls, Long calcid) {


        FileLog fileLog = new FileLog();
        Long baseCalcId = 122865290L;
        StmtParamList paramList = new StmtParamList();
        MyStmtParamList myParamList = new MyStmtParamList();
        //  insertSQL = "insert into webauto.WS_CALC_LOGS_NEW (";
        //  updateSQL = "update webauto.WS_CALC_LOGS_NEW set ";
        String params = "";

        try {

            String sql1 = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            StmtParamList paramList1 = new StmtParamList();

            paramList1.add(new StmtParam(Types.BIGINT, baseCalcId));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql1, paramList1);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);


            // ----------------------------------- БЛОК ПРОВЕРКИ И ПАРСИНГА RS ----------------------------------------

            if (rs != null) {
                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    Logger.getLogger("").log(Level.SEVERE, "МЫ В НОВОМ ПАРАМЛИСТ-ГЕНЕРАТОРЕ-2", "1111");
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        int type = rsmd.getColumnType(i); //Оракл-тип (данных) текущего поля

                        // Определяем есть ли такое поле (на котором мы сейчас в результсете (rs.next)) вообще. Такое - в смысле с таким же именем
                        Boolean isFound = searchInClassFields(cls, rsmd.getColumnName(i));

                        int typeForParamList = 0;

                        // Если поле нашли, тогда надо проверить его тип и сконвертить Оракл тип к Java типу
                        if (isFound) {
                            switch (type) {
                                case Types.NUMERIC:

                                    /**Теперь надо сконвертить тип поля из класса в тип поля SQL. Связано это с тем, что SQL возвращает везде NUMERIC,
                                     * А в классах есть и long, и double, и integer.
                                     */
                                    //-*     typeForParamList = getLocalFieldType(wscalclog, rsmd.getColumnName(i));
                                    //-*     myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.NUMERIC)));
                                    //  insertSQL = insertSQL + rsmd.getColumnName(i) + ", ";
                                    //  updateSQL = updateSQL + rsmd.getColumnName(i) + "=?, ";

//                                    params = params + "?,";
                                    //Logger.getLogger("").log(Level.SEVERE, "Мы ставим тип - прошли проверку на ноль и рс.некст", "1111");
                                    // Ищем поле с таким именем в классе
                                    //-*      paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                                    break;

                                case Types.VARCHAR:

                                    //-*      typeForParamList = getLocalFieldType(wscalclog, rsmd.getColumnName(i));
                                    //-*      paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                                    //-*   myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.VARCHAR)));
                                    params = params + "?,";
                                    //   insertSQL = insertSQL + rsmd.getColumnName(i) + ", ";
                                    //   updateSQL = updateSQL + rsmd.getColumnName(i) + "=?, ";
                                    break;
                                case Types.TIMESTAMP:

                                    //-*   typeForParamList = getLocalFieldType(wscalclog, rsmd.getColumnName(i));
                                    //-*    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                                    //-*    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.TIMESTAMP)));
                                    params = params + "?,";
                                    //    insertSQL = insertSQL + rsmd.getColumnName(i) + ", ";
                                    //    updateSQL = updateSQL + rsmd.getColumnName(i) + "=?, ";
                                    break;
                            }
                        }
                    }


                    //  insertSQL = StringUtils.removeEnd(insertSQL, ", ");
                    //   updateSQL = StringUtils.removeEnd(updateSQL, ", ");
                    //    params = StringUtils.removeEnd(params, ",");
                    //   insertSQL = insertSQL + ") values(" + params + ")";
                    //   updateSQL = updateSQL + " where CALCID = " + String.valueOf(calcid);
                }
                //paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                //myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.TIMESTAMP)));
            }

            try {
                fileLog.saveAllReportPanels4("D:/log777_2.txt", paramList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileLog.saveAllReportPanels3(myParamList);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return paramList;
    }

    public static int resultSetCount(WebRowSet resultSet) throws SQLException {
        try {
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            return i;
        } catch (Exception e) {
            System.out.println("Error getting row count");
            e.printStackTrace();
        }
        return 0;
    }

    public static int getLocalFieldType(Object anyClass, String name) {

        int varType = 0;
        try {
            String className = getClassName(anyClass); //Вытаскиваем имя класса
            Class<?> clazz = Class.forName(className);
            for (Field field : clazz.getDeclaredFields()) {
                if (name != null && (field.getName()) != null) {
                    String s = "Мы ищем - " + name;
                    //       Logger.getLogger("").log(Level.SEVERE, s, "!!!");
                    if (field.getName().equalsIgnoreCase(name)) {

                        if (field.getType().isAssignableFrom(Long.class)) {
                            //             Logger.getLogger("").log(Level.SEVERE, "Поле являеется Лонг (мы сейчас определяем тип (а не значение))", "!!!");
                            varType = Types.BIGINT;
                            break;
                        } else if (field.getType().isAssignableFrom(long.class)) {
                            varType = Types.BIGINT;
                            //             Logger.getLogger("").log(Level.SEVERE, "Поле - маленький Лонг", "!!!");
                            break;
                        } else if (field.getType().isAssignableFrom(int.class)) {
                            varType = Types.INTEGER;
                            //         Logger.getLogger("").log(Level.SEVERE, "Поле являеется Integer", "!!!");
                            break;
                        } else if (field.getType().isAssignableFrom(String.class)) {
                            varType = Types.VARCHAR;
                            //              Logger.getLogger("").log(Level.SEVERE, "Пиздец какой-то - поле-то у нас СТРИНГ", "!!!");
                            break;
                        } else if (field.getType().isAssignableFrom(Integer.class)) {
                            varType = Types.INTEGER;
                            Logger.getLogger("").log(Level.SEVERE, "А мы нашли Integer", "!!!");
                            break;

                        } else if (field.getType().isAssignableFrom(Double.class)) {
                            varType = Types.DOUBLE;
                            //       Logger.getLogger("").log(Level.SEVERE, "А мы нашли Double", "!!!");
                            break;

                        } else if (field.getType().isAssignableFrom(double.class)) {
                            varType = Types.DOUBLE;
                            //     Logger.getLogger("").log(Level.SEVERE, "А мы нашли Double", "!!!");
                            break;

                        } else if (field.getType().isAssignableFrom(java.sql.Date.class)) {
                            varType = Types.DATE;
                            //     Logger.getLogger("").log(Level.SEVERE, "А мы нашли SQL дату", "!!!");
                            break;
                        } else {
                            //varType = Types.DATE;
                            varType = Types.TIMESTAMP;
                            //      Logger.getLogger("").log(Level.SEVERE, "Поставили TIMESTAMP", "!!!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return varType;


    }

    /**
     * это аналог searchInClassFields, но он возвращает реальное имя в классе (потому что имя в классе может не
     * соответствовать слегка имени в БД, например в другом регистре быть набрано.
     */
    public static String getRealFieldName(Object anyClass, String name) {

        String realFieldName = "";
        String className = getClassName(anyClass); //Вытаскиваем имя класса
        try {
            Class<?> clazz = Class.forName(className); //Берем сам класс
            for (Field field : clazz.getDeclaredFields()) { //Бегаем по полям класса
                if (name != null && (field.getName()) != null) { // Проверяем, что у нас не ноль и переданное в параметре метода имя и текунщее пробегаемое в цикле поле класса

                    //if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {
                    if (field.getName().equalsIgnoreCase(name)) {
                        realFieldName = field.getName();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realFieldName;
    }

    public static WebRowSet getWebRowSetByCalcId(String userSQL, long calcid) {

        WebRowSet rs = null;

        try {
            String sql = userSQL;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }


        return rs;
    }

    public Integer getReordsCountByIdAndSQL(String sql, long calcid) {
        Integer result = null;

        try {
            //String sql = sqlLogging.SQL_GET_CALC_COEFF_COUNT_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                return null;
            } else {

                if (rs.next()) {
                    result = rs.getInt("COUNT");
                }

            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
        return result;
    }

    public static void parseWebRowSet(Integer count, WebRowSet rs, Object obj, Unit unit) throws IllegalAccessException {

        try {
            for (int i = 1; i <= count; i++) {

                ResultSetMetaData rsmd = null;
                rsmd = rs.getMetaData();
                String fieldName = rsmd.getColumnName(i);
                if (DAOUtils.searchInClassFields(obj, fieldName)) { // если нашли поле в классе, то...
                    Integer fieldLocalType = DAOUtils.getLocalFieldType(obj, fieldName); // определяем тип поля

                    switch (fieldLocalType) { // в зависимости от определенного локального типа пишем в класс через рефлексию ...
                        case Types.DOUBLE:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getDouble(i), true);
                            break;

                        case Types.BIGINT:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getLong(i), true);
                            break;

                        case Types.INTEGER:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getInt(i), true);
                            break;

                        case Types.VARCHAR:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getString(i), true);
                            break;


                        case Types.DATE:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getDate(i), true);
                            break;


                        default:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), null, true);
                            break;
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void parseWebRowSet2(Integer count, WebRowSet rs, Object obj, CalcEntity unit) throws IllegalAccessException {

        try {
            for (int i = 1; i <= count; i++) {

                ResultSetMetaData rsmd = null;
                rsmd = rs.getMetaData();
                String fieldName = rsmd.getColumnName(i);
                if (DAOUtils.searchInClassFields(obj, fieldName)) { // если нашли поле в классе, то...
                    Integer fieldLocalType = DAOUtils.getLocalFieldType(obj, fieldName); // определяем тип поля

                    switch (fieldLocalType) { // в зависимости от определенного локального типа пишем в класс через рефлексию ...
                        case Types.DOUBLE:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getDouble(i), true);
                            break;

                        case Types.BIGINT:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getLong(i), true);
                            break;

                        case Types.INTEGER:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getInt(i), true);
                            break;

                        case Types.VARCHAR:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getString(i), true);
                            break;


                        case Types.DATE:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), rs.getDate(i), true);
                            break;


                        default:
                            FieldUtils.writeField(unit, DAOUtils.getRealFieldName(obj, fieldName), null, true);
                            break;
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LinkedHashMap<String, String> parseWebRowSet4GettingHash(Integer count, WebRowSet rs, Object obj) throws IllegalAccessException {

        LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения

        try {
            for (int i = 1; i <= count; i++) {

                ResultSetMetaData rsmd = null;
                rsmd = rs.getMetaData();
                String fieldName = rsmd.getColumnName(i);

                if (!fieldName.equalsIgnoreCase("REQUESTMESSAGE")) { // если нашли поле в классе, то...
                    if (DAOUtils.searchInClassFields(obj, fieldName)) { // если нашли поле в классе, то...
                        Integer fieldLocalType = DAOUtils.getLocalFieldType(obj, fieldName); // определяем тип поля

                        switch (fieldLocalType) { // в зависимости от определенного локального типа пишем в класс через рефлексию ...
                            case Types.DOUBLE:
                                hash.put(rs.getMetaData().getColumnName(i), String.valueOf(rs.getDouble(i)));
                                break;

                            case Types.BIGINT:
                                hash.put(rs.getMetaData().getColumnName(i), String.valueOf(rs.getLong(i)));
                                break;

                            case Types.INTEGER:
                                hash.put(rs.getMetaData().getColumnName(i), String.valueOf(rs.getInt(i)));
                                break;

                            case Types.VARCHAR:
                                hash.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                                break;


                            case Types.DATE:
                                hash.put(rs.getMetaData().getColumnName(i), String.valueOf(rs.getDate(i)));
                                break;
                        }
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public static String dateToString(java.sql.Date date) {

        String dateToString = "";
        if (date == null) {

            dateToString = "n/a";

        } else {


            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dateToString = df.format(date);

        }

        return dateToString;
    }


}
