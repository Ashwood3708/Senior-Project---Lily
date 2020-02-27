import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Selenium {
    private WebDriver driver;

private int finalTime = 0;


    //still need error case
    public Selenium(String x, String y) {
        run(x,y);
        pdfParser g = new pdfParser();
        try {
            g.readTxt("transcript.txt");
            System.out.print(g.toString());
        }catch (FileNotFoundException e){
            System.out.println("file not found");
            System.exit(-1);
        }

    }

    public void run(String x, String y)  {
try {
        //access site through chrome.exe
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://ssbprod-ncat.uncecs.edu/pls/NCATPROD/twbkwbis.P_WWWLogin");

        //login
        WebElement studentId = driver.findElement(By.name("sid"));
        WebElement pin= driver.findElement(By.name("PIN"));
        studentId.sendKeys(x);
        pin.sendKeys(y);
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
}catch (Exception e){
    System.out.println("incorrect login");
    driver.quit();
    System.exit(-1);
}
    }

    public static void writeToText(String buffer) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("transcript.txt"));
        writer.write(buffer);
        writer.close();
    }
}