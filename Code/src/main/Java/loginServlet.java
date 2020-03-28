import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet{

    public  void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    public  void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        response.setContentType("text/html");
        String banner = request.getParameter("banner").trim();
        String pin = request.getParameter("pin").trim();
        PrintWriter writer = response.getWriter();
        RequestDispatcher rdObj = null;
        Boolean check = null;
        ArrayList<String> file = null;
        //try {
        Selenium selenium = new Selenium();
        Student student = new Student();
        check = selenium.run(banner, pin);
        //}catch(Exception e){
        //    e.printStackTrace();
        //}

        if(check.equals(true)){
            file = selenium.checkText();
            if(file != null) {
                String head = "<!DOCTYPE html>\n" +
                        "<html lang='en'>\n" +
                        "<head>\n" +
                        "    <link rel='shortcut icon' href='files/Logo.PNG' type='image/x-icon' />\n" +
                        "    <title>Lily | Home</title>\n" +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1'>\n" +
                        "    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" +
                        "    <script type='application/x-javascript'>\n" +
                        "        addEventListener('load', function() {\n" +
                        "            setTimeout(hideURLbar, 0);\n" +
                        "        }, false);\n" +
                        "        function hideURLbar(){\n" +
                        "            window.scrollTo(0,1); }\n" +
                        "    </script>\n" +
                        "    <link href='css/home.css' rel='stylesheet' type='text/css' media='all'>\n" +
                        "\n" +
                        "</head>";

                String table = "<body onload='displayInfo()'>\n" +
                        "<div class='full-nav'>\n" +
                        "    <a class='left-nav'>INFORMATION</a>\n" +
                        "    <div class='right-nav'>\n" +
                        "        <a><img src='favicon_io/apple-touch-icon.png' alt='Lily Logo' width='125' height='125'> </a>\n" +
                        "    </div>\n" +
                        "\n" +
                        "</div>\n" +
                        "<div>\n" +
                        "    <table class='table-info' cellpadding='10' id='table'>\n" +
                        "        <tbody>\n" +
                        "        <tr class='header'>\n" +
                        "            <td><b>Cumulative GPA</b></td>\n" +
                        "            <td><b>Major GPA</b></td>\n" +
                        "        </tr>\n" +
                        "        <tr class='header'>\n" +
                        "            <td>" + file.get(0) + "</td>\n" +
                        "            <td>" + file.get(1) + "</td>\n" +
                        "        </tr>\n" +
                        "        <tr class='header'>\n" +
                        "            <td colspan='2'><b>Senior Audit Form</b></td>\n" +
                        "        </tr>\n" +
                        "        <tr class='header'>\n" +
                        "            <td><a href='files/senior_audit.pdf' target='_blank'>View</a></td>\n" +
                        "            <td><a href='files/senior_audit.pdf' download='files/senior_audit.pdf' class='float-right'>Download</a></td>\n" +
                        "        </tr>\n" +
                        "        <tr class='header'>\n" +
                        "            <td colspan='2'><b>Recommendations</b></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td>" + student.getList() + "</td>\n" +
                        "        </tr>\n" +
                        "       <!-- <tr class='header'>\n" +
                        "            <td colspan='2'><b>Pre-Requisitions</b></td>\n" +
                        "        </tr> -->\n" +
                        "        <tr class='header'>\n" +
                        "            <td colspan='2'><img src='files/pre-req.png' alt='Pre Req' class='center'/></object></td>\n" +
                        "        </tr>\n" +
                        "        <tr class='header'>\n" +
                        "            <td colspan='2'><a href='curriculum.html' target='_blank'>Click Here To View Curriculum Guide</a></td>\n" +
                        "        </tr>\n" +
                        "        <tr class='header'>\n" +
                        "            <td colspan='2'><a href='https://www.ncat.edu/' target='_blank'>Browse Here For More Info</a></td>\n" +
                        "        </tr>\n" +
                        "        </tbody>\n" +
                        "    </table>\n" +
                        "</div>\n" +
                        "    <div class='table-info'>\n" +
                        "        <form name='form' action='/index.jsp' method='post'>\n" +
                        "\n" +
                        "            <input type='submit' value='SIGNOUT'>\n" +
                        "\n" +
                        "        </form>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>";
                writer.write(head + table);
            }else{
                writer.write("<p id='errMsg' style='color: red; font-size: larger;'>User Must Not Be Computer Science!</p>");
                rdObj = request.getRequestDispatcher("/index.jsp");
                rdObj.include(request, response);
            }
        }else{
            writer.write("<p id='errMsg' style='color: red; font-size: larger;'>User Does Not Exist!</p>");
            rdObj = request.getRequestDispatcher("/index.jsp");
            rdObj.include(request, response);
        }
    }
}

