/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hasisi
 */
@Entity
@Table(name = "ELEC_USAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElecUsage.findAll", query = "SELECT e FROM ElecUsage e")
    , @NamedQuery(name = "ElecUsage.findByUsageid", query = "SELECT e FROM ElecUsage e WHERE e.usageid = :usageid")
    , @NamedQuery(name = "ElecUsage.findByUDate", query = "SELECT e FROM ElecUsage e WHERE e.uDate = :uDate")
    , @NamedQuery(name = "ElecUsage.findByUHour", query = "SELECT e FROM ElecUsage e WHERE e.uHour = :uHour")
    , @NamedQuery(name = "ElecUsage.findByFridgeUsage", query = "SELECT e FROM ElecUsage e WHERE e.fridgeUsage = :fridgeUsage")
    , @NamedQuery(name = "ElecUsage.findByAirconUsage", query = "SELECT e FROM ElecUsage e WHERE e.airconUsage = :airconUsage")
    , @NamedQuery(name = "ElecUsage.findByWashmaUsage", query = "SELECT e FROM ElecUsage e WHERE e.washmaUsage = :washmaUsage")
    , @NamedQuery(name = "ElecUsage.findByTemperature", query = "SELECT e FROM ElecUsage e WHERE e.temperature = :temperature")
    , @NamedQuery(name = "ElecUsage.findByEmailANDFnameANDSname", query = "SELECT e FROM ElecUsage e WHERE e.resid.email = :email AND "
                + "e.resid.fName = :fName AND e.resid.sName = :sName")
})
public class ElecUsage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USAGEID")
    private Integer usageid;
    @Column(name = "U_DATE")
    @Temporal(TemporalType.DATE)
    private Date uDate;
    @Column(name = "U_HOUR")
    private Integer uHour;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FRIDGE_USAGE")
    private Double fridgeUsage;
    @Column(name = "AIRCON_USAGE")
    private Double airconUsage;
    @Column(name = "WASHMA_USAGE")
    private Double washmaUsage;
    @Column(name = "TEMPERATURE")
    private Integer temperature;
    @JoinColumn(name = "RESID", referencedColumnName = "RESID")
    @ManyToOne
    private Resident resid;

    public ElecUsage() {
    }

    public ElecUsage(Integer usageid) {
        this.usageid = usageid;
    }

    public Integer getUsageid() {
        return usageid;
    }

    public void setUsageid(Integer usageid) {
        this.usageid = usageid;
    }

    public Date getUDate() {
        return uDate;
    }

    public void setUDate(Date uDate) {
        this.uDate = uDate;
    }

    public Integer getUHour() {
        return uHour;
    }

    public void setUHour(Integer uHour) {
        this.uHour = uHour;
    }

    public Double getFridgeUsage() {
        return fridgeUsage;
    }

    public void setFridgeUsage(Double fridgeUsage) {
        this.fridgeUsage = fridgeUsage;
    }

    public Double getAirconUsage() {
        return airconUsage;
    }

    public void setAirconUsage(Double airconUsage) {
        this.airconUsage = airconUsage;
    }

    public Double getWashmaUsage() {
        return washmaUsage;
    }

    public void setWashmaUsage(Double washmaUsage) {
        this.washmaUsage = washmaUsage;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Resident getResid() {
        return resid;
    }

    public void setResid(Resident resid) {
        this.resid = resid;
    }
    
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usageid != null ? usageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElecUsage)) {
            return false;
        }
        ElecUsage other = (ElecUsage) object;
        if ((this.usageid == null && other.usageid != null) || (this.usageid != null && !this.usageid.equals(other.usageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smart.ElecUsage[ usageid=" + usageid + " ]";
    }
    
}
