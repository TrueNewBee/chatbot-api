package vip.chentianxiang.chatbottestweichatinterface.service.validate;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 14:08
 * @Github: https://github.com/TrueNewBee
 * @Description: 微信校验接口
 */
public interface IWxValidateService {

    /**
     * 验签
     * @param signature 签名
     * @param timestamp 时间
     * @param nonce     当前
     * @return          结果
     */
    boolean checkSign(String signature, String timestamp, String nonce);
}
