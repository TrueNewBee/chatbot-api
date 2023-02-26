package vip.chentianxiang.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.google.j2objc.annotations.J2ObjCIncompatible;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.chentianxiang.chatbot.api.domain.ai.IOpenAI;
import vip.chentianxiang.chatbot.api.domain.ai.service.OpenAI;
import vip.chentianxiang.chatbot.api.domain.zsxq.IZsxqApi;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.vo.Topics;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 15:17
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${my-chatbot-api.groupId}")
    private String groupId;
    @Value("${my-chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;


    @Test
    public void test_zxsqApi() throws IOException {
        // 拉取问题
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果: {}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId: {} text: {}", topicId, text);

            // 回答问题
            // zsxqApi.answer(groupId, cookie, topicId, text, false);
        }
    }

    @Test
    public void test_openAi() throws IOException {
        String response = openAI.doChatGPT("帮我写个冒泡排序!");
        logger.info("测试结果: {}", JSON.toJSONString(response));

    }
}
