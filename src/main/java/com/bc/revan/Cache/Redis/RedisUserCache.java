package com.bc.revan.Cache.Redis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bc.revan.Entities.User;

import jakarta.annotation.PostConstruct;

@Repository
public class RedisUserCache implements IUserCache{
	private   String KEY = "User";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
    
    @Autowired
    public RedisUserCache(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.KEY=this.getClass().getName();
    }
    @PostConstruct
    private void init(){
       hashOperations = redisTemplate.opsForHash(); 
    }
   @Override
    public void delete(final String id) {
        hashOperations.delete(KEY, String.valueOf(id));
    }
	@Override
	public Map<Object, Object> getAll() {
		  return hashOperations.entries(KEY);
	}
	@Override
	public void add(User item) {
		 hashOperations.put(KEY, String.valueOf(item.getId()), item);
		
	}
	@Override
	public User getById(String id) {
		 return (User) hashOperations.get(KEY, String.valueOf(id));
	}
	@Override
	public void deleteAll() {
		for (User userItem : (List<User>) this.getAll()) {
			hashOperations.delete(KEY, String.valueOf(userItem.getId()));
		}
		
	}
}
