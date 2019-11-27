package com.zuel.fleamarket.service;

import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.Category;
import com.zuel.fleamarket.model.Goods;

import java.util.List;

public class GoodsService {
    /**
     * 查询所有的货品类别
     */
    public BaseResponse getGoodsCategories() {
        BaseResponse baseResponse = new BaseResponse();
        List<Category> categories = Category.dao.find("select * from category");
        if (!categories.isEmpty()) {
            // 货品类别查询成功
            baseResponse.setData(categories);
            baseResponse.setResult(ResultCodeEnum.CATEGORY_QUERY_SUCCESS);
        } else {
            // 货品类别为空
            baseResponse.setResult(ResultCodeEnum.CATEGORY_QUERY_NULL);
        }
        return baseResponse;
    }

    /**
     * 查询对应商品类别的所有商品
     *
     * @param categoryId
     * @return
     */
    public BaseResponse getGoodsByCategory(String categoryId) {
        BaseResponse baseResponse = new BaseResponse();
        List<Goods> goods = Goods.dao.find("select * from goods where g_c_id = " + "'" + categoryId + "'" + "and g_state = 1");
        if (!goods.isEmpty()) {
            // 货品查询成功
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_SUCCESS);
            baseResponse.setData(goods);
        } else {
            // 货品查询结果为空
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_NULL);
        }
        return baseResponse;
    }
}
