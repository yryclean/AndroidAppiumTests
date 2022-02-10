import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;


import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GesturesTest extends Base{
        @Test
        public void gestureTest() throws MalformedURLException {
            AndroidDriver<AndroidElement> driver = capabilities();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
            TouchAction tap = new TouchAction(driver);
            WebElement expandElement = driver.findElement(By.xpath("//android.widget.TextView[@text='Expandable Lists']"));
            tap.tap(tapOptions().withElement(element(expandElement))).perform();
            WebElement oneTap = driver.findElement(By.xpath("//android.widget.TextView[@text='1. Custom Adapter']"));
            tap.tap(tapOptions().withElement(element(oneTap))).perform();
            WebElement longTap = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
            tap.longPress(longPressOptions().withElement(element(longTap)).withDuration(Duration.ofSeconds(2))).release().perform();
            WebElement sampleMenu = driver.findElement(By.xpath("//android.widget.TextView[@text='Sample menu']"));

            Assert.assertTrue(sampleMenu.isDisplayed());
    }
}
