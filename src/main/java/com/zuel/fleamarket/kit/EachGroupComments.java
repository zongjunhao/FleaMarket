package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Comment;

import java.util.List;

/**
 * 获取每组评论，每条评论以及其回复评论
 */
public class EachGroupComments {
    // 每条评论
    private Comment comment;
    // 每条评论的回复评论
    private List<Comment> replyComments;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<Comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
}
