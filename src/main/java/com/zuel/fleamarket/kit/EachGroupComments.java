package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Comment;

import java.util.List;

/**
 * 获取每组评论，每条评论以及其回复评论
 */
public class EachGroupComments {
    // 每条评论
    private CommentAndUser commentAndUser;
    // 每条评论的回复评论
    private List<CommentAndUser> replyCommentsAndUsers;

    public CommentAndUser getCommentAndUser() {
        return commentAndUser;
    }

    public List<CommentAndUser> getReplyCommentsAndUsers() {
        return replyCommentsAndUsers;
    }

    public void setReplyCommentsAndUsers(List<CommentAndUser> replyCommentsAndUsers) {
        this.replyCommentsAndUsers = replyCommentsAndUsers;
    }

    public void setCommentAndUser(CommentAndUser commentAndUser) {
        this.commentAndUser = commentAndUser;
    }

}
