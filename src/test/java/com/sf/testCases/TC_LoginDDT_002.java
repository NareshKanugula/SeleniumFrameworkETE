package com.sf.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sf.pageObjects.LoginPage;
import com.sf.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd)
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		if(isAlertPresent()==true)
		{
		driver.switchTo().alert().accept();;
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		}
	
	public boolean isAlertPresent() //user defined method to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		//String path=System.getProperty("user.dir")+"src/test/java/com/sf/testData/LoginData.xlsx";
		String path="F:\\SERVLET PROGRAMS\\SeleniumFrameworkETE\\src\\test\\java\\com\\sf\\testData\\LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "sheet1");
		int cocount=XLUtils.getCellCount(path, "sheet1", 1);
		String logindata[][]=new String[rownum][cocount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<cocount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j);
			}
		}
		return logindata;
	}
}
