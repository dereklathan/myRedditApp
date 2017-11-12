/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author derek
 */
@Entity
@Table(name = "reddit_user_client_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedditUserClientInfo.findAll", query = "SELECT r FROM RedditUserClientInfo r")
    , @NamedQuery(name = "RedditUserClientInfo.findById", query = "SELECT r FROM RedditUserClientInfo r WHERE r.id = :id")
    , @NamedQuery(name = "RedditUserClientInfo.findByCreated", query = "SELECT r FROM RedditUserClientInfo r WHERE r.created = :created")
    , @NamedQuery(name = "RedditUserClientInfo.findByUpdated", query = "SELECT r FROM RedditUserClientInfo r WHERE r.updated = :updated")})
public class RedditUserClientInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "client_info", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ClientInfo clientInfo;
    @JoinColumn(name = "reddit_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RedditUser redditUser;
    @JoinColumn(name = "token_info", referencedColumnName = "id")
    @ManyToOne
    @Cascade({CascadeType.MERGE, CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private TokenInfo tokenInfo;

    public RedditUserClientInfo() {
    }

    public RedditUserClientInfo(Integer id) {
        this.id = id;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public RedditUser getRedditUser() {
        return redditUser;
    }

    public void setRedditUser(RedditUser redditUser) {
        this.redditUser = redditUser;
    }

    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
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
        if (!(object instanceof RedditUserClientInfo)) {
            return false;
        }
        RedditUserClientInfo other = (RedditUserClientInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.RedditUserClientInfo[ id=" + id + " ]";
    }
    
}
