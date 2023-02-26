package vip.chentianxiang.chatbot.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbot.api.domain.zsxq.IZsxqApi;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.req.AnswerReq;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.req.ReqData;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.res.AnswerRes;

import java.io.IOException;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 12:26
 * @Github: https://github.com/TrueNewBee
 * @Description: Api接口调用实现类
 */
@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    /**
     * @param groupId 回答的ID
     * @param cookie  用户的cookie
     * @return 返回获取到问答信息
     * @throws IOException 返回报错信息
     */
    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据。groupId：{} jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicID err code is" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json; charset=UTF-8");
        post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.52");

        /* 测试数据
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"毋庸置疑~\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

         */
        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.toJSONString(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            logger.info("回答问题结果. groupId: {} topicId: {} jsonStr: {}", groupId, topicId, jsonStr);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is" + response.getStatusLine().getStatusCode());
        }
    }
}
