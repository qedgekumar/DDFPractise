package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil{
	String inputpath="./FileInput/Loginnew.xlsx";
	String outputpath="./FileOutput/DDFResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void starTest() throws Throwable
	{
		//define path of html
		report=new ExtentReports("./target/Reports/DDF.html");
		//create object for Excel file util class
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc=xl.rowCount("sheet1");
		Reporter.log("No of rows are:"+rc);
		for(int i=1;i<=rc;i++)
		{
			logger=report.startTest("validate login");
			String user=xl.getCellData("sheet1", i, 0);
			String pass=xl.getCellData("sheet1", i, 1);
			//call admin login method from function library
			boolean res=FunctionLibrary.adminLogin(user, pass);
			if(res) {
				//write as login success into results cell
				xl.setcellData("sheet1", i, 2, "Login success", outputpath);
				//write as pass into status cell
				xl.setcellData("sheet1", i, 3, "pass", outputpath);
				logger.log(LogStatus.PASS, "valid username and password");
			}
			else
			{
				//write as login fail into results cell
				xl.setcellData("sheet1", i, 2, "Login Fail", outputpath);
				//write as fail into status cell
				xl.setcellData("sheet1", i, 3, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Invalid username and password");
			}
			report.endTest(logger);
			report.flush();
			
		}
		
	}

}
