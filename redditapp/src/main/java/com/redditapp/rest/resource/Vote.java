/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource;

import com.redditapp.dao.CommentDao;
import com.redditapp.dao.LinkDao;
import com.redditapp.dao.RedditUserClientInfoDao;
import com.redditapp.dao.SubredditDao;
import com.redditapp.dao.VoteCommentDao;
import com.redditapp.dao.VoteLinkDao;
import com.redditapp.entity.Link;
import com.redditapp.entity.Comment;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.entity.Subreddit;
import com.redditapp.entity.VoteComment;
import com.redditapp.entity.VoteLink;
import com.redditapp.gson.GsonUtil;
import com.redditapp.rest.client.response.CommentListingResponse;
import com.redditapp.rest.resource.filter.AuthFilter;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import com.redditapp.rest.client.ListingsClient;
import com.redditapp.rest.client.VoteClient;
import com.redditapp.rest.client.response.VoteResponse;
import com.redditapp.rest.resource.request.VoteRequest;
import com.redditapp.rest.resource.response.ListingResponse;
import com.redditapp.rest.resource.session.SessionPool;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author derek
 */
@Path("vote")
public class Vote extends Resource {
    @Inject private RedditUserClientInfoDao redditUserClientInfoDao;
    @Inject private SessionPool sessionPool;
    @Inject private VoteClient voteClient;
    @Inject private ListingsClient listingsClient;
    @Inject private CommentDao commentDao;
    @Inject private LinkDao linkDao;
    @Inject private VoteCommentDao voteCommentDao;
    @Inject private VoteLinkDao voteLinkDao;
    @Inject private SubredditDao subredditDao;
    @Inject private GsonUtil gsonUtil;
    
    private StringBuilder pathBuilder;
    
    @GET
    @Path("getThing")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response getThing(@Context HttpHeaders headers, @QueryParam("link") String link) {
        int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
        ListingResponse response = new ListingResponse();
        String responseJson;
        Boolean isComment = this.isComment(link);
        if(isComment == null) {
             response.setError("Link is invalid for some reason.");
             responseJson = gsonUtil.getGson().toJson(response);
             return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build(); 
        }
        List<RedditUserClientInfo> redditUserClientInfos = this.redditUserClientInfoDao.getAuthorizedAddedBy(userId);
        RedditUserClientInfo redditUserClientInfo = null;
        for(RedditUserClientInfo r : redditUserClientInfos) {
            if(r.getTokenInfo().getExpiration().isAfter(LocalDateTime.now()) || r.getTokenInfo().getRemainingRequests() > 0) {
                redditUserClientInfo = r;
                break;
            }
        }
        if(redditUserClientInfo != null) {
            CommentListingResponse commentListingResponse = this.listingsClient.getComments(redditUserClientInfo, pathBuilder.toString());
            if(commentListingResponse.getError() == null) {
                List<Integer> redditUserIds = new ArrayList();
                for(RedditUserClientInfo r : redditUserClientInfos) {
                    if(!redditUserIds.contains(r.getRedditUser().getId())) {
                        redditUserIds.add(r.getRedditUser().getId());
                    }
                }
                response.setIsComment(isComment);
                String subredditName = commentListingResponse.getThreadLink().getSubreddit();
                Subreddit subreddit = this.subredditDao.getByName(subredditName);
                if(subreddit == null) {
                    subreddit = new Subreddit();
                    subreddit.setName(subredditName);
                    this.subredditDao.saveOrUpdate(subreddit);
                    subreddit = this.subredditDao.getByName(subredditName);
                }
                
                response.getThreadLink().setTitle(commentListingResponse.getThreadLink().getTitle());
                response.getThreadLink().setThingId(commentListingResponse.getThreadLink().getName());
                response.getThreadLink().setSelfText(commentListingResponse.getThreadLink().getSelfText());
                response.getThreadLink().setUrl(commentListingResponse.getThreadLink().getUrl());
                response.getThreadLink().setAuthor(commentListingResponse.getThreadLink().getAuthor());
                String threadLinkPermalink = commentListingResponse.getThreadLink().getCommentsUrl();
                Link threadLink = this.linkDao.getByPermalink(threadLinkPermalink);
                if(threadLink == null) {
                    threadLink = new Link();
                    threadLink.setSubreddit(subreddit);
                    threadLink.setPostedBy(commentListingResponse.getThreadLink().getAuthor());
                    threadLink.setSelftext(commentListingResponse.getThreadLink().getSelfText());
                    threadLink.setTitle(commentListingResponse.getThreadLink().getTitle());
                    threadLink.setUrl(commentListingResponse.getThreadLink().getUrl());
                    threadLink.setPermalink(threadLinkPermalink);
                    threadLink.setThingId(commentListingResponse.getThreadLink().getName());
                    this.linkDao.saveOrUpdate(threadLink);
                    threadLink = this.linkDao.getByPermalink(threadLinkPermalink);
                }
                if(isComment) {
                    String commentId = commentListingResponse.getCommentListing().getComments().get(0).getId();
                    String commentPermalink = threadLinkPermalink + commentListingResponse.getCommentListing().getComments().get(0).getId() + "/";
                    com.redditapp.entity.Comment comment = this.commentDao.getByPermalink(commentPermalink);
                    if(comment == null) {
                        comment = new com.redditapp.entity.Comment();
                        comment.setBody(commentListingResponse.getCommentListing().getComments().get(0).getBody());
                        comment.setPostedBy(commentListingResponse.getCommentListing().getComments().get(0).getAuthor());
                        comment.setLink(threadLink);
                        comment.setPermalink(commentPermalink);
                        comment.setThingId(commentListingResponse.getCommentListing().getComments().get(0).getName());
                        this.commentDao.saveOrUpdate(comment);
                    }
                    response.getComment().setAuthor(comment.getPostedBy());
                    response.getComment().setBody(comment.getBody());
                    response.getComment().setThingId(commentListingResponse.getCommentListing().getComments().get(0).getName());
                    List<VoteComment> voteComments = this.voteCommentDao.getByThingId(comment.getThingId());
                    int score = 0;
                    for(VoteComment v : voteComments) {
                        if(redditUserIds.contains(v.getRedditUser().getId())) {
                            score += v.getScore();
                        }
                    }
                    response.setScore(score);
                    response.setScoreLimit(redditUserIds.size());
                }
                else {
                    List<VoteLink> voteLinks = this.voteLinkDao.getByThingId(threadLink.getThingId());
                    int score = 0;
                    for(VoteLink v : voteLinks) {
                        if(redditUserIds.contains(v.getRedditUser().getId())) {
                            score += v.getScore();
                        }
                    }
                    response.setScore(score);
                    response.setScoreLimit(redditUserIds.size());
                }
            }
            else {
                response.setError(commentListingResponse.getError());
            }
            
        }
        else {
            response.setError("You have no clients with available requests.");
        }
        
        responseJson = gsonUtil.getGson().toJson(response);
        System.out.println(responseJson);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @POST
    @AuthFilter
    @Path("submitVote")
    @Produces({MediaType.APPLICATION_JSON})
    public Response sumbitVote(@Context HttpHeaders headers, String request) {
        int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
        VoteRequest voteRequest = gsonUtil.getGson().fromJson(request, VoteRequest.class);
        int newScore = voteRequest.getScore();
        Boolean isComment = this.isComment(voteRequest.getPermalink());
        ListingResponse response = new ListingResponse();
        response.setIsComment(isComment);
        List<RedditUserClientInfo> authorized = this.redditUserClientInfoDao.getAuthorizedAddedBy(userId);
        List<Integer> redditUserIds = new ArrayList();
        for(RedditUserClientInfo r : authorized) {
            if(!redditUserIds.contains(r.getRedditUser().getId())) {
                redditUserIds.add(r.getRedditUser().getId());
            }
        }
        int scoreLimit = redditUserIds.size();
        if(newScore > scoreLimit) {
            newScore = scoreLimit;
        }
        else if(newScore < 0 - scoreLimit) {
            newScore = 0 - scoreLimit;
        }
        response.setScoreLimit(scoreLimit);
        List<Integer> hasVotedUp = new ArrayList();
        List<Integer> hasVotedDown = new ArrayList();
        List<RedditUserClientInfo> toUpVote = new ArrayList();
        List<RedditUserClientInfo> toDownVote = new ArrayList();
        List<RedditUserClientInfo> toUnVote = new ArrayList();
        int currentScore;
        if(isComment) {
            Comment comment = this.commentDao.getByThingId(voteRequest.getThingId());
            if(comment != null) {
                response.getComment().setAuthor(comment.getPostedBy());
                response.getComment().setBody(comment.getBody());
                response.getComment().setThingId(comment.getThingId());
                response.getThreadLink().setSelfText(comment.getLink().getSelftext());
                response.getThreadLink().setThingId(comment.getLink().getThingId());
                response.getThreadLink().setTitle((comment.getLink().getTitle()));
                response.getThreadLink().setUrl(comment.getLink().getUrl());
            }
            else {
                throw new RuntimeException("vote attempted on unsaved comment");
            }
            List<VoteComment> voteComments = this.voteCommentDao.getByAddedByIdAndThingId(userId, voteRequest.getThingId());
            for(VoteComment v : voteComments) { 
                if(redditUserIds.contains(v.getRedditUser().getId())) {
                    if(v.getScore() == 1) {
                        hasVotedUp.add(v.getRedditUser().getId());
                    }
                    else if(v.getScore() == -1) {
                        hasVotedDown.add(v.getRedditUser().getId());
                    }
                }
            }
        }
        else {
            Link link = this.linkDao.getByThingId(voteRequest.getThingId());
            if(link != null) {
                response.getThreadLink().setSelfText(link.getSelftext());
                response.getThreadLink().setThingId(link.getThingId());
                response.getThreadLink().setTitle((link.getTitle()));
                response.getThreadLink().setUrl(link.getUrl());
            }
            else {
                throw new RuntimeException("vote attempted on unsaved link");
            }
            List<VoteLink> voteLinks = this.voteLinkDao.getByAddedByIdAndThingId(userId, voteRequest.getThingId());
            for(VoteLink v : voteLinks) { 
                if(redditUserIds.contains(v.getRedditUser().getId())) {
                    if(v.getScore() == 1) {
                        hasVotedUp.add(v.getRedditUser().getId());
                    }
                    else if(v.getScore() == -1) {
                        hasVotedDown.add(v.getRedditUser().getId());
                    }
                }
            }
        }
        currentScore = hasVotedUp.size() - hasVotedDown.size();
        System.out.println("newScore: " + newScore);
        for(RedditUserClientInfo r : authorized) {
            if(currentScore != newScore) {
                if(redditUserIds.contains(r.getRedditUser().getId())) {
                    System.out.println("currentScore: " + currentScore);
                    if(currentScore < newScore) {
                        if(hasVotedDown.contains(r.getRedditUser().getId())) {
                            if(currentScore + 1 < newScore) {
                                toUpVote.add(r);
                                currentScore += 2;
                            }
                            else {
                                toUnVote.add(r);
                                currentScore++;
                            }
                        }
                        else if (!hasVotedUp.contains(r.getRedditUser().getId())) {
                            toUpVote.add(r);
                            currentScore++;
                        }
                    }
                    else if(currentScore > newScore) {
                        if(hasVotedUp.contains(r.getRedditUser().getId())) {
                            if(currentScore - 1 > newScore) {
                                toDownVote.add(r);
                                currentScore -= 2;
                            }
                            else {
                                toUnVote.add(r);
                                currentScore--;
                            }
                        }
                        else if(!hasVotedDown.contains(r.getRedditUser().getId())) {
                            toDownVote.add(r);
                            currentScore--;
                        }
                    }
                    redditUserIds.remove(r.getRedditUser().getId());
                }
            }
        }
        System.out.print("hasVotedUp: ");
        for(int r : hasVotedUp) {
            System.out.print(r + " ");
        }
        System.out.println();
        System.out.print("hasVotedDown: ");
        for(int r : hasVotedDown) {
            System.out.print(r + " ");
        }
        System.out.println();
        System.out.print("toUnvote: ");
        for(RedditUserClientInfo r : toUnVote) {
            System.out.print(r.getRedditUser().getId() + " ");
        }
        System.out.println();
        System.out.print("toUpVote: ");
        for(RedditUserClientInfo r : toUpVote) {
            System.out.print(r.getRedditUser().getId() + " ");
        }
        System.out.println();
        System.out.print("toDownvote: ");
        for(RedditUserClientInfo r : toDownVote) {
            System.out.print(r.getRedditUser().getId() + " ");
        }
        System.out.println();
        response.setScore(currentScore);
        
        if(isComment) {
            this.voteComment(toUnVote, voteRequest.getThingId(), 0);
            this.voteComment(toUpVote, voteRequest.getThingId(), 1);
            this.voteComment(toDownVote, voteRequest.getThingId(), -1);
        }
        else {
            this.voteLink(toUnVote, voteRequest.getThingId(), 0);
            this.voteLink(toUpVote, voteRequest.getThingId(), 1);
            this.voteLink(toDownVote, voteRequest.getThingId(), -1);
        }
        String responseJson = gsonUtil.getGson().toJson(response);
        System.out.println(responseJson);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    private void voteLink(List<RedditUserClientInfo> redditUserClientInfos, String thingId, int dir) {
        Link link = this.linkDao.getByThingId(thingId);
        if(link == null) {
            throw new RuntimeException("vote attempted on unsaved link");
        }
        VoteLink voteLink;
        for(RedditUserClientInfo r : redditUserClientInfos) {
            voteLink = this.voteLinkDao.getByRedditUserIdAndThingId(r.getRedditUser().getId(), thingId);
            if(voteLink == null) {
                voteLink = new VoteLink();
                voteLink.setLink(link);
                voteLink.setRedditUser(r.getRedditUser());
            }
            voteLink.setScore(dir);
            VoteResponse response = null;
            switch (dir) {
                case 0:
                    response = this.voteClient.unvote(r, thingId);
                    break;
                case 1:
                    response = this.voteClient.voteUp(r, thingId);
                    break;
                case -1:
                    response = this.voteClient.voteDown(r, thingId);
                    break;
                default:
                    break;
            }
            if(response != null && (response.getError() == null || response.getError().isEmpty())) {
                System.out.println(r.getRedditUser().getUsername() + " voted " + Integer.toString(dir) + " on link " + thingId);
                this.voteLinkDao.saveOrUpdate(voteLink);
            }
            if(response != null && response.getError() != null) {
                System.out.println(response.getError());
            }
        }
    }
    
    private void voteComment(List<RedditUserClientInfo> redditUserClientInfos, String thingId, int dir) {
        Comment comment = this.commentDao.getByThingId(thingId);
        if(comment == null) {
            throw new RuntimeException("vote attempted on unsaved comment");
        }
        VoteComment voteComment;
        for(RedditUserClientInfo r : redditUserClientInfos) {
            voteComment = this.voteCommentDao.getByRedditUserIdAndThingId(r.getRedditUser().getId(), thingId);
            if(voteComment == null) {
                voteComment = new VoteComment();
                voteComment.setRedditUser(r.getRedditUser());
                voteComment.setComment(comment);
            }
            voteComment.setScore(dir);
            VoteResponse response = null;
            switch (dir) {
                case 0:
                    response = this.voteClient.unvote(r, thingId);
                    break;
                case 1:
                    response = this.voteClient.voteUp(r, thingId);
                    break;
                case -1:
                    response = this.voteClient.voteDown(r, thingId);
                    break;
                default:
                    break;
            }
            if(response != null && (response.getError() == null || response.getError().isEmpty())) {
                System.out.println(r.getRedditUser().getUsername() + " voted " + Integer.toString(dir) + " on comment " + thingId);
                this.voteCommentDao.saveOrUpdate(voteComment);
            }            
        }
    }
    
    private Boolean isComment(String permalink) {
        String[] splitlink = permalink.split("/");
        this.pathBuilder = new StringBuilder();
        int count = 0;
        for(int i = 0; i < splitlink.length; i++) {
            if(splitlink[i].contentEquals("r")) {
                count = splitlink.length - i;
                for(int j = i; j < splitlink.length; j++) {
                    pathBuilder.append("/").append(splitlink[j]);
                }
                break;
            }
        }
        Boolean isComment = null;
        switch(count) {
            case 6:
                isComment=true;
                break;
            case 5:
                isComment=false;
                break;
            default:
                System.out.println("count is " + count);
                break;
        }
        return isComment;
    }

}
