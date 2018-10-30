/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author SHAPPN
 */
public class WsCommonLogs implements Serializable {
    private static final long serialVersionUID = 1L;
    private int input_mode = 3;
   
    private long calcid;
    private Long agentid;
    private Long agencyid;
 
    private String requestmessage;
  
    private Date pdate;
   
    private Date fromdate;
   
    private Date todate;
    private long payperiod;
    private String purchaseregion;
    private long insuranceregion;
 
    private int damageprogrammstoa;
    private String officialstoa;
    private String carregnumber;
    private String carvin;
    private Long carweight;
    private Long cartonnage;
    private Long carseats;
    private int carusetype;
    private Long caruseregion;
    private Long kbmclassfinalosago;
    private Double kbmidrequestrsafinalosago;
    private int kbmidsourcefinalosago;  
    private double kbmcoefffinalosago;  
    private Long id;
    private String requesttype;   
    private String responsemessage;  
    private int spProgramm;
    private String server;
    private String clientIP;
    private int CalcReqSourceTypeID; 
    private String SessionID;   
    private String Error; 
    private String main_pnumber; 
    private Date rasDate;
    private int createMode;
    private long policyHeader;
    private int policyState;
    private int parentState;
    

    public WsCommonLogs() {
        this.main_pnumber = "";
        this.carvin = "";
        this.carregnumber = "";
        this.officialstoa = "";
        this.purchaseregion = "";
        this.requestmessage = "";
        this.responsemessage = "";
        this.requesttype = "";
        this.server = "";
        this.clientIP = "";
        this.SessionID = "";
        this.Error = "";

    }

    public WsCommonLogs(long calcid) {
        this.main_pnumber = "";
        this.calcid = calcid;
        this.carvin = "";
        this.carregnumber = "";
        this.officialstoa = "";
        this.purchaseregion = "";
        this.requestmessage = "";
        this.responsemessage = "";
        this.requesttype = "";
        this.server = "";
        this.clientIP = "";
        this.SessionID = "";
        this.Error = "";
    }
   

    public long getCalcid() {
        return calcid;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    public Long getAgentid() {
        return agentid;
    }

    public void setAgentid(Long agentid) {
        this.agentid = agentid;
    }

    public Long getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(Long agencyid) {
        this.agencyid = agencyid;
    }

    public String getRequestmessage() {
        return requestmessage;
    }

    public void setRequestmessage(String requestmessage) {
        this.requestmessage = requestmessage;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public long getPayperiod() {
        return payperiod;
    }

    public void setPayperiod(long payperiod) {
        this.payperiod = payperiod;
    }

    public String getPurchaseregion() {
        return purchaseregion;
    }

    public void setPurchaseregion(String purchaseregion) {
        this.purchaseregion = purchaseregion;
    }

    public long getInsuranceregion() {
        return insuranceregion;
    }

    public void setInsuranceregion(long insuranceregion) {
        this.insuranceregion = insuranceregion;
    }

    public int getDamageprogrammstoa() {
        return damageprogrammstoa;
    }

    public void setDamageprogrammstoa(int damageprogrammstoa) {
        this.damageprogrammstoa = damageprogrammstoa;
    }

    public String getOfficialstoa() {
        return officialstoa;
    }

    public void setOfficialstoa(String officialstoa) {
        this.officialstoa = officialstoa;
    }

    public String getCarregnumber() {
        return carregnumber;
    }

    public void setCarregnumber(String carregnumber) {
        this.carregnumber = carregnumber;
    }

    public String getCarvin() {
        return carvin;
    }

    public void setCarvin(String carvin) {
        this.carvin = carvin;
    }

    public Long getCarweight() {
        return carweight;
    }

    public void setCarweight(Long carweight) {
        this.carweight = carweight;
    }

    public Long getCartonnage() {
        return cartonnage;
    }

    public void setCartonnage(Long cartonnage) {
        this.cartonnage = cartonnage;
    }

    public Long getCarseats() {
        return carseats;
    }

    public void setCarseats(Long carseats) {
        this.carseats = carseats;
    }

    public int getCarusetype() {
        return carusetype;
    }

    public void setCarusetype(int carusetype) {
        this.carusetype = carusetype;
    }

    public Long getCaruseregion() {
        return caruseregion;
    }

    public void setCaruseregion(Long caruseregion) {
        this.caruseregion = caruseregion;
    }

    public Long getKbmclassfinalosago() {
        return kbmclassfinalosago;
    }

    public void setKbmclassfinalosago(Long kbmclassfinalosago) {
        this.kbmclassfinalosago = kbmclassfinalosago;
    }

    public double getKbmidrequestrsafinalosago() {
        return kbmidrequestrsafinalosago;
    }

    public void setKbmidrequestrsafinalosago(Double kbmidrequestrsafinalosago) {
        this.kbmidrequestrsafinalosago = kbmidrequestrsafinalosago;
    }

    public int getKbmidsourcefinalosago() {
        return kbmidsourcefinalosago;
    }

    public void setKbmidsourcefinalosago(int kbmidsourcefinalosago) {
        this.kbmidsourcefinalosago = kbmidsourcefinalosago;
    }

    public double getKbmcoefffinalosago() {
        return kbmcoefffinalosago;
    }

    public void setKbmcoefffinalosago(double kbmcoefffinalosago) {
        this.kbmcoefffinalosago = kbmcoefffinalosago;
    }

    public Long getId() {
        return id;
    }   

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }

    public int getSpProgramm() {
        return spProgramm;
    }

    public void setSpProgramm(int spProgramm) {
        this.spProgramm = spProgramm;
    }
    
     public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    } 

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
    
     public int getCalcReqSourceTypeID() {
        return CalcReqSourceTypeID;
    }

    public void setCalcReqSourceTypeID(int CalcReqSourceTypeID) {
        this.CalcReqSourceTypeID = CalcReqSourceTypeID;
    }
    
    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }  

    public int getInput_mode() {
        return input_mode;
    }

    public void setInput_mode(int input_mode) {
        this.input_mode = input_mode;
    }
    
    
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsCommonLogs)) {
            return false;
        }
        WsCommonLogs other = (WsCommonLogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    } 

    @Override
    public String toString() {
        return "ru.reso.common.logs.WsCommonLogs[ id=" + id + " ]";
    }   

    /**
     * @return the main_pnumber
     */
    public String getMain_pnumber() {
        return main_pnumber;
    }

    /**
     * @param main_pnumber the main_pnumber to set
     */
    public void setMain_pnumber(String main_pnumber) {
        this.main_pnumber = main_pnumber;
    }

    public int getCreateMode() {
        return createMode;
    }

    public int getParentState() {
        return parentState;
    }

    public long getPolicyHeader() {
        return policyHeader;
    }

    public int getPolicyState() {
        return policyState;
    }

    public void setCreateMode(int createMode) {
        this.createMode = createMode;
    }

    public void setParentState(int parentState) {
        this.parentState = parentState;
    }

    public void setPolicyHeader(long policyHeader) {
        this.policyHeader = policyHeader;
    }

    public void setPolicyState(int policyState) {
        this.policyState = policyState;
    }    

    public void setRasDate(Date rasDate) {
        this.rasDate = rasDate;
    }

    public Date getRasDate() {
        return rasDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
  
}
