/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
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
            + " POLICYTYPE) "
            + " values (?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?,?,?,?,?,?,"
            +          "?,?,?,?,?)";
    
    
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
            + " NOTAPPLYSPECPROGDISCOUNT = ?, "
            + " TARGETCOMPANY = ?, "         
            + " CARBRANDNAME = ?, "
            + " CARMODELNAME = ?, "
            + " CARTYPE = ?, "
            + " CARBODYNUMBER = ?,"
            + " CARCHASSISNUMBER = ?,"
            + " CREDITAUTO = ?,"
            + " LENDERID = ?,"
            + " AUTORACE = ?,"
            + " CARKIND = ?,"
            + " CARVENDORTYPE = ?,"
            + " CARPRICE = ?,"
            + " RIGHTWHEEL = ?,"
            + " ANTITHEFTSYSTEM = ?,"
            + " THFMECHDEVICE = ?,"
            + " CARPURCHASEDATE = ?,"
            + " CARPARKCOUNT = ?,"
            + " POWERAUTO = ?,"
            + " CARUSEDTRAILER = ?,"
            + " CARACCIDENTPLACEINSSUM = ?,"
            + " CARACCIDENTPLACEKIND = ?,"
            + " CARACCIDENTPLACENUM = ?,"
            + " CAREXTEQUIPMENTINSSUM = ?,"
            + " INSFROMDATE1 = ?,"
            + " INSTODATE1 = ?,"
            + " INSFROMDATE2 = ?,"
            + " INSTODATE2 = ?,"
            + " INSFROMDATE3 = ?,"
            + " INSTODATE3 = ?,"
            + " CARUSEREGION = ?,"
            + " CARUSEREGIONKLADR,"
            + " CARCATEGORY = ?,"
            + " PERIODUSE = ?,"
            + " CARVEHICLETYPEOSAGO = ?,"
            + " INSSUMDGO = ?,"
            + " WITHWEARDGO = ?,"
            + " KBMCHECKCARDIAGNISTICCARD = ?,"
            + " CASCO = ?,"
            + " DAMAGE = ?,"
            + " THEFT = ?,"
            + " HELP = ?,"
            + " GAP = ?,"
            + " ACCIDENT = ?,"
            + " EQUIPMENT = ?,"
            + " OSAGO = ?,"
            + " DGO = ?,"
            + " PNUMBER = ?,"
            + " COMMERCPROGRAMMID = ?,"
            + " CARDAMAGEQUANITYMANUAL = ?,"
            + " ISANOTHERSK = ?,"
            + " INSURER = ?,"
            + " OWNER_REGION = ?,"
            + " DRIVERLISTTYPE = ?,"
            + " CARMODELCODE = ?,"
            + " DRIVERLISTTYPEOSAGO = ?,"
            + " POLICY = ?"
            + " where "
            + "CALCID = ? ";

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
            = "select t.value as value from WS_COEFF_CALC t "
            + " where t.calcid = ? "
            + " and t.coefid = ? ";
    
    final String SQL_GET_CALC_LOG_BY_ID
            = " select * from WS_CALC_LOGS_NEW t "
            + " where t.calcid = ?";

}