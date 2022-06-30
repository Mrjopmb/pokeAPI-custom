package com.formacionpokemon.formacionpokemon.controller.fetcher;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetcher;

@Component
public class allDataFetcher implements DataFetcher<String> {

	@Override
	public String get(DataFetchingEnvironment environment){
		String uri = "https://pokeapi.co/api/v2/pokemon/" + "zapdos";
		System.out.println(uri);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
}
