import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        pdfParser g = new pdfParser();
        g.read();
        String print = g.toString();
        System.out.print(print);
    }
}
