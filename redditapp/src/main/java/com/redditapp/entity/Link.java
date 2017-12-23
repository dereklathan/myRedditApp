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
@Table(name = "link")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT l FROM Link l")
    , @NamedQuery(name = "Link.findById", query = "SELECT l FROM Link l WHERE l.id = :id")
    , @NamedQuery(name = "Link.findByCreated", query = "SELECT l FROM Link l WHERE l.created = :created")
    , @NamedQuery(name = "Link.findByUpdated", query = "SELECT l FROM Link l WHERE l.updated = :updated")
    , @NamedQuery(name = "Link.findByPostedBy", query = "SELECT l FROM Link l WHERE l.postedBy = :postedBy")
    , @NamedQuery(name = "Link.findBySelftext", query = "SELECT l FROM Link l WHERE l.selftext = :selftext")
    , @NamedQuery(name = "Link.findByTitle", query = "SELECT l FROM Link l WHERE l.title = :title")
    , @NamedQuery(name = "Link.findByUrl", query = "SELECT l FROM Link l WHERE l.url = :url")
    , @NamedQuery(name = "Link.findByPermalink", query = "SELECT l FROM Link l WHERE l.permalink = :permalink")})
public class Link extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "posted_by")
    private String postedBy;
    @Size(max = 10000)
    @Column(name = "selftext")
    private String selftext;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "permalink")
    private String permalink;
    @JoinColumn(name = "subreddit", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subreddit subreddit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "link")
    private List<VoteLink> voteLinkList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "link")
    private List<Comment> commentList;

    public Link() {
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(Subreddit subreddit) {
        this.subreddit = subreddit;
    }

    @XmlTransient
    public List<VoteLink> getVoteLinkList() {
        return voteLinkList;
    }

    public void setVoteLinkList(List<VoteLink> voteLinkList) {
        this.voteLinkList = voteLinkList;
    }

    @XmlTransient
    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
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
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.Link[ id=" + id + " ]";
    }
    
}
