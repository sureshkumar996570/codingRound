import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class SignInTest {

	WebDriver driver;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		setDriverPath();
		driver = new ChromeDriver(options);

		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		waitFor(5000);

		driver.findElement(By.linkText("Your trips")).click();
		driver.findElement(By.id("SignIn")).click();

		driver.switchTo().frame(driver.findElement(By.id("modal_window")));

		driver.findElement(By.id("signInButton")).click();

		String errors1 = driver.findElement(By.id("errors1")).getText();
		Assert.assertTrue(errors1.contains("There were errors in your submission"));
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
			System.setProperty("webdriver.chrome.driver", "codingRound-master/chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "codingRound-master/chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "codingRound-master/chromedriver_linux");
		}
	}

}
