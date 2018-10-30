package ru.reso.resocalc.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.WebRowSet;


import ru.reso.resocalc.Entity.MyStmtParam;
import ru.reso.resocalc.Entity.MyStmtParamList;
import ru.reso.resocalc.Entity.WsCalcLogsNew;
import ru.reso.resocalc.Utils.sqlLogging;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;

/**
 * @author SHAPPN
 */
public class WsCalcLogDao {


    public static void addLogAutomative(WsCalcLogsNew wscalclog) {
        try {
            String sql = sqlLogging.SQL_INSERT_WsCalcLogNEW2;
            //Long calc = 122865290L;
            Long calc = 122867771L;
            FileLog fileLog = new FileLog();

            Logger.getLogger("").log(Level.SEVERE, "Мы вошли в addLogAutomative", "1111");
            StmtParamList paramList = paramListGenerator2(sql, wscalclog, calc);
            //StmtParamList paramList = WsCalcLogsNew2StmtParamList(wscalclog);
            StmtParamList paramList1 = WsCalcLogsNew2StmtParamList(wscalclog);

            try {
                fileLog.saveAllReportPanels4("D:/params_from_generator.txt", paramList);
                fileLog.saveAllReportPanels4("D:/params_from_random.txt", paramList1);
                // fileLog.compare2ArrayList(paramList, paramList1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            DBConnection conn = new DBConnection();
            conn.prepareStatementExecuteUpdate(sql, paramList);

            Logger.getLogger("").log(Level.SEVERE, "Мы добавили", "1111");
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
    }

    public static Long getTestCalcId() {

        Long baseCalcId = null;
        StmtParamList paramList = new StmtParamList();

        try {

            String sql1 = sqlLogging.SQL_TEST_CALC_ID;
            WebRowSet rs = getFullWebRowSet(sql1);

            if (rs != null) {
                while (rs.next()) {
                    //ResultSetMetaData rsmd = rs.getMetaData();
                    Logger.getLogger("").log(Level.SEVERE, "Мы внутри вайла от getTestCalcId", "1111");
                    //for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    baseCalcId = rs.getLong(1);
                    //}
                }
            }
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return baseCalcId;
    }

    private static StmtParamList paramListGenerator2(String sql, WsCalcLogsNew wscalclog, Long calcid) {

        FileLog fileLog = new FileLog();
        Long baseCalcId = 122865290L;
        StmtParamList paramList = new StmtParamList();
        MyStmtParamList myParamList = new MyStmtParamList();

        try {

            String sql1 = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            StmtParamList paramList1 = new StmtParamList();


            //    String sql = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            //  StmtParamList paramList = new StmtParamList();
            //MyStmtParamList myParamList = new MyStmtParamList();
            paramList1.add(new StmtParam(Types.BIGINT, baseCalcId));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql1, paramList1);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);


            //WebRowSet rs = getWebRowSet(sql1, Types.BIGINT, baseCalcId);

            // ----------------------------------- БЛОК ПРОВЕРКИ И ПАРСИНГА RS ----------------------------------------

            if (rs != null) {
                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    // Logger.getLogger("").log(Level.SEVERE, "Мы прошли проверку на ноль и рс.некст", "1111");
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {

                        int type = rsmd.getColumnType(i); //Оракл-тип (данных) текущего поля

                        // Определяем есть ли такое поле (на котором мы сейчас в результсете (rs.next)) вообще. Такое - в смысле с таким же именем
                        Boolean isFound = searchInClassFields(wscalclog, rsmd.getColumnName(i));

                        int typeForParamList = 0;

                        // Если поле нашли, тогда надо проверить его тип и сконвертить Оракл тип к Java типу
                        if (isFound) {
                            switch (type) {
                                case Types.NUMERIC:

                                    /**Теперь надо сконвертить тип поля из класса в тип поля SQL. Связано это с тем, что SQL возвращает везде NUMERIC,
                                     * А в классах есть и long, и double, и integer.
                                     */
                                    typeForParamList = getLocalFieldType(wscalclog, rsmd.getColumnName(i));
                                    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.NUMERIC)));

                                    //Logger.getLogger("").log(Level.SEVERE, "Мы ставим тип - прошли проверку на ноль и рс.некст", "1111");

                                    // Ищем поле с таким именем в классе
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                                    break;

                                case Types.VARCHAR:

                                    typeForParamList = getLocalFieldType(wscalclog, rsmd.getColumnName(i));
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                                    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.VARCHAR)));
                                    break;
                                case Types.TIMESTAMP:

                                    typeForParamList = getLocalFieldType(wscalclog, rsmd.getColumnName(i));
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i))));
                                    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wscalclog, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.TIMESTAMP)));
                                    break;
                            }
                        }

                        try {
                            fileLog.saveAllReportPanels4("D:/log777_1.txt", paramList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fileLog.saveAllReportPanels3(myParamList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return paramList;

    }

    private static WebRowSet getFullWebRowSet(String sql1) {

        DBConnection conn = new DBConnection();
        String rsStr = null;
        WebRowSet rs = null;
        try {
            rsStr = conn.prepareStatementExecuteQueryWOParametrs(sql1);
            rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }

    private static WebRowSet getWebRowSet(String sql, int type, Long value) {

        StmtParamList paramList = new StmtParamList();
        paramList.add(new StmtParam(type, value));
        DBConnection conn = new DBConnection();
        String rsStr = null;
        WebRowSet rs = null;
        try {
            rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void addLog(WsCalcLogsNew wscalclog) {
        try {
            String sql = sqlLogging.SQL_INSERT_WsCalcLogNEW2;
            Long calc = 122867771L;
            Logger.getLogger("").log(Level.SEVERE, "Мы вошли в Add", "1111");
            StmtParamList paramList = WsCalcLogsNew2StmtParamList(wscalclog);
            DBConnection conn = new DBConnection();
            conn.prepareStatementExecuteUpdate(sql, paramList);

            Logger.getLogger("").log(Level.SEVERE, "Мы добавили", "1111");
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
    }

    public static Object searchInClassFieldsAndGet(Object anyClass, String name) {

        Object var = new Object();
        String lookingForField = "МЫ ИЩЕМ  -  " + name;
        Logger.getLogger("").log(Level.SEVERE, lookingForField, "!!!");

        try {

            Class<?> clazz = Class.forName("ru.reso.resocalc.Entity.WsCalcLogsNew");

            for (Field field : clazz.getDeclaredFields()) {

                if (name.equalsIgnoreCase("OWNER_REGION")) {
                    Logger.getLogger("").log(Level.SEVERE, "НАШЛИ РЕГИОЕН!!!! Мы перебираем поля + " + field.getName(), "!!!");
                }

                if (name != null && (field.getName()) != null) {

                    //if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {
                    if (field.getName().equalsIgnoreCase(name)) {
                        field.setAccessible(true);
                        // Теперь, когда мы нашли поле, нам надо попробовать вытащить его тип.
                     //   Logger.getLogger("").log(Level.SEVERE, "Мы проверили, поле есть. То есть [" + field.getName() + "] и [" + name + "] - это одно и тоже", "!!!");
                        //if (field.getType().isAssignableFrom(Long.class)) {
                        //  if (field.getType().isInstance(Long.class)) {
                        if (field.get(anyClass) instanceof Long) {
                            try {
                                field.setAccessible(true);
                                var = (Long) field.get(anyClass);
                        //        String catchedValue = "Поле Лонг. Мы поймали - " + String.valueOf(var);
                      //         Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                                //   break;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else if (field.getType().isAssignableFrom(int.class)) {
                            try {
                                Logger.getLogger("").log(Level.SEVERE, "Поле являеется Integer", "!!!");
                                field.setAccessible(true);
                                var = (Integer) field.get(anyClass);
                                //String catchedValue = "Мы поймали - " + String.valueOf(var);
                                //                   Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                                //     break;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            //} else if (field.getType().isAssignableFrom(String.class)) {
                        } else if (field.get(anyClass) instanceof String) {
                            try {

                                field.setAccessible(true);
                                var = (String) field.get(anyClass);
                                String catchedValue = "Мы поймали String. Поле - " + field.getName() + ". Значение - " + String.valueOf(var);
                                Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                                //   break;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else if ((field.getType().isAssignableFrom(Double.class)) || (field.getType().isAssignableFrom(double.class))) {
                            try {

                                field.setAccessible(true);
                                var = (Double) field.get(anyClass);
                                //        String catchedValue = "Мы поймали Double - " + String.valueOf(var);
                                //          Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else if (field.getType().isAssignableFrom(java.sql.Date.class)) {
                            try {

                                field.setAccessible(true);
                                var = (java.sql.Date) field.get(anyClass);
                                //        String catchedValue = "Мы поймали SQL DATE - " + String.valueOf(var);
                                //        Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else if (field.getType().isAssignableFrom(Integer.class)) {
                            try {

                                field.setAccessible(true);
                                var = (Integer) field.get(anyClass);
                                //         String catchedValue = "Мы поймали Integer - " + String.valueOf(var);
                                //         Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            // Иначе это java.sql.date

                            //   field.setAccessible(true);
                            //   var = (java.sql.Date) field.get(anyClass);
                            //  String catchedValue = "Мы поймали Date - " + String.valueOf(var);
                            //   Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");


                            var = new Long(15);

                        }

                        //var = new Long(15);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return var;
    }

    public static int getLocalFieldType(Object anyClass, String name) {

        int varType = 0;
        try {
            Class<?> clazz = Class.forName("ru.reso.resocalc.Entity.WsCalcLogsNew");
            for (Field field : clazz.getDeclaredFields()) {
                if (name != null && (field.getName()) != null) {

                    String s = "Мы ищем - " + name;
                       Logger.getLogger("").log(Level.SEVERE, s, "!!!");

                    //if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {
                        if (field.getName().equalsIgnoreCase(name)) {
                        //Logger.getLogger("").log(Level.SEVERE, "Нашли поле в классе (определяем тип)", "!!!");
                        if (field.getType().isAssignableFrom(Long.class)) {
                                     Logger.getLogger("").log(Level.SEVERE, "Поле являеется Лонг (мы сейчас определяем тип (а не значение))", "!!!");
                            varType = Types.BIGINT;
                            break;
                        } else if (field.getType().isAssignableFrom(long.class)) {
                            varType = Types.BIGINT;
                            Logger.getLogger("").log(Level.SEVERE, "Поле - маленький Лонг", "!!!");
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
                                  Logger.getLogger("").log(Level.SEVERE, "А мы нашли SQL дату", "!!!");
                            break;
                        } else {
                            //varType = Types.DATE;
                            varType = Types.TIMESTAMP;
                            Logger.getLogger("").log(Level.SEVERE, "Поставили TIMESTAMP", "!!!");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return varType;


    }

    public static Boolean searchInClassFields(Object anyClass, String name) {

        Boolean result = false;

        for (Field field : anyClass.getClass().getFields()) {

            if (name != null && (field.getName()) != null) {
                if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {
                    result = true;
                }
            }
        }

        try {

            Class<?> clazz = Class.forName("ru.reso.resocalc.Entity.WsCalcLogsNew");

            for (Field field : clazz.getDeclaredFields()) {

                if (name != null && (field.getName()) != null) {
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

    public static StringBuffer paramListGenerator(WsCalcLogsNew wsCalcLogsNew, Long calcid) {
        StringBuffer strBuffer = new StringBuffer();
        FileLog fileLog = new FileLog();

        try {

            String sql = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            StmtParamList paramList = new StmtParamList();
            MyStmtParamList myParamList = new MyStmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            if (rs == null) {
                strBuffer.append("  |  WEBROWSET IS EMPTY |  ");
            } else {

                while (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();

                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        if (i > 1) {
                            strBuffer.append(",");
                        }

                        int type = rsmd.getColumnType(i); //Оракл-тип (данных) текущего поля
                        String typeName = rsmd.getColumnTypeName(i);
                        String columnClassName = rsmd.getColumnClassName(i);

                        // Определяем есть ли такое поле (на котором мы сейчас в результсете (rs.next)) вообще. Такое - в смысле с таким же именем
                        Boolean isFound = searchInClassFields(wsCalcLogsNew, rsmd.getColumnName(i));

                        int typeForParamList = 0;
                        // Если поле нашли, тогда надо проверить его тип и сконвертить Оракл тип к Java типу
                        if (isFound) {
                            switch (type) {
                                case Types.NUMERIC:
                                    //    Logger.getLogger("").log(Level.SEVERE, "Цифирь", "Мы нашли поле");


                                    /**Теперь надо сконвертить тип поля из класса в тип поля SQL. Связано это с тем, что SQL возвращает везде NUMERIC,
                                     * А в классах есть и long, и double, и integer.
                                     */
                                    typeForParamList = getLocalFieldType(wsCalcLogsNew, rsmd.getColumnName(i));

                                    // Ищем поле с таким именем в классе
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i))));
                                    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.NUMERIC)));

                                    break;

                                case Types.VARCHAR:

                                    typeForParamList = getLocalFieldType(wsCalcLogsNew, rsmd.getColumnName(i));

                                    // Ищем поле с таким именем в классе
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i))));
                                    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.VARCHAR)));
                                    break;
                                case Types.TIMESTAMP:
                                    Logger.getLogger("").log(Level.SEVERE, "ДАТА", "Мы нашли ДАТУ");
                                    typeForParamList = getLocalFieldType(wsCalcLogsNew, rsmd.getColumnName(i));
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i))));
                                    myParamList.add(new MyStmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i)), rsmd.getColumnName(i), fieldTypeToStr(Types.TIMESTAMP)));
                                    break;
                            }
                        }


                        try {
                            fileLog.saveAllReportPanels3(myParamList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        strBuffer.append("[");
                        strBuffer.append(rsmd.getColumnName(i));
                        strBuffer.append("]");
                        strBuffer.append("-");
                        strBuffer.append("[");
                        strBuffer.append(type);
                        strBuffer.append("]");
                    }

                    strBuffer.append("   ИТОГО  ");
                    strBuffer.append(rsmd.getColumnCount());
                    strBuffer.append("   ЕБАНОЕ ПОЛЕ");
                    strBuffer.append("  |   ");
                    strBuffer.append(getMockObject(1).getAdmUser());
                }
                //strBuffer.append("  |  WEBROWSET IS NOT EMPTY |  ");
            }

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return strBuffer;
    }

    private static String fieldTypeToStr(int typeForParamList) {
        String result = "";

        switch (typeForParamList) {
            case Types.NUMERIC:
                result = "NUMERIC";
                break;
            case Types.INTEGER:
                result = "INTEGER";
                break;
            case Types.VARCHAR:
                result = "VARCHAR";
                break;
            case Types.TIMESTAMP:
                result = "TIMESTAMP";
                break;
        }

        return result;
    }

    public static WsCalcLogsNew getLogByCalcID(long calcid) {
        WsCalcLogsNew calcLog = null;

        try {

            String sql = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.BIGINT, calcid));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);
            calcLog = webRowSet2WsCalcLogsNew(rs);

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return calcLog;
    }

    private static StmtParamList WsCalcLogsNew2StmtParamList(WsCalcLogsNew wsCalcLogsNew) {

        StmtParamList paramList = new StmtParamList();
        Long calc = 122867777L;

        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCalcid()));//CALCID
        //paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getCalcid()));//CALCID
        paramList.add(new StmtParam(Types.BIGINT, calc));//CALCID
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getNotapplyspecprogdiscount()));//NOTAPPLYSPECPROGDISCOUNT
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getTargetcompany()));//TARGETCOMPANY
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarbrandname()));//CARBRANDNAME
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarmodelname()));//CARMODELNAME    
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCartype()));//CARMODELTYPE

        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarbodynumber()));//CARMODELTYPE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarchassisnumber()));//CARCHASSISNUMBER
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCreditauto()));//CREDITAUTO
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getLenderid()));//LENDERID
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getLenderid()));//LENDERID
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getAutorace()));//AUTORACE
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarkind()));//CARKIND
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarvendortype()));//CARVENDORTYPE
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getCarprice()));//CARPRICE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getRightwheel()));//RIGHTWHEEL
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getAntitheftsystem()));//ANTITHEFTSYSTEM
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getAntitheftsystem()));//ANTITHEFTSYSTEM
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getThfmechdevice()));//THFMECHDEVICE
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getCarpurchasedate()));//CARPURCHASEDATE
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCarparkcount()));//CARPARKCOUNT
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getCarparkcount()));//CARPARKCOUNT
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getPowerauto()));//POWERAUTO
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getPowerauto()));//POWERAUTO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarusedtrailer()));//CARUSEDTRAILER
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getCaraccidentplaceinssum()));//CARACCIDENTPLACEINSSUM
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCaraccidentplacekind()));//CARACCIDENTPLACEKIND
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getCaraccidentplacekind()));//CARACCIDENTPLACEKIND
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCaraccidentplacenum()));//CARACCIDENTPLACENUM
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getCaraccidentplacenum()));//CARACCIDENTPLACENUM
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getCarextequipmentinssum()));//CAREXTEQUIPMENTINSSUM
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInsfromdate1()));//INSFROMDATE1
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInstodate1()));//INSTODATE1
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInsfromdate2()));//INSFROMDATE2
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInstodate2()));//INSTODATE2
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInsfromdate3()));//INSFROMDATE3
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInstodate3()));//INSTODATE3
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCaruseregion()));//CARUSEREGION
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getCaruseregion()));//CARUSEREGION
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCaruseregionkladr()));//CARUSEREGIONKLADR
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarcategory()));//CARCATEGORY
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getPerioduse()));//PERIODUSE
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getPerioduse()));//PERIODUSE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarvehicletypeosago()));//CARVEHICLETYPEOSAGO
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getInssumdgo()));//INSSUMDGO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getWithweardgo()));//WITHWEARDGO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getKbmcheckcardiagnisticcard()));//KBMCHECKCARDIAGNISTICCARD
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCasco()));//CASCO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getDamage()));//DAMAGE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getTheft()));//THEFT
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getHelp()));//HELP
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getGap()));//GAP
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getAccident()));//ACCIDENT
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getEquipment()));//EQUIPMENT
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getOsago()));//OSAGO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getDgo()));//DGO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getPnumber()));//PNUMBER
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCommercprogrammid()));//COMMERCPROGRAMMID
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarDamageQuanityManual()));//CARDAMAGEQUANITYMANUAL
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getIsanothersk()));//ISANOTHERSK
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getInsurer()));//INSURER
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getInsurer()));//INSURER
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getOwnerRegion()));//OWNER_REGION
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getOwnerRegion()));//OWNER_REGION
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getDriverlisttype()));//DRIVERLISTTYPE
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCarmodelcode()));//CARMODELCODE
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getCarmodelcode()));//CARMODELCODE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getDriverlisttypeosago()));//DRIVERLISTTYPEOSAGO
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getPolicy()));//POLICY
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarYear()));//CarYear
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getAdmUser()));//ADMUSER
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCrash()));//CRASH
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getHolderRequestId()));//HOLDERREQUESTID
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getHolderRequestId()));//HOLDERREQUESTID
        //paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getOwnerRequestId()));//OWNERREQUESTID
        paramList.add(new StmtParam(Types.BIGINT, wsCalcLogsNew.getOwnerRequestId()));//OWNERREQUESTID
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getEquifaxScore()));//EQUIFAXSCORE
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getPolicyType()));//POLICYTYPE
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCustomKb()));//CUSTOMKB


        return paramList;
    }

    private static WsCalcLogsNew webRowSet2WsCalcLogsNew(WebRowSet rs) {
        WsCalcLogsNew calcLog = null;

        if (rs == null) {
            return calcLog;
        }

        try {
            if (rs.next()) {
                calcLog = new WsCalcLogsNew(rs.getLong("CALCID"));
                calcLog.setNotapplyspecprogdiscount(rs.getString("NOTAPPLYSPECPROGDISCOUNT"));
                calcLog.setTargetcompany(rs.getString("TARGETCOMPANY"));
                calcLog.setCarbrandname(rs.getString("CARBRANDNAME"));
                calcLog.setCarmodelname(rs.getString("CARMODELNAME"));
                calcLog.setCartype(rs.getString("CARTYPE"));
                calcLog.setCarbodynumber(rs.getString("CARBODYNUMBER"));
                calcLog.setCarchassisnumber(rs.getString("CARCHASSISNUMBER"));
                calcLog.setCreditauto(rs.getString("CREDITAUTO"));
                calcLog.setAutorace(rs.getString("AUTORACE"));
                calcLog.setLenderid(rs.getLong("LENDERID"));
                calcLog.setCarkind(rs.getInt("CARKIND"));
                calcLog.setCarvendortype(rs.getInt("CARVENDORTYPE"));
                calcLog.setCarprice(rs.getDouble("CARPRICE"));
                calcLog.setRightwheel(rs.getString("RIGHTWHEEL"));
                calcLog.setAntitheftsystem(rs.getLong("ANTITHEFTSYSTEM"));
                calcLog.setThfmechdevice(rs.getString("THFMECHDEVICE"));
                calcLog.setCarpurchasedate(rs.getDate("CARPURCHASEDATE"));
                calcLog.setCarparkcount(rs.getLong("CARPARKCOUNT"));
                calcLog.setPowerauto(rs.getLong("POWERAUTO"));
                calcLog.setCarusedtrailer(rs.getString("CARUSEDTRAILER"));
                calcLog.setCaraccidentplaceinssum(rs.getDouble("CARACCIDENTPLACEINSSUM"));
                calcLog.setCarAccidentPlaceKind(rs.getLong("CARACCIDENTPLACEKIND"));
                calcLog.setCaraccidentplacenum(rs.getLong("CARACCIDENTPLACENUM"));
                calcLog.setCarextequipmentinssum(rs.getDouble("CAREXTEQUIPMENTINSSUM"));
                calcLog.setInsfromdate1(rs.getDate("INSFROMDATE1"));
                calcLog.setInstodate1(rs.getDate("INSTODATE1"));
                calcLog.setInsfromdate2(rs.getDate("INSFROMDATE2"));
                calcLog.setInstodate2(rs.getDate("INSTODATE2"));
                calcLog.setInsfromdate3(rs.getDate("INSFROMDATE3"));
                calcLog.setInstodate3(rs.getDate("INSTODATE3"));
                calcLog.setCaruseregion(rs.getLong("CARUSEREGION"));
                calcLog.setCaruseregionkladr(rs.getString("CARUSEREGIONKLADR"));
                calcLog.setCarcategory(rs.getInt("CARCATEGORY"));
                calcLog.setPerioduse(rs.getLong("PERIODUSE"));
                calcLog.setCarvehicletypeosago(rs.getString("CARVEHICLETYPEOSAGO"));
                calcLog.setInssumdgo(rs.getDouble("INSSUMDGO"));
                calcLog.setWithweardgo(rs.getString("WITHWEARDGO"));
                calcLog.setKbmcheckcardiagnisticcard(rs.getString("KBMCHECKCARDIAGNISTICCARD"));
                calcLog.setCasco(rs.getString("CASCO"));
                calcLog.setDamage(rs.getString("DAMAGE"));
                calcLog.setTheft(rs.getString("THEFT"));
                calcLog.setHelp(rs.getString("HELP"));
                calcLog.setGap(rs.getString("GAP"));
                calcLog.setAccident(rs.getString("ACCIDENT"));
                calcLog.setEquipment(rs.getString("EQUIPMENT"));
                calcLog.setOsago(rs.getString("OSAGO"));
                calcLog.setDgo(rs.getString("DGO"));
                calcLog.setPnumber(rs.getString("PNUMBER"));
                calcLog.setCommercprogrammid(rs.getInt("COMMERCPROGRAMMID"));
                calcLog.setCarDamageQuanityManual(rs.getInt("CARDAMAGEQUANITYMANUAL"));
                calcLog.setIsanothersk(rs.getString("ISANOTHERSK"));
                calcLog.setInsurer(rs.getLong("ISANOTHERSK"));
                calcLog.setOwnerRegion(rs.getLong("OWNER_REGION"));
                calcLog.setDriverlisttype(rs.getString("DRIVERLISTTYPE"));
                calcLog.setDriverlisttype(rs.getString("DRIVERLISTTYPE"));
                calcLog.setCarmodelcode(rs.getLong("CARMODELCODE"));
                calcLog.setPolicy(rs.getInt("POLICY"));
                calcLog.setDriverlisttypeosago(rs.getString("DRIVERLISTTYPEOSAGO"));
                calcLog.setCarYear(rs.getInt("CARYEAR"));
                calcLog.setAdmUser(rs.getString("ADMUSER"));
                calcLog.setCrash(rs.getString("CRASH"));
                calcLog.setHolderRequestId(rs.getLong("HOLDERREQUESTID"));
                calcLog.setOwnerRequestId(rs.getLong("OWNERREQUESTID"));
                calcLog.setEquifaxScore(rs.getInt("EQUIFAXSCORE"));
                calcLog.setEquifaxerrmsg(rs.getString("EQUIFAXERRMSG"));
                calcLog.setPolicyType(rs.getInt("POLICYTYPE"));
                calcLog.setCustomKb(rs.getInt("CUSTOMKB"));
            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to convert webRowSet to WsCalcLogsNew", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", ex);
            }
        }

        return calcLog;
    }

    private void UpdateWsCalcLogsNew(WsCalcLogsNew wsCalcLogsNew) {

        try {

            String sql = sqlLogging.SQL_UPDATE_WsCalcLogNEW;
            StmtParamList paramList = WsCalcLogsNew2StmtParamList(wsCalcLogsNew);
            DBConnection conn = new DBConnection();
            conn.prepareStatementExecute(sql, paramList);

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
    }

    public static WsCalcLogsNew getMockObject(Integer generateType) {

        WsCalcLogsNew mockObject = new WsCalcLogsNew();
        String type = "";
        Date currentDate = new Date();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        switch (generateType) {
            case 0:
                type = "CONSTANT";
                break;
            case 1:
                type = generateString();
                //mockObject.setCalcid(new Long(getRNIR(1,5000)));
                mockObject.setCalcid(new Long(122867777L));
                mockObject.setNotapplyspecprogdiscount("Н");
                mockObject.setTargetcompany(generateString());
                mockObject.setCarbrandname("GRAVITZAPA");
                mockObject.setCarmodelname(generateString());
                mockObject.setCartype("Л");
                mockObject.setCarbodynumber(generateString());
                mockObject.setCarchassisnumber(generateString());
                mockObject.setCreditauto("Н");
                mockObject.setLenderid(new Long(getRNIR(1, 10)));
                mockObject.setAutorace("Д");
                mockObject.setCarkind(getRNIR(1, 10));
                mockObject.setCarvendortype(0);
                mockObject.setCarprice(new Double(getRNIR(1, 10)));
                mockObject.setRightwheel("Н");
                mockObject.setAntitheftsystem(new Long(getRNIR(1, 100)));
                mockObject.setThfmechdevice("Н");
                mockObject.setCarpurchasedate(sqlDate);
                mockObject.setCarparkcount(new Long(getRNIR(1, 10)));
                mockObject.setPowerauto(new Long(getRNIR(1, 10)));
                mockObject.setCarusedtrailer("Н");
                mockObject.setCaraccidentplaceinssum(new Double(getRNIR(1, 10)));
                mockObject.setCaraccidentplacekind(new Long(getRNIR(1, 10)));
                mockObject.setCaraccidentplacenum(new Long(getRNIR(1, 10)));
                mockObject.setCarextequipmentinssum(new Double(getRNIR(1, 10)));
                mockObject.setInsfromdate1(sqlDate);
                mockObject.setInstodate1(sqlDate);
                mockObject.setInsfromdate2(sqlDate);
                mockObject.setInsfromdate3(sqlDate);
                mockObject.setInstodate2(sqlDate);
                mockObject.setInstodate3(sqlDate);
                mockObject.setCaruseregion(new Long(getRNIR(1, 10)));
                mockObject.setCaruseregionkladr(generateString());
                mockObject.setCarcategory(getRNIR(1, 10));
                mockObject.setPerioduse(new Long(getRNIR(1, 25)));
                mockObject.setCarvehicletypeosago(generateString());
                mockObject.setInssumdgo(new Double(getRNIR(1, 10)));
                mockObject.setWithweardgo("Х");
                mockObject.setKbmcheckcardiagnisticcard("У");
                mockObject.setCasco("Ю");
                mockObject.setDamage("Ш");
                mockObject.setTheft("К");
                mockObject.setHelp("И");
                mockObject.setGap("В");
                mockObject.setAccident("А");
                mockObject.setEquipment("М");
                mockObject.setOsago("В");
                mockObject.setDgo("С");
                mockObject.setCrash("Е");
                mockObject.setPnumber("М");
                mockObject.setCommercprogrammid(getRNIR(1, 5));

          /*      mockObject.setCarownertypeid(getRNIR(1,150));
                mockObject.setInsuranttypeid(-1); */

                mockObject.setIsanothersk("Я");
                mockObject.setInsurer(new Long(getRNIR(1, 18)));
                mockObject.setOwnerregion(new Long(getRNIR(1, 250)));
                mockObject.setDriverlisttype("A");
                mockObject.setCarmodelcode(new Long(getRNIR(1, 25)));
                mockObject.setDriverlisttypeosago(generateString());
                mockObject.setPolicy(getRNIR(1, 15));
                mockObject.setCarYear(getRNIR(1941, 2018));
                mockObject.setAdmUser("ВАСЯ");
                mockObject.setHolderRequestId(new Long(getRNIR(1, 25)));
                mockObject.setOwnerRequestId(new Long(getRNIR(1, 25)));
                mockObject.setEquifaxScore(getRNIR(1, 15));
                mockObject.setEquifaxerrmsg("ERROR!");
                mockObject.setPolicyType(1);
                mockObject.setCustomKb(getRNIR(1, 5));
                break;
        }
        return mockObject;
    }

    private static int getRNIR(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static String generateString() {
        String sourceString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = getRNIR(2, 10);
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = sourceString.charAt(rng.nextInt(sourceString.length()));
        }
        return new String(text);
    }


}