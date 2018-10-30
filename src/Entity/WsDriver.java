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
 * @author KOVAVV
 */
public class WsDriver implements Serializable{
    private static final long serialVersionUID = 1L;
    private String partnerId = "";
    private String FIO = "";
    private Date birthDay;
    private Long age = 0L;
    private String sex = "";
    private Date licenseDate;
    private String licenseSeria = "";
    private String licenseNumber = "";
    private Long kbmClassDriverOsago = -1L;
    private Double kbmIdRequestRsaDriverOsago = -1.0;
    private Integer kbmIdSourceDriverOsago = -1;
    private Double kvs = -1.0;
    private Double kvsOsago = -1.0;
    private Long stage = -1L;
    private Double kbmCoeffDriverOsago = -1.0;
    private Double kbmCoeffDriverCasco = -1.0;
    private Long calcId;
    private Integer relateToRisk = -1;
    
    public WsDriver() { } 
    
    public WsDriver(long calcID) {
        this.setCalcId(calcID);
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getLicenseSeria() {
        return licenseSeria;
    }

    public void setLicenseSeria(String licenseSeria) {
        this.licenseSeria = licenseSeria;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Long getKbmClassDriverOsago() {
        return kbmClassDriverOsago;
    }

    public void setKbmClassDriverOsago(Long kbmClassDriverOsago) {
        this.kbmClassDriverOsago = kbmClassDriverOsago;
    }

    public Double getKbmIdRequestRsaDriverOsago() {
        return kbmIdRequestRsaDriverOsago;
    }

    public void setKbmIdRequestRsaDriverOsago(Double kbmIdRequestRsaDriverOsago) {
        this.kbmIdRequestRsaDriverOsago = kbmIdRequestRsaDriverOsago;
    }

    public Integer getKbmIdSourceDriverOsago() {
        return kbmIdSourceDriverOsago;
    }

    public void setKbmIdSourceDriverOsago(Integer kbmIdSourceDriverOsago) {
        this.kbmIdSourceDriverOsago = kbmIdSourceDriverOsago;
    }

    public Double getKvs() {
        return kvs;
    }

    public void setKvs(Double kvs) {
        this.kvs = kvs;
    }

    public Double getKvsOsago() {
        return kvsOsago;
    }

    public void setKvsOsago(Double kvsOsago) {
        this.kvsOsago = kvsOsago;
    }

    public Long getStage() {
        return stage;
    }

    public void setStage(Long stage) {
        this.stage = stage;
    }

    public Double getKbmCoeffDriverOsago() {
        return kbmCoeffDriverOsago;
    }

    public void setKbmCoeffDriverOsago(Double kbmCoeffDriverOsago) {
        this.kbmCoeffDriverOsago = kbmCoeffDriverOsago;
    }

    public Double getKbmCoeffDriverCasco() {
        return kbmCoeffDriverCasco;
    }

    public void setKbmCoeffDriverCasco(Double kbmCoeffDriverCasco) {
        this.kbmCoeffDriverCasco = kbmCoeffDriverCasco;
    }

    public Long getCalcId() {
        return calcId;
    }

    public void setCalcId(Long calcId) {
        this.calcId = calcId;
    }

    public Integer getRelateToRisk() {
        return relateToRisk;
    }

    public void setRelateToRisk(Integer relateToRisk) {
        this.relateToRisk = relateToRisk;
    }
}