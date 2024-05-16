package com.baseball.controller;

import java.time.LocalDate;
import com.baseball.parseJSON.ParseJSON;
import com.baseball.service.BaseballAPIService;
import org.springframework.web.bind.annotation.*;

//TeamController is the RestController for this API that defines all actions entered by the user
@RestController
@RequestMapping("/")
public class TeamController {
    private final BaseballAPIService baseballAPIService;
    private ParseJSON parser = new ParseJSON();
    private String[] currentDateArray = LocalDate.now().toString().split("-");
    private String month = currentDateArray[1];
    private String day = currentDateArray[2];
    private String year = currentDateArray[0];
    public static String accessLevel = "trial";
    String jsonString;
    private boolean isPast = false;
    boolean validAPIkey = false;

    //TeamController constructor contains a BaseballAPIService object
    public TeamController(BaseballAPIService baseballAPIService) {
        this.baseballAPIService = baseballAPIService;
    }
    
    //Changes the current date of the scoreboard database
    @GetMapping("/changeDate/{date}")
    public String changeDate(@PathVariable String date) {
    	if(validAPIkey == false) {
    		return "Please enter a valid API key using /key/(Insert key here)";
    	}
    	
    	if(date.length() != 8 || date == null) {
    		return date + " is not a valid date entry. Please enter the date as MMDDYYYY.";
    	}
    	
    	String monthInput = date.substring(0, 2);
    	String dayInput = date.substring(2, 4);
    	String yearInput = date.substring(4);
    	int[] numberDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    	int monthNum;
    	int dayNum;
    	int yearNum;
    	try {
    		monthNum = Integer.parseInt(monthInput);
    		dayNum = Integer.parseInt(dayInput);
    		yearNum = Integer.parseInt(yearInput);
    	}catch(NumberFormatException ex) {
    		return date + " is not a valid date entry. Please enter the date as MMDDYYYY.";
    	}
    	
    	if(yearNum % 4 == 0) {
    		numberDaysInMonth[1] = 29;
    	}
    	
    	if(monthNum < 1 || monthNum > 12 || dayNum < 1 || dayNum > numberDaysInMonth[monthNum - 1]) {
    		return date + " is not a valid date entry. Please enter the date as MMDDYYYY.";
    	}
    	
    	if(yearNum < 2012 || yearNum > 2024) {
    		return "Valid years are 2012 through 2024 inclusive. Please try again.";
    	}
    	
    	month = monthInput;
    	day = dayInput;
    	year = yearInput;
    	
    	LocalDate dateObject = LocalDate.parse(yearInput + "-" + monthInput + "-" + dayInput);
    	String dateObjectMonth = dateObject.getMonth().toString();
    	String printableMonth = dateObjectMonth.charAt(0) + (dateObjectMonth.substring(1).toLowerCase());
    	
    	LocalDate now = LocalDate.now();
    	
    	if(dateObject.isBefore(now)) {
    		isPast = true;
    	}
    	else {
    		isPast = false;
    	}
    	    	
    	return "Date changed successfully. Current date: " + printableMonth + " " + dayInput + " " + yearInput + ".";
    }

    //Returns the scoreboard for the current set date
    @GetMapping("/scoreboard")
    public String getSchedule() {
    	if(validAPIkey == false) {
    		return "Please enter a valid API key using /key/(Insert key here)";
    	}
    	
    	jsonString = baseballAPIService.getBaseballInformation(month, day, year);
    	if(isPast == true) {
	    	boolean goodAPIPull = parser.initializeGameArrayPast(jsonString);
	        
	        if(jsonString == null || jsonString.length() == 0) {
	        	return "Error retrieving information from API";
	        }
	    	else if(!goodAPIPull) {
	    		LocalDate dateObject = LocalDate.parse(year + "-" + month + "-" + day);
	        	String dateObjectMonth = dateObject.getMonth().toString();
	        	String printableMonth = dateObjectMonth.charAt(0) + (dateObjectMonth.substring(1).toLowerCase());
	    		return "There are no games on this date. Please choose another date and try again. Current date: " + printableMonth + " " + day + " " + year + ".";
	    	}
	        else {
	        	//parser.initializeGameArray(jsonString);
	        	return parser.parseSchedule(true);
	        }
    	}
    	else {
    		boolean goodAPIPull = parser.initializeGameArrayFuture(jsonString);
    		
    		if(jsonString == null || jsonString.length() == 0) {
    			return "Error retrieving information from API";
    		}
    		else if(!goodAPIPull) {
	    		LocalDate dateObject = LocalDate.parse(year + "-" + month + "-" + day);
	        	String dateObjectMonth = dateObject.getMonth().toString();
	        	String printableMonth = dateObjectMonth.charAt(0) + (dateObjectMonth.substring(1).toLowerCase());
	    		return "There are no games on this date. Please choose another date and try again. Current date: " + printableMonth + " " + day + " " + year + ".";
    		}
    		else {
    			return parser.parseSchedule(false);
    		}
    	}
    }
    
    //Returns a specific Game object for the current set date
    @GetMapping("/scoreboard/{id}")
    public String getGame(@PathVariable int id) {
    	if(validAPIkey == false) {
    		return "Please enter a valid API key using /key/(Insert key here)";
    	}
    	
    	if(isPast == true) {
	    	jsonString = baseballAPIService.getBaseballInformation(month, day, year);
	    	boolean goodAPIPull = parser.initializeGameArrayPast(jsonString);
	    	
	    	if(jsonString == null) {
	    		return "Error retrieving information from API";
	    	}
	    	else if(!goodAPIPull) {
	    		LocalDate dateObject = LocalDate.parse(year + "-" + month + "-" + day);
	    		return "There are no games on this date. Please choose another date and try again. Current date: " + dateObject.getMonth() + " " + day + " " + year + ".";
	    	}
	    	else {
	    		return parser.parseGames(id - 1);
	    	}
    	}
    	else {
    		return "You can only use /scoreboard/gameID for games that have already been played. Please change the date to a date in the past and try again.";
    	}
    }
    
    //Allows the user to enter an API key retrieved from Sportradar, checks to confirm the entered key is valid
    @GetMapping("/key/{id}")
    public String setAPIKey(@PathVariable String id) {
    	validAPIkey = baseballAPIService.setAPIKey(id);
    	if(validAPIkey) {
    		return "The API key you entered is valid.";
    	}
    	else {
    		return "The API key you entered is invalid. Please try again.";
    	}
    }
    
    //Changes the access level of the key to be entered. The access level is "trial" by default
    @GetMapping("/accessLevel/{level}")
    public String setAccessLevel(@PathVariable String level) {
    	String lowerCaseInput = level.toLowerCase();
    	if(lowerCaseInput.equals("trial")) {
    		accessLevel = "trial";
    		return "Access level changed to \"trial\"";
    	}
    	else if(lowerCaseInput.equals("production")) {
    		accessLevel = "production";
    		return "Access level changed to \"production\"";
    	}
    	else if(lowerCaseInput.equals("tracking")) {
    		accessLevel = "tracking";
    		return "Access level changed to \"tracking\"";
    	}
    	else if(lowerCaseInput.equals("trial-tracking")) {
    		accessLevel = "trial-tracking";
    		return "Access level changed to \"trial-tracking\"";
    	}
    	else {
    		return level + " is an invalid access level. Valid values are \"trial\", \"production\", \"tracking\", and \"trial-tracking\"";
    	}
    }
    
    //Returns the current state of the API including whether the API key is valid. If it's valid, it also returns the current set date. If it's invalid, it also returns the current access level.
    @GetMapping("/currentState")
    public String getCurrentState() {
    	String returnedString = "";
    	if(validAPIkey) {
    		returnedString += "The current API key is a valid API key.\n";
    	}
    	else {
    		return "The current API key is not a valid API key. Use /key/(Enter API key here) to enter an API key. The current access level is set to: \"" + accessLevel + "\". If this is incorrect, use /accessLevel/(Enter access level here) to change the access level.\n";
    	}
    	
    	returnedString += "The date is currently set to " + month + "/" + day + "/" + year + "\n";
    	
    	return returnedString;
    }
    
    //This prints out all the options currently available to the user
    @GetMapping("/help")
    public String printOptions() {
    	if(!validAPIkey) {
    		return "The current API key is not a valid API key. "
    				+ "\nPlease use /key/(Enter API key here) to enter an API key. The current access level is \"" + accessLevel + "\". "
    				+ "\nIf this is incorrect please use /accessLevel/(Enter access level here) to change the access level."
    				+ "\nAccess level options are: \"trial\", \"production\", \"tracking\", and \"trial-tracking\".";
    	}
    	
    	String returnedString = "The current API key is a valid API key.\n";
    	
    	returnedString += "The following are the current options to be used in this API:\n";
    	
    	returnedString += "/changeDate/(Enter date here in MMDDYYYY format): This will change the current date of the scoreboard information. The current date is: " + month + "/" + day + "/" + year + "\n";
    		
    	returnedString += "/currentState: This will print the current state of the database. Because your API key is valid, the only information relevant currently is the current date of the scoreboard database.\n";
    	
    	returnedString += "/scoreboard: This will print the MLB scoreboard for the current date above. If the date is in the past, a number will appear to the left of each game. This can be used with the following command.\n";
    	
    	returnedString += "/scoreboard/(Enter ID here): This only works for dates in the past. The ID entered after /scoreboard/ corresponds to the number printed next to the game in /scoreboard. This will give a more in depth look at that specific game.";
    
    	return returnedString;
    }
}
