package vip.chentianxiang.chatbot.api.domain.zsxq.model.req;

import lombok.Data;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 14:12
 * @Github: https://github.com/TrueNewBee
 * @Description: 请求问答接口信息
 */
@Data
public class AnswerReq {

    private ReqData req_data;

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

}
