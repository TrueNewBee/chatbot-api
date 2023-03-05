package vip.chentianxiang.chatbottestweichatinterface.ai;

import java.io.IOException;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 17:57
 * @Github: https://github.com/TrueNewBee
 * @Description: ChatGPT Open AI 接口 https://api.forchange.cn/
 */
public interface IOpenAI {

    String doChatGPT(String question) throws IOException;
}
