import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class Page_AdjustBalance {
	
	//***** Define variable ***********
	static AndroidDriver <MobileElement> driverApp;
	static AppiumDriver <MobileElement> driverApp1;
	static DesiredCapabilities dc = new DesiredCapabilities();
	static String callingClass;
	static String methodToCall = "verificationAdjustBalance";
	static String currentBalance;
	static String newBalance;
	public static void adjustBalance(DesiredCapabilities dc, AndroidDriver<MobileElement> driverApp, String newValue) throws MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		System.out.println("Running AdjustBalance");
		driverApp.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		currentBalance = driverApp.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView")).getText();
		System.out.println("Current balance = " + currentBalance);
		driverApp.findElement(By.xpath("//*[@text = 'ADJUST BALANCE']")).click();
		driverApp.findElement(By.xpath("//*[@text = 'Write the correct balance and Wallet will create a correction record for it. Use this if you’ve forgotten to track just a few expenses.']")).click();
		driverApp.findElement(By.id("android:id/input")).sendKeys(newValue);
		driverApp.findElement(By.xpath("//*[@text = 'INSERT']")).click();
		driverApp.findElement(By.xpath("//*[@text = 'BUDGETS & GOALS']")).click();
		driverApp.findElement(By.xpath("//*[@text = 'ACCOUNTS']")).click();
		newBalance = driverApp.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.viewpager.widget.ViewPager/android.widget.RelativeLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView")).getText();		
		//String errorText = driverApp.findElement(By.xpath("//*[@class='android.widget.TextView[0]']")).getText();
		//driverApp.findElement(By.xpath("driverApp.findElement(By.xpath(\"//*[@text = 'LOG IN']\")).click();")).click();
		System.out.println("Current Balance = " + currentBalance);
		System.out.println("New Balance = " + newBalance);
		verification(currentBalance, newBalance);
	} //end adjustBalance method
	public static void verification(String currentBalance, String newBalance) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		callingClass = new Exception().getStackTrace()[2].getClassName(); 
        Class<?> cls = Class.forName(callingClass);
        Object obj = cls.newInstance();	
        String class_name = cls.getName();
        System.out.println("This is " + class_name + " calling");	
        Class[] arg = new Class[2];
	    arg[0] = String.class;
	    arg[1] = String.class;	   
	    Method method = cls.getDeclaredMethod(methodToCall, arg[0], arg[1]);
		method.invoke(obj,currentBalance, newBalance);
	} //end adjustBalanceVerification 
}//end public class LoginSignUp




