package vip.chentianxiang.chatbot.api.domain.zsxq.model.vo;

import lombok.Data;

/**
 * @PROJECT_NAME: 新建文件夹
 * @USER: TrueNewBee
 * @DATE: 2023/2/26 11:22
 * @DESCRIPTION:
 */
@Data
public class Question {

    private Owner owner;

    private Questionee questionee;

    private String text;

    private boolean expired;

    private boolean anonymous;

    private OwnerDetail owner_detail;

    private String owner_location;

}
