package me.jastz.app.mini.auth.wechat;

import me.jastz.app.mini.auth.AuthInfoFactory;
import me.jastz.app.mini.wechat.WechatMiniTemplate;
import me.jastz.app.mini.wechat.domain.WxaSessionValue;
import me.jastz.app.mini.wechat.vo.Code2Session;

/**
 * @author zhiwen
 */
public class WechatAuthInfoFactory implements AuthInfoFactory<WxaSessionValue> {

    private WechatMiniTemplate wechatMiniTemplate;

    public WechatAuthInfoFactory(WechatMiniTemplate wechatMiniTemplate) {
        this.wechatMiniTemplate = wechatMiniTemplate;
    }

    @Override
    public WxaSessionValue getAuthInfo(String code) {
        WxaSessionValue wxaSessionValue = new WxaSessionValue();
        Code2Session code2Session = wechatMiniTemplate.miniProgramOperates().code2Session(code);
        wxaSessionValue.setOpenId(code2Session.getOpenid());
        wxaSessionValue.setSessionKey(code2Session.getSession_key());
        return wxaSessionValue;
    }
}
