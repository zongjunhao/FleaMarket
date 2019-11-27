package com.zuel.fleamarket.service;

import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.Category;

import java.util.List;

/**
 * 查询所有的货品类别
 */
public class GoodsService {
    public BaseResponse getGoodsCategories() {
        BaseResponse baseResponse = new BaseResponse();
        List<Category> categories = Category.dao.find("select * from category");
        if (categories != null) {
            // 货品查询成功
            baseResponse.setData(categories);
            baseResponse.setResult(ResultCodeEnum.CATEGORY_QUERY_SUCCESS);
        } else {
            // 货品类别为空
            baseResponse.setResult(ResultCodeEnum.CATEGORY_NULL);
        }
        return baseResponse;
    }
}
