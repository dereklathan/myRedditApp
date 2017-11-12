/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "token_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TokenInfo.findAll", query = "SELECT t FROM TokenInfo t")
    , @NamedQuery(name = "TokenInfo.findById", query = "SELECT t FROM TokenInfo t WHERE t.id = :id")
    , @NamedQuery(name = "TokenInfo.findByAccessToken", query = "SELECT t FROM TokenInfo t WHERE t.accessToken = :accessToken")
    , @NamedQuery(name = "TokenInfo.findByTokenType", query = "SELECT t FROM TokenInfo t WHERE t.tokenType = :tokenType")
    , @NamedQuery(name = "TokenInfo.findByRefreshToken", query = "SELECT t FROM TokenInfo t WHERE t.refreshToken = :refreshToken")
    , @NamedQuery(name = "TokenInfo.findByScope", query = "SELECT t FROM TokenInfo t WHERE t.scope = :scope")
    , @NamedQuery(name = "TokenInfo.findByCreated", query = "SELECT t FROM TokenInfo t WHERE t.created = :created")
    , @NamedQuery(name = "TokenInfo.findByUpdated", query = "SELECT t FROM TokenInfo t WHERE t.updated = :updated")
    , @NamedQuery(name = "TokenInfo.findByExpiration", query = "SELECT t FROM TokenInfo t WHERE t.expiration = :expiration")
    , @NamedQuery(name = "TokenInfo.findByRemainingRequests", query = "SELECT t FROM TokenInfo t WHERE t.remainingRequests = :remainingRequests")})
public class TokenInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "access_token")
    private String accessToken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "token_type")
    private String tokenType;
    @Size(max = 64)
    @Column(name = "refresh_token")
    private String refreshToken;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "scope")
    private String scope;

    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration")
    private LocalDateTime expiration;
    @Column(name = "remaining_requests")
    private Integer remainingRequests;
    @OneToMany(mappedBy = "tokenInfo")
    private List<RedditUserClientInfo> redditUserClientInfoList;

    public TokenInfo() {
    }

    public TokenInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public Integer getRemainingRequests() {
        return remainingRequests;
    }

    public void setRemainingRequests(Integer remainingRequests) {
        this.remainingRequests = remainingRequests;
    }

    @XmlTransient
    public List<RedditUserClientInfo> getRedditUserClientInfoList() {
        return redditUserClientInfoList;
    }

    public void setRedditUserClientInfoList(List<RedditUserClientInfo> redditUserClientInfoList) {
        this.redditUserClientInfoList = redditUserClientInfoList;
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
        if (!(object instanceof TokenInfo)) {
            return false;
        }
        TokenInfo other = (TokenInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.redditapp.entity.TokenInfo[ id=" + id + " ]";
    }
    
}
