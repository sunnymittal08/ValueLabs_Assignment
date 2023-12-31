import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException {

//        Step 1:
//        1. Navigate to the following URL https://subscribe.stctv.com/
//        2. Validate the Subscription Packages – Type & Price and Currency for all Countries (SA /Kuwait and Baharin).
//        Step2 for the assignment:
//        Kindly click the link
//        Select-Language
//        Select-country one by one all 3
//        Validate all three countries package, amount, package name(Basic Validation) in GIT applications/Bit Bucket(public account).

        //Initialize Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        //Launch the browser with URL
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://subscribe.stctv.com/");

        //Implicit Wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Change language to English
        driver.findElement(By.id("translation-btn")).click();

        //Verify the page title
        String expectedPageTitle = "stc tv | Watch Movies, Series & Live TV - Enjoy Free Trial";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(expectedPageTitle,actualPageTitle);

        String countrySelected = driver.findElement(By.id("country-name")).getText();
        String firstPackageName =  driver.findElement(By.id("name-lite")).getText();
        String secondPackageName =  driver.findElement(By.id("name-classic")).getText();
        String thirdPackageName =  driver.findElement(By.id("name-premium")).getText();
        String monthlyPriceLite =  driver.findElement(By.id("currency-lite")).getText();
        String monthlyPriceClassic =  driver.findElement(By.id("currency-classic")).getText();
        String monthlyPricePremium =  driver.findElement(By.id("currency-premium")).getText();

        //Select the Country Selection
        driver.findElement(By.id("arrow")).click();

        //Select Bahrain
        driver.findElement(By.id("bh")).click();

        try {
            if (countrySelected.equals("Bahrain")) {
                Assert.assertEquals("LITE", firstPackageName);
                Assert.assertEquals("CLASSIC", secondPackageName);
                Assert.assertEquals("PREMIUM", thirdPackageName);
                Assert.assertEquals("2 BHD/month", monthlyPriceLite);
                Assert.assertEquals("3 BHD/month", monthlyPriceClassic);
                Assert.assertEquals("6 BHD/month", monthlyPricePremium);
            }
        }catch(Exception e) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshotFile, new File("D:\\BahrainError.png"));
        }

        //Select the country
        driver.findElement(By.id("arrow")).click();
        //Select Kuwait
        driver.findElement(By.id("kw")).click();

        try{
        if(countrySelected.equals("Kuwait")) {
            Assert.assertEquals("LITE", firstPackageName);
            Assert.assertEquals("CLASSIC", secondPackageName);
            Assert.assertEquals("PREMIUM", thirdPackageName);
            Assert.assertEquals("1.2 KWD/month", monthlyPriceLite);
            Assert.assertEquals("2.5 KWD/month", monthlyPriceClassic);
            Assert.assertEquals("4.8 KWD/month", monthlyPricePremium);
        }
        }catch(Exception e) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshotFile, new File("D:\\KuwaitError.png"));
        }

        //Select the country
        driver.findElement(By.id("arrow")).click();
        //Select KSA
        driver.findElement(By.id("sa")).click();

        try{
        if(countrySelected.equals("KSA")) {
            Assert.assertEquals("LITE", firstPackageName);
            Assert.assertEquals("CLASSIC", secondPackageName);
            Assert.assertEquals("PREMIUM", thirdPackageName);
            Assert.assertEquals("15 SAR/month", monthlyPriceLite);
            Assert.assertEquals("25 SAR/month", monthlyPriceClassic);
            Assert.assertEquals("60 SAR/month", monthlyPricePremium);
        }
        }catch(Exception e) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshotFile, new File("D:\\KSAError.png"));
        }

        driver.close();
    }

}