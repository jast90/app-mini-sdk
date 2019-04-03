package me.jastz.app.mini.wechat;

import me.jastz.app.mini.wechat.impl.WechatMiniProgramOperatesImpl;
import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiwen
 */
public class WechatMiniTemplate {

    private String miniProgramAppId;

    private String miniProgramAppSecret;


    private RestTemplate restTemplate;

    public WechatMiniTemplate(String miniProgramAppId, String miniProgramAppSecret) {
        this.miniProgramAppId = miniProgramAppId;
        this.miniProgramAppSecret = miniProgramAppSecret;
        restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Lists.newArrayList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.getMessageConverters().addAll(messageConverters);
    }

    public WechatMiniProgramOperates miniProgramOperates() {
        return new WechatMiniProgramOperatesImpl(restTemplate, getMiniProgramAppId(), getMiniProgramAppSecret());
    }

    public String getMiniProgramAppId() {
        return miniProgramAppId;
    }

    public void setMiniProgramAppId(String miniProgramAppId) {
        this.miniProgramAppId = miniProgramAppId;
    }

    public String getMiniProgramAppSecret() {
        return miniProgramAppSecret;
    }

    public void setMiniProgramAppSecret(String miniProgramAppSecret) {
        this.miniProgramAppSecret = miniProgramAppSecret;
    }
}
