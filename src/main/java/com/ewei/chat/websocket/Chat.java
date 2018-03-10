package com.ewei.chat.websocket;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import javax.websocket.*;

/**
 * 聊天服务
 * 
 *
 */

@ServerEndpoint(value="/chat",configurator=GetHttpSessionConfigurator.class)
public class Chat {
	
	//静态变量，用于记录当前在线人数
	private static int onlineCount = 0;

	//用来存放每个客户端对应的Chat对象。
	private static  CopyOnWriteArraySet<Chat> chatSet = new CopyOnWriteArraySet<Chat>();
	
	//websocket用于与客户端通信,发送数据
	private Session session;
	
	//request的session，用于获取用户信息
	private HttpSession httpSession;
	
	//用户id
	private String userid;
	
	//在线列表
	private	static List list = new ArrayList();
	
	//用户名和websocket的session绑定的路由表
	private static Map routeTable = new HashMap();
	
	/**
	 * 连接成功调用的方法
	 * @param session websocket用于与客户端通信,发送数据
	 * @param config 获取httpSession，用来区别哪个客户端
	 */
	@OnOpen
	public void onOpen(Session session,EndpointConfig config) {
		
		//设置websocket连接的session
		this.session = session;
		
		//存放当前对象
		chatSet.add(this);
		
		//当前人数+1
		addOnlineCount();
		
		//获得http中request的httpSession
		this.httpSession = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
		
		//获得当前用户的userid
		this.userid = (String)httpSession.getAttribute("userid");
		
		//将用户id加入在线列表
		list.add(userid);
		
		//将用户名和session绑定到路由表
		routeTable.put(userid,session);
		
		//测试代码 
		System.out.println("有新连接加入，当前在线人数有：" + getOnlineCount());
		
		//封装消息
		String message = setJSON("[" + userid + "]加入聊天室,当前在线人数为"+getOnlineCount()+"位","notice",list);
		sendAll(message);
		
	}
	
	/**
	 * 接受前端传来的JSON数据，数据格式如下
	 * message : {
     *           content : message,
     *           from : '${userid}',
     *           to : to,      //接收人,如果没有则置空,如果有多个接收人则用,分隔
     *           time : getDateFull()
     *       },
     * type : "message"
     * 
     * @Param jsonMessage 前端传来的JSON数据
	 */
	@OnMessage
	public void onMessage(String jsonMessage) {
		JSONObject chat = JSON.parseObject(jsonMessage);//把json字符串转化成JSONObject对象
		JSONObject message = JSON.parseObject(chat.get("message").toString());//将messaage中的JSON字符串转换成JSONObject对象
		sendWho(message.get("from").toString(),message.get("to").toString(),jsonMessage);//校验发送者和接收人
		
	 }
	
	/**
	 * 链接关闭调用的方法
	 */
	@OnClose
	public void onClose(){
		//把个人对象移除chatSet
		chatSet.remove(this);
		//在线人数减少一个
		subOnlineCount();
		//移出在线列表
		list.remove(userid);
		//广播消息
		String message = setJSON("[" + userid +"]离开了聊天室,当前在线人数为"+getOnlineCount()+"位", "notice", list);
		sendAll(message);
	}
	
	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}
	
	
	public int getOnlineCount() {
		return onlineCount;
	}

	public void addOnlineCount() {
		Chat.onlineCount++;
	}
	
	public void subOnlineCount() {
		Chat.onlineCount--;
	}
	
	/**
	 * 把数据转换成发送给前端的json格式
	 * @param message 要发送的数据
	 * @param type	消息类型
	 * @param list	在线列表
	 * @return
	 */
	public String setJSON(String message,String type,List list) {
		JSONObject json = new JSONObject();
		json.put("message",message);
		json.put("type", type);
		json.put("list", list);
		return json.toString();
	}
	
	//广播消息
	public void sendAll(String message) {
		for(Chat chat:chatSet) {
			try {
				chat.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("广播失败");
			}
		}
	}
	
	//校验发送给谁
	public void sendWho(String fromWho,String toWho,String sendMessage) {
			if(toWho.equals("")) {
				//转发的对象为空,默认广播
				sendAll(sendMessage);
			}else {
				//自己也要发送一份
				Session mySession = (Session)routeTable.get(fromWho);
				try {
					mySession.getBasicRemote().sendText(sendMessage);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String [] sendList = toWho.split(",");
				for(int i = 0;i<sendList.length;i++) {
					Session session = (Session)routeTable.get(sendList[i]);	//根据用户名获取接收者的session
					try {
						session.getBasicRemote().sendText(sendMessage);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
}
