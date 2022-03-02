package com.feed.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.feed.dto.Posts;

@RestController

public class Controller {

	@Autowired
	RestTemplate restTemplate;

	 Logger logger = LoggerFactory.getLogger(Controller.class);
	 String uri = "http://jsonplaceholder.typicode.com/posts";
	/*
	 * @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE) public
	 * List<Integer> getAllposts() { String uri =
	 * "http://jsonplaceholder.typicode.com/posts"; ResponseEntity<Posts[]>
	 * responseEntity = restTemplate.getForEntity(uri, Posts[].class); Posts[]
	 * objects = responseEntity.getBody(); ObjectMapper mapper = new ObjectMapper();
	 * return Arrays.stream(objects).map(Posts::getId).collect(Collectors.toList());
	 * 
	 * }
	 */
	@GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> getAllpostCount() {
		
		Object[] responseEntity = restTemplate.getForObject(uri, Object[].class);

		return Arrays.asList(responseEntity.length);

	}

	@GetMapping(value = "/userId", produces = MediaType.APPLICATION_JSON_VALUE)
	public  Map<Integer, Long> getAllpostUniqueId() {
		String uri = "http://jsonplaceholder.typicode.com/posts";
		Posts[] responseEntity = restTemplate.getForObject(uri, Posts[].class);
		List<Posts> distinctElements = Stream.of(responseEntity).distinct().collect(Collectors.toList());
		
		 Map<Integer, Long> countForId = distinctElements.stream()
		  .collect(Collectors.groupingBy(Posts::getUserId, Collectors.counting()));
		 
		return countForId;

	}
	
	@PutMapping(value = "/update", consumes =  MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public Posts updatePost(@RequestBody Posts posts) {
		String paramUrl = "https://jsonplaceholder.typicode.com/posts/{pId}";
		// create map for URL parameters
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("pId", 4);
		System.out.println("PUT with parameterized URL");
		restTemplate.put(paramUrl, posts, params);
		System.out.println("Resource updated");
		System.out.println("PUT with parameterized URL");
		// put with object array for parameters
		restTemplate.put(paramUrl, posts,new Object[] {4});
		System.out.println("Resource updated"+posts);
		 return posts;
	    }
		
	


}
