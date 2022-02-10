import io.appium.java_client.MobileBy;
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

public class EcommerceLongpressLinkTest extends BaseEmulatorRealDevice{

    @Test
    public void ecommerceTest() throws MalformedURLException, InterruptedException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Yuriy");
        driver.hideKeyboard();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belarus\"));");
        WebElement belarus = driver.findElement(By.xpath("//android.widget.TextView[@text='Belarus']"));
        TouchAction tap = new TouchAction(driver);
        tap.tap(tapOptions().withElement(element(belarus))).perform();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
                + "new UiSelector().text(\"Jordan 6 Rings\"));");
        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0; i < count; i++) {
            String textName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (textName.equalsIgnoreCase("Jordan 6 Rings")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);
        WebElement checkBox = driver.findElement(By.xpath("//*[@text='Send me e-mails on discounts related to selected products in future']"));
        tap.tap(tapOptions().withElement(element(checkBox))).perform();

        Assert.assertTrue(checkBox.isEnabled());
        WebElement longPress = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        tap.longPress(longPressOptions().withDuration(Duration.ofSeconds(2)).withElement(element(longPress))).release().perform();

        Assert.assertEquals(driver.findElement(By.id("android:id/message")).getText(), "Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    }
}
