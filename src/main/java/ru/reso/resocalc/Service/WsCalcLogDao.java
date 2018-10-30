package ru.reso.resocalc.Service;

import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
        //} catch (ClassNotFoundException | SQLException | NamingException e) {
        } catch (SQLException  e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }
    }

    public static String testPing(String sql) {
        String test = "";

        try {

            DBConnection conn = new DBConnection();

            StmtParamList paramList = new StmtParamList();
            paramList.add(new StmtParam(Types.INTEGER, 122865181));
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);
            //calcLog = webRowSet2WsCalcLogsNew(rs);

            if (rs.next()) {
                test = rs.getString("insurantid");
            }


        //} catch (SQLException | NamingException | ClassNotFoundException e) {
        } catch (SQLException  e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return test;
    }

    public static String testLogByCalcID(long calcid) {
        WsCalcLogsNew calcLog = null;
        String rsStr = "";

        try {
            //String sql = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
              String sql = "select * from webauto.WS_CALC_LOGS_NEW t where t.calcid=?";
                          //select * from webauto.WS_CALC_LOGS_NEW t  where t.calcid = ?";
            //String sql = "select * from  webauto.ws_calc_logs_new t where t.calcid = ?"; // OSAGO

            StmtParamList paramList = new StmtParamList();
            //paramList.add(new StmtParam(Types.NUMERIC, calcid));//CALCID
            paramList.add(new StmtParam(Types.INTEGER, 122865181));

            DBConnection conn = new DBConnection();
            rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            Logger.getGlobal().log(Level.INFO, "I have connect from testLogByCalcID" , "ITS OK");
            Logger.getGlobal().log(Level.INFO, rsStr , "Log for result String");


        //} catch (SQLException | NamingException | ClassNotFoundException e) {
        } catch (SQLException e) {
            Logger.getLogger("").log(Level.SEVERE, "Error ocurs while try to close SQL Connection", e);
        }

        return rsStr;
    }

    public static WsCalcLogsNew getLogByCalcID(long calcid) {
        WsCalcLogsNew calcLog = null;

        try {
            //String sql = sqlLogging.SQL_GET_CALC_LOG_BY_ID;
            String sql = "select * from webauto.WS_CALC_LOGS_NEW t where t.calcid=?";

//                        select * from webauto.WS_CALC_LOGS_NEW t where t.calcid = ?";
            Long calc =  122865181L;


            StmtParamList paramList = new StmtParamList();
            //paramList.add(new StmtParam(Types.NUMERIC, calcid));//CALCID
            paramList.add(new StmtParam(Types.BIGINT, calc));
            //paramList.add(new StmtParam(Types.INTEGER, 12286518));

            DBConnection conn = new DBConnection();
            String rsStr = conn.prepareStatementExecuteQuery(sql, paramList);
            //Logger.getGlobal().log(Level.INFO, rsStr , "Log for result String");
            WebRowSet rs = ResoDatabaseInvoke.decodeWebRowSet(rsStr);

            calcLog = webRowSet2WsCalcLogsNew(rs);

        //} catch (SQLException | NamingException | ClassNotFoundException e) {
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
}
