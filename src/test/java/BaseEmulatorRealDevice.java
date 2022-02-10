import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseEmulatorRealDevice {

    public static AndroidDriver<AndroidElement> capabilities(String device) throws MalformedURLException {

        AndroidDriver<AndroidElement> driver;

        DesiredCapabilities cap= new DesiredCapabilities();

        File appDir = new File("src");

        File app = new File(appDir, "General-Store.apk");

        if(device.equals("emulator")) {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        } else if (device.equals("real")) {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        }
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");//new step

        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        return driver;
    }
    public static double getValue(String value) {
        value = value.substring(1);
        double price = Double.parseDouble(value);
        return price;

    }
}
