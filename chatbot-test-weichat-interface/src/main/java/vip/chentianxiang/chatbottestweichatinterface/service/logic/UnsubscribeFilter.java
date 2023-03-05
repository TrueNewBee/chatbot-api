package vip.chentianxiang.chatbottestweichatinterface.service.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbottestweichatinterface.model.BehaviorMatter;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 15:26
 * @Github: https://github.com/TrueNewBee
 * @Description: 取消关注公众号
 */
// @Service("unsubscribe")
public class UnsubscribeFilter implements LogicFilter {

    private Logger logger = LoggerFactory.getLogger(UnsubscribeFilter.class);

    @Override
    public String filter(BehaviorMatter request) {
        logger.info("用户{}已取消关注", request.getOpenId());
        return null;
    }

}
