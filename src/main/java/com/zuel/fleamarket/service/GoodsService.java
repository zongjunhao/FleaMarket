package com.zuel.fleamarket.service;

import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.Category;
import com.zuel.fleamarket.model.Comment;
import com.zuel.fleamarket.model.Follow;
import com.zuel.fleamarket.model.Goods;

import java.util.ArrayList;
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
     * 查询所有的货品
     *
     * @return
     */
    public BaseResponse getGoods() {
        BaseResponse baseResponse = new BaseResponse();
        List<Goods> goodsList = Goods.dao.find("select * from goods");
        if (!goodsList.isEmpty()) {
            // 货品查询成功
            baseResponse.setData(goodsList);
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_SUCCESS);
        } else {
            // 货品为空
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_NULL);
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

    /**
     * 根据货品名称模糊查询商品
     *
     * @param g_name
     * @return
     */
    public BaseResponse queryGoods(String g_name) {
        BaseResponse baseResponse = new BaseResponse();
        List<Goods> goods = Goods.dao.find("select * from goods where g_name like %" + "'" + g_name + "'" + "%");
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

    /**
     * 删除商品
     *
     * @param g_id
     * @return
     */
    public BaseResponse deleteGoods(String g_id) {
        BaseResponse baseResponse = new BaseResponse();
        Goods goods = Goods.dao.findFirst("select * from goods where g_id = " + "'" + g_id + "'");
        if (goods != null) {
            if (goods.delete()) {
                // 商品删除成功
                baseResponse.setResult(ResultCodeEnum.GOODS_DELETE_SUCCESS);
            } else {
                // 商品删除失败
                baseResponse.setResult(ResultCodeEnum.GOODS_DELETE_FAILURE_DB_ERROR);
            }
        } else {
            // 商品不存在
            baseResponse.setResult(ResultCodeEnum.GOODS_NOT_EXIST);
        }
        return baseResponse;
    }

    /**
     * 修改货品的状态
     *
     * @param g_id
     * @param g_state
     * @return
     */
    public BaseResponse modifyGoodsState(String g_id, String g_state) {
        BaseResponse baseResponse = new BaseResponse();
        Goods goods = Goods.dao.findFirst("select * from goods where g_id = " + "'" + g_id + "'");
        if (goods != null) {
            goods.setGState(Integer.parseInt(g_state));
            if (goods.update()) {
                // 修改货品状态成功
                baseResponse.setResult(ResultCodeEnum.GOODS_UPDATE_STATE_SUCCESS);
            } else {
                // 修改货品状态失败，数据库错误
                baseResponse.setResult(ResultCodeEnum.GOODS_UPDATE_STATE_FAILURE_DB_ERROR);
            }
        } else {
            // 商品不存在
            baseResponse.setResult(ResultCodeEnum.GOODS_NOT_EXIST);
        }
        return baseResponse;
    }

    /**
     * 查询用户发布的所有商品
     *
     * @param u_id
     * @return
     */
    public BaseResponse getGoodsByUser(String u_id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Goods> goods = Goods.dao.find("select * from goods where g_u_id = " + "'" + u_id + "'");
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

    /**
     * 用户关注某商品
     *
     * @param u_id
     * @param g_id
     * @return
     */
    public BaseResponse followGoods(String u_id, String g_id) {
        BaseResponse baseResponse = new BaseResponse();
        Follow follow = new Follow();
        follow.setFUId(Integer.parseInt(u_id));
        follow.setFGId(Integer.parseInt(g_id));
        if (follow.save()) {
            // 添加关注成功
            baseResponse.setResult(ResultCodeEnum.GOODS_FOLLOW_SUCCESS);
        } else {
            // 添加关注失败
            baseResponse.setResult(ResultCodeEnum.GOODS_FOLLOW_FAILURE_DB_ERROR);
        }
        return baseResponse;
    }

    /**
     * 获取用户关注的商品
     *
     * @param u_id
     * @return
     */
    public BaseResponse getFollowList(String u_id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Follow> follows = Follow.dao.find("select * from follow where f_u_id = " + "'" + u_id + "'");
        List<Goods> goodsList = new ArrayList<Goods>();
        if (!follows.isEmpty()) {
            for (int i = 0; i < follows.size(); i++) {
                Follow follow = follows.get(i);
                Goods goods = Goods.dao.findFirst("select * from goods where g_id = " + follow.getFGId() + "'");
                goodsList.add(goods);
            }
            baseResponse.setResult(ResultCodeEnum.GOODS_FOLLOW_QUERY_SUCCESS);
            baseResponse.setData(goodsList);
        } else {
            // 关注商品为空
            baseResponse.setResult(ResultCodeEnum.GOODS_FOLLOW_QUERY_NULL);
        }
        return baseResponse;
    }

    /**
     * 用户对商品进行评论，或者针对哪一商品的回复
     *
     * @param u_id
     * @param g_id
     * @param com_desc
     * @param com_reply
     * @return
     */
    public BaseResponse comment(String u_id, String g_id, String com_desc, String com_reply) {
        BaseResponse baseResponse = new BaseResponse();
        Comment comment = new Comment();
        comment.setComUId(Integer.parseInt(u_id));
        comment.setComGId(Integer.parseInt(g_id));
        comment.setComDesc(com_desc);
        if (com_reply != null) {
            comment.setComReply(Integer.parseInt(com_reply));
        } else
            comment.setComReply(0);
        if (comment.save()) {
            baseResponse.setResult(ResultCodeEnum.COMMENT_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.COMMENT_FAILURE_DB_ERROR);
        }
        return baseResponse;
    }


    /**
     * 获取某一商品的详细信息
     *
     * @param g_id
     * @return
     */
    public BaseResponse getDetailedGoodsInfo(String g_id) {
        BaseResponse baseResponse = new BaseResponse();
        // 每一条评论和该条评论的回复评论
        class EachGroupComments {
            private Comment comment;
            private List<Comment> replyComments;

            public void setComment(Comment comment) {
                this.comment = comment;
            }

            public void setReplyComments(List<Comment> replyComments) {
                this.replyComments = replyComments;
            }
        }
        // 每一条商品信息和所有的评论
        class DetailedGoodsInfo {
            private Goods goods;
            private List<EachGroupComments> allComments;

            public void setGoods(Goods goods) {
                this.goods = goods;
            }

            public void setEachGroupCommentsList(List<EachGroupComments> eachGroupCommentsList) {
                this.allComments = eachGroupCommentsList;
            }
        }
        // 每一个商品的所有详细信息（商品信息和评论）
        DetailedGoodsInfo detailedGoodsInfo = new DetailedGoodsInfo();
        Goods goods = Goods.dao.findFirst("select * from goods where g_id = " + "'" + g_id + "'");
        if (goods != null) {
            // 商品的详细信息
            detailedGoodsInfo.setGoods(goods);
            // 该商品的所有评论信息（不包括回复信息）
            List<Comment> comments = Comment.dao.find("select * from comment where com_g_id = " + g_id + "'" + "and com_reply = 0");
            // 该商品的所有评论信息（包括所有回复信息）
            List<EachGroupComments> allComments = new ArrayList<EachGroupComments>();
            for (Comment comment :
                    comments) {
                // 每一组评论（每一条评论和它的回复评论）
                EachGroupComments eachGroupComments = new EachGroupComments();
                eachGroupComments.setComment(comment);
                List<Comment> replyComments = Comment.dao.find("select * from comment where com_reply = " + comment.getComId() + "'");
                eachGroupComments.setReplyComments(replyComments);
                allComments.add(eachGroupComments);
            }
            detailedGoodsInfo.setEachGroupCommentsList(allComments);

            baseResponse.setData(detailedGoodsInfo);
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_NULL);
        }
        return baseResponse;
    }
}
