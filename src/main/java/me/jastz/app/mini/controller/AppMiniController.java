package me.jastz.app.mini.controller;

/**
 * @author zhiwen
 */
public interface AppMiniController {

    /**
     * 根据 code 获取 session
     *
     * @param code
     * @param isDev 取值：true 是开发环境，false 正式环境
     * @return
     */
    String getSession(String code, boolean isDev);


}
