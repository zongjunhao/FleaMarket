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
     * 查询所有的货品
     */
    public void getGoods() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = goodsService.getGoods();
        renderJson(baseResponse);
    }

    /**
     * 查询对应货品类别的所有商品
     */
    public void getGoodsByCategory() {
        BaseResponse baseResponse = new BaseResponse();
        String categoryId = getPara("categoryId");
        if (!StrKit.isBlank(categoryId))
            baseResponse = goodsService.getGoodsByCategory(categoryId);
        else // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        renderJson(baseResponse);
    }

    /**
     * 根据货品名称模糊查询商品
     */
    public void queryGoods() {
        BaseResponse baseResponse = new BaseResponse();
        String g_name = getPara("g_name");
        if (!StrKit.isBlank(g_name)) {
            baseResponse = goodsService.queryGoods(g_name);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 删除商品
     */
    public void deleteGoods() {
        BaseResponse baseResponse = new BaseResponse();
        String g_id = getPara("g_id");
        if (!StrKit.isBlank(g_id)) {
            baseResponse = goodsService.deleteGoods(g_id);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改货品的状态
     */
    public void modifyGoodsState() {
        BaseResponse baseResponse = new BaseResponse();
        String g_id = getPara("g_id");
        String g_state = getPara("g_state");
        if (!StrKit.isBlank(g_state) && !StrKit.isBlank(g_id)) {
            baseResponse = goodsService.modifyGoodsState(g_id, g_state);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取用户发布的所有商品
     */
    public void getGoodsByUser() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        if (!StrKit.isBlank(u_id))
            baseResponse = goodsService.getGoodsByUser(u_id);
        else // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        renderJson(baseResponse);
    }

    /**
     * 用户关注商品
     */
    public void followGoods() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        String g_id = getPara("g_id");
        if (!StrKit.isBlank(u_id) && !StrKit.isBlank(g_id)) {
            baseResponse = goodsService.followGoods(u_id, g_id);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取用户关注的商品
     */
    public void getFollowList() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        if (!StrKit.isBlank(u_id)) {
            baseResponse = goodsService.getFollowList(u_id);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 用户对商品进行评论，或者针对某一评论的回复
     */
    public void comment()  {
        BaseResponse baseResponse = new BaseResponse();
        String g_id = getPara("g_id");
        String u_id = getPara("u_id");
        String com_desc = getPara("com_desc");
        String com_reply = getPara("com_reply");
        if (!StrKit.isBlank(g_id) && !StrKit.isBlank(u_id) && !StrKit.isBlank(com_desc)) {
            baseResponse = goodsService.comment(g_id, u_id, com_desc, com_reply);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 获取商品的详细信息，包括评论
     */
    public void getDetailedGoodsInfo() {
        BaseResponse baseResponse = new BaseResponse();
        String g_id = getPara("g_id");
        if (!StrKit.isBlank(g_id)) {
            baseResponse = goodsService.getDetailedGoodsInfo(g_id);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
    }
}
