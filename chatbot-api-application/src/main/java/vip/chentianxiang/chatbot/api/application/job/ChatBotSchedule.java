package vip.chentianxiang.chatbot.api.application.job;


import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vip.chentianxiang.chatbot.api.domain.ai.IOpenAI;
import vip.chentianxiang.chatbot.api.domain.zsxq.IZsxqApi;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import vip.chentianxiang.chatbot.api.domain.zsxq.model.vo.Topics;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


/**
 * @Author: TrueNewBee
 * @Date: 2023/2/26 20:29
 * @Github: https://github.com/TrueNewBee
 * @Description: 查询并回答问题的定时任务
 */
@EnableScheduling
@Component
public class ChatBotSchedule {

    private Logger logger =  LoggerFactory.getLogger(ChatBotSchedule.class);

    @Value("${my-chatbot-api.groupId}")
    private String groupId;
    @Value("${my-chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAI;

    // 表达式: cron.qqe2.com
    // 每隔一分钟执行一次
    // @Scheduled(cron = "0 */1 * * * ?")
    @Scheduled(cron = "0 */1 * * * ?")
    public void run(){
        try{
            // 进行随机查看回答
            if (new Random().nextBoolean()) {
                logger.info("随机打样中.....");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour >22 || hour <7){
                logger.info("打烊时间不工作,AI 下班了!");
                return;
            }

            // 1.检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("检索结果: {}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待回答的问题");
                return;
            }

            // 2. AI回答问题
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(topic.getQuestion().getText().trim());

            // 3. 问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号:{} 问题: {} 回答: {} 状态: {}",topic.getTopic_id(),topic.getQuestion(),answer,status);


        }catch (Exception e){
            logger.error("自动回答问题异常",e);
        }
    }

}
