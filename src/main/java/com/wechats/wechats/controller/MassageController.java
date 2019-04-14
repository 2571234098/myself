package com.wechats.wechats.controller;

import com.wechats.wechats.entity.Wechat;
import com.wechats.wechats.wechatutil.MassageUtil;
import com.wechats.wechats.wechatutil.MenuUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("wechat")
public class MassageController {

    @RequestMapping(value = "auther",method = RequestMethod.POST)
    @ResponseBody
    public String massage(@RequestBody  String massage) throws DocumentException, InstantiationException, IllegalAccessException {
        Wechat wechat = MassageUtil.getWechat(massage);
        if("event".equals(wechat.getMsgType())
                &&"subscribe".equals(wechat.getEvent())){
            String response = MassageUtil.getResponse(wechat,"欢迎关注***公众号");
            MenuUtil.getMenu();
            return response;
        }else if("event".equals(wechat.getMsgType())
                &&"unsubscribe".equals(wechat.getEvent())){
            return MassageUtil.getResponse(wechat,"天长日久，还会再见的");
        }else{
            return MassageUtil.getResponse(wechat,"功能尚未开发，敬请期待");
        }
    }
}
