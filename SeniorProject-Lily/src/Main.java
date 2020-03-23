import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        String[] user = {"950350071", "950373928", "950363140", "950340232"};

        String[] pass = {"255175", "123456", "302330", "124469"};
        int x = 3;
        Selenium run = new Selenium(user[x], pass[x]);

    }
}
