package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.zuel.fleamarket.kit.BaseResponse;
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
}
