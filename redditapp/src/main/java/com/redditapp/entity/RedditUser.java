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
 * @author derek
 */
@Entity
@Table(name = "reddit_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedditUser.findAll", query = "SELECT r FROM RedditUser r")
    , @NamedQuery(name = "RedditUser.findById", query = "SELECT r FROM RedditUser r WHERE r.id = :id")
    , @NamedQuery(name = "RedditUser.findByUsername", query = "SELECT r FROM RedditUser r WHERE r.username = :username")
    , @NamedQuery(name = "RedditUser.findBySalt", query = "SELECT r FROM RedditUser r WHERE r.salt = :salt")
    , @NamedQuery(name = "RedditUser.findByCreated", query = "SELECT r FROM RedditUser r WHERE r.created = :created")
    , @NamedQuery(name = "RedditUser.findByUpdated", query = "SELECT r FROM RedditUser r WHERE r.updated = :updated")
    , @NamedQuery(name = "RedditUser.findByPassword", query = "SELECT r FROM RedditUser r WHERE r.password = :password")})
public class RedditUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "added_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User addedBy;


    public RedditUser() {
    }

    public RedditUser(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User getAddedBy() {
        return addedBy;
    }
    
    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
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
        if (!(object instanceof RedditUser)) {
            return false;
        }
        RedditUser other = (RedditUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.RedditUser[ id=" + id + " ]";
    }
    
}
