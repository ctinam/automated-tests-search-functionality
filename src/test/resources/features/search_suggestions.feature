
Feature: Search suggestions for various inputs

	Scenario: Search suggestions for empty search bar
		Given user is on the homepage
		When user leaves the search bar empty
		Then user should see suggestions of the most popular searches
		
	Scenario Outline: Correct search suggestions appear for valid inputs
		Given user is on the homepage
		When user inputs "<search_term>" into the search bar
		Then the user should see search suggestions like "<first_suggestion>" and "<second_suggestion>"
		
		Examples: 
      | search_term  	| first_suggestion | second_suggestion |
      | women					| women bag				 | women jacket			 |
      | yoga					| yoga mat				 | yoga shorts			 |
	
	Scenario Outline: Correct search suggestions appear for inputs with minor typos
		Given user is on the homepage
		When user inputs "<search_term>" into the search bar
		Then the user should see search suggestions like "<suggestion>"
		
		Examples: 
      | search_term  	| suggestion |
      | bagg					| bag			 	 |
      | paants				| pants			 |
      
#	Scenario: Select a suggestion from empty search bar
#		Given user is on the homepage
#		When user leaves the search bar empty
#		And user should see suggestions of the popular searches
#		And user selects a suggestion
#		Then user should see list of results related to search term
		
	
	Scenario Outline: Select a suggestion from partial input into search bar
		Given user is on the homepage
		When user inputs "<search_term>" into the search bar
		And the user should see search suggestions
		And user selects a suggestion
		Then user should see a list of results related to "<search_term>"
		
		Examples: 
      | search_term  	|
      | hoo						|
      | yog						|
	
	@last
	Scenario: No search suggestions appear for invalid inputs
		Given user is on the homepage
		When user inputs "asdfghjk" into the search bar
		Then the user should see message "No suggestions available"