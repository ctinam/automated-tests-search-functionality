package StepDefinitions;

import org.openqa.selenium.WebDriver;
import PageObjects.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchInputsSteps {

  private WebDriver driver;
  private HomePage home;

  public SearchInputsSteps() {
    this.driver = Hooks.driver;
    this.home = new HomePage(driver);

  }

  @Given("user is on the homepage")
  public void user_is_on_the_homepage() throws InterruptedException {
    home.homepageIsDisplayed();
  }

  @When("user inputs {string} into the search bar")
  public void user_inputs_into_the_search_bar(String searchTerm) {
    home.clickSearchBar();
    home.fillSearchData(searchTerm);
  }

  @When("user clicks the search button")
  public void user_clicks_the_search_button() throws InterruptedException {
    home.clickSearchBar();
    Thread.sleep(2000);
    home.clickSearchButton();
  }

  @When("user press the enter key")
  public void user_press_the_enter_key() throws InterruptedException {
    Thread.sleep(2000);
    home.clickSearchBar();
    home.pressEnterKey();
  }

  @Then("user should see a list of results related to {string}")
  public void user_should_see_a_list_of_results_related_to(String searchTerm) {
    home.searchResultIsDisplayed(searchTerm);

  }

  @Then("user should see a message saying {string}")
  public void user_should_see_a_message_saying(String str) {
    home.messageNoticeIsDisplayed(str);
  }

  @When("user leaves the search bar empty")
  public void user_leaves_the_search_bar_empty() throws InterruptedException {
    home.clickSearchBar();
  }

  @Then("user should see a warning message saying {string}")
  public void user_should_see_a_warning_message_saying(String str) {
    home.messageNoticeIsDisplayed(str);
  }

  @When("user clicks on search bar")
  public void user_clicks_on_search_bar() {
    home.clickSearchBar();
  }

}
