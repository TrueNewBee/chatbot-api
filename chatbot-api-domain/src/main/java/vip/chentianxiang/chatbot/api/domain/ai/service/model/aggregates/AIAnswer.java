package vip.chentianxiang.chatbot.api.domain.ai.service.model.aggregates;


import lombok.Data;
import vip.chentianxiang.chatbot.api.domain.ai.service.model.vo.Choices;

import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 18:10
 * @Github: https://github.com/TrueNewBee
 * @Description: 封装AI的答案
 */
@Data
public class AIAnswer {

    private List<Choices> choices;

    private String error;

}
