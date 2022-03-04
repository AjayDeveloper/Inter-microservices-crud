package com.feed;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
	public void testupdatePost()  {
		 Posts posts = new Posts();
		 posts = feedServiceImpl.updatePost(posts);
		 Assert.assertNotNull(null,posts);
		 
				
	}
	@Test
	public void testupdatePostwithData()  {
		 Posts posts = new Posts(1,2,"1800flowers","1800Flowers");
		 posts = feedServiceImpl.updatePost(posts);
		 Assert.assertNotNull(null,posts);
		 
				
	}

}
