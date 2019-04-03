package me.jastz.app.mini.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import me.jastz.common.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhiwen
 */
public class AliPayMiniTemplate {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String appId;
    private String privateKey;
    private String aliPayPublicKey;
    private AlipayClient alipayClient;

    public AliPayMiniTemplate(String appId, String privateKey, String aliPayPublicKey) {
        this.appId = appId;
        this.privateKey = privateKey;
        this.aliPayPublicKey = aliPayPublicKey;
        alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", getAppId(), getPrivateKey(), "json", "GBK", getAliPayPublicKey(), "RSA2");
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
    }

    /**
     * 参考：https://docs.alipay.com/mini/introduce/auth
     *
     * @param authCode
     * @return
     * @throws AlipayApiException
     */
    public AlipaySystemOauthTokenResponse getUserInfoByAuthCode(String authCode) {
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        if (response.isSuccess()) {
            return response;
        } else {
            logger.debug(JsonUtil.objectToJson(response));
            return null;
        }
    }

    public AlipayUserInfoShareResponse getUserInfo(String accessToken) {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response == null) {
            return null;
        }
        if (response.isSuccess()) {
            return response;
        } else {
            logger.debug(JsonUtil.objectToJson(response));
            return null;
        }
    }

}
