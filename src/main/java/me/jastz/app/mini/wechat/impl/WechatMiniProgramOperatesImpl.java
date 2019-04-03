package me.jastz.app.mini.wechat.impl;

import me.jastz.app.mini.wechat.WechatMiniProgramOperates;
import me.jastz.app.mini.wechat.vo.Code2Session;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhiwen
 */
public class WechatMiniProgramOperatesImpl implements WechatMiniProgramOperates {

    private RestTemplate restTemplate;
    private String appId;
    private String appSecret;

    public WechatMiniProgramOperatesImpl(RestTemplate restTemplate, String appId, String appSecret) {
        this.restTemplate = restTemplate;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override
    public Code2Session code2Session(String code) {
        return restTemplate.getForObject(code2SessionUrl, Code2Session.class, appId, appSecret, code);
    }
}
