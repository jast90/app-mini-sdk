package me.jastz.app.mini.wechat.util;


import me.jastz.app.mini.crypto.Pkcs7Encoder;
import me.jastz.app.mini.wechat.domain.WxaUserInfo;
import me.jastz.common.json.JsonUtil;

import java.util.Base64;

/**
 * Created by zhiwen on 2017/3/17.
 */
public class WxaUtil {



    public static WxaUserInfo getUserInfo(String sessionKey, String encryptedData, String iv) {
        byte[] sessionKeyByte = Base64.getDecoder().decode(sessionKey.getBytes());
        byte[] encryptedDateByte = Base64.getDecoder().decode(encryptedData.getBytes());
        byte[] ivByte = Base64.getDecoder().decode(iv.getBytes());
        WxaUserInfo wxaUserInfo = JsonUtil.jsonToObject(new String(Pkcs7Encoder.decryptOfDiyIv(encryptedDateByte
                , sessionKeyByte, ivByte)), WxaUserInfo.class);
        return wxaUserInfo;
    }


    public static void main(String[] args) {
        getUserInfo("oSrwE0ScvJLBQmBdywwscS3DP3bI"
                , "ETOcV1U59Ax9mvFvz1mNal3CWh8qeM/NKFnagfO7+e+8SLl/ChTsmpWNdSFKU9ql6o9K0AXEMQMu4RNg2fFMxsUadbj+yH34zznBTNXIDVc2vdj0dwb2B39HkhycKc4fDwbdZrsDrWeWh9E6h84DI7KbH5AXkKVkLRcFxiSeytpnrmYSrDHT0roj1e2EtwSpuaZcwXBKYQEn/2+sZmjUH9Xd2u/t2aHO2ujKQ21vacWsUplmvw0W5WShd+2PB9U4oBNIAkUXh+PMu2hVOpv8bHwslzKtk7Mut5TeHJ5WbkHfHziTtTZCporBRmxAfiWuZZ66DrCyDyG0fKXpUyQWCo+EEObMncZJFmGG56bjqNCd59jA++69b9YcYnlkFRIGi/B+37vq/pGJfBH29+xC2UsQ7aYsPTK723MxNeFSGVvQOXf0tlu4goETqtkhLSRZjz+VoA0iAeofGaU5vmPUjzMHT3Ee4zZ097tEVcHjQI4="
                , "7aHJHWPpUPvDvkucdqdo7Q==");
        WxaUserInfo wxaUserInfo = JsonUtil.jsonToObject("{\"openId\":\"oLr0J0RSjn1U9VMRyL_pgf0o354Q\",\"nickName\":\"疯狂的乌龟\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Shenzhen\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/RQCuDQnImhtBYzc46rTYFeJLn7BVpWUB8tvu8Ep2JugA0Q80mfnHLK7ACmhGCricKoiaKGhv23eGCFPbukuGcAsA/0\",\"unionId\":\"okyCzwSdQoLYMoYIIM9rBucb9GSU\",\"watermark\":{\"timestamp\":1490346644,\"appid\":\"wx508d2d28f5c722f1\"}}", WxaUserInfo.class);
        System.out.println(wxaUserInfo);
    }
}
