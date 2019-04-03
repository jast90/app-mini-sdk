package me.jastz.app.mini.wechat.domain;

import me.jastz.app.mini.user.UserInfo;

/**
 * @author zhiwen
 */
public class WxaSessionValue extends UserInfo {
    private String sessionKey;
    private String openId;

    public WxaSessionValue() {
    }

    public WxaSessionValue(String sessionKey, String openId) {
        this.sessionKey = sessionKey;
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
