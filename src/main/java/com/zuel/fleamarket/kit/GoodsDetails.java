package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Goods;
import com.zuel.fleamarket.model.User;

import java.util.List;

/**
 * 获取商品的详细信息
 */
public class GoodsDetails {
    private Goods goods;
    private User user;
    private boolean isFollowed;

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    private List<EachGroupComments> allComments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<EachGroupComments> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<EachGroupComments> allComments) {
        this.allComments = allComments;
    }
}
