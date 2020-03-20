import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;


public class main{
    public static void main(String args[]){
        //System.setProperty("webdriver.chrome.driver", "/Users/robbie/Downloads/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("aggie access login");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".g:nth-child(1) .LC20lb")).click();
        // do not add credentials to github
        driver.findElement(By.id("UserID")).sendKeys("950336183");
        driver.findElement(By.id("PIN")).sendKeys("092298");
        driver.findElement(By.cssSelector("p > input:nth-child(1)")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) .submenulinktext2")).click();
        driver.findElement(By.linkText("Student Records")).click();
        driver.findElement(By.linkText("Academic Transcript")).click();
        driver.findElement(By.cssSelector("form:nth-child(2) > input")).click();
        driver.findElement(By.cssSelector(".pagebodydiv")).click();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability("download.default_directory","C:");
        cap.setCapability("download.prompt_for_download","false");
        cap.setCapability("directory_upgrade","true");
        cap.setCapability("plugins.plugins_disabled","Chrome PDF Viewer");

        WebDriver d = new ChromeDriver(cap);
        //driver.close();
    }

    public void yessit(int x){}

}