/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author derek
 */
@Entity
@Table(name = "subreddit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subreddit.findAll", query = "SELECT s FROM Subreddit s")
    , @NamedQuery(name = "Subreddit.findById", query = "SELECT s FROM Subreddit s WHERE s.id = :id")
    , @NamedQuery(name = "Subreddit.findByCreated", query = "SELECT s FROM Subreddit s WHERE s.created = :created")
    , @NamedQuery(name = "Subreddit.findByUpdated", query = "SELECT s FROM Subreddit s WHERE s.updated = :updated")
    , @NamedQuery(name = "Subreddit.findByName", query = "SELECT s FROM Subreddit s WHERE s.name = :name")})
public class Subreddit extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subreddit")
    private List<Link> linkList;

    public Subreddit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
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
        if (!(object instanceof Subreddit)) {
            return false;
        }
        Subreddit other = (Subreddit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.Subreddit[ id=" + id + " ]";
    }
    
}
