import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Web {

    private static final int LONG = 5;
    private static final int SHORT = 2;

    private Web(){
        
    }

    class xPath {
        public static void click (WebDriver driver, String xPath){
            WebElement element = driver.findElement((By.xpath(xPath)));
            Web.click(driver, element);
        }

        public static void carefulClick (WebDriver driver, String xPath){
            WebElement element = driver.findElement((By.xpath(xPath)));
            Web.carefulClick(driver, element);
        }

        public static String getText (WebDriver driver, String xPath){
            WebElement element = driver.findElement((By.xpath(xPath)));
            return Web.getText(driver, element);
        }

        public static String getText (WebDriver driver, String xPath, Collection<String> letters){
            WebElement element = driver.findElement((By.xpath(xPath)));
            return Web.getText(driver, element, letters);
        }

        public static String getTextFast (WebDriver driver, String xPath){
            WebElement element = driver.findElement((By.xpath(xPath)));
            return Web.getTextFast(driver, element);
        }

        public static String getTextFast (WebDriver driver, String xPath, Collection<String> letters){
            WebElement element = driver.findElement((By.xpath(xPath)));
            return Web.getTextFast(driver, element, letters);
        }

        public static void clearTextBox (WebDriver driver, String xPath){
            WebElement element = driver.findElement((By.xpath(xPath)));
            Web.clearTextBox(driver, element);
        }
        
        public static boolean exists (WebDriver driver, String xPath){
            try {
                Web.xPath.getText(driver, xPath);
                return true;
            } catch (NoSuchElementException e){
                return false;
            }
        }
    }

    public static void type (WebDriver driver, String xPath, String content){
        WebElement element = driver.findElement((By.xpath(xPath)));
        Web.type(driver, element, content);
    }

    class ID {
        public static void click (WebDriver driver, String ID){
            WebElement element = driver.findElement((By.id(ID)));
            Web.click(driver, element);
        }

        public static void carefulClick (WebDriver driver, String ID){
            WebElement element = driver.findElement((By.id(ID)));
            Web.carefulClick(driver, element);
        }

        public static String getText (WebDriver driver, String ID){
            WebElement element = driver.findElement((By.id(ID)));
            return Web.getText(driver, element);
        }

        public static String getText (WebDriver driver, String ID, Collection<String> letters){
            WebElement element = driver.findElement((By.id(ID)));
            return Web.getText(driver, element, letters);
        }

        public static String getTextFast (WebDriver driver, String ID){
            WebElement element = driver.findElement((By.id(ID)));
            return Web.getTextFast(driver, element);
        }

        public static String getTextFast (WebDriver driver, String ID, Collection<String> letters){
            WebElement element = driver.findElement((By.id(ID)));
            return Web.getTextFast(driver, element, letters);
        }

        public static void clearTextBox (WebDriver driver, String ID){
            WebElement element = driver.findElement((By.id(ID)));
            Web.clearTextBox(driver, element);
        }
        
        public static boolean exists (WebDriver driver, String ID){
            try {
                Web.ID.getText(driver, ID);
                return true;
            } catch (NoSuchElementException e){
                return false;
            }
        }

        public static void type (WebDriver driver, String ID, String content){
            WebElement element = driver.findElement((By.id(ID)));
            Web.type(driver, element, content);
        }
    }

    public static RemoteWebDriver chrome(int implicitWait){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        RemoteWebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

        return driver;
    }

    public static void hitEnter (WebDriver driver){
        Actions search = new Actions(driver);
        search.sendKeys(Keys.ENTER).perform();
    }
    
    public static boolean takeScreenshot (WebDriver driver, String savePath){
        try {
            TakesScreenshot screenShot = ((TakesScreenshot) driver);
            File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
            File saveIn = new File(savePath);
            FileUtils.copyFile(sourceFile, saveIn);
            return true;
        } catch (IOException e){
            return false;
        }
    }

    public static boolean takeScreenshot (WebDriver driver, String savePath, int zoom){
        String script = "document.body.style.zoom='"+ zoom + "%'";
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script);
        boolean out = Web.takeScreenshot(driver, savePath);
        js.executeScript("document.body.style.zoom='100%'");
        return out;
    }

    public static boolean takeScreenshot (WebDriver driver, String savePath, String zoom){
        String script = "document.body.style.zoom='"+ zoom + "%'";
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script);
        boolean out = Web.takeScreenshot(driver, savePath);
        js.executeScript("document.body.style.zoom='100%'");
        return out;
    }

    private static void click (WebDriver driver, WebElement element){
        WebDriverWait waitLong = new WebDriverWait(driver, Duration.ofSeconds(LONG));
        WebDriverWait waitShort = new WebDriverWait(driver, Duration.ofSeconds(SHORT));

        waitLong.until(ExpectedConditions.visibilityOf(element));
        waitShort.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    private static void carefulClick (WebDriver driver, WebElement element){
        WebDriverWait waitLong = new WebDriverWait(driver, Duration.ofSeconds(LONG));
        WebDriverWait waitShort = new WebDriverWait(driver, Duration.ofSeconds(SHORT));

        for (int i=0; i<10; i++){
            waitLong.until(ExpectedConditions.visibilityOf(element));
            waitShort.until(ExpectedConditions.elementToBeClickable(element));
        }

        element.click();
    }

    private static String getText (WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SHORT));

        wait.until(ExpectedConditions.visibilityOf(element));
        String out = element.getText();
        return out;
    }

    private static String getTextFast (WebDriver driver, WebElement element){
        String out = element.getText();
        return out;
    }

    private static String getText (WebDriver driver, WebElement element, Collection<String> letters){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SHORT));

        wait.until(ExpectedConditions.visibilityOf(element));
        String out = element.getText();

        for (String x : letters){
            out.replace(x, "");
        }

        return out;
    }

    private static String getTextFast (WebDriver driver, WebElement element, Collection<String> letters) {
        String out = element.getText();

        for (String x : letters) {
            out.replace(x, "");
        }

        return out;
    }

    private static void clearTextBox (WebDriver driver, WebElement element){
        WebDriverWait waitLong = new WebDriverWait(driver, Duration.ofSeconds(LONG));
        WebDriverWait waitShort = new WebDriverWait(driver, Duration.ofSeconds(SHORT));

        waitLong.until(ExpectedConditions.visibilityOf(element));
        waitShort.until(ExpectedConditions.elementToBeClickable(element));

        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.BACK_SPACE);

        element.click();
    }

    private static void type (WebDriver driver, WebElement element, String content){
        WebDriverWait waitLong = new WebDriverWait(driver, Duration.ofSeconds(LONG));
        WebDriverWait waitShort = new WebDriverWait(driver, Duration.ofSeconds(SHORT));

        waitLong.until(ExpectedConditions.visibilityOf(element));
        waitShort.until(ExpectedConditions.elementToBeClickable(element));

        element.sendKeys(content);
    }
}
