package com.zuel.fleamarket.config;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.zuel.fleamarket.controller.IndexController;

public class MainConfig extends JFinalConfig {

    @Override
    public void configConstant(Constants me) {
        //读取数据库配置文件
        PropKit.use("config.properties");
        //设置当前是否为开发模式
        me.setDevMode(PropKit.getBoolean("devMode"));
        //设置默认上传文件保存路径 getFile等使用
        me.setBaseUploadPath("upload/temp/");
        //设置上传最大限制尺寸
        me.setMaxPostSize(1024 * 1024 * 1024);
        //设置默认下载文件路径 renderFile使用
        me.setBaseDownloadPath("download");
        //设置默认视图类型
        me.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
