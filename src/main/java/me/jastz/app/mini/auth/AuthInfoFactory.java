package me.jastz.app.mini.auth;

import me.jastz.app.mini.user.UserInfo;

/**
 * @author zhiwen
 */
public interface AuthInfoFactory<T extends UserInfo> {
    /**
     * 获取认证信息
     *
     * @return
     */
    T getAuthInfo(String code);

}
