package vip.chentianxiang.chatbot.api.domain.zsxq.model.aggregates;

import lombok.Data;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.res.RespData;

/**
 * @USER: TrueNewBee
 * @DATE: 2023/2/26 11:22
 * @DESCRIPTION: 未回答问题的聚合信息
 */
@Data
public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespData resp_data;

}
