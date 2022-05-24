package Pages;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestBase.testData;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChangeStatus extends testData
{
	WebDriver driver;

	@BeforeTest
	public void open_URL()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://dev.waffarad.com/Merchant/Account/Login?ReturnURL=/");
		driver.manage().window().maximize();

		WebElement UserName = driver.findElement(By.id("UserName"));
		UserName.clear();
		UserName.sendKeys("admin.merchant@waffarad.com");

		WebElement Password = driver.findElement(By.id("Password"));
		Password.clear();
		Password.sendKeys("M\"XwdU7]q)");

		driver.findElement(By.id("SubmitLogin")).click();

		assertEquals("https://dev.waffarad.com/Merchant/home/ChooseMerchant", driver.getCurrentUrl());

		WebElement Title_Choose_Merchant = driver.findElement(By.xpath("//*[@for='MerchantId']"));
		assertEquals(Title_Choose_Merchant.getText(), "Choose Merchant");

		WebElement Choose_Merchant_List = driver.findElement(By.id("MerchantId"));
		Select option = new Select (Choose_Merchant_List);
		option.selectByIndex(1);

		WebElement save = driver.findElement(By.xpath("//button[@type ='submit']"));
		save.click();

		WebElement orders = driver.findElement(By.xpath("//*[@href='/Merchant/Orders']"));
		orders.click();

		assertEquals("https://dev.waffarad.com/Merchant/Orders", driver.getCurrentUrl());
	}
	
	@Test
	public void CheckThat_OrderWhichPaid_NotEdit()
	{
		test = extent.startTest("CheckThat_OrderWhichPaid_NotEdit");

		WebElement Status_List = driver.findElement(By.id("44"));
		Select option = new Select (Status_List);
		option.selectByIndex(3);
	}
}