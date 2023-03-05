package vip.chentianxiang.chatbottestweichatinterface.ai.service;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vip.chentianxiang.chatbottestweichatinterface.ai.IOpenAI;
import vip.chentianxiang.chatbottestweichatinterface.ai.service.model.aggregates.AIAnswer;
import vip.chentianxiang.chatbottestweichatinterface.ai.service.model.vo.Choices;

import java.io.IOException;
import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 18:00
 * @Github: https://github.com/TrueNewBee
 * @Description: 调用 OpenAI接口 免登入 https://api.forchange.cn/
 */
@Service
public class OpenAI implements IOpenAI {

    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Override
    public String doChatGPT(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.forchange.cn/");
        post.addHeader("Content-Type", "application/json");

        String paramJson = "{\n" +
                "  \"prompt\": \"Human: "+question+"\\nAI:\\nHuman:"+question+"\\nAI:\",\n" +
                "  \"tokensLength\": 45\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 接收返回json数据
            String jsonStr = EntityUtils.toString(response.getEntity());
            // 把json数据转换成 model对象
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            // 用于封装choice中的text
            for (Choices choice : choices){
                answers.append(choice.getText());
            }
            return answers.toString();

        } else {
            throw new RuntimeException("api.forchange.cn Err code is" + response.getStatusLine().getStatusCode());
        }
    }
}
