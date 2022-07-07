package com.formacionpokemon.formacionpokemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;


@RestController
public class pokeController {
	
	@Autowired
	private pokeService service;
	
	@GetMapping("/")
	public String getAllData() {
		//System.out.println(service.getData("zapdos"));
		//return service.getData("zapdos");
		return "Hola Mundo! <BR> "
				+ "PokeAPI API <BR> <BR> "
				+ "1.- data/{pokemon} <BR>"
				+ "2.-  abilities/{pokemon} <BR>"
				+ "3.-  base-experience/{pokemon} <BR>"
				+ "5.-  held-items/{pokemon} <BR>"
				+ "6.-  id/{pokemon} <BR>"
				+ "7.-  name/{pokemon} <BR>"
				+ "8.-  location-area-encounters/{pokemon} <BR>";
	}
	
	@GetMapping("data/{name}")
	public String getAllData(@PathVariable String name) {
		
		return service.getData(name);
	}
	
	@GetMapping("abilities/{name}")
	public List<Object> getPokeAbilities(@PathVariable String name) {
		String res = service.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		JSONArray array = jsonObject.optJSONArray("abilities");
		for (int i = 0; i < array.length(); i++) {
			JSONObject jsonO = array.getJSONObject(i);
			JSONObject jsonI = jsonO.optJSONObject("ability");
			String uri = jsonI.optString("url");
			jsonI.put("information", new JSONObject(service.getFromURL(uri)));
			jsonI.remove("url");
			jsonO.remove("ability");
			jsonO.put("ability", (Object)jsonI);
		}
		
		return array.toList();
	}
	
	@GetMapping("base-experience/{name}")
	public String getPokeBaseExperience(@PathVariable String name) {
		String res = service.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		String str = "{\"base-experience\": "+ jsonObject.optInt("base_experience") +"}";
		return str;
	}
	
	@GetMapping("held-items/{name}")
	public String getPokeHeldItems(@PathVariable String name) {
		String res = service.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		try {
			String str = "{\"held-items\": "+ jsonObject.optJSONArray("held-items").toList() +"}";
			return str;
		} catch(Exception e) {
			String str = "{\"held-items\": "+ new ArrayList<Object>() +"}";
			return str;
		}
	}
	
	@GetMapping("id/{name}")
	public String getPokeId(@PathVariable String name) {
		String res = service.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		String str = "{\"id\": "+ jsonObject.optInt("id") +"}";
		return str;
	}
	
	@GetMapping("name/{name}")
	public String getPokeName(@PathVariable String name) {
		String res = service.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		JSONArray array = jsonObject.optJSONArray("forms");
		String str = "{\"name\": "+ array.optJSONObject(0).optString("name") +"}";
		return str;
	}
	
	@GetMapping("location-area-encounters/{name}")
	public String getPokeLocationAreaEncounters(@PathVariable String name) {
		String res = service.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		String uri = jsonObject.optString("location_area_encounters");
		String resTwo = service.getFromURL(uri);
		return resTwo;
	}
}
