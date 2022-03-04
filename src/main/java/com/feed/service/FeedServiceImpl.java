package com.feed.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.feed.dto.Posts;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	RestTemplate restTemplate;

	private String paramUrl = "https://jsonplaceholder.typicode.com/posts/{pId}";

	@Override
	public Posts updatePost(Posts posts) {

		// create map for URL parameters
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("pId", 4);
		System.out.println("PUT with parameterized URL");
		restTemplate.put(paramUrl, posts, params);
		System.out.println("Resource updated");
		System.out.println("PUT with parameterized URL");
		// put with object array for parameters
		restTemplate.put(paramUrl, posts, new Object[] { 4 });
		System.out.println("Resource updated" + posts);
		return posts;
	}

	@Override
	public Map<Integer, Long> getAllpostUniqueId() {
		String uri = "http://jsonplaceholder.typicode.com/posts";
		Posts[] responseEntity = restTemplate.getForObject(uri, Posts[].class);
		List<Posts> distinctElements = Stream.of(responseEntity).distinct().collect(Collectors.toList());
		Map<Integer, Long> countForId = distinctElements.stream()
				.collect(Collectors.groupingBy(Posts::getUserId, Collectors.counting()));
		return countForId;
	}

	@Override
	public Integer getAllpostCount() {
		String uri = "http://jsonplaceholder.typicode.com/posts";
		ResponseEntity<Posts[]> responseEntity = restTemplate.getForEntity(uri, Posts[].class);
		return responseEntity.getStatusCode()==HttpStatus.OK ? responseEntity.getBody().length : null;

	}

}
