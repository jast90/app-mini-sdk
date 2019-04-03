package me.jastz.app.mini.wechat;


import me.jastz.app.mini.wechat.vo.Code2Session;

/**
 * @author zhiwen
 */
public interface WechatMiniProgramOperates {
    String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={appSecret}&js_code={code}&grant_type=authorization_code";

    Code2Session code2Session(String code);
}
