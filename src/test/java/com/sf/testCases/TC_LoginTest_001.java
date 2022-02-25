package com.sf.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.sf.pageObjects.LoginPage;

import junit.framework.Assert;

public class TC_LoginTest_001 extends BaseClass
{

		@Test
		public void loginTest() throws IOException
		{
			driver.get(baseURL);
			Logger.info("URL is opened");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			LoginPage lp=new LoginPage(driver);
			lp.setUserName(username);
			Logger.info("Engtered user name");
			lp.setPassword(password);
			Logger.info("Entered passwod");
			
			lp.clickSubmit();
						
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
			{
				Assert.assertTrue(true);
				Logger.info("Login test passed");
			}
			else
			{
				captureScreen(driver,"loginTest");
				Assert.assertTrue(false);
				Logger.info("Login test failed");
			}
		}
}
