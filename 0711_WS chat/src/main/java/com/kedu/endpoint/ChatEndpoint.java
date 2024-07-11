package com.kedu.endpoint;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonObject;
import com.kedu.configurator.SpringProvider;
import com.kedu.configurator.WSHttpSessionConfigurator;
import com.kedu.dao.ChatDAO;
import com.kedu.dto.ChatDTO;
import com.kedu.services.ChatService;

@ServerEndpoint(value="/chat", configurator=WSHttpSessionConfigurator.class)
public class ChatEndpoint {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
	private HttpSession hSession;	
	
	private ChatService cServ = SpringProvider.getSpring().getBean(ChatService.class);
	private ChatDAO cdao;
	
	@OnOpen
	public void onConnect(EndpointConfig config, Session session) {
		System.out.println("연결 확인 : " + session.getId());
		ChatDTO dto = new ChatDTO();
		
		cServ.func();
		
//		try {
//			List<ChatDTO> list = cServ.selectAll(dto);
//			
//			JsonObject data = new JsonObject();
//			data.addProperty("sender", dto.getSender());
//			data.addProperty("message", dto.getMessage());
//			data.addProperty("write_date", dto.getWrite_date());
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		this.hSession = (HttpSession) config.getUserProperties().get("hSession");
		clients.add(session);
	}
	
	@OnMessage
	public void onMessage(Session session, String message) {
		try {
			System.out.println("사이즈 : " + clients.size());
			
			ChatDTO dto = new ChatDTO();
			String sender = (String) this.hSession.getAttribute("loginID");
	
			JsonObject data = new JsonObject();
			data.addProperty("sender", sender);
			data.addProperty("message", message);
			
			cServ.insert(sender, message); // DB에 채팅 넣기
			synchronized(clients) {
				for(Session client : clients) {
					try {
						client.getBasicRemote().sendText(data.toString());
						
						
					} catch (Exception e) {}
					
				}
			}
		}catch(Exception e) {}
		
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
	}
	
	@OnError
	public void onError(Throwable t, Session session) {
		clients.remove(session);
		t.printStackTrace();
	}
	
	
	
}
