import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Selenium {
    private WebDriver driver;
    private CourseRecommendations courseRecommendations;

    public Selenium(String x, String y) {
        run(x, y);
         pdfParser g = new pdfParser();
        try {
            g.readTxt("transcript.txt");
            courseRecommendations = new CourseRecommendations(g.getPerson());
        } catch (FileNotFoundException e) {
            System.out.println(" transcript.txt not found");
            //System.exit(-1);
        }

    }

    private void run(String x, String y) {
        try {
            //access site through chrome.exe in libs folder
            System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");

            driver = new ChromeDriver(options);
            driver.navigate().to("https://ssbprod-ncat.uncecs.edu/pls/NCATPROD/twbkwbis.P_WWWLogin");

            //login
            WebElement studentId = driver.findElement(By.name("sid"));
            WebElement pin = driver.findElement(By.name("PIN"));
            studentId.sendKeys(x);
            pin.sendKeys(y);
            pin.submit();

            //locate transcript
            //--click Student
            String hrefLink = driver.findElement(By.xpath("//a[@href='/pls/NCATPROD/twbkwbis.P_GenMenu?name=bmenu.P_StuMainMnu']")).getAttribute("href");
            driver.get(hrefLink);


            //--click Student Records
            hrefLink = driver.findElement(By.xpath("//a[@href='/pls/NCATPROD/twbkwbis.P_GenMenu?name=bmenu.P_AdminMnu']")).getAttribute("href");
            driver.get(hrefLink);


            //--click Transcript
            hrefLink = driver.findElement(By.xpath("//a[@href='/pls/NCATPROD/bwskotrn.P_ViewTermTran']")).getAttribute("href");
            driver.get(hrefLink);
            //Thread.sleep(1000);  // Let the user actually see something!

            //--click submit btn
            driver.findElement(By.cssSelector("input[type='submit'][value='Submit']")).click();
            //Thread.sleep(0);  //should see transcript now

            //Scrape transcript text
            String str = driver.findElement(By.className("pagebodydiv")).getText();
            writeToText(str);
            driver.quit();

        } catch (Exception e) {
            System.out.println("incorrect login");
            driver.quit();
            System.exit(-1);
        }
    }

    private static void writeToText(String buffer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("transcript.txt"));
        writer.write(buffer);
        writer.close();
    }

    public CourseRecommendations getCourseRecommendations(){
        return courseRecommendations;
    }
}