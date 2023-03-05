package vip.chentianxiang.chatbottestweichatinterface.service.receive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbottestweichatinterface.engine.Engine;
import vip.chentianxiang.chatbottestweichatinterface.model.BehaviorMatter;
import vip.chentianxiang.chatbottestweichatinterface.model.MessageTextEntity;
import vip.chentianxiang.chatbottestweichatinterface.utils.XmlUtil;

import javax.annotation.Resource;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 16:36
 * @Github: https://github.com/TrueNewBee
 * @Description: 返回微信消息
 */

@Service
public class WxReceiveServiceImpl implements IWxReceiveService {
    @Value("${wx.config.originalid:gh_95b2229b90fb}")
    private String originalId;

    @Override
    public String doReceive(BehaviorMatter request) throws Exception {
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
