import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class HotelBookingTest {

	static WebDriver driver;

	@FindBy(linkText = "Hotels")
	static WebElement hotelLink;

	@FindBy(id = "Tags")
	static WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	static WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	static WebElement travellerSelection;
	
	@FindBy(css = ".ui-state-default.ui-state-highlight.ui-state-active")
	static WebElement checkIN;
	
	@FindBy(css = ".ui-state-default.ui-state-active")
	static WebElement checkOut;

	@Test
	public void shouldBeAbleToSearchForHotels() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		setDriverPath();
		 driver = new ChromeDriver(options);

		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();

		PageFactory.initElements(driver, HotelBookingTest.class);
		
		 hotelLink.click();

		localityTextBox.sendKeys("Indiranagar, Bangalore");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-1 .list")));
		from.click();
		
		waitFor(5000);
		checkIN.click();
		
		waitFor(5000);
		checkOut.click();
		
		new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		searchButton.click();

		driver.quit();

	}
	
	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

}
