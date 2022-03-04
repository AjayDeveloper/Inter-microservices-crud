package com.feed.service;

import java.util.List;
import java.util.Map;

import com.feed.dto.Posts;

public interface FeedService {

	Posts updatePost(Posts posts);
	Map<Integer, Long> getAllpostUniqueId(); 
	 Integer getAllpostCount();
}
