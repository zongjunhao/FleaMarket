package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Notice;
import com.zuel.fleamarket.model.User;

/**
 * 求购信息和用户
 */
public class NoticeAndUser {
    private Notice notice;
    private User user;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
