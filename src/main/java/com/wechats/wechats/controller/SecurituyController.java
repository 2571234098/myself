package com.wechats.wechats.controller;

import com.wechats.wechats.entity.SDK;
import com.wechats.wechats.wechatutil.Sha1Util;
import com.wechats.wechats.wechatutil.TokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@RestController
@RequestMapping("security")
public class SecurituyController {
    @RequestMapping("sdkSignature")
    public SDK jsSdk(String url){
        /*签名生成规则如下：
        参与签名的字段包括noncestr（随机字符串）,
        有效的jsapi_ticket, timestamp（时间戳）,
        url（当前网页的URL，不包含#及其后面部分） 。
        对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，
        使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串string1。
        这里需要注意的是所有参数名均为小写字符。对string1作sha1加密，字段名和字段值都采用原始值，不进行URL 转义。*/
        String noncestr= UUID.randomUUID().toString();
        String jsapi_ticket= TokenUtil.getTicket();
        String timestamp=System.currentTimeMillis()+"";
        Set<String> set = new TreeSet<>();
        set.add("noncestr="+noncestr);
        set.add("jsapi_ticket="+jsapi_ticket);
        set.add("timestamp="+timestamp);
        set.add("url="+url);

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : set) {
            if(stringBuilder.length()>0){
                stringBuilder.append("&");
            }
            stringBuilder.append(s);
        }
        String signature = Sha1Util.sha1(stringBuilder.toString());

        SDK sdk = new SDK(timestamp,noncestr,signature);
        System.out.println(sdk.getSignature());
        return sdk;
    }

}
