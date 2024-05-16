# Baseball_REST_API

The Baseball_REST_API project is a REST API written in Java Spring Boot that displays baseball scores from the MLB based on the Sportradar API located at sportradar.com. It allows the user to change the current date from the command line, and print out the MLB scoreboard from that date. If the date is in the past, the user can also enter a specific game ID located on the printed scoreboard to get more in depth information on that game. 

## Table of contents

- Requirements 
- Installation 
- Configuration
- Options
- Troubleshooting 
- Testing
- Creators

## Requirements 

The requirements for running this project are that the user must have either a Java compiler installed, or Docker installed on their local machine. For more information on this, please see below under "Installation".  

Once one of those two is installed, the user must go to sportradar.com to acquire an API key to be used for accessing MLB scores. An API key is free for 30 days from this website, and no payment information must be entered to acquire a free key. 

After a key is acquired, the user must enter that key using /key/(Enter API key here). More on that is described below under "Options". 

The user must also have installed a command line interface that is Linux-based that recognizes the curl command. 

Finally, because the program uses port 8080 to run, the user must confirm that port 8080 is not currently in use by another program. If it is currently in use by another program, please terminate that program before running this project.

## Installation
To install a Java compiler, go to oracle.com/java/technologies/downloads/, and download at minimum Java 17. This will allow the user to compile and run the Java classes located in this project.

If the user does not wish to download a Java compiler, the user must download Docker. Docker Desktop can be downloaded at docker.com/products/docker-desktop/. Once that is downloaded, the user can go to the installed Docker Desktop application and run the API using the Docker engine from the command line. 

The user must also install a command line interface that can recognize the curl command. Any CLI that is Linux-based will be able to recognize this command. As the owner of the program, I used Cygwin to test features of this program. This can be downloaded at cygwin.com. However, any Linux-based CLI will work for running this project. 

## Configuation
The user must confirm that they have a valid API key from sportradar.com that can pull from the MLB API. The user must then enter the API key into this program, where the program will confirm whether or not the entered key is valid. Please note that by default, the program assumes the key is a "Trial" API key. If the key is "Production", "Tracking", or "Trial-Tracking", the user must enter this information under /accessLevel to successfully access the API. More on that is described below.

Please note that the user only needs to enter a valid API key once per running of the program. Once the program confirms that the key is valid, /key and /accessLevel are not necessary.

## Options
The following are the options when running this project. All commands must be entered in a command line interface that recognizes the curl command:

curl localhost:8080/help: This option prints out all the options currently available to the user. If the current entered API key is not valid, and the program is not connected to the Sportradar API, a message will pop up that the current API key is not valid, and will instruct the user on how to enter a valid API key. If that current API key is valid, the program will list out all available options that the user can enter into the program. 

curl localhost:8080/key/(Enter API key here): This option allows the user to enter an API key that was acquired from Sportradar that allows the API to connect to the Sportradar MLB API. Please note that the default access level is set to "trial", however if the key is not a trial key (it is "production", "tracking", or "trial-tracking"), the user must use /accessLevel to update the access level of the key they are trying to enter. A message will appear on the CLI that confirms whether or not the project is successfully connected to the MLB API.

curl localhost:8080/accessLevel/(Enter access level here): This option is only necessary if the access level of the key attempting to be entered is not a "trial" key. Valid access level options are "trial", "production", "tracking", and "trial-tracking". If any other option is entered, an error message will pop up and the current access level will not be changed. If the option entered is a valid option, the access level will change to that option and a success message will pop up confirming that the access level has been changed.

curl localhost:8080/currentState: This option will print out the current state of the program. If the current API key is not valid, the program will alert the user of this and explain how to enter a valid API key. If the current API key is valid, the program will validate to the user that the current API key is valid, and will print the current date of the scoreboard database to the user.

curl localhost:8080/changeDate/(Enter date here in MMDDYYYY format): This option allows the user to change the date of the current scoreboard database. By default, the current date as of the program being run is the current date of the scoreboard database. The entered date must be in MMDDYYYY format where MM is the current month number, DD is the current date number, and YYYY is the current year. For example, to enter May 5, 2023 as the current date, the user must enter "curl localhost:8080/changeDate/05052023". The program does valid error checking, and will alert the user if it does not understand the date entered. If the date is changed successfully, the program will alert the user that the date has been changed.

Please note: The program will allow the user to change the date even if no games were played on that day. Also, the Sportradar database only goes back to 2012, so no dates before 2012 can be entered. The database also only goes to the current date that the project was created (2024), so no dates beyond 2024 are allowed.

curl localhost:8080/scoreboard: This option will print out the MLB games that were played on the date corresponding to the current date of the scoreboard database. If a valid API key has not been entered, the program will alert the user of this and not do anything else. If the current date is a past date, an ID number will appear next to each game, and the user can enter that ID number using the option below to get more information on that game. If the current date is the current day or in the future, no ID number will appear next to each game, and only the games played on that date can be viewed.

curl localhost:8080/scoreboard/(Enter ID here): The final option in this program allows the user to get more detailed information of games that were played in the past. Using the above /scoreboard option on a past date will print out games with an ID number next to them. Using that ID number on this option will print out more detailed information of that game including the score, winning/losing pitchers, and other information pertaining to that game. This option is only for when the current set date is in the past. If the set date is the current day or in the future, this option will not work.

## Troubleshooting
If the user is getting errors in running the program, it is very likely that the API key is not valid, and the program isn't connecting to the Sportradar MLB API correctly. The user can always use /help or /currentState to see if the entered API key is valid. If the API key is valid, then the rest of the options should work. If the API key is not valid, please confirm that the access level is correct. This information can also be viewed from /help or /currentState, and can be changed by using the /accessLevel option. Once the access level is changed successfully, the user must re-enter the API key using /key, where the program will confirm whether or not they key entered is valid.

Another possible issue will occur when changing the data of the scoreboard database. Please confirm that the format is MMDDYYYY where M is the month, D is the day, and Y is the year. To change the current date to May 5, 2023, please use "curl localhost:8080/changeDate/05052023". Valid years are 2012-2024 inclusive. 

## Testing
This project has been thoroughly tested using JUnit tests, and the tests are available for viewing in the GitHub repository. As of the last commit to this repository, all tests in all test classes pass. Please note that there were JUnit tests for the ParseJSONTest class that input example JSON Strings and tested whether or not the output was valid, however when creating the Maven Build for Dockerizing the program, Maven complained that the Strings were too long, so I had to delete them. All of the tests that had to be deleted passed before being deleted.

## Creators 
- Chris Pichler (chrispichler.aolo@gmail.com)


