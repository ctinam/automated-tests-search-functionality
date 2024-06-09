package PageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  private WebDriver driver;


  public HomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id=\"search\"]")
  @CacheLookup
  private WebElement searchField;

  @FindBy(xpath = "/html/body/div[2]/header/div[2]/div[2]/div[2]/form/div[2]/button")
  @CacheLookup
  private WebElement searchBtn;

  @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/div/strong/a")
  @CacheLookup
  private WebElement firstProductResult;

  @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div")
  @CacheLookup
  private WebElement noResults;

  @FindBy(xpath = "//*[@id=\"search_autocomplete\"]")
  @CacheLookup
  private WebElement searchDropdown;

  @FindBy(xpath = "/html/body/div[2]/header/div[2]/div[2]/div[2]/form/div[1]/div/div[1]/ul")
  @CacheLookup
  private WebElement searchSuggestions;

  @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div[3]/div[2]/ul/li[3]/a")
  @CacheLookup
  private WebElement nextPageBtn;

  @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div[3]/div[2]/ul/li[1]/a")
  @CacheLookup
  private WebElement prevPageBtn;

  @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div[3]/div[2]/ul")
  @CacheLookup
  private WebElement pageNumList;

  @FindBy(xpath = "//*[@id=\"toolbar-amount\"]")
  @CacheLookup
  private WebElement paginationInfo;

  public boolean homepageIsDisplayed() throws InterruptedException {
    if (!searchField.isDisplayed()) {
      throw new NoSuchElementException();
    }
    Thread.sleep(3000);
    return true;

  }

  public void clickSearchButton() {
    if (searchBtn.isEnabled()) {
      searchBtn.click();
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
      return;
    }
    throw new AssertionError("Unable to click search button");
  }

  public void clickSearchBar() {
    if(searchField.isEnabled()) {
      searchField.click();
      return;
    }
    throw new AssertionError("Search bar is not enabled");
  }

  public void fillSearchData(String searchTerm) {
    if(searchField.isEnabled()) {
      searchField.clear();
      searchField.sendKeys(searchTerm);
      return;
    }
    throw new AssertionError("Search bar is not enabled");
  }


  public void searchResultIsDisplayed (String searchTerm) {
    String res = firstProductResult.getText();
    Assert.assertTrue(res.toUpperCase().contains(searchTerm.toUpperCase()));
  }
  public void pressEnterKey() {
    if(searchField.isEnabled()) {
      searchField.sendKeys(Keys.ENTER);
      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
      return;
    }
    throw new AssertionError("Search bar is not enabled");
  }

  public void messageNoticeIsDisplayed(String str) {
    if(noResults.isDisplayed()) {
      String res = noResults.getText();
      Assert.assertTrue("No message displayed",res.toUpperCase().contains(str.toUpperCase()));
      return;
    }
    throw new AssertionError("No message displayed");

  }

  public void clearSearchBar() {
    searchField.isEnabled();
    searchField.clear();
  }

  public void searchDropdownIsVisible() {
    if (!searchDropdown.isDisplayed()) {
      throw new AssertionError("Search suggestions dropdown is not displayed");
    }
  }

  public void isInSearchSuggestions(String suggestion1,  String suggestion2) {
    List<WebElement> allOptions = searchSuggestions.findElements(By.tagName("li"));
    boolean sugg1Found = false;
    boolean sugg2Found = false;
    for(int i=0; i<allOptions.size(); i++) {
      if(allOptions.get(i).getText().toUpperCase().contains(suggestion1.toUpperCase())) {
        sugg1Found = true;
      } else if(allOptions.get(i).getText().toUpperCase().contains(suggestion2.toUpperCase())) {
        sugg2Found = true;
      }
    }
    if (!(sugg1Found && sugg2Found)) {
      throw new AssertionError(suggestion1 + " and " + suggestion2 + " are not in search suggestions dropdown");
    }
  }

  public void clickSecondSuggestion() {
    WebElement secondSugg = driver.findElement(By.xpath("//*[@id=\"qs-option-1\"]"));
    secondSugg.click();
  }

  public void scrollToBottom() throws InterruptedException {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    Thread.sleep(2000);
  }

  public void clickNextPageButton() {
    if(nextPageBtn.isEnabled()) {
      nextPageBtn.click();
    } else {
      throw new AssertionError("Next page button is not enabled");
    }
  }
  public void clickPrevPageButton() {
    if(prevPageBtn.isEnabled()) {
      prevPageBtn.click();
    } else {
      throw new AssertionError("Previous page button is not enabled");
    }
  }

  public void clickPageNum(int pageNum) {
    List<WebElement> allOptions = pageNumList.findElements(By.tagName("li"));
    for(int i=0; i<allOptions.size(); i++) {
      String currText = allOptions.get(i).getText().toUpperCase();
      if (currText.contains(String.valueOf(pageNum).toUpperCase())) {
        allOptions.get(i).click();
        return;
      }
    }
  }
  public void isOnPageNum(int pageNum) {
    List<WebElement> allOptions = pageNumList.findElements(By.tagName("li"));
    boolean currPageNumFound = false;
    String currStrIndicator = "You're currently reading page";
    for(int i=0; i<allOptions.size(); i++) {
      String currText = allOptions.get(i).getText().toUpperCase();
      if (currText.contains(String.valueOf(pageNum).toUpperCase())) {
        if (currText.contains(currStrIndicator.toUpperCase())) {
          currPageNumFound = true;
        }
      }
    }
    if (!currPageNumFound) {
      throw new AssertionError("Expected to be on page number " + pageNum);
    }
  }

  public boolean pageBtnIsDisplayed(String btn) throws NoSuchElementException  {
    List<WebElement> allOptions = pageNumList.findElements(By.tagName("li"));
    for(int i=0; i<allOptions.size(); i++) {
      String currText = allOptions.get(i).getText().toUpperCase();
      if (currText.contains(btn.toUpperCase())) {
        return true;
      }
    }
    return false;
  }

  public boolean isLastPage() {
    String paginationText = paginationInfo.getText();
    String[] parts = paginationText.split(" ");
    String[] range = parts[1].split("-");
    int endRange = Integer.parseInt(range[1]);
    int totalItems = Integer.parseInt(parts[3]);

    return endRange == totalItems;
  }
}
