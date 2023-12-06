package com.bc.revan.Cache.Redis.Queue;

public interface  IMessagePublisher {
	void publish(final String message);
}
