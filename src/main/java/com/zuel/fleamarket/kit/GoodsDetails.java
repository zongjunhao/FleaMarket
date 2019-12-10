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
    private int followed;
    private List<EachGroupComments> allComments;

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

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
