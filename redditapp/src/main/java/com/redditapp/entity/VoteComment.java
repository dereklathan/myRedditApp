/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author derek
 */
@Entity
@Table(name = "vote_comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoteComment.findAll", query = "SELECT v FROM VoteComment v")
    , @NamedQuery(name = "VoteComment.findById", query = "SELECT v FROM VoteComment v WHERE v.id = :id")
    , @NamedQuery(name = "VoteComment.findByCreated", query = "SELECT v FROM VoteComment v WHERE v.created = :created")
    , @NamedQuery(name = "VoteComment.findByUpdated", query = "SELECT v FROM VoteComment v WHERE v.updated = :updated")
    , @NamedQuery(name = "VoteComment.findByScore", query = "SELECT v FROM VoteComment v WHERE v.score = :score")})
public class VoteComment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "score")
    private Integer score;
    @JoinColumn(name = "comment", referencedColumnName = "id")
    @ManyToOne
    private Comment comment;
    @JoinColumn(name = "reddit_user", referencedColumnName = "id")
    @ManyToOne
    private RedditUser redditUser;

    public VoteComment() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
        if (!(object instanceof VoteComment)) {
            return false;
        }
        VoteComment other = (VoteComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.VoteComment[ id=" + id + " ]";
    }
    
}
