package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameScreenShot {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		/*File src = driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("./snaps/img.png");
		FileUtils.copyFile(src, dest);
		*/
		driver.switchTo().frame(0);
		WebElement clickMe = driver.findElement(By.id("Click"));
		File src = clickMe.getScreenshotAs(OutputType.FILE);
		File dest= new File("./snaps/img.png");
		FileUtils.copyFile(src, dest);
		
		clickMe.click();
		System.out.println("1st Frame Click Text :" +clickMe.getText());
		
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("No. of Frames :" +frames.size());
		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@src='page.html']"));
		driver.switchTo().frame(frame1);
		driver.switchTo().frame("frame2");
		WebElement nestedFrameClick = driver.findElement(By.id("Click1"));
		nestedFrameClick.click();
		System.out.println("Nested Frame Click Text :" +nestedFrameClick.getText());
		
		driver.switchTo().defaultContent();
				
	}

}
