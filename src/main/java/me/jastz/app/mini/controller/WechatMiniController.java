package me.jastz.app.mini.controller;

import me.jastz.app.mini.wechat.domain.LoginForm;

/**
 * @author zhiwen
 */
public interface WechatMiniController extends AppMiniController {
    /**
     * 根据 wx.getUserInfo(OBJECT) 获得用户信息进行登入
     *
     * @param loginForm
     */
    void login(LoginForm loginForm);
}
