package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.service.GoodsService;

public class GoodsController extends Controller {
    GoodsService goodsService = new GoodsService();

    /**
     * 查询所有的货品类别
     */
    public void getGoodsCategories() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = goodsService.getGoodsCategories();
        renderJson(baseResponse);
    }

    /**
     * 查询对用货品类别的所有商品
     */
    public void getGoodsByCategory() {
        BaseResponse baseResponse = new BaseResponse();
        String categoryId = getPara("categoryId");
        if (!StrKit.isBlank(categoryId))
            baseResponse = goodsService.getGoodsByCategory(categoryId);
        else
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        renderJson(baseResponse);
    }
}
