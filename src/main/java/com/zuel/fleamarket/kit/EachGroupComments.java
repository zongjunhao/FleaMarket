package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Comment;

import java.util.List;

public class EachGroupComments {
    private Comment comment;
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
