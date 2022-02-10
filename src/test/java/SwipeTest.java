import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class SwipeTest extends Base {

    @Test
    public void swipeTest() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Date Widgets']")).click();
        TouchAction touchAction = new TouchAction(driver);
        WebElement inline = driver.findElementByAndroidUIAutomator("text(\"2. Inline\")");
        touchAction.tap(tapOptions().withElement(element(inline))).perform();
        driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc='9']")).click();
        WebElement elem15 = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc='15']"));
        WebElement elem45 = driver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc='45']"));
        touchAction.longPress(longPressOptions().withElement(element(elem15)).withDuration(Duration.ofSeconds(1))).moveTo(element(elem45)).release().perform();
        WebElement minutes = driver.findElement(By.xpath("//android.widget.TextView[@text='45']"));
        Assert.assertTrue(minutes.isDisplayed());
    }
}
