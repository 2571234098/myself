package com.wechats.wechats.controller;


import com.wechats.wechats.wechatutil.Sha1Util;
import com.wechats.wechats.wechatutil.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping(value = "wechat")
public class AutherControoler {
    @RequestMapping( value = "auther",method = RequestMethod.GET)
    @ResponseBody
    /*signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
      timestamp	时间戳
      nonce	随机数
      echostr 随机字符串*/
    public String auther(String signature,String timestamp,String nonce,String echostr){
        /*1）将token、timestamp、nonce三个参数进行字典序排序
          2）将三个参数字符串拼接成一个字符串进行sha1加密
          3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信*/
        Set<String> set = new TreeSet();
        set.add(WechatUtil.TOKEN);
        set.add(timestamp);
        set.add(nonce);

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            stringBuilder.append(s);
        }
        String s = Sha1Util.sha1(stringBuilder.toString());
        if(s.equals(signature)){
            System.out.println("微信认证");
            return echostr;
        }
        return "";
    }
}
