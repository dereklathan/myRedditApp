/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.components;

/**
 *
 * @author derek
 */
public class Link {
    protected String domain;
    protected Float approved_at_utc;
    protected String banned_by;
    protected Object media_embed;
    protected String subreddit;
    protected String selftext_html;
    protected String selftext;
    protected String likes;
    protected String suggested_sort;
    protected Object[] user_reports;
    protected Object secure_media;
    protected boolean is_reddit_media_domain;
    protected boolean saved;
    protected String id;
    protected Float banned_at_utc;
    protected Integer view_count;
    protected boolean archived;
    protected boolean clicked;
    protected String report_reasons;
    protected String title;
    protected int num_crossposts;
    protected String link_flair_text;
    protected Object mod_reports;
    protected boolean can_mod_post;
    protected boolean is_crosspostable;
    protected boolean pinned;
    protected int score;
    protected String approved_by;
    protected boolean over_18;
    protected boolean hidden;
    protected String thumbnail;
    protected String subreddit_id;
    protected String edited;
    protected String link_flair_css_class;
    protected String author_flair_css_class;
    protected boolean contest_mode;
    protected int gilded;
    protected int downs;
    protected boolean brand_safe;
    protected Object secure_media_embed;
    protected String removal_reason;
    protected String author_flair_text;
    protected boolean stickied;
    protected boolean can_gild;
    protected boolean is_self;
    protected String parent_whitelist_status;
    protected String name;
    protected boolean spoiler;
    protected String permalink;
    protected String subreddit_type;
    protected boolean locked;
    protected boolean hide_score;
    protected Float created;
    protected String url;
    protected String whitelist_status;
    protected boolean quarantine;
    protected String author;
    protected Float created_utc;
    protected String subreddit_name_prefixed;
    protected int ups;
    protected Object media;
    protected int num_comments;
    protected boolean visited;
    protected Integer num_reports;
    protected boolean is_video;
    protected Boolean distinguished;
    
    public String getDomain() {
        return domain;
    }
    
    public Float getApprovedAtUtc() {
        return approved_at_utc;
    }
    
    public String getBannedBy() {
        return banned_by;
    }
    
    public String getSubreddit() {
        return subreddit;
    }
    
    public String getSelfTextHtml() {
        return selftext_html;
    }
    
    public String getSelfText() {
        return selftext;
    }
    
    public String getLikes() {
        return likes;
    }
    
    public String getSuggestedSort() {
        return suggested_sort;
    }
    
    public Object getSecureMedia() {
        return secure_media;
    }
    
    public boolean isRedditMediaDomain() {
        return is_reddit_media_domain;
    }
    
    public boolean isSaved() {
        return saved;
    }
    
    public String getId() {
        return id;
    }
    
    public Float getBannedAtUtc() {
        return banned_at_utc;
    }
    
    public Integer getViewCount() {
        return view_count;
    }
    
    public Boolean isArchived() {
        return archived;
    }
    
    public Boolean isClicked() {
        return clicked;
    }
    
    public String getReportReasons() {
        return report_reasons;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Integer getNumCrossposts() {
        return num_crossposts;
    }
    
    public String getLinkFlairText() {
        return link_flair_text;
    }
    
    public Boolean canModPost() {
        return can_mod_post;
    }
    
    public Boolean isCrossPostable() {
        return is_crosspostable;
    }
    
    public Boolean isPinned() {
        return pinned;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public String getApprovedBy() {
        return approved_by;
    }
    
    public Boolean isOver18() {
        return over_18;
    }
    
    public Boolean isHidden() {
        return hidden;
    }
    
    public String getThumbnail() {
        return thumbnail;
    }
    
    public String getSubredditId() {
        return subreddit_id;
    }
    
    public String getEdited() {
        return edited;
    }
    
    public String getLinkFlairCssClass() {
        return link_flair_css_class;
    }
    
    public String getAuthorFlairCssClass() {
        return author_flair_css_class;
    }
    
    public Boolean isContestMode() {
        return contest_mode;
    }
    
    public Integer getGilds() {
        return gilded;
    }
    
    public Integer getDowns() {
        return downs;
    }
    
    public Boolean isBrandSafe() {
        return brand_safe;
    }
    
    public String getRemovalReason() {
        return removal_reason;
    }
    
    public String getAuthorFlairText() {
        return author_flair_text;
    }
    
    public Boolean isStickied() {
        return stickied;
    }
    
    public Boolean canGild() {
        return can_gild;
    }
    
    public Boolean isSelf() {
        return is_self;
    }
    
    public String getParentWhitelistStatus() {
        return parent_whitelist_status;
    }
    
    public String getName() {
        return name;
    }
    
    public Boolean isSpoiler() {
        return spoiler;
    }
    
    public String getCommentsUrl() {
        return permalink;
    }
    
    public String getSubredditType() {
        return subreddit_type;
    }
    
    public Boolean isLocked() {
        return locked;
    }
    
    public Boolean isScoreHidden() {
        return hide_score;
    }
    
    public Float getCreated() {
        return created;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getWhitelistStatus() {
        return whitelist_status;
    }
    
    public Boolean isQuarantine() {
        return quarantine;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public Float getCreatedUtc() {
        return created_utc;
    }
    
    public String getSubredditNamePrefixed() {
        return subreddit_name_prefixed;
    }
    
    public Integer getUps() {
        return ups;
    }
    
    public Integer getNumComments() {
        return num_comments;
    }
    
    public Boolean isVisited() {
        return visited;
    }
    
    public Integer getNumReports() {
        return num_reports;
    }
    
    public Boolean isVideo() {
        return is_video;
    }
    
    public Boolean isDistinguished() {
        return distinguished;
    }
}
