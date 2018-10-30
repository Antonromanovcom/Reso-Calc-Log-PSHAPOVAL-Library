package ru.reso.resocalc.Entity.SubEntities;

import ru.reso.resocalc.Entity.Interfaces.Unit;

import java.util.Date;

/**
 * Подкласс. Тот самый, что будет представлен в ArrayList'е класса WsPartner
 */

public class PartnerUnit implements Unit {

    private Long id;
    private Integer type;
    private String isresident;
    private String name;
    private Date birthdate;
    private String inn;
    private Integer doctype;
    private String docseria;
    private String docnumber;
    private Date docissuedate;
    private String addrkladr;
    private String vipclient;
    private Integer parthner_type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIsresident() {
        return isresident;
    }

    public void setIsresident(String isresident) {
        this.isresident = isresident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Integer getDoctype() {
        return doctype;
    }

    public void setDoctype(Integer doctype) {
        this.doctype = doctype;
    }

    public String getDocseria() {
        return docseria;
    }

    public void setDocseria(String docseria) {
        this.docseria = docseria;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }

    public Date getDocissuedate() {
        return docissuedate;
    }

    public void setDocissuedate(Date docissuedate) {
        this.docissuedate = docissuedate;
    }

    public String getAddrkladr() {
        return addrkladr;
    }

    public void setAddrkladr(String addrkladr) {
        this.addrkladr = addrkladr;
    }

    public String getVipclient() {
        return vipclient;
    }

    public void setVipclient(String vipclient) {
        this.vipclient = vipclient;
    }

    public Integer getParthner_type() {
        return parthner_type;
    }

    public void setParthner_type(Integer parthner_type) {
        this.parthner_type = parthner_type;
    }
}
