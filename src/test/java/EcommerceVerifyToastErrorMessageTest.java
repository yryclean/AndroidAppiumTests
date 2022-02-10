import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class EcommerceVerifyToastErrorMessageTest extends BaseEmulatorRealDevice {

    @Test
    public void ecommerce2Test() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        WebElement toast = driver.findElement(By.xpath("//android.widget.Toast[1]"));
        Assert.assertEquals(toast.getAttribute("name"), "Please enter your name");
    }
}
