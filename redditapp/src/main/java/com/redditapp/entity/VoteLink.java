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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author derek
 */
@Entity
@Table(name = "vote_link")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoteLink.findAll", query = "SELECT v FROM VoteLink v")
    , @NamedQuery(name = "VoteLink.findById", query = "SELECT v FROM VoteLink v WHERE v.id = :id")
    , @NamedQuery(name = "VoteLink.findByCreated", query = "SELECT v FROM VoteLink v WHERE v.created = :created")
    , @NamedQuery(name = "VoteLink.findByUpdated", query = "SELECT v FROM VoteLink v WHERE v.updated = :updated")
    , @NamedQuery(name = "VoteLink.findByScore", query = "SELECT v FROM VoteLink v WHERE v.score = :score")})
public class VoteLink extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    @JoinColumn(name = "link", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Link link;
    @JoinColumn(name = "reddit_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RedditUser redditUser;

    public VoteLink() {
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public RedditUser getRedditUser() {
        return redditUser;
    }

    public void setRedditUser(RedditUser redditUser) {
        this.redditUser = redditUser;
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
        if (!(object instanceof VoteLink)) {
            return false;
        }
        VoteLink other = (VoteLink) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.VoteLink[ id=" + id + " ]";
    }
    
}
