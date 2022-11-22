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
public class Page_LoginSignUp {	
	//***** Define variable ***********
	static AndroidDriver <MobileElement> driverApp;
	static AppiumDriver <MobileElement> driverApp1;
	static DesiredCapabilities dc = new DesiredCapabilities();
	static String loginVerification;
	static String callingClass;
	static String methodToCall = "verification";	
	public static void clickLogin(DesiredCapabilities dc, AndroidDriver<MobileElement> driverApp, String userName, String passWord) throws MalformedURLException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		driverApp.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driverApp.findElement(By.xpath("//*[@text = 'Already have an account? LOG IN']")).click();
		driverApp.findElement(By.xpath("//*[@text = 'Enter email']")).sendKeys(userName);
		driverApp.findElement(By.xpath("//*[@text = 'min. 6 characters']")).sendKeys(passWord);
		driverApp.findElement(By.xpath("//*[@text = 'LOG IN']")).click();
		//String errorText = driverApp.findElement(By.xpath("//*[@class='android.widget.TextView[0]']")).getText();
		//driverApp.findElement(By.xpath("driverApp.findElement(By.xpath(\"//*[@text = 'LOG IN']\")).click();")).click();
		try {
			String errorText = driverApp.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView").getText();
			System.out.println("Error text = " + errorText);
			loginVerification = errorText;			
		}
		catch (Exception e) {
			successLoginToMainPage(driverApp);
		}		
	        verification(loginVerification);
	} //end clickLogin method
	static void successLoginToMainPage(AndroidDriver<MobileElement> driverApp) {
		try {
			driverApp.findElement(By.xpath("//*[@text = 'This is your cash account, where you can track your cash payments.']")).click(); //first tooltip
			//driverApp.findElement(By.xpath("//*[@text = 'This is your cash account, where you can track your cash payments.']")).click();			
		}
		catch(Exception e) {
			loginVerification = driverApp.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.TextView")).getText(); //Parameter to ensure Login is successful			
		}
	}//end successLoginToMainPage	
	public static void verification(String loginVerifcation) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		callingClass = new Exception().getStackTrace()[2].getClassName(); 
        	Class<?> cls = Class.forName(callingClass);
       	        Object obj = cls.newInstance();	
                String class_name = cls.getName();
                System.out.println("This is " + class_name + " calling");	
                Class[] arg = new Class[1];
	        arg[0] = String.class;
	        Method method = cls.getDeclaredMethod(methodToCall, arg[0]);
		method.invoke(obj,loginVerification);
	} //end loginVerification 
}//end Page_LoginSignUp 




