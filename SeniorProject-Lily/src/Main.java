public class Main {
    public static void main(String[] args)  {

        String[] user = {"950350071", "950373928", "950363140", "950340232",""};
        String[] pass = {"255175", "123456", "302330", "124469",""};
        int x = 2;

        Selenium run = new Selenium(user[x], pass[x]);
        System.out.println(run.getCourseRecommendations().toString());
    }
}
