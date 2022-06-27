package com.formacionpokemon.formacionpokemon.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class pokeController {
	@Autowired
	private pokeService pokeservice;
	
	@GetMapping("/")
	public @ResponseBody String getAllData() {
		return "Hola Mundo!";
	}
	
	@GetMapping("data/{name}")
	public String getPokeData(@PathVariable String name) {
		
		return pokeservice.getData(name).toString();
	}
	
	@RequestMapping("abilities/{name}")
	public List<Object> getPokeAbilities(@PathVariable String name) {
		String res = pokeservice.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		JSONArray array = jsonObject.optJSONArray("abilities");
		for (int i = 0; i < array.length(); i++) {
			JSONObject jsonO = array.getJSONObject(i);
			JSONObject jsonI = jsonO.optJSONObject("ability");
			String uri = jsonI.optString("url");
			jsonI.put("information", new JSONObject(pokeservice.getFromURL(uri)));
			jsonI.remove("url");
			jsonO.remove("ability");
			jsonO.put("ability", (Object)jsonI);
		}
		
		return array.toList();
	}
	
	@GetMapping("base-experience/{name}")
	public String getPokeBaseExperience(@PathVariable String name) {
		String res = pokeservice.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		String str = "{\"base-experience\": "+ jsonObject.optInt("base_experience") +"}";
		return str;
	}
	
	@GetMapping("held-items/{name}")
	public String getPokeHeldItems(@PathVariable String name) {
		String res = pokeservice.getData(name);
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
		String res = pokeservice.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		String str = "{\"id\": "+ jsonObject.optInt("id") +"}";
		return str;
	}
	
	@GetMapping("name/{name}")
	public String getPokeName(@PathVariable String name) {
		String res = pokeservice.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		JSONArray array = jsonObject.optJSONArray("forms");
		String str = "{\"name\": "+ array.optJSONObject(0).optString("name") +"}";
		return str;
	}
	
	@GetMapping("location-area-encounters/{name}")
	public String getPokeLocationAreaEncounters(@PathVariable String name) {
		String res = pokeservice.getData(name);
		JSONObject jsonObject = new JSONObject(res);
		String uri = jsonObject.optString("location_area_encounters");
		String resTwo = pokeservice.getFromURL(uri);
		return resTwo;
	}
}
