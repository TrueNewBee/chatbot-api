package vip.chentianxiang.chatbottestweichatinterface.ai.service.model.vo;

import lombok.Data;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 18:03
 * @Github: https://github.com/TrueNewBee
 * @Description: 选择
 */
@Data
public class Choices {

    private String text;

    private int index;

    private String finish_reason;

    private boolean block;

}
