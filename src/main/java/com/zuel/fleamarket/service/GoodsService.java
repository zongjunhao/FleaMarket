package com.zuel.fleamarket.service;

import com.jfinal.upload.UploadFile;
import com.zuel.fleamarket.kit.*;
import com.zuel.fleamarket.model.*;

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
    public BaseResponse followGoods(String u_id, String g_id, String followState) {
        BaseResponse baseResponse = new BaseResponse();
        if (followState.equals("0")) {
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
        } else if (followState.equals("1")) {
            Follow follow = Follow.dao.findFirst("select * from follow where f_u_id = " + "'" + u_id + "'" + "and f_g_id = " + "'" + g_id + "'");
            if (follow != null) {
                if (follow.delete()) {
                    baseResponse.setResult(ResultCodeEnum.GOODS_CANCEL_FOLLOW__SUCCESS);
                } else {
                    baseResponse.setResult(ResultCodeEnum.GOODS_CANCEL_FOLLOW_FAILURE_DB_ERROR);
                }
            } else {
                baseResponse.setResult(ResultCodeEnum.GOODS_NOT_FOLLOW);
            }
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
    public BaseResponse comment(String g_id, String u_id, String com_desc, String com_reply) {
        BaseResponse baseResponse = new BaseResponse();
        Comment comment = new Comment();
        comment.setComGId(Integer.parseInt(g_id));
        comment.setComUId(Integer.parseInt(u_id));
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
    public BaseResponse getDetailedGoodsInfo(String u_id, String g_id) {
        BaseResponse baseResponse = new BaseResponse();

        GoodsDetails goodsDetails = new GoodsDetails();
        Goods goods = Goods.dao.findFirst("select * from goods where g_id = " + "'" + g_id + "'");
        if (goods != null) {
            // 商品的详细信息
            goodsDetails.setGoods(goods);
            // 获取商品的用户信息
            int g_u_id = goods.getGUId();
            User user = User.dao.findFirst("select * from user where u_id = " + "'" + g_u_id + "'");
            goodsDetails.setUser(user);
            // 判断用户是否关注该商品
            boolean isFollowed = false;
            List<Follow> followList = Follow.dao.find("select * from follow where f_u_id = " + "'" + u_id + "'");
            for (Follow follow: followList
                 ) {
                if (follow.getFGId() == Integer.parseInt(g_id)) {
                    isFollowed = true;
                    break;
                }
            }
            goodsDetails.setFollowed(isFollowed);
            // 该商品的所有评论信息（不包括回复信息）
            List<Comment> comments = Comment.dao.find("select * from comment where com_g_id = " + "'" + g_id + "'" + "and com_reply = 0");
            // 该商品的所有评论信息（包括所有回复信息）
            List<EachGroupComments> allComments = new ArrayList<EachGroupComments>();
            for (Comment comment :
                    comments) {
                // 每一组评论（每一条评论和它的回复评论）
                EachGroupComments eachGroupComments = new EachGroupComments();
                // 每一条评论和它的发布者信息
                CommentAndUser commentAndUser = new CommentAndUser();
                // 获取该评论的发布者信息
                User user1 = User.dao.findFirst("select * from user where u_id = " + "'" + comment.getComUId() + "'");

                commentAndUser.setComment(comment);
                commentAndUser.setUser(user1);

                List<Comment> replyComments = Comment.dao.find("select * from comment where com_reply = " + "'" + comment.getComId() + "'");

                List<CommentAndUser> commentAndUserList = new ArrayList<CommentAndUser>();
                for (Comment comment1 : replyComments
                ) {
                    CommentAndUser commentAndUser1 = new CommentAndUser();
                    User user2 = User.dao.findFirst("select * from user where u_id = " + "'" + comment1.getComUId() + "'");
                    commentAndUser1.setUser(user2);
                    commentAndUser1.setComment(comment1);
                    commentAndUserList.add(commentAndUser1);
                }
                eachGroupComments.setCommentAndUser(commentAndUser);
                eachGroupComments.setReplyCommentsAndUsers(commentAndUserList);
                allComments.add(eachGroupComments);
            }
            goodsDetails.setAllComments(allComments);

            baseResponse.setData(goodsDetails);
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.GOODS_QUERY_NULL);
        }
        return baseResponse;
    }

    /**
     * 上传货品
     *
     * @param u_id
     * @param c_id
     * @param g_name
     * @param g_price
     * @param g_realprice
     * @param g_describe
     * @param uploadFile
     * @return
     */
    public BaseResponse uploadGoods(final String u_id, final String c_id, final String g_name, final String g_price, final String g_realprice, final String g_describe, final UploadFile uploadFile) {
        BaseResponse baseResponse = new BaseResponse();

        Goods goods = new Goods();
        goods.setGState(1);
        goods.setGUId(Integer.parseInt(u_id));
        goods.setGCId(Integer.parseInt(c_id));
        goods.setGName(g_name);
        goods.setGPrice(Integer.parseInt(g_price));
        goods.setGRealprice(Integer.parseInt(g_realprice));
        goods.setGDescribe(g_describe);
        // 保存图片的路径
        String fileName = uploadFile.getFileName();
        String path = "/upload/" + fileName;
        goods.setGImagepath(path);

        if (goods.save()) {
            baseResponse.setResult(ResultCodeEnum.GOODS_UPLOAD_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.GOODS_UPLOAD_FAILURE_DB_ERROR);
        }
        return baseResponse;
    }

    /**
     * 发布求购信息
     *
     * @param u_id
     * @param n_name
     * @param n_price
     * @param n_describe
     * @return
     */
    public BaseResponse notice(String u_id, String n_name, String n_price, String n_describe) {
        BaseResponse baseResponse = new BaseResponse();
        Notice notice = new Notice();
        notice.setNUId(Integer.parseInt(u_id));
        notice.setNName(n_name);
        notice.setNPrice(Integer.parseInt(n_price));
        notice.setNDescribe(n_describe);
        notice.setNState(1);
        if (notice.save()) {
            baseResponse.setResult(ResultCodeEnum.NOTICE_UPLOAD_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.NOTICE_UPLOAD_FAILURE_DB_ERROR);
        }
        return baseResponse;
    }

    /**
     * 修改求购信息的状态
     *
     * @param n_id
     * @param n_state
     * @return
     */
    public BaseResponse modifyNoticeState(String n_id, String n_state) {
        BaseResponse baseResponse = new BaseResponse();
        Notice notice = Notice.dao.findFirst("select * from notice where n_id = " + "'" + n_id + "'");
        if (notice != null) {
            notice.setNState(Integer.parseInt(n_state));
            if (notice.update()) {
                // 修改状态成功
                baseResponse.setResult(ResultCodeEnum.NOTICE_UPDATE_STATE_SUCCESS);
            } else {
                // 修改状态失败，数据库错误
                baseResponse.setResult(ResultCodeEnum.NOTICE_UPDATE_STATE_FAILURE_DB_ERROR);
            }
        } else {
            // 求购信息不存在
            baseResponse.setResult(ResultCodeEnum.GOODS_NOT_EXIST);
        }
        return baseResponse;
    }

    /**
     * 获取所有的求购信息
     *
     * @return
     */
    public BaseResponse getNotice() {
        BaseResponse baseResponse = new BaseResponse();
        List<NoticeAndUser> noticeAndUserList = new ArrayList<NoticeAndUser>();
        List<Notice> noticeList = Notice.dao.find("select * from notice order by n_updatetime desc");
        if (!noticeList.isEmpty()) {
            // 求购信息查询成功
            for (Notice notice: noticeList
                 ) {
                NoticeAndUser noticeAndUser = new NoticeAndUser();
                User user = User.dao.findById(notice.getNUId());

                noticeAndUser.setNotice(notice);
                noticeAndUser.setUser(user);
                noticeAndUserList.add(noticeAndUser);
            }
            baseResponse.setData(noticeAndUserList);
            baseResponse.setResult(ResultCodeEnum.NOTICE_QUERY_SUCCESS);
        } else {
            // 货品为空
            baseResponse.setResult(ResultCodeEnum.NOTICE_QUERY_NULL);
        }
        return baseResponse;
    }
}
