package com.zuel.fleamarket.kit;

import com.zuel.fleamarket.model.Goods;

import java.util.List;

/**
 * 获取商品的详细信息
 */
public class GoodsDetails {
    private Goods goods;
    private List<EachGroupComments> allComments;

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
