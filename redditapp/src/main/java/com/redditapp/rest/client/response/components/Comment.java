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
public class Comment {
    private String subreddit_id;
    private Float approved_at_utc;
    private String banned_by;
    private String removal_reason;
    private String link_id;
    private String likes;
    private CommentListing replies;
    private Object[] user_reports;
    private Boolean saved;
    private String id;
    private Float banned_at_utc;
    private Integer gilded;
    private Boolean archived;
    private String report_reasons;
    private String author;
    private Boolean can_mod_post;
    private Integer ups;
    private String parent_id;
    private Integer score;
    private String approved_by;
    private Integer downs;
    private String body;
    private String edited;
    private String author_flair_css_class;
    private Boolean collapsed;
    private Boolean is_submitter;
    private String collapsed_reason;
    private String body_html;
    private Boolean stickied;
    private Boolean can_gild;
    private String subreddit;
    private Boolean score_hidden;
    private String permalink;
    private String subreddit_type;
    private String name;
    private Float created;
    private String author_flair_text;
    private Float created_utc;
    private String subreddit_name_prefixed;
    private Integer controversiality;
    private Integer depth;
    private Object[] mod_reports;
    private Integer num_reports;
    private Boolean distinguished;
    
    public String getSubredditId() {
        return subreddit_id;
    }
    
    public Float getApprovedAtUtc() {
        return approved_at_utc;
    }
    
    public String getBannedBy() {
        return banned_by;
    }
    
    public String getRemovalReason() {
        return removal_reason;
    }
    
    public String getLinkId() {
        return link_id;
    }
    
    public String getLikes() {
        return likes;
    }
    
    public CommentListing getReplies() {
        return replies;
    }
    
    public void setretReplies(CommentListing replies) {
        this.replies = replies;
    }
    
    public Object[] getUserReports() {
        return user_reports;
    }
    
    public Boolean isSaved() {
        return saved;
    }
    
    public String getId() {
        return id;
    }
    
    public Float getBannedAtUtc() {
        return banned_at_utc;
    }
    
    public Integer getGilds() {
        return gilded;
    }
    
    public Boolean isArchived() {
        return archived;
    }
    
    public String getReportReasons() {
        return report_reasons;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public Boolean canModPost() {
        return can_mod_post;
    }
    
    public Integer getUps() {
        return ups;
    }
    
    public String getParentId() {
        return parent_id;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public String getApprovedBy() {
        return approved_by;
    }
    
    public Integer getDowns() {
        return downs;
    }
    
    public String getBody() {
        return body;
    }
    
    public String getEdited() {
        return edited;
    }
    
    public String getAuthorFlairCssClass() {
        return author_flair_css_class;
    }
    
    public Boolean isCollapsed() {
        return collapsed;
    }
    
    public Boolean isSubmitter() {
        return is_submitter;
    }
    
    public String getCollapsedReason() {
        return collapsed_reason;
    }
    
    public String getBodyHtml() {
        return body_html;
    }
    
    public Boolean isStickied() {
        return stickied;
    }
    
    public Boolean canGild() {
        return can_gild;
    }
    
    public String getSubreddit() {
        return subreddit;
    }
    
    public Boolean isScoreHidden() {
        return score_hidden;
    }
    
    public String getCommentUrl() {
        return permalink;
    }
    
    public String getSubredditType() {
        return subreddit_type;
    }
    
    public String getName() {
        return name;
    }
    
    public Float getCreated() {
        return created;
    }
    
    public String getAuthorFlairText() {
        return author_flair_text;
    }
    
    public Float getCreatedUtc() {
        return created_utc;
    }
    
    public String getSubredditNamePrefixed() {
        return subreddit_name_prefixed;
    }
    
    public Integer getControversiality() {
        return controversiality;
    }
    
    public Integer getDepth() {
        return depth;
    }
    
    public Object[] getModReports() {
        return mod_reports;
    }
    
    public Integer getNumReports() {
        return num_reports;
    }
    
    public Boolean isDistinguished() {
        return distinguished;
    }
}
