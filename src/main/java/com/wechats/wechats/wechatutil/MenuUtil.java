package com.wechats.wechats.wechatutil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MenuUtil {

    private MenuUtil(){}

    public static String getMenu(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("\"button\":[");
        stringBuilder.append("{");
        stringBuilder.append("\"type\":\"click\",");
        stringBuilder.append("\"name\":\"今日推荐\",");
        stringBuilder.append("\"key\":\"V1001_TODAY_MUSIC\"");
        stringBuilder.append("},");
        stringBuilder.append("{");
        stringBuilder.append("\"name\":\"会员中心\",");
        stringBuilder.append("\"sub_button\":[");
        stringBuilder.append("{");
        stringBuilder.append("\"type\":\"view\",");
        stringBuilder.append("\"name\":\"注册\",");
        stringBuilder.append("\"url\":\"http://www.soso.com/\"");
        stringBuilder.append("},");
        stringBuilder.append("{");
        stringBuilder.append("\"type\":\"view\",");
        stringBuilder.append("\"name\":\"附近的店\",");
        stringBuilder.append("\"url\":\"http://6tnm2q.natappfree.cc/wechat/map/\"");
        stringBuilder.append("}]");
        stringBuilder.append("}]");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String menuServer() {
        String menu = getMenu();
        String token = TokenUtil.getToken();
        String weiXinUrl = " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token;
        URL url = null;
        OutputStream outputStream=null;
        InputStream inputStream=null;
        ByteArrayOutputStream byteArrayOutputStream=null;
        try {
            url = new URL(weiXinUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            /*打开输入流、输出流*/
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            outputStream = urlConnection.getOutputStream();
            /*吧菜单信息通过输出流的形式发送给微信平台*/
            outputStream.write(menu.getBytes("utf-8"));
            inputStream = urlConnection.getInputStream();
            /*创建内存流*/
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len=0;
            byte [] b = new byte[1024];
            while ((len=inputStream.read(b))!=-1){
                byteArrayOutputStream.write(b,0,len);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String response = new String(bytes);
            System.out.println("微信返回的响应值："+response);
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    return "";
    }

    public static void main(String[] args) {
        String menu = MenuUtil.getMenu();
        System.out.println(menu);
        String s = MenuUtil.menuServer();
        System.out.println(s);
    }
}
