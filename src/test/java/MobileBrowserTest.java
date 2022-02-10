
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MobileBrowserTest extends BaseBrowser {

    @Test
    public void mobileBrowserTest() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        AndroidDriver<AndroidElement> driver = capabilities();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
        driver.findElement(By.xpath("//*[@href='/angularAppdemo/products']")).click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)","");
        String devops = driver.findElement(By.xpath("//*[@href='/angularAppdemo/products/3']")).getText();
        Assert.assertEquals(devops, "Devops");
        driver.findElement(By.xpath("//*[@href='/angularAppdemo/products/3']")).click();


    }
}
