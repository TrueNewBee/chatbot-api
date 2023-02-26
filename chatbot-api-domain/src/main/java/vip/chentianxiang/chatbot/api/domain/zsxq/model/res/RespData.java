package vip.chentianxiang.chatbot.api.domain.zsxq.model.res;

import lombok.Data;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @USER: TrueNewBee
 * @DATE: 2023/2/26 12:04
 * @DESCRIPTION:
 */
@Data
public class RespData {

    private List<Topics> topics;
}
