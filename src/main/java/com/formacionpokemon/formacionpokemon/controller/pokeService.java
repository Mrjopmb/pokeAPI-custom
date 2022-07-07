package com.formacionpokemon.formacionpokemon.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class pokeService {
	
	
	public String getData(String name) {
		String uri = "https://pokeapi.co/api/v2/pokemon/" + name;
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
	
	public String getFromURL(String uri) {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
	
	public String testData() {
		String uri = "https://api.thecatapi.com/v1/images/search";
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
	
	

}
