package com.wechats.wechats.wechatutil;

import com.wechats.wechats.entity.Wechat;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.lang.reflect.Field;

public class MassageUtil {
    private MassageUtil(){

    }

    public static Wechat getWechat(String massage) throws DocumentException, IllegalAccessException, InstantiationException {

        Document document = DocumentHelper.parseText(massage);
        Element rootElement = document.getRootElement();
        Class<Wechat> wechatClass = Wechat.class;
        Field[] declaredFields = wechatClass.getDeclaredFields();
        Wechat wechat = wechatClass.newInstance();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            Element element = rootElement.element(name);
            if(element!=null){
                String textTrim = element.getTextTrim();
                declaredField.setAccessible(true);
                declaredField.set(wechat,textTrim);
            }
        }
        return wechat;
    }

    public static String getResponse(Wechat wechat,String content){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>");
        stringBuilder.append("<ToUserName><![CDATA["+wechat.getFromUserName()+"]]></ToUserName>");
        stringBuilder.append("<FromUserName><![CDATA["+wechat.getToUserName()+"]]></FromUserName>");
        stringBuilder.append("<CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
        stringBuilder.append("<MsgType><![CDATA[text]]></MsgType>");
        stringBuilder.append("<Content><![CDATA["+content+"]]></Content>");
        stringBuilder.append("</xml>");

        return stringBuilder.toString();
    }
}
