/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.reso.resocalc.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHAPPN
 */
@Entity
@Table(name = "WS_PARTNER", catalog = "", schema = "WEBAUTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsPartner.findAll", query = "SELECT w FROM WsPartner w"),
    @NamedQuery(name = "WsPartner.findByCalcid", query = "SELECT w FROM WsPartner w WHERE w.wsPartnerPK.calcid = :calcid"),
    @NamedQuery(name = "WsPartner.findById", query = "SELECT w FROM WsPartner w WHERE w.id = :id"),
    @NamedQuery(name = "WsPartner.findByType", query = "SELECT w FROM WsPartner w WHERE w.type = :type"),
    @NamedQuery(name = "WsPartner.findByIsresident", query = "SELECT w FROM WsPartner w WHERE w.isresident = :isresident"),
    @NamedQuery(name = "WsPartner.findByName", query = "SELECT w FROM WsPartner w WHERE w.name = :name"),
    @NamedQuery(name = "WsPartner.findByBirthdate", query = "SELECT w FROM WsPartner w WHERE w.birthdate = :birthdate"),
    @NamedQuery(name = "WsPartner.findByInn", query = "SELECT w FROM WsPartner w WHERE w.inn = :inn"),
    @NamedQuery(name = "WsPartner.findByDoctype", query = "SELECT w FROM WsPartner w WHERE w.doctype = :doctype"),
    @NamedQuery(name = "WsPartner.findByDocseria", query = "SELECT w FROM WsPartner w WHERE w.docseria = :docseria"),
    @NamedQuery(name = "WsPartner.findByDocnumber", query = "SELECT w FROM WsPartner w WHERE w.docnumber = :docnumber"),
    @NamedQuery(name = "WsPartner.findByDocissuedate", query = "SELECT w FROM WsPartner w WHERE w.docissuedate = :docissuedate"),
    @NamedQuery(name = "WsPartner.findByAddrkladr", query = "SELECT w FROM WsPartner w WHERE w.addrkladr = :addrkladr"),
    @NamedQuery(name = "WsPartner.findByVipclient", query = "SELECT w FROM WsPartner w WHERE w.vipclient = :vipclient"),
    @NamedQuery(name = "WsPartner.findByParthnerType", query = "SELECT w FROM WsPartner w WHERE w.wsPartnerPK.parthnerType = :parthnerType")})
public class WsPartner implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WsPartnerPK wsPartnerPK;
    @Column(name = "ID")
    private Long id;
    @Column(name = "TYPE")
    private Integer type;
    @Column(name = "ISRESIDENT", length = 1)
    private String isresident;
    @Column(name = "NAME", length = 255)
    private String name;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @Column(name = "INN", length = 12)
    private String inn;
    @Column(name = "DOCTYPE")
    private Integer doctype;
    @Column(name = "DOCSERIA", length = 20)
    private String docseria;
    @Column(name = "DOCNUMBER", length = 20)
    private String docnumber;
    @Column(name = "DOCISSUEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docissuedate;
    @Column(name = "ADDRKLADR", length = 40)
    private String addrkladr;
    @Column(name = "VIPCLIENT", length = 1)
    private String vipclient;

    public WsPartner() {
    }

    public WsPartner(WsPartnerPK wsPartnerPK) {
        this.wsPartnerPK = wsPartnerPK;
    }

    public WsPartner(long calcid, int parthnerType) {
        this.wsPartnerPK = new WsPartnerPK(calcid, parthnerType);
    }

    public WsPartnerPK getWsPartnerPK() {
        return wsPartnerPK;
    }

    public void setWsPartnerPK(WsPartnerPK wsPartnerPK) {
        this.wsPartnerPK = wsPartnerPK;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wsPartnerPK != null ? wsPartnerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsPartner)) {
            return false;
        }
        WsPartner other = (WsPartner) object;
        if ((this.wsPartnerPK == null && other.wsPartnerPK != null) || (this.wsPartnerPK != null && !this.wsPartnerPK.equals(other.wsPartnerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.reso.common.logs.WsPartner[ wsPartnerPK=" + wsPartnerPK + " ]";
    }
    
}
