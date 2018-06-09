/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hasisi
 */
@Entity
@Table(name = "RESIDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resident.findAll", query = "SELECT r FROM Resident r")
    , @NamedQuery(name = "Resident.findByResid", query = "SELECT r FROM Resident r WHERE r.resid = :resid")
    , @NamedQuery(name = "Resident.findByFName", query = "SELECT r FROM Resident r WHERE r.fName = :fName")
    , @NamedQuery(name = "Resident.findBySName", query = "SELECT r FROM Resident r WHERE r.sName = :sName")
    , @NamedQuery(name = "Resident.findByDob", query = "SELECT r FROM Resident r WHERE r.dob = :dob")
    , @NamedQuery(name = "Resident.findByAddress", query = "SELECT r FROM Resident r WHERE r.address = :address")
    , @NamedQuery(name = "Resident.findByPostcode", query = "SELECT r FROM Resident r WHERE r.postcode = :postcode")
    , @NamedQuery(name = "Resident.findByEmail", query = "SELECT r FROM Resident r WHERE r.email = :email")
    , @NamedQuery(name = "Resident.findByMobile", query = "SELECT r FROM Resident r WHERE r.mobile = :mobile")
    , @NamedQuery(name = "Resident.findByNoOfRes", query = "SELECT r FROM Resident r WHERE r.noOfRes = :noOfRes")
    , @NamedQuery(name = "Resident.findByEnergyProvider", query = "SELECT r FROM Resident r WHERE r.energyProvider = :energyProvider")})
public class Resident implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESID")
    private Integer resid;
    @Size(max = 20)
    @Column(name = "F_NAME")
    private String fName;
    @Size(max = 20)
    @Column(name = "S_NAME")
    private String sName;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 50)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 20)
    @Column(name = "POSTCODE")
    private String postcode;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "NO_OF_RES")
    private Integer noOfRes;
    @Size(max = 20)
    @Column(name = "ENERGY_PROVIDER")
    private String energyProvider;
    @OneToMany(mappedBy = "resid")
    private Collection<ResCredential> resCredentialCollection;
    @OneToMany(mappedBy = "resid")
    private Collection<ElecUsage> elecUsageCollection;

    public Resident() {
    }

    public Resident(Integer resid) {
        this.resid = resid;
    }

    public Integer getResid() {
        return resid;
    }

    public void setResid(Integer resid) {
        this.resid = resid;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) throws ParseException {
        //SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        //Date date = formatter.parse(dob);
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getNoOfRes() {
        return noOfRes;
    }

    public void setNoOfRes(Integer noOfRes) {
        this.noOfRes = noOfRes;
    }

    public String getEnergyProvider() {
        return energyProvider;
    }

    public void setEnergyProvider(String energyProvider) {
        this.energyProvider = energyProvider;
    }

    @XmlTransient
    public Collection<ResCredential> getResCredentialCollection() {
        return resCredentialCollection;
    }

    public void setResCredentialCollection(Collection<ResCredential> resCredentialCollection) {
        this.resCredentialCollection = resCredentialCollection;
    }

    @XmlTransient
    public Collection<ElecUsage> getElecUsageCollection() {
        return elecUsageCollection;
    }

    public void setElecUsageCollection(Collection<ElecUsage> elecUsageCollection) {
        this.elecUsageCollection = elecUsageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resid != null ? resid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resident)) {
            return false;
        }
        Resident other = (Resident) object;
        if ((this.resid == null && other.resid != null) || (this.resid != null && !this.resid.equals(other.resid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smart.Resident[ resid=" + resid + " ]";
    }
    
}
