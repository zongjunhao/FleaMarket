package com.zuel.fleamarket.kit;

import com.jfinal.captcha.Captcha;
import com.jfinal.captcha.CaptchaCache;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class MyCaptchaCache extends CaptchaCache {
    @Override
    public void put(Captcha captcha) {
        // 储存验证码到数据库
        Record record = new Record();
        record.set("key", captcha.getKey());
        record.set("value", captcha.getValue());
        record.set("expireAt", captcha.getExpireAt());
        Db.save("comm_captcha", record);
    }

    @Override
    public Captcha get(String key) {
        // 从数据库中取出验证码
        Record record = Db.findFirst("select * from comm_captcha where key = ? order by id desc", key);
        Captcha captcha = new Captcha(record.getStr("key"), record.getStr("value"));
        return captcha;
    }

    @Override
    public void remove(String key) {
        // 删除验证码
        Record record = Db.findFirst("select * from comm_captcha where key = ?", key);
        Db.deleteById("comm_captcha", record.getInt("id"));
    }
}
