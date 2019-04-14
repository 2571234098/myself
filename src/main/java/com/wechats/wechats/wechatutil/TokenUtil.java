package com.wechats.wechats.wechatutil;

import com.google.gson.Gson;
import com.wechats.wechats.entity.Ticket;
import com.wechats.wechats.entity.Token;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TokenUtil {
    private TokenUtil() {

    }
    public static String getTicket(){
        String ticketUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getToken()+"&type=jsapi";
        InputStream inputStream=null;
        ByteArrayOutputStream byteArrayOutputStream=null;
        try {
            URL url = new URL(ticketUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            inputStream = urlConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len=0;
            byte [] b= new byte[1024];
            while ((len=inputStream.read(b))!=-1){
                byteArrayOutputStream.write(b,0,len);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String response = new String(bytes);
            Gson gson = new Gson();
            Ticket tickets = gson.fromJson(response, Ticket.class);
            String ticket = tickets.getTicket();
            System.out.println("微信平台返回的TICKET:"+ticket);
            return ticket;
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(byteArrayOutputStream!=null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String getToken() {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + WechatUtil.GRANT_TYPE + "&appid=" + WechatUtil.APPID + "&secret=" + WechatUtil.SECRET;
        InputStream inputStream=null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            URL url = new URL(tokenUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            inputStream = urlConnection.getInputStream();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, len);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String response = new String(bytes);
            System.out.println("微信的获取accessToken：" + response);
            Gson gson = new Gson();
            Token token = gson.fromJson(response, Token.class);
            String access_token = token.getAccess_token();
            return access_token;
        } catch (MalformedURLException e) {
            System.out.println("微信获取accessToken的地址格式错误");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(byteArrayOutputStream!=null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String ticket = getTicket();
        System.out.println(ticket);
    }
}
