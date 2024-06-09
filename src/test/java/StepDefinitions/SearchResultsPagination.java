package StepDefinitions;

import org.openqa.selenium.WebDriver;
import PageObjects.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchResultsPagination {

  private WebDriver driver;

  public SearchResultsPagination() {
    this.driver = Hooks.driver;

  }

  @When("the user clicks the {string} button on the search results page")
  public void the_user_clicks_the_button_on_the_search_results_page(String btn) throws InterruptedException {
    HomePage home = new HomePage(driver);
    home.scrollToBottom();
    if (btn.toUpperCase().equals("NEXT")) {
      home.clickNextPageButton();
    } else if (btn.toUpperCase().equals("PREV")) {
      home.clickPrevPageButton();
    }
  }

  @When("the user should see the {string} page of search results for {string}")
  public void the_user_should_see_the_next_page_of_search_results_for(String page, String searchTerm) {
    HomePage home = new HomePage(driver);
    home.searchResultIsDisplayed(searchTerm);
  }
  
  @When("the user clicks on page number {int}")
  public void the_user_clicks_on_page_number(Integer pageNum) throws InterruptedException {
    HomePage home = new HomePage(driver);
    home.clickPageNum(pageNum);
    Thread.sleep(3000);
  }

  @Then("the user is on page number {int} of search results for {string}")
  public void the_user_is_on_page_number_of_search_results_for(Integer pageNum, String searchTerm) throws InterruptedException {
    HomePage home = new HomePage(driver);
    home.scrollToBottom();
    home.isOnPageNum(pageNum);
    home.searchResultIsDisplayed(searchTerm);
  }
  @Then("there should not be a {string} button for search result pagination")
  public void there_should_not_be_a_button_for_search_result_pagination(String btn) throws InterruptedException {
    HomePage home = new HomePage(driver);
    home.scrollToBottom();

      if(home.pageBtnIsDisplayed(btn)) {
        throw new AssertionError(btn+" button is enabled when it should not be");
      }
  }
  
    @When("the user is on the last page of search results")
    public void the_user_is_on_the_last_page_of_search_results() {
      HomePage home = new HomePage(driver);
      if(!home.isLastPage()) {
        throw new AssertionError("Not on last page");
      }
    }


}
