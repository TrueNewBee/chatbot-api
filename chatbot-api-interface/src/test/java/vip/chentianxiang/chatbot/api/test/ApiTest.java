package vip.chentianxiang.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @PROJECT_NAME: 新建文件夹
 * @USER: TrueNewBee
 * @DATE: 2023/2/25 15:15
 * @DESCRIPTION: 单元测试
 */
public class ApiTest {

    // 获取回答
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51112128518884/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221857d4616f7699-0510035f06881cc-7b555472-1327104-1857d4616f889e%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg1N2Q0NjE2Zjc2OTktMDUxMDAzNWYwNjg4MWNjLTdiNTU1NDcyLTEzMjcxMDQtMTg1N2Q0NjE2Zjg4OWUifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%221857d4616f7699-0510035f06881cc-7b555472-1327104-1857d4616f889e%22%7D; zsxq_access_token=48503C4B-AAFC-BB68-18E5-7BA5FF74057C_C9C1242161026549; abtest_env=beta; zsxqsessionid=dd7e54ce0457f1265569ddefa57189ee");
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    // 发送回答
    @Test
    public void  answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/214848251451141/answer");
        post.addHeader("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221857d4616f7699-0510035f06881cc-7b555472-1327104-1857d4616f889e%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg1N2Q0NjE2Zjc2OTktMDUxMDAzNWYwNjg4MWNjLTdiNTU1NDcyLTEzMjcxMDQtMTg1N2Q0NjE2Zjg4OWUifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%221857d4616f7699-0510035f06881cc-7b555472-1327104-1857d4616f889e%22%7D; zsxq_access_token=48503C4B-AAFC-BB68-18E5-7BA5FF74057C_C9C1242161026549; abtest_env=beta; zsxqsessionid=dd7e54ce0457f1265569ddefa57189ee");
        post.addHeader("Content-Type","application/json; charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"毋庸置疑~\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    // 调用ChatGPT 接口
    /**
     * curl https://api-inference.huggingface.co/models/microsoft/CodeGPT-small-java \
     * 	-X POST \
     * 	-d '{"inputs": "Can you please let us know more details about your "}' \
     * 	-H "Authorization: Bearer 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"
     */
    /*
    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api-inference.huggingface.co/models/microsoft/CodeGPT-small-java");

        post.addHeader("Content-Type","application/json");
        post.addHeader("Authorization","Bearer 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");

        String paramJson = "{\"inputs\": \"如何学习java\"}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    } */

    // 测试  白嫖别人的接口就是爽
    @Test
    public void test_ChatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.forchange.cn/");
        post.addHeader("Content-Type", "application/json");
        // post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\n" +
                "  \"prompt\": \"Human: 如何成为狂飙中的高启强 \\nAI:\\nHuman:如何成为狂飙中的高启强\\nAI:\",\n" +
                "  \"tokensLength\": 45\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
