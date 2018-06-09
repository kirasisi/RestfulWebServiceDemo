/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hasisi
 */
@Entity
@Table(name = "RES_CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResCredential.findAll", query = "SELECT r FROM ResCredential r")
    , @NamedQuery(name = "ResCredential.findByUsername", query = "SELECT r FROM ResCredential r WHERE r.username = :username")
    , @NamedQuery(name = "ResCredential.findByHashpw", query = "SELECT r FROM ResCredential r WHERE r.hashpw = :hashpw")})
public class ResCredential implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 128)
    @Column(name = "HASHPW")
    private String hashpw;
    @JoinColumn(name = "RESID", referencedColumnName = "RESID")
    @ManyToOne
    private Resident resid;

    public ResCredential() {
    }

    public ResCredential(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashpw() {
        return hashpw;
    }

    public void setHashpw(String hashpw) {
        this.hashpw = hashpw;
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
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResCredential)) {
            return false;
        }
        ResCredential other = (ResCredential) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smart.ResCredential[ username=" + username + " ]";
    }
    
}
