/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author SHAPPN
 */
@Embeddable
public class WsPartnerPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CALCID", nullable = false)
    private long calcid;
    @Basic(optional = false)
    @Column(name = "PARTHNER_TYPE", nullable = false)
    private int parthnerType;

    public WsPartnerPK() {
    }

    public WsPartnerPK(long calcid, int parthnerType) {
        this.calcid = calcid;
        this.parthnerType = parthnerType;
    }

    public long getCalcid() {
        return calcid;
    }

    public void setCalcid(long calcid) {
        this.calcid = calcid;
    }

    public int getParthnerType() {
        return parthnerType;
    }

    public void setParthnerType(int parthnerType) {
        this.parthnerType = parthnerType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) calcid;
        hash += (int) parthnerType;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WsPartnerPK)) {
            return false;
        }
        WsPartnerPK other = (WsPartnerPK) object;
        if (this.calcid != other.calcid) {
            return false;
        }
        if (this.parthnerType != other.parthnerType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.reso.common.logs.WsPartnerPK[ calcid=" + calcid + ", parthnerType=" + parthnerType + " ]";
    }
    
}
