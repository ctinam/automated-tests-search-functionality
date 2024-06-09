Feature: Search results pagination

	Background:
		Given user is on the homepage
    When user inputs "jacket" into the search bar
    And user clicks the search button
    And user should see a list of results related to "jacket"

	Scenario: Navigate to the second page of search results using next button
		When the user is on page number 1 of search results for "jacket"
		And the user clicks the "Next" button on the search results page
    Then the user is on page number 2 of search results for "jacket"
    
 	Scenario: Navigate back to the first page of search results
    When the user is on page number 1 of search results for "jacket"
		And the user clicks the "Next" button on the search results page
    And the user is on page number 2 of search results for "jacket"
    And the user clicks the "Prev" button on the search results page
    Then the user is on page number 1 of search results for "jacket"
    
	Scenario: Clicking on page numbers to navigate
    When the user clicks on page number 2
    Then the user is on page number 2 of search results for "jacket"
    
	Scenario: Cannot go to previous page if on the first page
		Then there should not be a "Previous" button for search result pagination
    
	Scenario: Cannot go to the next page if on the last page
    When the user clicks the "Next" button on the search results page
    And the user is on the last page of search results
    Then there should not be a "Next" button for search result pagination