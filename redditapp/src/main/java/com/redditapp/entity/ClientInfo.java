/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author derek
 */
@Entity
@Table(name = "client_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientInfo.findAll", query = "SELECT c FROM ClientInfo c")
    , @NamedQuery(name = "ClientInfo.findById", query = "SELECT c FROM ClientInfo c WHERE c.id = :id")
    , @NamedQuery(name = "ClientInfo.findByClientId", query = "SELECT c FROM ClientInfo c WHERE c.clientId = :clientId")
    , @NamedQuery(name = "ClientInfo.findByClientSecret", query = "SELECT c FROM ClientInfo c WHERE c.clientSecret = :clientSecret")
    , @NamedQuery(name = "ClientInfo.findBySalt", query = "SELECT c FROM ClientInfo c WHERE c.salt = :salt")
    , @NamedQuery(name = "ClientInfo.findByCreated", query = "SELECT c FROM ClientInfo c WHERE c.created = :created")
    , @NamedQuery(name = "ClientInfo.findByUpdated", query = "SELECT c FROM ClientInfo c WHERE c.updated = :updated")})
public class ClientInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "client_id")
    private String clientId;
    @Size(max = 128)
    @Column(name = "client_secret")
    private String clientSecret;
    @Size(max = 16)
    @Column(name = "salt")
    private String salt;
    @Size(max = 16)
    @Column(name = "state_val", insertable = false)
    private String state_val;
    @Size(max = 32)
    @Column(name = "name")
    private String name;


    public ClientInfo() {
    }

    public ClientInfo(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }   
    
    public String getState() {
        return state_val;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof ClientInfo)) {
            return false;
        }
        ClientInfo other = (ClientInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.ClientInfo[ id=" + id + " ]";
    }
    
}
