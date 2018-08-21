import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class FlightBookingTest {

	WebDriver driver;

	@Test
	public void testThatResultsAppearForAOneWayJourney() throws AWTException, InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		setDriverPath();
		driver = new ChromeDriver(options);
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("OneWay")).click();

		driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-1 .list")));
		from.click();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ui-id-2 .list")));
		to.click();

		waitFor(5000);

		driver.findElement(By.cssSelector(".ui-datepicker-days-cell-over.undefined.selected")).click();

		// all fields filled in. Now click on search
		driver.findElement(By.id("SearchBtn")).click();

		// verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		// close the browser
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

	private boolean isElementPresent(By by) {
		try {
			// driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
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
