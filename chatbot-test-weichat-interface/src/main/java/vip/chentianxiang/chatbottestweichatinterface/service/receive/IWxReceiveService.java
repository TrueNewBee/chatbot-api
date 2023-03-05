package vip.chentianxiang.chatbottestweichatinterface.service.receive;

import vip.chentianxiang.chatbottestweichatinterface.model.BehaviorMatter;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 16:35
 * @Github: https://github.com/TrueNewBee
 * @Description: 微信返回消息接口
 */
public interface IWxReceiveService {

    /**
     * 接收信息
     *
     * @param behaviorMatter    入参
     * @return                  出惨
     * @throws Exception        异常
     */
    String doReceive(BehaviorMatter behaviorMatter) throws Exception;

}