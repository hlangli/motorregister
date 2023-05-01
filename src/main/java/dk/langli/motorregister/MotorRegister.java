package dk.langli.motorregister;

import static java.time.temporal.ChronoUnit.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MotorRegister {
	private static final String BASE_URL = "https://motorregister.skat.dk/dmr-kerne/";
	
	public static void main(String[] args) {
		System.out.println(new MotorRegister().toVin("DP10408"));
	}
	
	private void setup(WebDriver driver) {
		Options options = driver.manage();
		driver.get(BASE_URL);
		options.timeouts().implicitlyWait(Duration.of(5, SECONDS));
		driver.findElement(By.cssSelector("a[title='Køretøjsdetaljer']")).click();
		options.timeouts().implicitlyWait(Duration.of(5, SECONDS));
		driver.findElement(By.cssSelector("a[title='Vis køretøj']")).click();
		options.timeouts().implicitlyWait(Duration.of(5, SECONDS));
	}
	
	public String toVin(String numberPlate) {
		Browser browser = new Browser();
		return browser.applyDriver(driver -> {
			setup(driver);
			Options options = driver.manage();
			driver.findElement(By.id("regnr")).click();
			driver.findElement(By.id("soegeord")).sendKeys(numberPlate);
			driver.findElement(By.id("fremsoegKtBtn")).click();
			options.timeouts().implicitlyWait(Duration.of(5, SECONDS));
			return driver.findElement(By.xpath("span[text() = 'Stelnummer:']/following-sibling::span")).getText();
		});
	}

	public String toNumberPlate(String vin) {
		return null;
	}
}
