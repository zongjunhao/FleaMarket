package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.upload.UploadFile;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.service.GoodsService;

import java.util.List;

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
            if (Integer.parseInt(categoryId) == 0) {
                baseResponse = goodsService.getGoods();
            } else {
                baseResponse = goodsService.getGoodsByCategory(categoryId);
            }
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
    public void comment() {
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
        String u_id = getPara("u_id");
        String g_id = getPara("g_id");
        if (!StrKit.isBlank(g_id)) {
            baseResponse = goodsService.getDetailedGoodsInfo(u_id, g_id);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 上传货品
     */
    public void uploadGoods() {
        BaseResponse baseResponse = new BaseResponse();
        UploadFile uploadFile = getFile("file");
        String u_id = getPara("u_id");
        String c_id = getPara("c_id");
        String g_name = getPara("g_name");
        String g_price = getPara("g_price");
        String g_realprice = getPara("g_realprice");
        String g_describe = getPara("g_describe");

        if (!StrKit.isBlank(u_id) && !StrKit.isBlank(c_id) && !StrKit.isBlank(g_describe) && !StrKit.isBlank(g_name) && !StrKit.isBlank(g_price) && !StrKit.isBlank(g_realprice)) {
            baseResponse = goodsService.uploadGoods(u_id, c_id, g_name, g_price, g_realprice, g_describe, uploadFile);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 发布求购信息
     */
    public void notice() {
        BaseResponse baseResponse = new BaseResponse();
        String u_id = getPara("u_id");
        String n_name = getPara("n_name");
        String n_price = getPara("n_price");
        String n_describe = getPara("n_describe");
        if (!StrKit.isBlank(u_id) && !StrKit.isBlank(n_name) && !StrKit.isBlank(n_price) && !StrKit.isBlank(n_describe)) {
            baseResponse = goodsService.notice(u_id, n_name, n_price, n_describe);
        } else {
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改求购信息的状态
     */
    public void modifyNoticeState() {
        BaseResponse baseResponse = new BaseResponse();
        String n_id = getPara("n_id");
        String n_state = getPara("n_state");
        if (!StrKit.isBlank(n_id) && !StrKit.isBlank(n_state)) {
            baseResponse = goodsService.modifyNoticeState(n_id, n_state);
        } else {
            // 请求的参数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 查询所有的求购信息
     */
    public void getNotice() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = goodsService.getNotice();
        renderJson(baseResponse);
    }
}
