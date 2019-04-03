package me.jastz.app.mini.alipay.domain;

import me.jastz.app.mini.user.UserInfo;

/**
 * @author zhiwen
 */
public class TokenUid extends UserInfo {
    private String aliPayUserId;
    private String token;

    public String getAliPayUserId() {
        return aliPayUserId;
    }

    public void setAliPayUserId(String aliPayUserId) {
        this.aliPayUserId = aliPayUserId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
