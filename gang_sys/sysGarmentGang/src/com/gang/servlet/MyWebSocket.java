package com.gang.servlet;  
  
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.gang.entity.UserInfo;
  
/** 
 * WebSocket服务端
 * 用于:订单支付成功后与页面通讯等
 * @author dongk
 * @date 2018-3-23 16:26:43
 *
 */
@ServerEndpoint(value="/myWebsocket",configurator=GetHttpSessionConfigurator.class)
public class MyWebSocket {
	
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
    private static int onlineCount = 0;  
  
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识  
    public static Map<Integer,MyWebSocket> webSocketSet = new HashMap<Integer,MyWebSocket>();
  
    //与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session;  
    
    /** 
     * 连接建立成功调用的方法 
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     */  
    @OnOpen  
    public void onOpen(Session session, EndpointConfig config){  
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("wscSession");
        if(userInfo!=null){
        	this.session = session; 
        	//webSocketSet.put(userInfo.getId(), this);
        	addOnlineCount();//在线数加1  
        	//System.out.println(userInfo.getAccount() + " 连接Websocket！当前在线人数为" + getOnlineCount());
        }else{
        	System.out.println("用户没有登录,不能连接Websocket!");
        }
    }
        
  
    /** 
     * 连接关闭调用的方法 
     */  
    @OnClose  
    public void onClose(){  
    	Integer key = null;
    	if(!webSocketSet.isEmpty()){
		   for(Integer getKey: webSocketSet.keySet()){ 
	             if(webSocketSet.get(getKey).session == this.session){
	                 key = getKey;
	             }  
		   } 
		}
    	if(key!=null){
    		webSocketSet.remove(key);  //从map中删除  
    		subOnlineCount();           //在线数减1  
    		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount()); 
    	}else{
    		System.out.println("onClose()关WebSocket异常!");
    	}
        
    }  
    
    
    @OnMessage
    public void onMessage(String message) {  
        System.out.println("来自客户端的消息:" + message);  
        if(this.session.isOpen()){
        	this.session.getAsyncRemote().sendText(message);
        }else{
        	System.out.println("当前用户session已关闭!");
        }
    }
  
    /**
     * 根据用户id发送消息
     * @param message
     * @param userId
     * @throws IOException
     */
	public void sendMessage(String message, Integer userId) throws IOException {
		if(!webSocketSet.isEmpty()){
			for(Integer key : webSocketSet.keySet()){
				if(key == userId){
					MyWebSocket mws = webSocketSet.get(key);
					if(mws!=null){
						mws.session.getAsyncRemote().sendText(message); 
					}
				}
			}
		}
	}
  
    /** 
     * 发生错误时调用 
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error){  
        System.out.println("WebSocket发生错误");  
        error.printStackTrace();  
    }  
  
    /** 
     * 服务端给所有客户端发送消息
     * @param message 
     * @throws IOException 
     */  
    public void sendMessage(String message) throws IOException{  
        //this.session.getBasicRemote().sendText(message);  //同步
        this.session.getAsyncRemote().sendText(message);  //异步
    }  
    
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
        MyWebSocket.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
        MyWebSocket.onlineCount--;  
    }  
}  