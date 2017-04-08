package com.Test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class TestAutomation {
	public static WebDriver driver;
	String baseURL = "http://phptravels.net";
	/*@Parameters({ "browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"Seleniumjars\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"Seleniumjars\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						"Seleniumjars\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}*/
	@Test(priority=1)
	@Parameters({"url","username","password"})
	public void Login(String url,String username,String password) {
		System.setProperty("webdriver.gecko.driver", "Seleniumjars\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement el=driver.findElement(By.id("currency"));
		Select dropdown = new Select(el);
		dropdown.selectByVisibleText("EUR");
		WebElement Actual=driver.findElement(By.linkText("English"));
		if(Actual.equals("English"))
		{
			System.out.println("Language selected as English");
		}
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath(".//*[@id='loginfrm']/div[4]/button")).click();

	}
	@Test(priority=2,dependsOnMethods = { "Login" })
	public void verifydateandTime()
	{
		Functions vdate=new Functions();
		Object sdate=vdate.date();
		String Stime=vdate.time();
		WebElement cdate=driver.findElement(By.xpath("//span[@class='h4']"));
		//Object Actual1=cdate.getText();
		System.out.println(cdate.getText());
		Assert.assertEquals(cdate.getText(),sdate);
		WebElement cTime=driver.findElement(By.id("txt"));
		String time=cTime.getText();
		System.out.println(time);
		if(time.contains(Stime)){
			System.out.println("Time Matched");
		}else
		{
			System.out.println("Time not Matched");
		}

		/*driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();*/
	}
	@Test(priority=3,dependsOnMethods = { "Login" })
	public void Newsletter()
	{
		WebElement el=driver.findElement(By.xpath("html/body/div[3]/div[3]/div/div[1]/ul/li[4]/a"));
		el.click();
		WebElement text=driver.findElement(By.xpath(".//*[@id='newsletter']/div/div/h4"));
		String Actual=text.getText();
		String Expected="Join our newsletter to keep updated";
		if(Actual.equalsIgnoreCase(Expected))
		{
			System.out.println("Text matches");

		}
		else{
			System.out.println("Text not matches");
		}
		WebElement tbuttn=driver.findElement(By.xpath(".//*[@id='newsletter']/div/div/label/span/span[2]"));
		tbuttn.click();
		tbuttn.click();
		driver.findElement(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li[1]/a")).click();
		/*driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();*/
	}
	@Test(priority=3,dependsOnMethods = { "Login" })
	public void Test4() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.linkText("Hotels")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("citiesInput")).sendKeys("Bangalore", Keys.ENTER);  
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String checkin = driver.findElement(By.name("checkin")).getAttribute("value");
		String checkout = driver.findElement(By.name("checkout")).getAttribute("value");
		if(!checkin.equals(checkout))
		{
			System.out.println("Dates are not equal");
		}
		driver.findElement(By.name("checkin")).click();
		driver.findElement(By.name("checkin")).clear();
		driver.findElement(By.name("checkin")).sendKeys("04/08/2017");
		driver.findElement(By.name("checkout")).click();
		driver.findElement(By.name("checkout")).clear();
		driver.findElement(By.name("checkout")).sendKeys("04/09/2017",Keys.ENTER );
		//driver.findElement(By.xpath("(//td[contains(text(),'10')])[1]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement from = driver.findElement(By.xpath("(//div[@class='slider-handle round'])[1]"));

		js.executeScript("arguments[0].setAttribute('style', 'left: 25.31%;')",from);

		WebElement to = driver.findElement(By.xpath("(//div[@class='slider-handle round'])[2]"));

		js.executeScript("arguments[0].setAttribute('style', 'left: 50.63%;')",to);
		driver.findElement(By.xpath("//label[contains(text(),'Bed & Breakfast')]")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,450)", "");
		driver.findElement(By.id("searchform")).click();
		/*driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Logout")).click();*/
	}

	@AfterTest
	public void Closebrowser() {
		driver.close();;
	}

}
