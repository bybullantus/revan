package com.bc.revan.Cache.Redis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bc.revan.Entities.Dto.LiveMatchesDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Repository
public class RedisLiveMatchesCache implements ILiveMatchesCache{
	private String KEY = "LiveMatches";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
    @Autowired
	ObjectMapper mapper;
    @Autowired
    public RedisLiveMatchesCache(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
      
    }
    @PostConstruct
    private void init(){
       hashOperations = redisTemplate.opsForHash(); 
    }
	
	@Override
	public Map<Object, Object> getAll() {
		  return hashOperations.entries(KEY);

	}

	@Override
	public void add(LiveMatchesDto item) {
		item.setId(item.getFixture().getId());
		 hashOperations.put(KEY, String.valueOf(item.getId()), item);
		
	}

	@Override
	public void delete(String id) {
        hashOperations.delete(KEY, String.valueOf(id));
		
	}

	@Override
	public void deleteAll() {
		
		 Set<Object> keys = hashOperations.keys(KEY);
		    for (Object key : keys) {
		        hashOperations.delete(KEY, key);
		        
		    }
		    
		    
		/*for (LiveMatchesDto liveMatchesItem : (List<LiveMatchesDto>) this.getAll()) {
			
			hashOperations.delete(KEY, String.valueOf(liveMatchesItem.getId()));
		}*/
		
	}

	@Override
	public LiveMatchesDto getById(String id) {
		 return (LiveMatchesDto) hashOperations.get(KEY, String.valueOf(id));

	}

}
