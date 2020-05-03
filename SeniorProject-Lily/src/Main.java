import java.io.FileNotFoundException;

public class Main {
        public static void main(String[] args) throws FileNotFoundException {

            //TODO add user & pass for aggie access
            //https://ssbprod-ncat.uncecs.edu/pls/NCATPROD/twbkwbis.P_WWWLogin

            String user = " ";
            String pass = " ";
            Selenium web = new Selenium(user, pass);
            System.out.println(web.getCourseRecommendations().toString());


            //specialized info
            //System.out.println("Taken Classes: \n"+ web.getCourseRecommendations().getTakenClasses());
            //System.out.println("Current Classes: \n"+ web.getCourseRecommendations().getCurrentClasses());
            //System.out.println("Recommended Classes List: \n"+ web.getCourseRecommendations().getFinalList());


        }
    }
