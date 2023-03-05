package vip.chentianxiang.chatbottestweichatinterface.service.validate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbottestweichatinterface.utils.sdk.SignatureUtil;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 14:08
 * @Github: https://github.com/TrueNewBee
 * @Description: 消息验证
 */
@Service
public class WxValidateServiceImpl implements IWxValidateService {

    @Value("${wx.config.token}")
    private String token;

    @Override
    public boolean checkSign(String signature, String timestamp, String nonce) {
        return SignatureUtil.check(token, signature, timestamp, nonce);
    }
}
