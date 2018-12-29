package easemytrip;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
public class EaseMyTrip
{
	public static void main(String[] args) throws InterruptedException
	{
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://www.easemytrip.com");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("FromSector_show")).sendKeys("Bangalore");
		driver.findElement(By.xpath("//span[contains(text(),'BLR')]")).click();
		driver.findElement(By.id("Editbox13_show")).sendKeys("Delhi");
		driver.findElement(By.xpath("//span[contains(text(),'DEL')]")).click();
		driver.findElement(By.id("ddate")).click();
		driver.findElement(By.className("active-date")).click();
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		
		WebElement lp=driver.findElement(By.xpath("(//span[contains(@class,'ui-slider')])[1]"));
		WebElement hp=driver.findElement(By.xpath("(//span[contains(@class,'ui-slider')])[2]"));
		WebElement dt=driver.findElement(By.xpath("(//span[contains(@class,'ui-slider')])[3]"));
		WebElement at=driver.findElement(By.xpath("(//span[contains(@class,'ui-slider')])[6]"));
		Thread.sleep(5000);
		Actions act=new Actions(driver);
		act.clickAndHold(lp).moveByOffset(10,0).release().perform();
		act.clickAndHold(hp).moveByOffset(-30,0).release().perform();
		act.clickAndHold(dt).moveByOffset(80,0).release().perform();
		act.clickAndHold(at).moveByOffset(-40,0).release().perform();
		Thread.sleep(5000);
		driver.findElements(By.xpath("//button[contains(text(),'Book')]")).get(2).click();
		String s=driver.findElement(By.xpath("(//span[contains(@ng-bind,'total')])[7]")).getText().replaceAll(",","");
		int price=Integer.parseInt(s);
		System.out.println("Flight price is: "+price);
		try
		{
			Assert.assertTrue(price>=7980 && price<=26356,"Flight price is not within the filter range...");
			System.out.println("Flight price is within the filter range...");
		}	
		catch(AssertionError a)
		{
			System.out.println(a.getMessage());	
		}
		Thread.sleep(2000);
		driver.close();
	}
}
