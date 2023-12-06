package com.bc.revan.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.bc.revan.Cache.Redis.ILiveMatchesCache;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Scope("prototype")
public class LiveHandler implements WebSocketHandler {
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	ILiveMatchesCache liveMatchesCache;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		while (session.isOpen()) {			
			/*List<Country> d = new ArrayList<Country>();
			d.add(Country.builder().id(1).flag(new Date().toString()).code("111").name("dad").build());
			d.add(Country.builder().id(2).flag(new Date().toString()).code("111").name("dad").build());
			session.sendMessage(new TextMessage(mapper.writeValueAsString(d)));*/
			
			session.sendMessage(new TextMessage(mapper.writeValueAsString(liveMatchesCache.getAll())));
			Thread.sleep(5000);
		}
	}
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// String tutorial = (String) message.getPayload();
		/*while (session.isOpen()) {
			Thread.sleep(5000);
			List<Country> d = new ArrayList<Country>();
			d.add(Country.builder().id(2).id(1).flag(new Date().toString()).code("111").name("dad").build());
			d.add(Country.builder().id(2).id(1).flag(new Date().toString()).code("111").name("dad").build());
			session.sendMessage(new TextMessage(mapper.writeValueAsString(d)));
		}*/
	}
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
	}
}