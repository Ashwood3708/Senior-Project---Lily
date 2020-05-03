/******
 * Login.js file is a file with a function that is used to update the home page with student info
 ******/

function write(usersHomePageData, x) {

        let head = "<!DOCTYPE html>\n" +
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

        let table = "<body>\n" +
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
            "            <td colspan='2'><b>Major GPA</b></td>\n" +
            "        </tr>\n" +
            "        <tr class='header'>\n" +
            "            <td>" + usersHomePageData[0] + "</td>\n" +
            "            <td colspan='2'>" + usersHomePageData[1] + "</td>\n" +
            "        </tr>\n" +
            "        <tr class='center'>\n" +
            "            <td colspan='3'><b>Senior Audit Form</b></td>\n" +
            "        </tr>\n" +
            "        <tr class='header'>\n" +
            "            <td><a href='files/senior_audit.pdf' target='_blank'>View</a></td>\n" +
            "            <td colspan='2'><a href='files/senior_audit.pdf' download='files/senior_audit.pdf' class='float-right'>Download</a></td>\n" +
            "        </tr>\n" +
            "        <tr class='center'>\n" +
            "            <td colspan='3'><b>Recommendations</b></td>\n" +
            "        </tr>\n" +
            "        <tr class='recs'>\n" +
            "            <td><b>Completed</b></td>\n" +
            "            <td><b>Currently Taken</b></td>\n" +
            "            <td><b>Recommended</b></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td class='recs'>\n" + usersHomePageData[2] + "</td>\n" +
            "            <td class='recs'>\n" + usersHomePageData[3] + "</td>\n" +
            "            <td class='recs'>\n" + x + "</td>\n" +
            "        </tr>\n" +
            "        <tr class='header'>\n" +
            "            <td colspan='3'><img src='files/pre-req.png' alt='Pre Req' class='center'/></td>\n" +
            "        </tr>\n" +
            "        <tr class='header'>\n" +
            "            <td colspan='3'><a href='curriculum.html' target='_blank'>Click Here To View Interactive Curriculum Guide</a></td>\n" +
            "        </tr>\n" +
            "        <tr class='header'>\n" +
            "            <td colspan='3'><a href='https://www.ncat.edu/' target='_blank'>Browse Here For More Info</a></td>\n" +
            "        </tr>\n" +
            "        </tbody>\n" +
            "    </table>\n" +
            "</div>\n" +
            "<div class='table-info'>\n" +
            "    <form name='form' action='index.jsp' method='post'>\n" +
            "\n" +
            "        <input type='submit' value='SIGNOUT'>\n" +
            "\n" +
            "    </form>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";
        document.write(head + table);
    }
