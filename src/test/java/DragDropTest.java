import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class DragDropTest extends BaseEmulatorRealDevice {

    @Test
    public void dragDropTest() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("real");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
        TouchAction tap = new TouchAction(driver);
        WebElement first = driver.findElement(By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_1']"));
        WebElement second = driver.findElement(By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_2']"));
        tap.longPress((element(first))).moveTo(element(second)).release().perform();
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Dropped!']")).isDisplayed());
    }
}
