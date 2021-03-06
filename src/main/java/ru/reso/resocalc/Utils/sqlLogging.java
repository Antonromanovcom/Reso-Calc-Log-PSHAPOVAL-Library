/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Utils;

/**
 * В этом интерфейсе хранятся все SQL-запросы.
 * @author SHAPPN
 */
public interface sqlLogging {

    String SQL_INSERT_COMM_LOG
            = " insert into webauto.WS_COMMON_LOGS "
            + " (CALCID,"
            + " AGENTID,"
            + " AGENCYID,"
            + " REQUESTMESSAGE,"
            + " PDATE,"
            + " FROMDATE,"
            + " TODATE,"
            + " PAYPERIOD,"
            + " PURCHASEREGION,"
            + " INSURANCEREGION,"
            + " DAMAGEPROGRAMMSTOA,"
            + " OFFICIALSTOA,"
            + " CARREGNUMBER,"
            + " CARVIN,"
            + " CARWEIGHT,"
            + " CARTONNAGE,"
            + " CARSEATS,"
            + " CARUSETYPE,"
            + " CARUSEREGION,"
            + " KBMCLASSFINALOSAGO,"
            + " KBMIDREQUESTRSAFINALOSAGO,"
            + " KBMIDSOURCEFINALOSAGO,"
            + " KBMCOEFFFINALOSAGO,"
            + " REQUESTTYPE,"
            + " SP_PROGRAMM,"
            + " SERVER,"
            + " CLIENT_IP,"
            + " CALCREQSOURCETYPEID,"
            + " SESSIONID, "
            + " INPUT_MODE, "
            + " ERROR,"
            + " MAIN_PNUMBER,"
            + " RASDATE,"
            + " CREATE_MODE,"
            + " POLICY_HEADER,"
            + " POLICY_STATE,"
            + " PARENT_STATE)"
            + " values "
            + "(?,?,?,?,?,"
            + "?,?,?,?,?,"
            + "?,?,?,?,?,"
            + "?,?,?,?,?,"
            + "?,?,?,?,?,"
            + "?,?,?,?,?,"
            + "?,?,?,?,?,?,?)";

    final String SQL_UPDATE_COMM_LOG
            = "update webauto.WS_COMMON_LOGS "
            + " set "
            + " AGENTID = ?,"
            + " AGENCYID = ?,"
            + " REQUESTMESSAGE = ?,"
            + " PDATE = ?,"
            + " FROMDATE = ?,"
            + " TODATE = ?,"
            + " PAYPERIOD = ?,"
            + " PURCHASEREGION = ?,"
            + " INSURANCEREGION = ?,"
            + " DAMAGEPROGRAMMSTOA = ?,"
            + " OFFICIALSTOA = ?,"
            + " CARREGNUMBER = ?,"
            + " CARVIN = ?,"
            + " CARWEIGHT = ?,"
            + " CARTONNAGE = ?,"
            + " CARSEATS = ?,"
            + " CARUSETYPE = ?,"
            + " CARUSEREGION = ?,"
            + " KBMCLASSFINALOSAGO = ?,"
            + " KBMIDREQUESTRSAFINALOSAGO = ?,"
            + " KBMIDSOURCEFINALOSAGO = ?,"
            + " KBMCOEFFFINALOSAGO = ?,"
            + " SP_PROGRAMM = ?,"
            + " RESPONSEMESSAGE = ?,"
            + " SERVER = ?,"
            + " CLIENT_IP = ?,"
            + " CALCREQSOURCETYPEID = ?,"
            + " SESSIONID = ? "
            + " ERROR = ?"
            + " where "
            + " CALCID = ?  && "
            + " REQUESTTYPE = ? ";

    final String SQL_UPDATE_LOG_OWNERREGION
            = "update webauto.WS_CALC_LOGS_NEW "
            + " set "
            + " OWNER_REGION = ?"
            + " where "
            + "CALCID = ? ";

    final String SQL_UPADATE_COM_LOG_ERROR
            = "update webauto.ws_common_logs "
            + " set "
            + " error = ?"
            + " where "
            + "calcid = ? ";

    final String SQL_INSERT_WsCalcLogNEW
            = " insert into webauto.WS_CALC_LOGS_NEW "
            + "( CALCID, "
            + " NOTAPPLYSPECPROGDISCOUNT, "
            + " TARGETCOMPANY, "          
            + " CARBRANDNAME, "
            + " CARMODELNAME, "
            + " CARTYPE, "
            + " CARBODYNUMBER, "
            + " CARCHASSISNUMBER, "
            + " CREDITAUTO, "
            + " LENDERID, "
            + " AUTORACE, "
            + " CARKIND, "
            + " CARVENDORTYPE, "
            + " CARPRICE, "
            + " RIGHTWHEEL, "
            + " ANTITHEFTSYSTEM, "
            + " THFMECHDEVICE, "
            + " CARPURCHASEDATE, "
            + " CARPARKCOUNT, "
            + " POWERAUTO, "
            + " CARUSEDTRAILER, "
            + " CARACCIDENTPLACEINSSUM, "
            + " CARACCIDENTPLACEKIND, "
            + " CARACCIDENTPLACENUM, "
            + " CAREXTEQUIPMENTINSSUM, "
            + " INSFROMDATE1, "
            + " INSTODATE1, "
            + " INSFROMDATE2, "
            + " INSTODATE2, "
            + " INSFROMDATE3, "
            + " INSTODATE3, "
            + " CARUSEREGION, "
            + " CARUSEREGIONKLADR,"
            + " CARCATEGORY, "
            + " PERIODUSE, "
            + " CARVEHICLETYPEOSAGO, "
            + " INSSUMDGO, "
            + " WITHWEARDGO, "
            + " KBMCHECKCARDIAGNISTICCARD, "
            + " CASCO, "
            + " DAMAGE, "
            + " THEFT, "
            + " HELP, "
            + " GAP, "
            + " ACCIDENT, "
            + " EQUIPMENT, "
            + " OSAGO, "
            + " DGO, "
            + " CRASH,"
            + " PNUMBER, "
            + " COMMERCPROGRAMMID, "
            + " CARDAMAGEQUANITYMANUAL, "
            + " ISANOTHERSK, "
            + " INSURER, "
            + " OWNER_REGION, "
            + " DRIVERLISTTYPE, "
            + " CARMODELCODE, "
            + " DRIVERLISTTYPEOSAGO, "
            + " POLICY,"
            + " CARYEAR,"
            + " ADMUSER,"
            + " HOLDERREQUESTID,"
            + " OWNERREQUESTID,"
            + " EQUIFAXSCORE,"
            + " POLICYTYPE) "
            + " values (?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?)";


    final String SQL_INSERT_WsCalcLogNEW2
            = " insert into webauto.WS_CALC_LOGS_NEW "
            + "( CALCID, "
            + " NOTAPPLYSPECPROGDISCOUNT, "
            + " TARGETCOMPANY, "
            + " CARBRANDNAME, "
            + " CARMODELNAME, "
            + " CARTYPE, "
            + " CARBODYNUMBER, "
            + " CARCHASSISNUMBER, "
            + " CREDITAUTO, "
            + " LENDERID, "
            + " AUTORACE, "
            + " CARKIND, "
            + " CARVENDORTYPE, "
            + " CARPRICE, "
            + " RIGHTWHEEL, "
            + " ANTITHEFTSYSTEM, "
            + " THFMECHDEVICE, "
            + " CARPURCHASEDATE, "
            + " CARPARKCOUNT, "
            + " POWERAUTO, "
            + " CARUSEDTRAILER, "
            + " CARACCIDENTPLACEINSSUM, "
            + " CARACCIDENTPLACEKIND, "
            + " CARACCIDENTPLACENUM, "
            + " CAREXTEQUIPMENTINSSUM, "
            + " INSFROMDATE1, "
            + " INSTODATE1, "
            + " INSFROMDATE2, "
            + " INSTODATE2, "
            + " INSFROMDATE3, "
            + " INSTODATE3, "
            + " CARUSEREGION, "
            + " CARUSEREGIONKLADR,"
            + " CARCATEGORY, "
            + " PERIODUSE, "
            + " CARVEHICLETYPEOSAGO, "
            + " INSSUMDGO, "
            + " WITHWEARDGO, "
            + " KBMCHECKCARDIAGNISTICCARD, "
            + " CASCO, "
            + " DAMAGE, "
            + " THEFT, "
            + " HELP, "
            + " GAP, "
            + " ACCIDENT, "
            + " EQUIPMENT, "
            + " OSAGO, "
            + " DGO, "
            + " PNUMBER, "
            + " COMMERCPROGRAMMID, "
            + " CAROWNERTYPEID, "
            + " INSURANTTYPEID, "
            + " CARDAMAGEQUANITYMANUAL, "
            + " ISANOTHERSK, "
            + " INSURER,"
            + " OWNER_REGION, "
            + " DRIVERLISTTYPE, "
            + " CARMODELCODE, "
            + " POLICY,"
            + " DRIVERLISTTYPEOSAGO, "
            + " CARYEAR, "
            + " ADMUSER, "
            + " CRASH,"
            + " HOLDERREQUESTID, "
            + " OWNERREQUESTID, "
            + " EQUIFAXSCORE, "
            + " EQUIFAXERRMSG, "
            + " POLICYTYPE, "
            + " CUSTOMKB)"
            + " values (?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?)";


     final String SQL_SELECT_WsCalcLogNEW_BY_ID
            = " select "
            + " CALCID, "
            + " NOTAPPLYSPECPROGDISCOUNT, "
            + " TARGETCOMPANY, "          
            + " CARBRANDNAME, "
            + " CARMODELNAME, "
            + " CARTYPE, "
            + " CARBODYNUMBER, "
            + " CARCHASSISNUMBER, "
            + " CREDITAUTO, "
            + " LENDERID, "
            + " AUTORACE, "
            + " CARKIND, "
            + " CARVENDORTYPE, "
            + " CARPRICE, "
            + " RIGHTWHEEL, "
            + " ANTITHEFTSYSTEM, "
            + " THFMECHDEVICE, "
            + " CARPURCHASEDATE, "
            + " CARPARKCOUNT, "
            + " POWERAUTO, "
            + " CARUSEDTRAILER, "
            + " CARACCIDENTPLACEINSSUM, "
            + " CARACCIDENTPLACEKIND, "
            + " CARACCIDENTPLACENUM, "
            + " CAREXTEQUIPMENTINSSUM, "
            + " INSFROMDATE1, "
            + " INSTODATE1, "
            + " INSFROMDATE2, "
            + " INSTODATE2, "
            + " INSFROMDATE3, "
            + " INSTODATE3, "
            + " CARUSEREGION, "
            + " CARUSEREGIONKLADR,"
            + " CARCATEGORY, "
            + " PERIODUSE, "
            + " CARVEHICLETYPEOSAGO, "
            + " INSSUMDGO, "
            + " WITHWEARDGO, "
            + " KBMCHECKCARDIAGNISTICCARD, "
            + " CASCO, "
            + " DAMAGE, "
            + " THEFT, "
            + " HELP, "
            + " GAP, "
            + " ACCIDENT, "
            + " EQUIPMENT, "
            + " OSAGO, "
            + " DGO, "
            + " PNUMBER, "
            + " COMMERCPROGRAMMID, "
            + " CARDAMAGEQUANITYMANUAL, "
            + " ISANOTHERSK, "
            + " INSURER, "
            + " OWNER_REGION, "
            + " DRIVERLISTTYPE, "
            + " CARMODELCODE, "
            + " DRIVERLISTTYPEOSAGO, "
            + " POLICY,"
            + " CARYEAR,"
            + " ADMUSER,"
            + " CRASH,"
            + " HOLDERREQUESTID,"
            + " OWNERREQUESTID,"
            + " EQUIFAXSCORE,"
            + " POLICYTYPE "
            + " FROM  webauto.WS_CALC_LOGS_NEW  cln "
            + " WHERE CALCID = ? ";

    final String SQL_UPDATE_WsCalcLogNEW
            = "update webauto.WS_CALC_LOGS_NEW "
            + " set "
            + " CALCID, "
            + " NOTAPPLYSPECPROGDISCOUNT, "
            + " TARGETCOMPANY, "
            + " CARBRANDNAME, "
            + " CARMODELNAME, "
            + " CARTYPE, "
            + " CARBODYNUMBER, "
            + " CARCHASSISNUMBER, "
            + " CREDITAUTO, "
            + " LENDERID, "
            + " AUTORACE, "
            + " CARKIND, "
            + " CARVENDORTYPE, "
            + " CARPRICE, "
            + " RIGHTWHEEL, "
            + " ANTITHEFTSYSTEM, "
            + " THFMECHDEVICE, "
            + " CARPURCHASEDATE, "
            + " CARPARKCOUNT, "
            + " POWERAUTO, "
            + " CARUSEDTRAILER, "
            + " CARACCIDENTPLACEINSSUM, "
            + " CARACCIDENTPLACEKIND, "
            + " CARACCIDENTPLACENUM, "
            + " CAREXTEQUIPMENTINSSUM, "
            + " INSFROMDATE1, "
            + " INSTODATE1, "
            + " INSFROMDATE2, "
            + " INSTODATE2, "
            + " INSFROMDATE3, "
            + " INSTODATE3, "
            + " CARUSEREGION, "
            + " CARUSEREGIONKLADR,"
            + " CARCATEGORY, "
            + " PERIODUSE, "
            + " CARVEHICLETYPEOSAGO, "
            + " INSSUMDGO, "
            + " WITHWEARDGO, "
            + " KBMCHECKCARDIAGNISTICCARD, "
            + " CASCO, "
            + " DAMAGE, "
            + " THEFT, "
            + " HELP, "
            + " GAP, "
            + " ACCIDENT, "
            + " EQUIPMENT, "
            + " OSAGO, "
            + " DGO, "
            + " PNUMBER, "
            + " COMMERCPROGRAMMID, "
            + " CARDAMAGEQUANITYMANUAL, "
            + " ISANOTHERSK, "
            + " INSURER, "
            + " OWNER_REGION, "
            + " DRIVERLISTTYPE, "
            + " CARMODELCODE, "
            + " DRIVERLISTTYPEOSAGO, "
            + " POLICY,"
            + " CARYEAR,"
            + " ADMUSER,"
            + " CRASH,"
            + " HOLDERREQUESTID,"
            + " OWNERREQUESTID,"
            + " EQUIFAXSCORE,"
            + " POLICYTYPE "
            + " where "
            + "CALCID = ? ";

    final String SQL_UPDATE_WsCalcLogNEW2
            = "update webauto.WS_CALC_LOGS_NEW "
            + " set "
            + "( CALCID, "
            + " NOTAPPLYSPECPROGDISCOUNT, "
            + " TARGETCOMPANY, "
            + " CARBRANDNAME, "
            + " CARMODELNAME, "
            + " CARTYPE, "
            + " CARBODYNUMBER, "
            + " CARCHASSISNUMBER, "
            + " CREDITAUTO, "
            + " LENDERID, "
            + " AUTORACE, "
            + " CARKIND, "
            + " CARVENDORTYPE, "
            + " CARPRICE, "
            + " RIGHTWHEEL, "
            + " ANTITHEFTSYSTEM, "
            + " THFMECHDEVICE, "
            + " CARPURCHASEDATE, "
            + " CARPARKCOUNT, "
            + " POWERAUTO, "
            + " CARUSEDTRAILER, "
            + " CARACCIDENTPLACEINSSUM, "
            + " CARACCIDENTPLACEKIND, "
            + " CARACCIDENTPLACENUM, "
            + " CAREXTEQUIPMENTINSSUM, "
            + " INSFROMDATE1, "
            + " INSTODATE1, "
            + " INSFROMDATE2, "
            + " INSTODATE2, "
            + " INSFROMDATE3, "
            + " INSTODATE3, "
            + " CARUSEREGION, "
            + " CARUSEREGIONKLADR,"
            + " CARCATEGORY, "
            + " PERIODUSE, "
            + " CARVEHICLETYPEOSAGO, "
            + " INSSUMDGO, "
            + " WITHWEARDGO, "
            + " KBMCHECKCARDIAGNISTICCARD, "
            + " CASCO, "
            + " DAMAGE, "
            + " THEFT, "
            + " HELP, "
            + " GAP, "
            + " ACCIDENT, "
            + " EQUIPMENT, "
            + " OSAGO, "
            + " DGO, "
            + " PNUMBER, "
            + " COMMERCPROGRAMMID, "
            + " CAROWNERTYPEID, "
            + " INSURANTTYPEID, "
            + " CARDAMAGEQUANITYMANUAL, "
            + " ISANOTHERSK, "
            + " INSURER, "
            + " OWNER_REGION, "
            + " DRIVERLISTTYPE, "
            + " CARMODELCODE, "
            + " POLICY,"
            + " DRIVERLISTTYPEOSAGO, "
            + " CARYEAR, "
            + " ADMUSER, "
            + " CRASH,"
            + " HOLDERREQUESTID, "
            + " OWNERREQUESTID, "
            + " EQUIFAXSCORE, "
            + " EQUIFAXERRMSG, "
            + " POLICYTYPE, "
            + " CUSTOMKB)"
            + " where "
            + "CALCID = ? ";


    final String SQL_UPDATE_WsCalcLogNEW3
            = "update webauto.WS_CALC_LOGS_NEW "
            + " set "
            + " NOTAPPLYSPECPROGDISCOUNT = ? "
            + " where "
            + "CALCID = 122867777";

    final String SQL_INSERT_WS_DRIVERS
            = " insert into webauto.WS_DRIVERS "
            + " ( FIO,"
            + " BIRTHDATE, "
            + " AGE, "
            + " SEX, "
            + " LICENCEDATE, "
            + " LICENCESERIA, "
            + " LICENCENUMBER, "
            + " KBMCLASSDRIVEROSAGO, "
            + " KBMIDREQUESTRSADRIVEROSAGO, "
            + " KBMIDSOURCEDRIVEROSAGO, "
            + " KVS,"
            + " KVSOSAGO,"
            + " STAGE,"
            + " KBMCOEFFDRIVEROSAGO, "
            + " KBMCOEFFDRIVERCASCO, "
            + " CALCID,"
            + " RELATE_TO_RISK) "
            + " values (?,?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?,?,?,?)";

    final String SQL_INSERT_WS_PARTHNER
            = " insert into webauto.WS_PARTNER "
            + " ( CALCID,"
            + " ID, "
            + " TYPE, "
            + " ISRESIDENT, "
            + " NAME, "
            + " BIRTHDATE, "
            + " INN, "
            + " DOCTYPE, "
            + " DOCSERIA, "
            + " DOCNUMBER, "
            + " DOCISSUEDATE,"
            + " ADDRKLADR,"
            + " VIPCLIENT,"
            + " PARTHNER_TYPE "
            + " ) "
            + " values (?,?,?,?,?,?,?,?,?,?,"
            + "?,?,?,?)";

    final String SQL_INSERT_WS_PREMIUM
            = " insert into webauto.WS_PREMIUM "
            + " ( CALCID,"
            + " DEDUCTIBLE_SUM, "
            + " PREMIUMTYPE, "
            + " PREMIUM_SUM)"
            + " values (?,?,?,?)";

    final String SQL_UPDATE_WS_COEFF_CALC
            = " update webauto.WS_COEFF_CALC "
            + " set "
            + "   VALUE = ? "
            + " where "
            + "  CALCID = ? && "
            + "  COEFID = ?";
    
     final String SQL_UPDATE_EQUIFAX_DATA_IN_WS_CALC_LOG_NEW
            = " update webauto.ws_calc_logs_new "
            + " set "
            + " HOLDERREQUESTID = ?, "
            + " OWNERREQUESTID = ?, "
            + " EQUIFAXSCORE = ?, "
            + " EQUIFAXERRMSG = ? "
            + " where "
            + "  CALCID = ?";

    final String SQL_INSERT_WS_COEFF_CALC
            = " insert into webauto.WS_COEFF_CALC "
            + " (CALCID, "
            + " COEFID, "
            + " VALUE) "
            + " values (?,?,?) ";
    
    
    final String SQL_GET_CALC_COEFF_BY_ID
            = "select * from WS_COEFF_CALC t "
            + " where t.calcid = ?";


    final String SQL_GET_CALC_COEFF_COUNT_BY_ID
            = "select count(*) as COUNT from WS_COEFF_CALC t where t.calcid = ?";


    final String SQL_GET_CALC_COEFF_BY_ID_OLD
            = "select t.value as value from WS_COEFF_CALC t "
            + " where t.calcid = ? "
            + " and t.coefid = ? ";
    
    final String SQL_GET_CALC_LOG_BY_ID
            = " select * from webauto.WS_CALC_LOGS_NEW t "
            + " where t.calcid = ?";

    final String SQL_TEST_ANTON
            = " select * from WS_CALC_LOGS_NEW t "
            + " where t.calcid = ?";

    final String SQL_TEST_CALC_ID
            = "select * from (select * from WS_CALC_LOGS_NEW t order by t.calcid)  where ROWNUM < 11";

    final String SQL_GET_COEFF_DESC_BY_ID
            = " select t.DECCRIPTION AS DESCR  from webauto.WS_DCT_COEFFICIENTS t where t.ID = ?";


    final String SQL_GET_BONUSES_BY_ID
            = "select * from webauto.WS_PREMIUM t where t.calcid = ?";


    final String SQL_GET_BONUSES_COUNT_BY_ID
            = "select count(*) as COUNT from webauto.WS_PREMIUM t where t.calcid = ?";


    final String SQL_GET_NON_MATCHING_PREMIUMS
            = "select * from webauto.WS_PREMIUM t WHERE t.CALCID=? AND t.PREMIUMTYPE != ANY (select d.PREMIUMTYPE from webauto.WS_PREMIUM d WHERE CALCID = ?)";

    final String SQL_GET_DRIVERS
            = "select * from webauto.WS_DRIVERS  WHERE CALCID=?";

    final String SQL_GET_PARTNERS
            = "select * from webauto.WS_PARTNER WHERE CALCID=?";

    final String SQL_GET_COMMON_LOGS = "select * from webauto.WS_COMMON_LOGS WHERE CALCID=?";


    final String SQL_GET_PREMIUM_DESC_BY_ID
            = "select t.DESCRIPTION AS DESCR from webauto.WS_DCT_PREMIUM t WHERE t.ID = ?";


    final String SQL_GET_WS_CALC_LOG_DESCRIPTION
            = " select COLUMN_NAME, C.COMMENTS from user_col_comments C join user_tab_cols K using(TABLE_NAME,COLUMN_NAME) where table_name = 'WS_CALC_LOGS_NEW' AND COLUMN_NAME = ?";

    final String SQL_GET_WS_COMMON_LOGS_DESCRIPTION
            = " select COLUMN_NAME, C.COMMENTS from user_col_comments C join user_tab_cols K using(TABLE_NAME,COLUMN_NAME) where table_name = 'WS_COMMON_LOGS' AND COLUMN_NAME = ?";

    final String SQL_GET_COEFFS_COUNT_BY_ID
            = "select count(*) as COUNT from webauto.WS_COEFF_CALC t where t.calcid = ?";




}
