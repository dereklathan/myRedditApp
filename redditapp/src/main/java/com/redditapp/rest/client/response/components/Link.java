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
    private String domain;
    private Float approved_at_utc;
    private String banned_by;
    private Object media_embed;
    private String subreddit;
    private String selftext_html;
    private String selftext;
    private String likes;
    private String suggested_sort;
    private Object[] user_reports;
    private String secure_media;
    private boolean is_reddit_media_domain;
    private boolean saved;
    private String id;
    private Float banned_at_utc;
    private Integer view_count;
    private boolean archived;
    private boolean clicked;
    private String report_reasons;
    private String title;
    private int num_crossposts;
    private String link_flair_text;
    private Object mod_reports;
    private boolean can_mod_post;
    private boolean is_crosspostable;
    private boolean pinned;
    private int score;
    private String approved_by;
    private boolean over_18;
    private boolean hidden;
    private String thumbnail;
    private String subreddit_id;
    private boolean edited;
    private String link_flair_css_class;
    private String author_flair_css_class;
    private boolean contest_mode;
    private int gilded;
    private int downs;
    private boolean brand_safe;
    private Object secure_media_embed;
    private String removal_reason;
    private String author_flair_text;
    private boolean stickied;
    private boolean can_gild;
    private boolean is_self;
    private String parent_whitelist_status;
    private String name;
    private boolean spoiler;
    private String permalink;
    private String subreddit_type;
    private boolean locked;
    private boolean hide_score;
    private Float created;
    private String url;
    private String whitelist_status;
    private boolean quarantine;
    private String author;
    private Float created_utc;
    private String subreddit_name_prefixed;
    private int ups;
    private String media;
    private int num_comments;
    private boolean visited;
    private Integer num_reports;
    private boolean is_video;
    private Boolean distinguished;
    
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
    
    public String getSecureMedia() {
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
    
    public Boolean isEdited() {
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
