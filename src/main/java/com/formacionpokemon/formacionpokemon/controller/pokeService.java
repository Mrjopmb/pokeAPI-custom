package com.formacionpokemon.formacionpokemon.controller;


import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class pokeService {
	
	
	public String getData(String name) {
		String uri = "https://pokeapi.co/api/v2/pokemon/" + name;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		//String result = restTemplate.getForObject(uri, String.class);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		
		return result.toString();
	}
	
	public String getFromURL(String uri) {
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.getForObject(uri, String.class);
		
		return result;
	}

}
