/*****
 * Selenium.java using web driver code, Selenium, to navigate to Aggie Access and log into with credentials that users
 * logged in on the Lily's login page. Once gained access to Aggie Access, user transcript is found and downloaded.
 * Selenium.java has a method, checkTranscript() that calls pdfParser.java that checks to see if the transcript
 * downloaded can be parsed.
 *****/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Selenium {
    private WebDriver driver;
    private CourseRecommendations courseRecommendations;
    private int finalTime = 0;


    /**
     * @return an array list of items to be updated on Lily's home page
     * items will only be calculated and returned if the transcript file was able to be parsed
     */
    public ArrayList<String> checkTranscript() {
        ArrayList<String> display = new ArrayList<>();
        pdfParser g = new pdfParser();
        try {
            if(g.readTxt("transcript.txt")){
                // splits string by white space (subject to change when list gets added)
                String[] s = g.getUserInfo().split(" ");
                courseRecommendations = new CourseRecommendations(g.getPerson());
                //System.out.print(courseRecommendations.toString());
                display.add(s[0]);
                display.add(s[1]);
                // gets the current classes, previously taken classes and recommended classes.
                display.add(courseRecommendations.getTakenClasses());
                display.add(courseRecommendations.getCurrentClasses());
                display.add(courseRecommendations.getFinalList());
                return display;
            }else{
                return null;
            }
        }catch (FileNotFoundException e){
            System.out.println("file not found");
            //System.exit(-1);
            return null;
        }

    }

    /****
     *
     * @param loginBanner is users banner ID
     * @param loginPin is users login PIN
     * @return true or false to determine whether login was successful or not
     * @throws NoSuchMethodError
     */
    public Boolean run(String loginBanner, String loginPin) throws NoSuchMethodError {
        try {
            //access site through chrome.exe
            //System.setProperty("webdriver.chrome.driver", "chromedriver");
            //TODO: Change path to match your path
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");

            //add options
            driver = new ChromeDriver(options);
            driver.navigate().to("https://ssbprod-ncat.uncecs.edu/pls/NCATPROD/twbkwbis.P_WWWLogin");

            //login
            WebElement studentId = driver.findElement(By.name("sid"));
            WebElement pin= driver.findElement(By.name("PIN"));
            studentId.sendKeys(loginBanner);
            pin.sendKeys(loginPin);
            pin.submit();

            //locate transcript
            //--click Student
            String hrefLink= driver.findElement(By.xpath("//a[@href='/pls/NCATPROD/twbkwbis.P_GenMenu?name=bmenu.P_StuMainMnu']")).getAttribute("href");
            driver.get(hrefLink);


            //--click Student Records
            hrefLink= driver.findElement(By.xpath("//a[@href='/pls/NCATPROD/twbkwbis.P_GenMenu?name=bmenu.P_AdminMnu']")).getAttribute("href");
            driver.get(hrefLink);


            //--click Transcript
            hrefLink= driver.findElement(By.xpath("//a[@href='/pls/NCATPROD/bwskotrn.P_ViewTermTran']")).getAttribute("href");
            driver.get(hrefLink);
            //Thread.sleep(1000);  // Let the user actually see something!

            //--click submit btn
            driver.findElement(By.cssSelector("input[type='submit'][value='Submit']")).click();
            Thread.sleep(finalTime);  //should see transcript now

            //Scrape transcript text
            String str = driver.findElement(By.className("pagebodydiv")).getText();
            writeToText(str);
            driver.quit();
            return true;
        }catch (Exception e){
            System.out.println("incorrect login");
            System.out.println(e);
            driver.quit();
            //System.exit(-1);
            return false;
        }
    }

    public static void writeToText(String buffer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("transcript.txt"));
        writer.write(buffer);
        writer.close();
    }
}