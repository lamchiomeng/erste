import java.text.SimpleDateFormat;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;

public class TC_AdjustBalance {
	//****** Variables declaration *******
	static AndroidDriver <MobileElement> driverApp;	
	static String testCase;
	static String testDataFile;
	static String testDataTab;
	static String testDataTab1;
	static String testDataTab2;
	static String dateTime;
	static String dateTime1;
	static String testResultFilename;
	static String deviceName;
	static String platformName;
	static String appPackage;
	static String appActivity;
	static String result;
	static String fileName;
	static String userName;
	static String passWord;
	static String newValue;
	static String currentBalanceWithoutCurrencySymbol;
	static String newBalanceWithoutCurrencySymbol;
	static String newValueString;
	static int countDesCapTab;
	static int countLoginDetailsTab;
	static int countAdjustBalanceTab;
	static double newValueDouble;
	static double newValueDoubleAfterRoundUp;
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException { //Main method
		//The following variables are for testing in DEV MODE. Comment it when testing in production
		testCase = "TC_AdjustBalance"; 		
		testDataFile = "C:\\TA\\TD\\TestData.xlsx";				
		testDataTab = "DesCap";	// defined by tester to indicate which tab in Test Data to refer 
		testDataTab1 = "LoginDetails";
		testDataTab2 = "AdjustBalance";
		dateTime = new SimpleDateFormat("yyyyMMdd_HHmm'.html'").format(new Date());
		dateTime1 = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
		testResultFilename = "C://TA//TR//"+testCase+"_"+dateTime;
		fileName = testCase+"_"+dateTime;		
		//The following variables are for testing in Production. Comment it when testing in DEV MODE
		/*testCase = args[0];
		testDataFile = args[1];			
		testDataTab = args[2];;		
		dateTime = new SimpleDateFormat("yyyyMMdd_HHmm'.xlsx'").format(new Date());
		testResultFilename = "c://TA//TR//"+testCase+"_"+dateTime;
		*/
		//The following setups call to read Test Data file for Desired Capabilities' definition
		FileInputStream desCap = new FileInputStream(testDataFile);		
	 	XSSFWorkbook wbDesCap = new XSSFWorkbook(desCap);
	 	XSSFSheet tabDesCap = wbDesCap.getSheet(testDataTab);
	 	System.out.println("No. of rows with data in DesCap tab : " + tabDesCap.getLastRowNum());
		for(countDesCapTab = 1; countDesCapTab <= tabDesCap.getLastRowNum(); countDesCapTab++) { //desCap tab loop        			
			XSSFRow currentrowDesCap = tabDesCap.getRow(countDesCapTab); //current row being read
			deviceName = currentrowDesCap.getCell(0).toString();
            platformName = currentrowDesCap.getCell(1).toString();	
            appPackage = currentrowDesCap.getCell(2).toString();	            
		    appActivity = currentrowDesCap.getCell(3).toString();	
		    DesCap desCapObj = new DesCap();
			desCapObj.setDesCap(deviceName, platformName, appPackage, appActivity);		
		}// end desCap tab loop	   
	}// end main method
	static void startScreen(DesiredCapabilities dc, AndroidDriver<MobileElement> driverApp) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, IOException {
		userName =  "lamchiomeng78@gmail.com";	
        passWord =  "123Tester456";
        Page_SplashScreen.clickStartNow(dc, driverApp);	
		System.out.println("dc = " + dc + "and driverApp = " + driverApp);
		Page_LoginSignUp.clickLogin(dc, driverApp, userName, passWord);
		adjustBalance(dc, driverApp);
	}//end startScreen method
	static void adjustBalance(DesiredCapabilities dc, AndroidDriver<MobileElement> driverApp) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, IOException {
		//The following setups call to read Test Data File for Login Details 
		FileInputStream adjustBalance = new FileInputStream(testDataFile);		
	 	XSSFWorkbook wbAdjustBalance = new XSSFWorkbook(adjustBalance);
	 	XSSFSheet tabAdjustBalance = wbAdjustBalance.getSheet(testDataTab2);
	 	System.out.println("No. of rows with data in adjustBalance tab : " + tabAdjustBalance.getLastRowNum());
		for(countAdjustBalanceTab = 1; countAdjustBalanceTab <= tabAdjustBalance.getLastRowNum(); countAdjustBalanceTab++) { //Login tab loop        			
			System.out.println("Round : " + countAdjustBalanceTab);
			XSSFRow currentRowAdjustBalance = tabAdjustBalance.getRow(countAdjustBalanceTab); //current row being read			
			newValue =  currentRowAdjustBalance.getCell(0).toString();
	        Page_AdjustBalance.adjustBalance(dc, driverApp, newValue);
		}//end for AdjustBalance loop tab
	}//end Adjust Balance method
	static void verification(String verification) {
		//This is just a landing for return from Page_LoginSetup. 
	}// end verification method
	static void verificationAdjustBalance(String currentBalance, String newBalance) { //verify balance is correctly updated and round-up (if more than 2 decimal points)
		newBalanceWithoutCurrencySymbol = newBalance.replaceAll("[^0-9.]", ""); //remove currency symbol
		currentBalanceWithoutCurrencySymbol = currentBalance.replaceAll("[^0-9.]", ""); //remove currency symbol
		newValueDouble = Double.parseDouble(newValue);
		newValueDoubleAfterRoundUp = (double)Math.round(newValueDouble * 100)/100; // Round up to 2 d.p if newValue has more than 2 d.p.
		System.out.println("aFTER ROUND UP = " + newValueDoubleAfterRoundUp);
		newValueString = String.valueOf(newValueDoubleAfterRoundUp);
		if(newBalanceWithoutCurrencySymbol.equals(newValueString)) {
			result = "PASS";
			createTestResult(result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
		}
		else {
			result = "FAIL";
			createTestResult(result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
		}
	}// end verification
	static void createTestResult(String result, String currentBalanceWithoutCurrencySymbol, String newValue, String newBalanceWithoutCurrencySymbol) {
		File file = new File(testResultFilename);//check whether test result file exist
		if(file.exists()) {
			addRow(testCase, result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
		}
		else { //if test result file not created yet
			createNewResultTable(testCase, result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);				
		}	
	}//end createTestResult
	static void createNewResultTable(String testCase, String result, String currentBalanceWithoutCurrencySymbol, String newValue, String newBalanceWithoutCurrencySymbol) { //construct test result file. Will only be executed if Test Result file absent
		StringBuilder htmlStringBuilder=new StringBuilder();
		//The following create table header
		htmlStringBuilder.append("<html><head><style>table{border-collapse: collapse; border:0px solid black}");
	    htmlStringBuilder.append("th,td {border: 1px solid black;}");
	    htmlStringBuilder.append("table.d {table-layout: fixed; width: 150%;}</style></head><body>");	
	    htmlStringBuilder.append("<h2>"+testCase+"</h2>");
	    htmlStringBuilder.append("<h2>"+"Date / Time : "+dateTime1+"</h2>");
	    htmlStringBuilder.append("<table class= 'd' >");
	    htmlStringBuilder.append("<tr><th><b>Test Case</b></th>");
	    htmlStringBuilder.append("<th><b>Current Balance</b></th>");
	    htmlStringBuilder.append("<th><b>New Value</b></th>");
	    htmlStringBuilder.append("<th><b>Expected New Balance</b></th>");
	    htmlStringBuilder.append("<th><b>Actual New Balance</b></th>");
	    htmlStringBuilder.append("<th><b>Result</b></th></tr>");
	    if(result.equals("PASS")) {
	    	resultPass(htmlStringBuilder.toString(), testCase, result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
	    }
	    else {
	    	resultFail(htmlStringBuilder.toString(), testCase, result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
	    }
	}// end createNewResultTable  
	static void addRow(String testCase, String result, String currentBalanceWithoutCurrencySymbol, String newValue, String newBalanceWithoutCurrencySymbol) {
		if(result.equals("PASS")) {
	    	resultPass("", testCase, result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
	    }
	    else {
	    	resultFail("", testCase, result, currentBalanceWithoutCurrencySymbol, newValue, newBalanceWithoutCurrencySymbol);
	    }
	}//end addrow
	static void resultPass(String stringBuilder, String testCase, String result, String currentBalanceWithoutCurrencySymbol, String newValue, String newBalanceWithoutCurrencySymbol) {
		try {
	        StringBuilder htmlStringBuilder=new StringBuilder();
	        htmlStringBuilder.append("<table class= 'd' >");
	        htmlStringBuilder.append(stringBuilder);
	        htmlStringBuilder.append("<tr><td>"+testCase+"</td>");
		    htmlStringBuilder.append("<td>"+currentBalanceWithoutCurrencySymbol+"</td>");
		    htmlStringBuilder.append("<td>"+newValue+"</td>");
		    htmlStringBuilder.append("<td>"+newValueDoubleAfterRoundUp+"</td>");
		    htmlStringBuilder.append("<td>"+newBalanceWithoutCurrencySymbol+"</td>");
		    htmlStringBuilder.append("<td>"+result+"</td></tr>");
	        htmlStringBuilder.append("</table></body></html>");
	        WriteToFile(htmlStringBuilder.toString(), fileName);		        
	    } 
		catch (IOException e) {
	        e.printStackTrace();
	    }
	}//end resultPass
	static void resultFail(String stringBuilder, String testCase, String result, String currentBalanceWithoutCurrencySymbol, String newValue, String newBalanceWithoutCurrencySymbol) {
		try {
			StringBuilder htmlStringBuilder=new StringBuilder();
			htmlStringBuilder.append("<table class= 'd' >");
	        htmlStringBuilder.append(stringBuilder);
	        htmlStringBuilder.append("<tr><td bgcolor = 'red'><p style ='color:white'>"+testCase+"</p></td>");
		    htmlStringBuilder.append("<td bgcolor = 'red'><p style ='color:white'>"+currentBalanceWithoutCurrencySymbol+"</p></td>");
		    htmlStringBuilder.append("<td bgcolor = 'red'><p style ='color:white'>"+newValue+"</p></td>");
		    htmlStringBuilder.append("<td bgcolor = 'red'><p style ='color:white'>"+newValueDoubleAfterRoundUp+"</p></td>");
		    htmlStringBuilder.append("<td bgcolor = 'red'><p style ='color:white'>"+newBalanceWithoutCurrencySymbol+"</p></td>");
		    htmlStringBuilder.append("<td bgcolor = 'red'><p style ='color:white'>"+result+"</p></td></tr>");
	        htmlStringBuilder.append("</table></body></html>");
	        WriteToFile(htmlStringBuilder.toString(),fileName);		        
	    } 
		catch (IOException e) {
	        e.printStackTrace();
	    }
	}// end resultFail
	static void WriteToFile(String fileContent, String fileName) throws IOException {
	    String projectPath = "C:/TA/TR";
	    String tempFile = projectPath + File.separator+fileName;
	    File file = new File(tempFile);
	    // if file does exists, then delete and create a new file
	          //write to file with OutputStreamWriter
	    //OutputStream outputStream = new FileOutputStream(file);
	    FileOutputStream fos =new FileOutputStream(file, true) ;
	    Writer writer=new OutputStreamWriter(fos);
	    writer.write(fileContent);
	    writer.close();
	}//end Writetofile		
}//end public class 
