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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.feed.dto.Posts;
import com.feed.service.FeedService;

@RestController

public class Controller {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FeedService service;

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
	public ResponseEntity<List<Object>>  getAllpostCount() {
		

		return new ResponseEntity<>(service.getAllpostCount(), HttpStatus.OK);

	}

	@GetMapping(value = "/userId", produces = MediaType.APPLICATION_JSON_VALUE)
	public  Map<Integer, Long> getAllpostUniqueId() {
		return service.getAllpostUniqueId();

	}
	
	@PutMapping(value = "/update", consumes =  MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
	public Posts updatePost(@RequestBody Posts posts) {
		 return service.updatePost(posts);
	    }
		
	


}
