package vip.chentianxiang.chatbottestweichatinterface.service.logic;

import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbottestweichatinterface.model.BehaviorMatter;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 15:26
 * @Github: https://github.com/TrueNewBee
 * @Description: 关注公众号
 */
// @Service("subscribe")
public class SubscribeFilter implements LogicFilter {

    @Override
    public String filter(BehaviorMatter request) {
        return "感谢关注，快来回复抽奖，参与小傅哥的活动吧！";
    }

}
