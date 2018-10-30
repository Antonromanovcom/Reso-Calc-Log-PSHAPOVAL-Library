package ru.reso.resocalc.Utils;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.reflect.FieldUtils;
import ru.reso.resocalc.Entity.Interfaces.CalcEntity;
import ru.reso.resocalc.Entity.MyStmtParamList;
import ru.reso.resocalc.Entity.Interfaces.Unit;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * author - ROMAB
 *
 * Основные методы-утилиты для работы с Энтити, а так же необходимые фабрикам. Основные типовые обработчики вынесены сюда.
 */
public class DAOUtils {

    /**
     * Получаем имя класса из Объекта
     * @param cls
     * @return - имя класса с путем пакета
     */
    public static String getClassName(Object cls) {
        String res = "";
        res = cls.getClass().getName();
        return res;
    }

    /**
     * Ищем поле в заданном классе
     * @param anyClass
     * @param name
     * @return - есть поле - true, нет поля - false
     */
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


    /**
     * Уникальный по сути метод. Парсер. Мы даем calcid, Объект (Энтити), в который будем парсить и SQL-запрос. И он нам возвращаем список параметров
     * в зависимости от того, какие поля есть в классе, сам подбирая типы данных. то есть он соотносит поля в запросе и поля в классе. Через Рефлексию.
     *
     * Так же он может сам генерить запросы. Но пока не все.
     *
     * На самом деле эта фигня написана только для WsCalcLog. А для механизма сравнения это не нужно. В следующей итерации (в следующем коммите) удалим это....
     *
     * @param sql
     * @param cls
     * @param calcid
     * @return
     */
    public static StmtParamList paramListGenerator2(String sql, Object cls, Long calcid) {


        FileLog fileLog = new FileLog();
        Long baseCalcId = 122865290L; // просто временный id, чтобы хоть как-то обратиться к табличке. На самом деле надо все выкачивать и все (*), и убрать это в будущем.
        StmtParamList paramList = new StmtParamList();
        MyStmtParamList myParamList = new MyStmtParamList(); // парамлист, куда мы будем закачивать параметры
        String params = "";

        try {

            String sql1 = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            StmtParamList paramList1 = new StmtParamList();

            paramList1.add(new StmtParam(Types.BIGINT, baseCalcId));
            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql1, paramList1); // выкачивакем табличку, чтобы бегать по ее полям.
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
                fileLog.saveAllReportPanels4("D:/log777_2.txt", paramList); // логгируем в файл
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

    // Количество записей в РезультСете
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

    /**
     * Возвращаем тип поля.  Данный метод позволяет вернуть тип поля по его имени, давая нам нужный уровень абстракции и автоматизации
     * @param anyClass - класс в котором мы ищем поле. Собственно его и хотим вернуть.
     * @param name - имя поля, которое ищем.
     * @return
     */
    public static int getLocalFieldType(Object anyClass, String name) {

        int varType = 0;
        try {
            String className = getClassName(anyClass); //Вытаскиваем имя класса
            Class<?> clazz = Class.forName(className);
            for (Field field : clazz.getDeclaredFields()) {
                if (name != null && (field.getName()) != null) {


                    if (field.getName().equalsIgnoreCase(name)) { // если имя нашли в классе через рефликсию, то определяем его (поля) тип....

                        if (field.getType().isAssignableFrom(Long.class)) {

                            varType = Types.BIGINT;
                            break;
                        } else if (field.getType().isAssignableFrom(long.class)) {
                            varType = Types.BIGINT;

                            break;
                        } else if (field.getType().isAssignableFrom(int.class)) {
                            varType = Types.INTEGER;

                            break;
                        } else if (field.getType().isAssignableFrom(String.class)) {
                            varType = Types.VARCHAR;

                            break;
                        } else if (field.getType().isAssignableFrom(Integer.class)) {
                            varType = Types.INTEGER;

                            break;

                        } else if (field.getType().isAssignableFrom(Double.class)) {
                            varType = Types.DOUBLE;

                            break;

                        } else if (field.getType().isAssignableFrom(double.class)) {
                            varType = Types.DOUBLE;

                            break;

                        } else if (field.getType().isAssignableFrom(java.sql.Date.class)) {
                            varType = Types.DATE;

                            break;
                        } else {

                            varType = Types.TIMESTAMP;

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

    /** Вытаскиваем данные по запросу (который передаем в параметре) и конвертим его в WebRowSet, который и возвращаем....
     *
     * @param userSQL
     * @param calcid
     * @return
     */
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


    /**
     * Вытаскиваем количество записей по запросу и calc id
     * @param sql - sql по которому надо вытащить количество записей
     * @param calcid - calcid для sql
     * @return - количество записей
     */
    public Integer getReordsCountByIdAndSQL(String sql, long calcid) {
        Integer result = null;

        try {

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

    /**
     * Моя собственная и довольно прикольная разработка. Метод парсит WebRowSet, который ему передали и пихает в переданный ему
     * так же в параметрах Unit значения через рефлексию (без сеттеров, включая защищенные поля). Причем не просто пихает.
     * Он парсит WebRowSet, через МетаДата берет названия полей в БД и тип, ищет их в переданном ему Object? подбирает соответствующий тип
     * и уже после этого через рефлексию пихает в класс.
     * @param count - количество записей в роусете. Сколько бегать-то...
     * @param rs - сам роусет
     * @param obj - Объект-шаблон, "с калькой" которого сравниваем поля роусета.
     * @param unit - Унит в который пихаем значения.
     * @throws IllegalAccessException
     *
     * Данный метод работает только с табличками с вертикальной структурой, такими как Drivers. То есть там где используется Unit. Для простых таблиц
     * используется parseWebRowSet2
     */
    public static void parseWebRowSet(Integer count, WebRowSet rs, Object obj, Unit unit) throws IllegalAccessException {

        try {
            for (int i = 1; i <= count; i++) {

                ResultSetMetaData rsmd = null;
                rsmd = rs.getMetaData(); // заливаем сюда метадату роусета.
                String fieldName = rsmd.getColumnName(i); // хватаем имя столбца/поля в цикле.
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

    /**
     * Версия parseWebRowSet для таблиц горизонтальной структуры. Без Юнитов.
     * @param count - количество записей в роусете. Сколько бегать-то...
     * @param rs - сам роусет
     * @param obj - Объект-шаблон, "с калькой" которого сравниваем поля роусета.
     * @param unit - куда пихаем значения
     * @throws IllegalAccessException
     */
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

    /**
     * Метод который парсит WebRowSet исключительно для взятия хэша. Используется в основном в CommonLogsFactory, но его возможно использовать и в других.
     * В методе предусмотрена возможность фильтровать некоторые столбцы. Например я пока убрал сравнение по REQUEST'у. Он слишком длинный.
     * @param count
     * @param rs
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static LinkedHashMap<String, String> parseWebRowSet4GettingHash(Integer count, WebRowSet rs, Object obj) throws IllegalAccessException {

        LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения

        try {
            for (int i = 1; i <= count; i++) {

                ResultSetMetaData rsmd = null;
                rsmd = rs.getMetaData();
                String fieldName = rsmd.getColumnName(i);

                if (!fieldName.equalsIgnoreCase("REQUESTMESSAGE")) { // выхидываем XML запроса. Он слишком огромный.
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

    /**
     * Метод аналогичный parseWebRowSet4GettingHash, но для таблички Coeffs. Без дополнительнйо фильтрации - никаких столбцов ни выкидываем.
      * @param count - сколько парсить. Количество записей.
     * @param rs - сам роусет, который парсим-фигарсим
     * @param obj - объект-шаблон.
     * @return - ЛинкедХэшМэп. Выбрана эта коллекция чтобы сохранить insertionorder
     * @throws IllegalAccessException
     */
    public static LinkedHashMap<String, String> parseWebRowSet4GettingHash4Coeffs(Integer count, WebRowSet rs, Object obj) throws IllegalAccessException {

        LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения

        try {
            for (int i = 1; i <= count; i++) {

                ResultSetMetaData rsmd = null;
                rsmd = rs.getMetaData();
                String fieldName = rsmd.getColumnName(i);


                if (DAOUtils.searchInClassFields(obj, fieldName)) { // если нашли поле в классе, то...
                    Integer fieldLocalType = DAOUtils.getLocalFieldType(obj, fieldName); // определяем тип поля

                    switch (fieldLocalType) { // в зависимости от определенного локального типа пишем в класс через рефлексию ...
                        case Types.DOUBLE:
                            hash.put(rs.getMetaData().getColumnName(i), String.valueOf(rs.getDouble(i)));
                            break;

                        case Types.BIGINT:
                            hash.put(rs.getMetaData().getColumnName(i), String.valueOf(rs.getLong(i)));
                            break;

                        case Types.VARCHAR:
                            hash.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                            break;

                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * В ХэшМеп, который мы отдаем клиенту дату мы пихаем как стринг с конвертацией. На клиенте в принципе по хрен что сравнивать. Суть та же.
     * @param date
     * @return
     */
    public static String dateToString(java.sql.Date date) {

        String dateToString = "";
        if (date == null) { // некоторые даты не заполнены в БД, поэтому обязательно надо сделать проверку на ноль.

            dateToString = "n/a";

        } else {


            DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); //формат в который конвертим дату
            dateToString = df.format(date);

        }

        return dateToString;
    }

    // Для вертикальный таблиц таких как Drivers мы группы значений пихаем в JSON? сравниваем их прям так, а потом распарсиваем на клиенте и распихиваем по столбцам таблички.
    public static String getJSONfromMap(Map<String, Object> map) {

        String json = "";

        try {

            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(map);
            // Logger.getLogger("").log(Level.SEVERE, "Без Претти-Принтера:   " + json, "аааа");
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            // Logger.getLogger("").log(Level.SEVERE, "С Претти-Принтером:   " + json, "аааа");


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
