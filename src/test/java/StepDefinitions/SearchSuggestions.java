package StepDefinitions;

import org.openqa.selenium.WebDriver;
import PageObjects.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSuggestions {

  private WebDriver driver;
  private HomePage home;

  public SearchSuggestions() {
    this.driver = Hooks.driver;
    this.home = new HomePage(driver);

  }
  @Then("user should see suggestions of the most popular searches")
  public void user_should_see_suggestions_of_the_most_popular_searches() {
    home.searchDropdownIsVisible();
  }

  @Then("the user should see search suggestions like {string} and {string}")
  public void the_user_should_see_search_suggestions_like_and(String suggestion1, String suggestion2) throws InterruptedException {
    Thread.sleep(5000);
    home.searchDropdownIsVisible();
    home.isInSearchSuggestions(suggestion1, suggestion2);
  }

  @Then("the user should see message {string}")
  public void the_user_should_see_message(String str) throws InterruptedException {
    Thread.sleep(3000);
    home.messageNoticeIsDisplayed(str);
  }

  @Then("the user should see search suggestions like {string}")
  public void the_user_should_see_search_suggestions_like(String suggestion) throws InterruptedException {
    Thread.sleep(5000);
    home.searchDropdownIsVisible();
    home.isInSearchSuggestions(suggestion, "");
  }

  @When("user selects a suggestion")
  public void user_selects_a_suggestion() throws InterruptedException {
    home.clickSecondSuggestion();
    Thread.sleep(5000);
  }

  @When("the user should see search suggestions")
  public void the_user_should_see_search_suggestions() throws InterruptedException {
    Thread.sleep(5000);
    home.searchDropdownIsVisible();

  }
}
