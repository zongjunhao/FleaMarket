package com.zuel.fleamarket.kit;

import com.jfinal.captcha.Captcha;
import com.jfinal.captcha.CaptchaCache;
import com.zuel.fleamarket.model.Captchas;

public class MyCaptchaCache extends CaptchaCache {
    @Override
    public void put(Captcha captcha) {
        // 储存验证码到数据库
//        Record record = new Record();
//        record.set("cap_key", captcha.getKey());
//        record.set("cap_value", captcha.getValue());
//        record.set("cap_expire", captcha.getExpireAt());
//        Db.save("captchas", record);
        Captchas captchas = new Captchas();
        captchas.setCapKey(captcha.getKey());
        captchas.setCapValue(captcha.getValue());
        captchas.setCapExpire(captcha.getExpireAt());
        captchas.save();
    }

    @Override
    public Captcha get(String key) {
        // 从数据库中取出验证码
//        Record record = Db.findFirst("select * from captchas where cap_key = " + "'" + key + "'" + " order by cap_id desc");
        Captchas captchas = Captchas.dao.findFirst("select * from captchas where cap_key = " + "'" + key + "'" + " order by cap_id desc");
//        Captcha captcha = new Captcha(record.getStr("cap_key"), record.getStr("cap_value"));
//        System.out.println("Captcha Key:" + commCaptcha.getKey());
//        System.out.println("Captcha Value:" + commCaptcha.getValue());
        Captcha captcha = new Captcha(captchas.getCapKey(), captchas.getCapValue());
        return captcha;
    }

    @Override
    public void remove(String key) {
        // 删除验证码
//        Record record = Db.findFirst("select * from captchas where cap_key = " + "'" + key + "'");
        Captchas captchas = Captchas.dao.findFirst("select * from captchas where cap_key = " + "'" + key + "'");
//        Db.deleteById("captchas", record.getInt("cap_id"));
        captchas.delete();
    }
}
