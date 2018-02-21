/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "comment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id")
    , @NamedQuery(name = "Comment.findByCreated", query = "SELECT c FROM Comment c WHERE c.created = :created")
    , @NamedQuery(name = "Comment.findByUpdated", query = "SELECT c FROM Comment c WHERE c.updated = :updated")
    , @NamedQuery(name = "Comment.findByBody", query = "SELECT c FROM Comment c WHERE c.body = :body")
    , @NamedQuery(name = "Comment.findByPostedBy", query = "SELECT c FROM Comment c WHERE c.postedBy = :postedBy")
    , @NamedQuery(name = "Comment.findByPermalink", query = "SELECT c FROM Comment c WHERE c.permalink = :permalink")
    , @NamedQuery(name = "Comment.findByThingId", query = "SELECT c FROM Comment c WHERE c.thingId = :thingId")})
public class Comment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10000)
    @Column(name = "body")
    private String body;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "posted_by")
    private String postedBy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "permalink")
    private String permalink;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "thing_id")
    private String thingId;
    @OneToMany(mappedBy = "comment")
    private List<VoteComment> voteCommentList;
    @OneToMany(mappedBy = "parent")
    private List<Comment> commentList;
    @JoinColumn(name = "parent", referencedColumnName = "id")
    @ManyToOne
    private Comment parent;
    @JoinColumn(name = "link", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Link link;

    public Comment() {
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
    
    public String getThingId() {
        return thingId;
    }
    
    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    @XmlTransient
    public List<VoteComment> getVoteCommentList() {
        return voteCommentList;
    }

    public void setVoteCommentList(List<VoteComment> voteCommentList) {
        this.voteCommentList = voteCommentList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.Comment[ id=" + id + " ]";
    }
    
}
