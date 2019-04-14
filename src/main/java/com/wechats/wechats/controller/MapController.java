package com.wechats.wechats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("wechat")
public class MapController {

    @RequestMapping("map")
    public String getMap(){
        System.out.println("微信地图");
        return "map";
    }

}
