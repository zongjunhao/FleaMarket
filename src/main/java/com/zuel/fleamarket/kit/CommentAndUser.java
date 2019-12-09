package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Comment;
import com.zuel.fleamarket.model.User;

/**
 * 每一条评论和发布该评论的用户信息
 */
public class CommentAndUser {
    private Comment comment;
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
