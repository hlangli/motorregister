package dk.langli.motorregister;

import java.util.function.Consumer;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	public void acceptDriver(Consumer<WebDriver> c) {
		applyDriver(d -> {
			c.accept(d);
			return null;
		});
	}

	public <V> V applyDriver(Function<WebDriver, V> f) {
		V retval = null;
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.addArguments("--headless");
		WebDriver driver = new ChromeDriver(chromeOptions);
		try {
			driver.manage();
			retval = f.apply(driver);
		} finally {
			driver.close();
			driver.quit();
		}
		return retval;
	}
}
