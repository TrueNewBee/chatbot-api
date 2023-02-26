package vip.chentianxiang.chatbot.api.domain.zsxq;

import vip.chentianxiang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import javax.swing.*;
import java.io.IOException;

/**
 * @USER: TrueNewBee
 * @DATE: 2023/2/26 11:22
 * @DESCRIPTION: 知识星球API接口
 */
public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
