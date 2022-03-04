package com.feed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.feed.dto.Posts;
import com.feed.service.FeedServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeedServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private FeedServiceImpl feedServiceImpl = new FeedServiceImpl();
	
	
	 @Test
	 public void testGetallPostUniqueId() throws NullPointerException {
		 Map<Integer, Long> countId = new HashMap<>();
		 countId.put(1,10L);
		 countId.put(2, 10L);
         
		 Mockito
         .when(restTemplate.getForEntity(
           "http://localhost:9001/userId", Posts.class))
         .thenReturn(new ResponseEntity(Arrays.asList(countId), HttpStatus.OK));
		 
		 Map<Integer,Long> count = feedServiceImpl.getAllpostUniqueId();
		 Assert.assertEquals(countId, count);
	 }

}
