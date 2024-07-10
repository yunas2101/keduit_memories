package com.kedu.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatEndpoint {

private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	
	
	@OnOpen
	public void onConnect(Session session) {
		System.out.println("연결 확인 : " + session.getId());
		clients.add(session);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		System.out.println("사이즈 : " + clients.size());
		
		synchronized(clients) {
			for(Session client : clients) {
				try {
					client.getBasicRemote().sendText(message);
				} catch (Exception e) {}
				
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
	
	@OnError
	public void onError(Throwable t, Session session) {
		clients.remove(session);
	}
}
