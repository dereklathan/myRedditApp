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
public class Subreddit {
    private String banner_img;
    private boolean user_sr_theme_enabled;
    private String user_flair_text;
    private String submit_text_html;
    private boolean user_is_banned;
    private boolean wiki_enabled;
    private boolean show_media;
    private String id;
    private String display_name_prefixed;
    private String submit_text;
    private String user_can_flair_in_sr;
    private String display_name;
    private String header_img;
    private String description_html;
    private String title;
    private boolean collapse_deleted_comments;
    private boolean user_has_favorited;
    private boolean over18;
    private String public_description_html;
    private boolean spoilers_enabled;
    private int[] icon_size;
    private String audience_target;
    private String suggested_comment_sort;
    private String active_user_count;
    private String icon_img;
    private String header_title;
    private String description;
    private boolean user_is_muted;
    private String submit_link_label;
    private String accounts_active;
    private boolean public_traffic;
    private int subscribers;
    private String user_flair_css_class;
    private String submit_text_label;
    private String whitelist_status;
    private String user_sr_flair_enabled;
    private String lang;
    private boolean user_is_moderated;
    private String is_enrolled_in_new_modmail;
    private String key_color;
    private String name;
    private boolean user_flair_enabled_in_sr;
    private float created;
    private String url;
    private boolean quarantine;
    private boolean hide_ads;
    private float created_utc;
    private int[] banner_size;
    private boolean user_is_contributor;
    private boolean allow_discovery;
    private boolean accounts_active_is_fuzzed;
    private String advertiser_category;
    private String public_description;
    private boolean link_flair_enabled;
    private boolean allow_images;
    private boolean show_media_preview;
    private int comment_score_hide_mins;
    private String subreddit_type;
    private String submission_type;
    private boolean user_is_subscriber;
    
    public String getBannerImg() {
        return banner_img;
    }
    
    public boolean isUserSrThemeEnabled() {
        return user_sr_theme_enabled;
    }
    
    public String getUserFlairText() {
        return user_flair_text;
    }
    
    public String getSubmitTextHtml() {
        return submit_text_html;
    }
    
    public boolean isUserBanned() {
        return user_is_banned;
    }
    
    public boolean isWikiEnabled() {
        return wiki_enabled;
    }
    
    public boolean isShowMedia() {
        return show_media;
    }
    
    public String getId() {
        return id;
    }
    
    public String getDisplayNamePrefixed() {
        return display_name_prefixed;
    }
    
    public String getSubmitText() {
        return submit_text;
    }
    
    public String getUserCanFlairInSr() {
        return user_can_flair_in_sr;
    }
    
    public String getDisplayName() {
        return display_name;
    }
    
    public String getHeaderImg() {
        return header_img;
    }
    
    public String getDescriptionHtml() {
        return description_html;
    }
    
    public String getTitle() {
        return title;
    }
    
    public boolean isCollapseDeletedComments() {
        return collapse_deleted_comments;
    }
    
    public boolean isUserFavorited() {
        return user_has_favorited;
    }
    
    public boolean isOver18() {
        return over18;
    }
    
    public String getPublicDescriptionHtml() {
        return public_description_html;
    }
    
    public boolean isSpolersEnabled() {
        return spoilers_enabled;
    }
    
    public int[] getIconSize() {
        return icon_size;
    }
    
    public String getAudienceTarget() {
        return audience_target;
    }
    
    public String getSuggestedCommentSort() {
        return suggested_comment_sort;
    }
    
    public String getActiveUserCount() {
        return active_user_count;
    }
    
    public String getIconImg() {
        return icon_img;
    }
    
    public String getHeaderTitle() {
        return header_title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isUserMuted() {
        return user_is_muted;
    }
    
    public String getSubmitLinkLabel() {
        return submit_link_label;
    }
    
    public String getAccountsActive() {
        return accounts_active;
    }
    
    public boolean isPublicTraffic() {
        return public_traffic;
    }
    
    public int getSubscribers() {
        return subscribers;
    }
    
    public String getUserFlairCssClass() {
        return user_flair_css_class;
    }
    
    public String getSubmitTextLabel() {
        return submit_text_label;
    }
    
    public String getWhitelistStatus() {
        return whitelist_status;
    }
    
    public String getUserSrFlairEnabled() {
        return user_sr_flair_enabled;
    }
    
    public String getLang() {
        return lang;
    }
    
    public boolean isUserModerated() {
        return user_is_moderated;
    }
    
    public String getIsEnrolledInNewModmail() {
        return is_enrolled_in_new_modmail;
    }
    
    public String getKeyColor() {
        return key_color;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isUserFlairEnabledInSr() {
        return user_flair_enabled_in_sr;
    }
    
    public float getCreated() {
        return created;
    }
    
    public String getUrl() {
        return url;
    }
    
    public boolean isQuarantine() {
        return quarantine;
    }
    
    public boolean isHideAds() {
        return hide_ads;
    }
    
    public float getCreatedUtc() {
        return created_utc;
    }
    
    public int[] getBannerSize() {
        return banner_size;
    }
    
    public boolean isUserContributor() {
        return user_is_contributor;
    }
    
    public boolean isAllowDiscovery() {
        return allow_discovery;
    }
    
    public boolean isAccountsActiveFuzzed() {
        return accounts_active_is_fuzzed;
    }
    
    public String getAdvertiserCategory() {
        return advertiser_category;
    }
    
    public String getPublicDescription() {
        return public_description;
    }
    
    public boolean isLinkFlairEnabled() {
        return link_flair_enabled;
    }
    
    public boolean isAllowImages() {
        return allow_images;
    }
    
    public boolean isShowMediaPreview() {
        return show_media_preview;
    }
    
    public int getCommentScoreHideMins() {
        return comment_score_hide_mins;
    }
    
    public String getSubredditType() {
        return subreddit_type;
    }
    
    public String getSubmissionType() {
        return submission_type;
    }
    
    public boolean isUserSubscriber() {
        return user_is_subscriber;
    }
}
