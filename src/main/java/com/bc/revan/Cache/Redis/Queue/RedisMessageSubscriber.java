package com.bc.revan.Cache.Redis.Queue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

	public static List<String> messageList = new ArrayList<String>();

	@Override
	public void onMessage(Message message, @Nullable byte[] pattern) {
	    messageList.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
		
	}

}
