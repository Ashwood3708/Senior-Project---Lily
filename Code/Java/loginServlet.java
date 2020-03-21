import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class loginServlet extends HttpServlet{
    protected  void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String banner = request.getParameter("bannerID");
        String pin = request.getParameter("userPIN");

        PrintWriter writer = response.getWriter();

        Selenium selenium = new Selenium();
        Boolean check = selenium.run(banner, pin);
        Boolean file = selenium.Selenium();
        if(check.equals(true) && file.equals(true)){
            //response.sendRedirect("home.html");
            System.out.println(banner + " " + pin);
        }else{
            writer.write("<p id='check' style='color: red;'>Invalid</p>");
            System.out.println("Eh-rare");
        }
    }
}

