package me.jastz.app.mini.auth.alipay;

import me.jastz.app.mini.alipay.AliPayMiniTemplate;
import me.jastz.app.mini.alipay.domain.TokenUid;
import me.jastz.app.mini.auth.AuthInfoFactory;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import me.jastz.common.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhiwen
 */
public class AliPayAuthInfoFactory implements AuthInfoFactory<TokenUid> {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AliPayMiniTemplate alipayMiniTemlate;

    public AliPayAuthInfoFactory(AliPayMiniTemplate alipayMiniTemlate) {
        this.alipayMiniTemlate = alipayMiniTemlate;
    }

    @Override
    public TokenUid getAuthInfo(String code) {
        AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = alipayMiniTemlate.getUserInfoByAuthCode(code);
        logger.debug("AlipaySystemOauthTokenResponse:{}", JsonUtil.objectToJson(alipaySystemOauthTokenResponse));
        TokenUid tokenUid = new TokenUid();
        tokenUid.setAliPayUserId(alipaySystemOauthTokenResponse.getAlipayUserId());
        tokenUid.setToken(alipaySystemOauthTokenResponse.getAccessToken());
        return tokenUid;
    }
}
