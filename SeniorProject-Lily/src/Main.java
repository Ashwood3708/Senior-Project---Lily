import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        //Selenium run = new Selenium();
        pdfParser g = new pdfParser();
        g.readTxt("transcript.txt");

        String print = g.toString();
        System.out.print(print);


    }
}
