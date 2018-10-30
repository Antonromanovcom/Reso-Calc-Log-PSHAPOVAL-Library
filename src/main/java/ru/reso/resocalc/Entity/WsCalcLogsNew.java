/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Entity;

import ru.reso.resocalc.Entity.Interfaces.CalcEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author ROMAB
 *
 * По сути класс основного логгирования.
 */
public class WsCalcLogsNew implements Serializable, CalcEntity {

    private static final long serialVersionUID = 1L;
    private Long calcid; // BL расчета
    private String notapplyspecprogdiscount = ""; // Признак не применять коммерческие программы при расчете премии
    private String targetcompany = "";  //Название компании, для которой делается расчет
    private String carbrandname = ""; //Название марки ТС
    private String carmodelname = ""; //Название модели ТС
    private String cartype = ""; //Тип ТС КАСКО
    private String carbodynumber = ""; //Номер кузова ТС
    private String carchassisnumber = ""; //Номер шасси ТС
    private String creditauto = ""; //ТС находится в залоге
    private Long lenderid = -1L; //Код кредитной организации
    private String autorace = ""; //Пробег менее 1000 км (новое ТС)
    private Integer carkind = -1; //Вид ТС (3 - пасажирские авто)
    private Integer carvendortype = -1; //Тип производства ТС (иностранное/отечественное)
    private double carprice = -1.0; //Цена ТС в руб.
    private String rightwheel = ""; //Руль находится с правой стороны
    private Long antitheftsystem = -1L; //Код спутниковой противоугонной системы
    private String thfmechdevice = ""; //Механическое противоугонное устройство
    private Date carpurchasedate; //Дата приобретения ТС
    private Long carparkcount = -1L; //Кол-во ТС в парке
    private Long powerauto = -1L; //Мощность ТС, л.с.
    private String carusedtrailer = ""; //ТС используется с прицепом
    private Double caraccidentplaceinssum = -1.0; //НС страховая сумма за одно место в ТС
    private Long caraccidentplacekind = -1L; //НС система страхования
    private Long caraccidentplacenum = -1L; //НС кол-во страхуемых мест
    private double carextequipmentinssum = -1.0; //ДО страховая сумма
    private Date insfromdate1; // ОСАГО I Период использования ТС с
    private Date instodate1; //ОСАГО I Период использования ТС по
    private Date insfromdate2; //ОСАГО II Период использования ТС с
    private Date instodate2; //ОСАГО II Период использования ТС по
    private Date insfromdate3; //ОСАГО III Период использования ТС с
    private Date instodate3; //ОСАГО III Период использования ТС по
    private Long caruseregion = -1L; //ОСАГО Регион использования ТС
    private String caruseregionkladr = ""; //ОСАГО Регион использование ТС, код КЛАДР
    private Integer carcategory = -1; //ОСАГО Категория ТС
    private Long perioduse = -1L; //ОСАГО Период использования ТС
    private String carvehicletypeosago = ""; //ОСАГО Тип использования ТС -
    private double inssumdgo = -1.0; //ДГО Страховая сумма
    private String withweardgo = ""; //ДГО с учетом износа
    private String kbmcheckcardiagnisticcard = "";
    private String casco = "Н"; //Наличие риска "Каско"
    private String damage = "Н"; //Наличие риска "Ущерб"
    private String theft = "Н"; //Наличие риска "Хищение"
    private String help = "Н"; //Наличие риска "Ресо Автопомощь"
    private String gap = "Н"; //Наличие риска "Гап"
    private String accident = "Н"; //Наличие риска "Несчастный Случай"
    private String equipment = "Н"; //Наличие риска "Доп Оборудование"
    private String osago = "Н"; //Наличие риска "Осаго"
    private String dgo = "Н"; //Наличие риска "ДГО"
    private String crash = "Н"; //Наличие риска "СТОЛКНОВЕНИЕ"
    private String pnumber = ""; // Номер бланка возобновляемого (пролонгируемого) полиса
    private Integer commercprogrammid = -1; //ID типа программы скидок применяемой при расчете полиса
    private Integer carownertypeid = -1; //ид тип собственника тс
    private Integer insuranttypeid = -1; //ид тип страхователя
    private Integer carDamageQuanityManual = -1; //Количество убытков (указал пользователь)
    private String isanothersk = ""; //Страхователь перешел из другой СК
    private Long insurer = -1L; // Код страховой компании , из которой перешел страхователь
    private Long owner_region = -1L; //Регион места жительства собственика КАСКО
    private String driverlisttype = ""; //Тип списка лиц допущенных к управлению
    private Long carmodelcode = -1L; //Код модели ТС
    private String driverlisttypeosago = ""; //Тип списка лиц допущенных к управлению ОСАГО
    private Integer policy = -1;     //Номер полиса, в котором производился расчет
    private Integer carYear = -1; //Год выпуска ТС
    private String admUser = ""; //Имя пользователя, который осуществлял расчет из программы полисы
    private long holderRequestId = 0; //Идентификатор страхователя в системе Equifax
    private long ownerRequestId = 0; //Идентификатор собственника в системе Equifax
    private int equifaxScore = 0; //скоринг балл   в системе Equifax
    private String equifaxerrmsg; //Ошибка EquiFax
    private int policyType;     //Тип полиса (0 - первоначальный, 1 - пролонгация)
    private int customKb;     //Дельта КВ,  указанная пользователем
    private LinkedHashMap<String, String> hash = new LinkedHashMap<>();     //Стринговый хэш всего объекта для сравнения


    public WsCalcLogsNew() {

    }

    public WsCalcLogsNew(Long calcid) {
        this.calcid = calcid;
    }

    public Long getCalcid() {
        return calcid;
    }

    public void setCalcid(Long calcid) {
        this.calcid = calcid;
    }

    public String getNotapplyspecprogdiscount() {
        return notapplyspecprogdiscount;
    }

    public void setNotapplyspecprogdiscount(String notapplyspecprogdiscount) {
        this.notapplyspecprogdiscount = notapplyspecprogdiscount;
    }

    public String getTargetcompany() {
        return targetcompany;
    }

    public void setTargetcompany(String targetcompany) {
        this.targetcompany = targetcompany;
    }

    public String getCarbrandname() {
        return carbrandname;
    }

    public void setCarbrandname(String carbrandname) {
        this.carbrandname = carbrandname;
    }

    public String getCarmodelname() {
        return carmodelname;
    }

    public void setCarmodelname(String carmodelname) {
        this.carmodelname = carmodelname;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getCarbodynumber() {
        return carbodynumber;
    }

    public void setCarbodynumber(String carbodynumber) {
        this.carbodynumber = carbodynumber;
    }

    public String getCarchassisnumber() {
        return carchassisnumber;
    }

    public void setCarchassisnumber(String carchassisnumber) {
        this.carchassisnumber = carchassisnumber;
    }

    public String getCreditauto() {
        return creditauto;
    }

    public void setCreditauto(String creditauto) {
        this.creditauto = creditauto;
    }

    public Long getLenderid() {
        return lenderid;
    }

    public void setLenderid(Long lenderid) {
        this.lenderid = lenderid;
    }

    public String getAutorace() {
        return autorace;
    }

    public void setAutorace(String autorace) {
        this.autorace = autorace;
    }

    public Integer getCarkind() {
        return carkind;
    }

    public void setCarkind(Integer carkind) {
        this.carkind = carkind;
    }

    public Integer getCarvendortype() {
        return carvendortype;
    }

    public void setCarvendortype(Integer carvendortype) {
        this.carvendortype = carvendortype;
    }

    public double getCarprice() {
        return carprice;
    }

    public void setCarprice(double carprice) {
        this.carprice = carprice;
    }

    public String getRightwheel() {
        return rightwheel;
    }

    public void setRightwheel(String rightwheel) {
        this.rightwheel = rightwheel;
    }

    public Long getAntitheftsystem() {
        return antitheftsystem;
    }

    public void setAntitheftsystem(Long antitheftsystem) {
        this.antitheftsystem = antitheftsystem;
    }

    public String getThfmechdevice() {
        return thfmechdevice;
    }

    public void setThfmechdevice(String thfmechdevice) {
        this.thfmechdevice = thfmechdevice;
    }

    public Date getCarpurchasedate() {
        return carpurchasedate;
    }

    public void setCarpurchasedate(Date carpurchasedate) {
        this.carpurchasedate = carpurchasedate;
    }

    public Long getCarparkcount() {
        return carparkcount;
    }

    public void setCarparkcount(Long carparkcount) {
        this.carparkcount = carparkcount;
    }

    public Long getPowerauto() {
        return powerauto;
    }

    public void setPowerauto(Long powerauto) {
        this.powerauto = powerauto;
    }

    public String getCarusedtrailer() {
        return carusedtrailer;
    }

    public void setCarusedtrailer(String carusedtrailer) {
        this.carusedtrailer = carusedtrailer;
    }

    public Double getCaraccidentplaceinssum() {
        return caraccidentplaceinssum;
    }

    public void setCaraccidentplaceinssum(Double caraccidentplaceinssum) {
        this.caraccidentplaceinssum = caraccidentplaceinssum;
    }

    public Long getCaraccidentplacekind() {
        return caraccidentplacekind;
    }

    public void setCarAccidentPlaceKind(Long caraccidentplacekind) {
        this.caraccidentplacekind = caraccidentplacekind;
    }

    public Long getCaraccidentplacenum() {
        return caraccidentplacenum;
    }

    public void setCaraccidentplacenum(Long caraccidentplacenum) {
        this.caraccidentplacenum = caraccidentplacenum;
    }

    public double getCarextequipmentinssum() {
        return carextequipmentinssum;
    }

    public void setCarextequipmentinssum(double carextequipmentinssum) {
        this.carextequipmentinssum = carextequipmentinssum;
    }

    public Date getInsfromdate1() {
        return insfromdate1;
    }

    public void setInsfromdate1(Date insfromdate1) {
        this.insfromdate1 = insfromdate1;
    }

    public Date getInstodate1() {
        return instodate1;
    }

    public void setInstodate1(Date instodate1) {
        this.instodate1 = instodate1;
    }

    public Date getInsfromdate2() {
        return insfromdate2;
    }

    public void setInsfromdate2(Date insfromdate2) {
        this.insfromdate2 = insfromdate2;
    }

    public Date getInstodate2() {
        return instodate2;
    }

    public void setInstodate2(Date instodate2) {
        this.instodate2 = instodate2;
    }

    public Date getInsfromdate3() {
        return insfromdate3;
    }

    public void setInsfromdate3(Date insfromdate3) {
        this.insfromdate3 = insfromdate3;
    }

    public Date getInstodate3() {
        return instodate3;
    }

    public void setInstodate3(Date instodate3) {
        this.instodate3 = instodate3;
    }

    public Long getCaruseregion() {
        return caruseregion;
    }

    public void setCaruseregion(Long caruseregion) {
        this.caruseregion = caruseregion;
    }

    public String getCaruseregionkladr() {
        return caruseregionkladr;
    }

    public void setCaruseregionkladr(String caruseregionkladr) {
        this.caruseregionkladr = caruseregionkladr;
    }

    public Integer getCarcategory() {
        return carcategory;
    }

    public void setCarcategory(Integer carcategory) {
        this.carcategory = carcategory;
    }

    public Long getPerioduse() {
        return perioduse;
    }

    public void setPerioduse(Long perioduse) {
        this.perioduse = perioduse;
    }

    public String getCarvehicletypeosago() {
        return carvehicletypeosago;
    }

    public void setCarvehicletypeosago(String carvehicletypeosago) {
        this.carvehicletypeosago = carvehicletypeosago;
    }

    public double getInssumdgo() {
        return inssumdgo;
    }

    public void setInssumdgo(double inssumdgo) {
        this.inssumdgo = inssumdgo;
    }

    public String getWithweardgo() {
        return withweardgo;
    }

    public void setWithweardgo(String withweardgo) {
        this.withweardgo = withweardgo;
    }

    public String getKbmcheckcardiagnisticcard() {
        return kbmcheckcardiagnisticcard;
    }

    public void setKbmcheckcardiagnisticcard(String kbmcheckcardiagnisticcard) {
        this.kbmcheckcardiagnisticcard = kbmcheckcardiagnisticcard;
    }

    public String getCasco() {
        return casco;
    }

    public void setCasco(String casco) {
        this.casco = casco;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getTheft() {
        return theft;
    }

    public void setTheft(String theft) {
        this.theft = theft;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getOsago() {
        return osago;
    }

    public void setOsago(String osago) {
        this.osago = osago;
    }

    public String getDgo() {
        return dgo;
    }

    public void setDgo(String dgo) {
        this.dgo = dgo;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public Integer getCommercprogrammid() {
        return commercprogrammid;
    }

    public void setCommercprogrammid(Integer commercprogrammid) {
        this.commercprogrammid = commercprogrammid;
    }

    public Integer getCarownertypeid() {
        return carownertypeid;
    }

    public Integer getInsuranttypeid() {
        return insuranttypeid;
    }

    public void setCarownertypeid(Integer carownertypeid) {
        this.carownertypeid = carownertypeid;
    }

    public void setInsuranttypeid(Integer insuranttypeid) {
        this.insuranttypeid = insuranttypeid;
    }

    public Integer getCarDamageQuanityManual() {
        return carDamageQuanityManual;
    }

    public void setCarDamageQuanityManual(Integer carDamageQuanityManual) {
        this.carDamageQuanityManual = carDamageQuanityManual;
    }

    public String getIsanothersk() {
        return isanothersk;
    }

    public Long getInsurer() {
        return insurer;
    }

    public void setIsanothersk(String isanothersk) {
        this.isanothersk = isanothersk;
    }

    public void setInsurer(Long insurer) {
        this.insurer = insurer;
    }

    public Long getOwnerRegion() {
        return owner_region;
    }

    public void setOwnerRegion(Long ownerregion) {
        this.owner_region = ownerregion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calcid != null ? calcid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof WsCalcLogsNew)) {
            return false;
        }
        WsCalcLogsNew other = (WsCalcLogsNew) object;
        return !((this.calcid == null && other.calcid != null) || (this.calcid != null && !this.calcid.equals(other.calcid)));
    }

    @Override
    public String toString() {
        return "ru.reso.common.logs.WsCalcLogsNew[ calcid=" + calcid + " ]";
    }

    /**
     * @return the driverlisttype
     */
    public String getDriverlisttype() {
        return driverlisttype;
    }

    /**
     * @param driverlisttype the driverlisttype to set
     */
    public void setDriverlisttype(String driverlisttype) {
        this.driverlisttype = driverlisttype;
    }

    public Long getCarmodelcode() {
        return carmodelcode;
    }

    public void setCarmodelcode(Long carmodelcode) {
        this.carmodelcode = carmodelcode;
    }

    /**
     * @return the driverlisttypeosago
     */
    public String getDriverlisttypeosago() {
        return driverlisttypeosago;
    }

    /**
     * @param driverlisttypeosago the driverlisttypeosago to set
     */
    public void setDriverlisttypeosago(String driverlisttypeosago) {
        this.driverlisttypeosago = driverlisttypeosago;
    }

    public Integer getPolicy() {
        return policy;
    }

    public void setPolicy(Integer policy) {
        this.policy = policy;
    }

    public Long getOwnerregion() {
        return owner_region;
    }

    public void setOwnerregion(Long ownerregion) {
        this.owner_region = ownerregion;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public String getAdmUser() {
        return admUser;
    }

    public void setAdmUser(String admUser) {
        this.admUser = admUser;
    }

    public String getCrash() {
        return crash;
    }

    public void setCrash(String crash) {
        this.crash = crash;
    } 

    public int getEquifaxScore() {
        return equifaxScore;
    }

    public void setCaraccidentplacekind(Long caraccidentplacekind) {
        this.caraccidentplacekind = caraccidentplacekind;
    }

    public long getHolderRequestId() {
        return holderRequestId;
    }

    public long getOwnerRequestId() {
        return ownerRequestId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setEquifaxScore(int equifaxScore) {
        this.equifaxScore = equifaxScore;
    }

    public void setHolderRequestId(long holderRequestId) {
        this.holderRequestId = holderRequestId;
    }

    public void setOwnerRequestId(long ownerRequestId) {
        this.ownerRequestId = ownerRequestId;
    }

    public String getEquifaxerrmsg() {
        return equifaxerrmsg;
    }

    public void setEquifaxerrmsg(String equifaxerrmsg) {
        this.equifaxerrmsg = equifaxerrmsg;
    }

    public int getPolicyType() {
        return policyType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }

    public int getCustomKb() {
        return customKb;
    }

    public void setCustomKb(int customKb) {
        this.customKb = customKb;
    }

    public HashMap<String, String> getHash() {
        return hash;
    }

    public void addToHash(String key, String value) {
        this.hash.put(key, value);
    }

    public void addToHashAll(LinkedHashMap<String, String> map) {
        this.hash.putAll(map);
    }
}
