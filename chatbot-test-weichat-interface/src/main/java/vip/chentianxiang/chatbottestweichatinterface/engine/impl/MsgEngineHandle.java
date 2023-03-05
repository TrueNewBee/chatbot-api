package vip.chentianxiang.chatbottestweichatinterface.engine.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbottestweichatinterface.engine.EngineBase;
import vip.chentianxiang.chatbottestweichatinterface.model.BehaviorMatter;
import vip.chentianxiang.chatbottestweichatinterface.model.MessageTextEntity;
import vip.chentianxiang.chatbottestweichatinterface.service.logic.LogicFilter;
import vip.chentianxiang.chatbottestweichatinterface.utils.XmlUtil;

/**
 * @description: 消息处理
 * @author: 小傅哥，微信：fustack
 * @date: 2021/12/18
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
extends EngineBase
 */
@Service
public class MsgEngineHandle  {

    @Value("${wx.config.originalid:gh_95b2229b90fb}")
    private String originalId;

    public String process(BehaviorMatter request) throws Exception {
        // LogicFilter router = super.router(request);
        // if (null == router) {
        //     return null;
        // }
        // String resultStr = router.filter(request);
        // if (StringUtils.isBlank(resultStr)) {
        //     return "";
        // }

        //反馈信息[文本]
        MessageTextEntity res = new MessageTextEntity();
        res.setToUserName(request.getOpenId());
        res.setFromUserName(originalId);
        res.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000L));
        res.setMsgType("text");
        res.setContent(request.getContent());
        return XmlUtil.beanToXml(res);
    }

}
