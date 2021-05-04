package com.guixin.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.guixin.pojo.vo.ChatVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Api(tags = "聊天室")
@ServerEndpoint(value = "/websocket/{sendUser}")
public class WebSocketController {
    // 当前会话,属于websocket的session
    private Session session;
    // 当前用户
    private String sendUser;

    private static final ConcurrentHashMap<String,WebSocketController> webSocketMap = new ConcurrentHashMap<>();

    private static final ArrayList<String> onlineList = new ArrayList<>();

    @OnOpen
    public void onOpen(@PathParam("sendUser")String sendUser,Session session){
        this.session = session;
        this.sendUser = sendUser;
        webSocketMap.put(sendUser,this);
        onlineList.add(sendUser);
        HashMap<String,Object> message = new HashMap<>();
        message.put("type",3);
        message.put("onlineUser",onlineList);
        broadcast(JSONUtil.toJsonStr(message),3);
        System.out.println("有新连接加入！当前在线人数为"+webSocketMap.size());
    }

    @OnMessage
    public void onMessage(Session session,String message){
        JSONObject jsonObject = JSONUtil.parseObj(message);
        ChatVo chatVo = new ChatVo();
        // 获取发送者
        chatVo.setSendUser(jsonObject.getStr("sendUser"));
        // 获取发送给谁
        chatVo.setToUser(jsonObject.getStr("toUser"));
        // 获取发送内容
        chatVo.setMessage(jsonObject.getStr("message"));
        // 获取用户头像
        chatVo.setAvatar(jsonObject.getStr("avatar"));
        // 接收到了消息
        chatVo.setType(1);
        if (webSocketMap.get(chatVo.getToUser()) !=null){
            webSocketMap.get(chatVo.getToUser()).sendMessage(JSONUtil.toJsonStr(chatVo));
        } else {
            webSocketMap.get(chatVo.getSendUser()).sendMessage("对方不在线！");
        }

    }

    @OnClose
    public void onClose(){
        webSocketMap.remove(sendUser);
        onlineList.remove(sendUser);
        HashMap<String,Object> message = new HashMap<>();
        message.put("type",2);
        message.put("onlineUser",onlineList);
        message.put("closeUser",sendUser);
        broadcast(JSONUtil.toJsonStr(message),2);
        System.out.println("有一连接关闭！当前在线人数为"+webSocketMap.size());
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void broadcast(String message,int type){
        if (type == 2){ // 用户离开
            for (WebSocketController chat: webSocketMap.values()){
                if (chat.session != this.session){
                    chat.sendMessage(message);
                }
            }
        } else{  // 群发通知
            for (WebSocketController chat:webSocketMap.values()){
                chat.sendMessage(message);
            }
        }
    }

    public synchronized void sendMessage(String message){
        this.session.getAsyncRemote().sendText(message);
    }

}
