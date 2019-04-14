package com.wechats.wechats.entity;

import org.omg.CORBA.PRIVATE_MEMBER;

public class Wechat {
    /*ToUserName	是	接收方帐号（收到的OpenID）
        FromUserName	是	开发者微信号
        CreateTime	是	消息创建时间 （整型）
        MsgType	是	消息类型，文本为text
        Content*/
    private String ToUserName;

    private String FromUserName;

    private String CreateTime;

    private String MsgType;

    private String Content;

    private String MsgId;

    private String Event;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
