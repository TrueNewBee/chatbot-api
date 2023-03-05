package vip.chentianxiang.chatbottestweichatinterface.model;

import lombok.Data;
import java.util.Date;

/**
 * @Author: TrueNewBee
 * @Date: 2023/3/5 15:20
 * @Github: https://github.com/TrueNewBee
 * @Description:  封装后消息类型
 */
@Data
public class BehaviorMatter {

    private String openId;
    private String fromUserName;
    private String msgType;
    private String content;
    private String event;
    private Date createTime;


}