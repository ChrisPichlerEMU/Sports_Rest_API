package com.baseball.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.baseball.controller.TeamController;

import org.springframework.http.ResponseEntity;

@Service
public class BaseballAPIService {
    private final RestTemplate restTemplate;
    
    //key starts out as empty, and is entered by the user. This value will only change if the API key entered is valid
    private static String key = "";

    public BaseballAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //This will connect to the Sportradar API assuming the key above is valid
    public String getBaseballInformation(String month, String day, String year) {
        String url = "https://api.sportradar.com/mlb/" + TeamController.accessLevel + "/v7/en/games/" + year + "/" + month + "/" + day + "/boxscore.json?api_key=" + key;
        ResponseEntity<String> response = null;
        
        try {
        	response = restTemplate.getForEntity(url, String.class);
        }catch(org.springframework.web.client.HttpClientErrorException ex) {
        	return "There was an error with the key that was entered. Please enter a new key using /key/(Insert key here)";
        }
        
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle errors
        	System.out.println("Error retrieving response.getBody()");
            return null;
        }
    }
    
    //This will set the key String value above. If it returns true, the key entered is valid. Otherwise, the key entered is invalid
    public boolean setAPIKey(String key) {
    	BaseballAPIService.key = key;
    	String response;
    	
    	//Test the if the key is entered by attempting to retrieve the json String from 5/5/2023 from Sportradar
    	response = this.getBaseballInformation("05", "05", "2023");
    	
    	if(response.equals("There was an error with the key that was entered. Please enter a new key using /key/(Insert key here)")) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
}
