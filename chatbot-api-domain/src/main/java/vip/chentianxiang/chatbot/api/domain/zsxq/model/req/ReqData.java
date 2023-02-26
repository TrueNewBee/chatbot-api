package vip.chentianxiang.chatbot.api.domain.zsxq.model.req;

import lombok.Data;

/**
 * @USER: TrueNewBee
 * @DATE: 2023/2/26 11:22
 * @DESCRIPTION:
 */
@Data
public class ReqData {

    private String text;
    private String[] image_ids = new String[]{};
    private boolean silenced;

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }

}
