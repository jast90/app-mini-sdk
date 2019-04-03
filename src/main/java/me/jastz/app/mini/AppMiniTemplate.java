package me.jastz.app.mini;

import me.jastz.app.mini.auth.AuthInfoFactory;
import me.jastz.app.mini.auth.alipay.AliPayAuthInfoFactory;
import me.jastz.app.mini.auth.wechat.WechatAuthInfoFactory;
import me.jastz.app.mini.enums.AppMiniType;
import me.jastz.app.mini.session.Session;
import me.jastz.app.mini.session.SessionIdFactory;
import me.jastz.app.mini.user.UserInfo;
import me.jastz.app.mini.wechat.domain.WxaSessionValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zhiwen
 */
public class AppMiniTemplate<T extends UserInfo> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Session<T> session;

    /**
     * 授权工厂
     *
     * @see AliPayAuthInfoFactory
     * @see WechatAuthInfoFactory
     */
    private AuthInfoFactory<T> authInfoFactory;

    public AppMiniTemplate(AuthInfoFactory authInfoFactory) {
        this.authInfoFactory = authInfoFactory;
    }

    /**
     * 根据code 获取 session
     *
     * @param code
     * @param isDev 取值：true 是开发环境，false 正式环境
     * @return
     */
    public String getSession(String code, boolean isDev) {
        String sessionId = SessionIdFactory.createSessionId();
        if (!isDev) {
            session.put(getSessionKey(sessionId), authInfoFactory.getAuthInfo(code));
        } else {
            session.put(getSessionKey(sessionId), null);
        }
        return sessionId;
    }

    /**
     * 关联Uid到SessionId
     *
     * @param uid
     * @param sessionId
     * @return
     */
    public void setSessionUid(int uid, String sessionId) {
        if (authInfoFactory instanceof WechatAuthInfoFactory) {
            T t = session.get(getSessionKey(sessionId));
            t.setUid(uid);
            session.put(getSessionKey(sessionId), t);
        }
    }

    /**
     * 获取当前业务平台登入的用户
     *
     * @param sessionId
     * @return
     */
    public int getSessionUid(String sessionId) throws Exception {
        UserInfo userInfo = session.get(getSessionKey(sessionId));
        if (userInfo == null) {
            throw new UserNotLoginException();
        }
        return userInfo.getUid();
    }

    /**
     * 获取session存储数据
     *
     * @param sessionId
     * @return
     * @throws Exception
     */
    public T getWechatSessionKey(String sessionId) throws Exception {
        T userInfo = session.get(getSessionKey(sessionId));
        return userInfo;
    }

    public String getSessionIdByUid(int uid) {
        String key = null;
        Map<String, T> values = session.allValue(getKey());
        for (String item : session.allValue(getKey()).keySet()) {
            if (uid == values.get(item).getUid()) {
                key = item;
                break;
            }
        }
        if (key == null) {
            return null;
        }
        String[] split = key.split(":");
        if (split.length == getKey().split(":").length) {
            return split[split.length - 1].replace("session_", "");
        }
        return null;
    }

    /**
     * 移除所有已登入的用户
     *
     * @param uid
     */
    public void removeByUid(int uid) {
        Map<String, WxaSessionValue> values = (Map<String, WxaSessionValue>) session.allValue(getKey());
        for (String key : session.allValue(getKey()).keySet()) {
            if (uid == values.get(key).getUid()) {
                session.removeByKey(key);
            }
        }
    }


    public String getSessionKey(String sessionId) {
        String key = getKey();
        logger.debug("redis session key :{}", key);
        return String.format(key, sessionId);
    }

    public String getKey() {
        String KEY = "%s:mini:session_%s";
        AppMiniType appMiniType = null;
        if (authInfoFactory instanceof WechatAuthInfoFactory) {
            appMiniType = AppMiniType.wechat;
        } else if (authInfoFactory instanceof AliPayAuthInfoFactory) {
            appMiniType = AppMiniType.alipay;
        }
        return String.format(KEY, appMiniType != null ? appMiniType.name() : "");
    }

}
