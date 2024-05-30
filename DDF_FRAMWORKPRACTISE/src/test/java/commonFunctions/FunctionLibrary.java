package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
	public static boolean adminLogin(String username,String password)
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("objReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("objUser"))).sendKeys(username);
		driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(password);
		driver.findElement(By.xpath(conpro.getProperty("objlogin"))).click();
		String Expected="dashboard";
		String Actual=driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			driver.findElement(By.xpath(conpro.getProperty("objLogoutLink"))).click();
			Reporter.log("username and password are valid:"+Expected+"  "+Actual,true);
			return true;
		}
		else
		{
			String Error_message=driver.findElement(By.xpath(conpro.getProperty("objError_message"))).getText();
			driver.findElement(By.xpath(conpro.getProperty("objokbutton"))).click();
			Reporter.log(Error_message+"-----"+Expected+"----"+Actual,true);
		}
		return false;
	}
	

}
