import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class EcommerceVerifyProductsAddedToCartTest extends BaseEmulatorRealDevice{

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
            if(textName.equalsIgnoreCase("Jordan 6 Rings")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
                + "new UiSelector().text(\"Air Jordan 1 Mid SE\"));");
        int count2 = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0; i < count2; i++) {
            String textName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(textName.equalsIgnoreCase("Air Jordan 1 Mid SE")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);
        WebElement prod1 = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[585,790][1010,844]']"));
        prod1.getText();
        WebElement prod2 = driver.findElement(By.xpath("//android.widget.TextView[@bounds='[70,790][495,844]']"));
        prod2.getText();
        Assert.assertEquals(prod1.getText(), "Air Jordan 1 Mid SE");
        Assert.assertEquals(prod2.getText(), "Jordan 6 Rings");

    }
}
