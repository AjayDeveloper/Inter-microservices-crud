package com.feed;

import java.util.ArrayList;
import java.util.List;

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
import com.feed.service.FeedService;
import com.feed.service.FeedServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FeedServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private FeedServiceImpl feedServiceImpl = new FeedServiceImpl();
	
	
	 @Test
	    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
	       List<Object> posts = new ArrayList();
	       posts.add(new Posts(1,1,"1800Flower","Nice roses"));
	       posts.add(new Posts(1,2,"1800Flower","Nice marigold"));
	       posts.add(new Posts(1,3,"1800Flower","Nice test"));
	       posts.add(new Posts(1,4,"1800Flower","Nice tedfs"));
	        Mockito
	          .when(restTemplate.getForEntity(
	            "https://jsonplaceholder.typicode.com/posts", Posts.class))
	          .thenReturn(new ResponseEntity(posts, HttpStatus.OK));

	        List<Object> posts2 = feedServiceImpl.getAllpostCount();
	        Assert.assertEquals(posts, posts2);
	    }

}
