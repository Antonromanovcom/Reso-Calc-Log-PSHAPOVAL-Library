package ru.reso.resocalc.Service;

import java.lang.reflect.Field;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.WebRowSet;


import ru.reso.resocalc.Entity.WsCalcLogsNew;
import ru.reso.resocalc.Utils.sqlLogging;
import ru.reso.wp.srv.db.models.StmtParam;
import ru.reso.wp.srv.db.models.StmtParamList;
import ru.reso.wp.srv.db.ResoDatabaseInvoke;

/**
 * @author SHAPPN
 */
public class WsCalcLogDao {

    public static void addLog(WsCalcLogsNew wscalclog) {
        try {
            String sql = sqlLogging.SQL_INSERT_WsCalcLogNEW;
            StmtParamList paramList = WsCalcLogsNew2StmtParamList(wscalclog);
            DBConnection conn = new DBConnection();
            conn.prepareStatementExecute(sql, paramList);
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
                if (name != null && (field.getName()) != null) {

                 //   Logger.getLogger("").log(Level.SEVERE, "Имя искомого поля и имя поля, вытаскиваемого из класса не ноль", "!!!");

                    if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {

                        Logger.getLogger("").log(Level.SEVERE, "Нашли поле в классе", "!!!");

                        // Теперь, когда мы нашли поле, нам надо попробовать вытащить его тип.

                        //if (field.getType().isInstance(long.class)) {
                        if (field.getType().isAssignableFrom(Long.class)) {
                            try {
                                Logger.getLogger("").log(Level.SEVERE, "Поле являеется Лонг", "!!!");

                                field.setAccessible(true);
                                var = (Long) field.get(anyClass);
                                String catchedValue = "Мы поймали - " + String.valueOf(var);
                                Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else if (field.getType().isAssignableFrom(int.class)){
                            try {
                                Logger.getLogger("").log(Level.SEVERE, "Поле являеется Integer", "!!!");
                                field.setAccessible(true);
                                var = (Integer) field.get(anyClass);
                                String catchedValue = "Мы поймали - " + String.valueOf(var);
                                Logger.getLogger("").log(Level.SEVERE, catchedValue, "!!!");
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Logger.getLogger("").log(Level.SEVERE, "Поле НЕ Лонг", "!!!");
                            //String tempType = "А поле у нас - " + field.getType().toString();
                            //Logger.getLogger("").log(Level.SEVERE, tempType, "!!!");
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
        return Types.NUMERIC;
    }

    public static Boolean searchInClassFields(Object anyClass, String name) {
        //public static Boolean searchInClassFields(WsCalcLogsNew anyClass, String name) {

        Boolean result = false;

        //Logger.getLogger("").log(Level.SEVERE, "ПИЗДЕЦОК", "Мы получили класс");

        for (Field field : anyClass.getClass().getFields()) {
            //     System.out.println(field.getName());
            //Logger.getLogger("").log(Level.SEVERE, "ХЕРЬ", "Мы нашли поле");

            if (name != null && (field.getName()) != null) {
                if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(field.getName(), name)) {
                    result = true;
                }
            }
        }

        try {

            Class<?> clazz = Class.forName("ru.reso.resocalc.Entity.WsCalcLogsNew");

            for (Field field : clazz.getDeclaredFields()) {
                //      Logger.getLogger("").log(Level.SEVERE, "ХЕРЬ-3", "Мы нашли поле");
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


        //StmtParamList paramList = new StmtParamList();

/*try { */
  /*      for (Field field: wsCalcLogsNew.getClass().getFields()) {
           System.out.println(field.getName());
           System.out.println(field.getType());
            Logger.getLogger("").log(Level.SEVERE, "ХЕРЬ-2", "Мы нашли поле");
    //       field.setAccessible(true);
  //         String value = (String) field.get(wsCalcLogsNew);

        }
//}

        //strBuffer.append("Name: " + field.getName() + "| Type: " + field.getType());
     /*   strBuffer.append("Name: ");
        strBuffer.append(wsCalcLogsNew.getCarmodelname());
        strBuffer.append("  |  ");

*/
  /*      try {
            //Foobar foobar = new Foobar("Peter");

            Class<?> clazz = Class.forName("ru.reso.resocalc.Entity.WsCalcLogsNew");

            strBuffer.append("Class: ");
            strBuffer.append(clazz);
            strBuffer.append("  |  ");

            Field field = clazz.getDeclaredField("carbrandname");
            field.setAccessible(true);
            String value = (String) field.get(wsCalcLogsNew);

            strBuffer.append("carbrandname: ");
            strBuffer.append(value);
            strBuffer.append("  |  ");

        } catch (Exception e) {
            e.printStackTrace();
        }


        //   Object temp = new Object();
        //   Class ws = WsCalcLogsNew.class;
        //  WsCalcLogsNew instance = new WsCalcLogsNew();
        //String instance = null;

          /*  if (field.getType().isInstance(String.class)){
                try {
                    temp = field.get(instance);
                    String value = (String) field.get(instance);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            System.out.println(temp);
            strBuffer.append("| Value: " + temp);
        }*/
        //  }

        try {

            String sql = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            StmtParamList paramList = new StmtParamList();
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


                        // Если поле нашли, тогда надо проверить его тип и сконвертить Оракл тип к Java типу
                        if (isFound) {
                            switch (type) {
                                case Types.NUMERIC:
                                    //    Logger.getLogger("").log(Level.SEVERE, "Цифирь", "Мы нашли поле");


                                    /**Теперь надо сконвертить тип поля из класса в тип поля SQL. Связано это с тем, что SQL возвращает везде NUMERIC,
                                     * А в классах есть и long, и double, и integer.
                                     */
                                    int typeForParamList = getLocalFieldType(wsCalcLogsNew, rsmd.getColumnName(i));


                                    // Ищем поле с таким именем в классе
                                    paramList.add(new StmtParam(typeForParamList, searchInClassFieldsAndGet(wsCalcLogsNew, rsmd.getColumnName(i))));

                                    /**
                                     * 1. ЦИКЛ ПО ВСЕМ ПОЛЯМ У НАС УЖЕ ЕСТЬ. МЫ В НЕМ
                                     * 2. НАДО СДЕЛАТЬ ЦИКЛ ПО ВСЕМ ПОЛЯМ КЛАССА
                                     * 3. ВЗЯТЬ ЗНАЧЕНИЕ
                                     * 4. ПИХНУТЬ В ПАРАМЛИСТ
                                     */
                                case Types.VARCHAR:


                                case Types.TIMESTAMP:
                                    //         Logger.getLogger("").log(Level.SEVERE, "ДАТА", "Мы нашли поле");

                            }

                        }


                        //paramList.add(new StmtParam(Types.NUMERIC, rsmd.getColumnName(i)));

                        //   }


                        for (StmtParam field : paramList) {

                       //     Logger.getLogger("").log(Level.SEVERE, String.valueOf(field.getValue()), "Мы нашли поле");

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
                }

                //strBuffer.append("  |  WEBROWSET IS NOT EMPTY |  ");

            }

        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return strBuffer;
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


        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCalcid()));//CALCID
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getNotapplyspecprogdiscount()));//NOTAPPLYSPECPROGDISCOUNT
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getTargetcompany()));//TARGETCOMPANY
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarbrandname()));//CARBRANDNAME
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarmodelname()));//CARMODELNAME    
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCartype()));//CARMODELTYPE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarbodynumber()));//CARMODELTYPE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarchassisnumber()));//CARCHASSISNUMBER
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCreditauto()));//CREDITAUTO
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getLenderid()));//LENDERID         
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getAutorace()));//AUTORACE
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarkind()));//CARKIND
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarvendortype()));//CARVENDORTYPE
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getCarprice()));//CARPRICE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getRightwheel()));//RIGHTWHEEL
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getAntitheftsystem()));//ANTITHEFTSYSTEM
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getThfmechdevice()));//THFMECHDEVICE
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getCarpurchasedate()));//CARPURCHASEDATE
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCarparkcount()));//CARPARKCOUNT
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getPowerauto()));//POWERAUTO
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCarusedtrailer()));//CARUSEDTRAILER
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getCaraccidentplaceinssum()));//CARACCIDENTPLACEINSSUM
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCaraccidentplacekind()));//CARACCIDENTPLACEKIND
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCaraccidentplacenum()));//CARACCIDENTPLACENUM
        paramList.add(new StmtParam(Types.DOUBLE, wsCalcLogsNew.getCarextequipmentinssum()));//CAREXTEQUIPMENTINSSUM
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInsfromdate1()));//INSFROMDATE1
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInstodate1()));//INSTODATE1
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInsfromdate2()));//INSFROMDATE2
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInstodate2()));//INSTODATE2
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInsfromdate3()));//INSFROMDATE3
        paramList.add(new StmtParam(Types.DATE, wsCalcLogsNew.getInstodate3()));//INSTODATE3
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCaruseregion()));//CARUSEREGION
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCaruseregionkladr()));//CARUSEREGIONKLADR
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarcategory()));//CARCATEGORY
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getPerioduse()));//PERIODUSE
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
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getInsurer()));//INSURER
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getOwnerRegion()));//OWNER_REGION
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getDriverlisttype()));//DRIVERLISTTYPE
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getCarmodelcode()));//CARMODELCODE
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getDriverlisttypeosago()));//DRIVERLISTTYPEOSAGO
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getPolicy()));//POLICY
        paramList.add(new StmtParam(Types.INTEGER, wsCalcLogsNew.getCarYear()));//CarYear
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getAdmUser()));//ADMUSER
        paramList.add(new StmtParam(Types.VARCHAR, wsCalcLogsNew.getCrash()));//CRASH
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getHolderRequestId()));//HOLDERREQUESTID
        paramList.add(new StmtParam(Types.NUMERIC, wsCalcLogsNew.getOwnerRequestId()));//OWNERREQUESTID
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


}
