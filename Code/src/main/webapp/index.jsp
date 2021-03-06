<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="shortcut icon" href="files/Logo.PNG" type="image/x-icon" />
    <title>Lily | Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript">
        addEventListener("load", function() {
            setTimeout(hideURLbar, 0);
        }, false);
        function hideURLbar(){
            window.scrollTo(0,1); }
    </script>
    <style><%@include file="/WEB-INF/css/login.css"%></style>
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#bannerID, #userPIN').click(function() {
                $("#errMsg").hide();
            });
        });
    </script>
</head>

<body>
<div class="login-layout wrapper">
    <img src="files/Logo.PNG" alt="Lily Logo" class="center"/>
    <div class="login-aggie">
        <div class="login-content">
            <p>Log into this website with your Aggie Access credentials.</p>
            <!-- Login form that calls a Java Servlet so login information can be retrieved -->
            <form name="form" method="post" action="loginServlet">
                <input class="text banner" id="bannerID" type="text" name="banner" placeholder="Banner ID">

                <input class="text" id="userPIN" type="password" name="pin" placeholder="PIN">

                <input type="submit" value="Login" id="btn">

            </form>
            <p>By clicking submit you are aware and you accept that your login credentials will be used to gain access
                to Aggie Access to retrieve your transcript so algorithms can be performed to calculate certain
                information. Note that none of your personal information will be stored or used.</p>
        </div>
    </div>
</div>

</body>

</html>

