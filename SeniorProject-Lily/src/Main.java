public class Main {
        public static void main(String[] args)  {

            String[] user = {"950350071", "950373928", "950363140", "",""};
            String[] pass = {"255175", "123456", "302330", "",""};
            int x = 0;

            Selenium web = new Selenium(user[x], pass[x]);
            System.out.println(web.getCourseRecommendations().toString());
            //System.out.println("Taken Classes: \n"+ web.getCourseRecommendations().getTakenClasses());
            //System.out.println("Current Classes: \n"+ web.getCourseRecommendations().getCurrentClasses());
            //System.out.println("Recommended Classes List: \n"+ web.getCourseRecommendations().getFinalList());


        }
    }
