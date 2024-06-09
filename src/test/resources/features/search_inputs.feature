
Feature: Search functionality with valid and invalid search terms
	
	Scenario Outline: Valid search term returns correct results when clicking search button
    Given user is on the homepage
    When user inputs "<search_term>" into the search bar
    And user clicks the search button
    Then user should see a list of results related to "<search_term>"

    Examples: 
      | search_term  			 |
      | jacket			 		   |  
      | fleece						 |
      | Zeppelin Yoga Pant |
      
  Scenario: Valid search term returns correct results when pressing the enter key
    Given user is on the homepage
    When user inputs "yoga" into the search bar
    And user press the enter key
    Then user should see a list of results related to "yoga"

	Scenario Outline: Invalid search term returns no results
		Given user is on the homepage
		When user inputs "<search_term>" into the search bar
    And user clicks the search button
    Then user should see a message saying "no results"
    
    Examples: 
      | search_term  			 |
      | ipad			  		   |  
      | nvshorovf					 |
      
	Scenario: Empty search term by clicking search button
		Given user is on the homepage
		When user leaves the search bar empty
		And user clicks the search button
		Then user should see a warning message saying "Please enter a search term"
	
	Scenario: Empty search term by pressing the enter key
		Given user is on the homepage
		When user clicks on search bar
		And user press the enter key
		Then user should see a warning message saying "Please enter a search term"
