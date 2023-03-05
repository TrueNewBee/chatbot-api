package vip.chentianxiang.chatbottestweichatinterface.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 15:19
 * @Github: https://github.com/TrueNewBee
 * @Description: 消息类型
 */
@Data
public class MessageTextEntity {

    @XStreamAlias("MsgId")
    private String msgId;
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("Content")
    private String content;
    @XStreamAlias("Event")
    private String event;
    @XStreamAlias("EventKey")
    private String eventKey;

    public MessageTextEntity() {
    }


}