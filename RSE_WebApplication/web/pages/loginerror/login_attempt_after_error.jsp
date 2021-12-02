
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@page import="utils.*" %>
    <%@ page import="constants.Constants" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Chat</title>
<!--        Link the Bootstrap (from twitter) CSS framework in order to use its classes-->
        <link rel="stylesheet" href="common/bootstrap.min.css"/>
<!--        Link jQuery JavaScript library in order to use the $ (jQuery) method-->
<!--        <script src="script/jquery-2.0.3.min.js"></script>-->
<!--        and\or any other scripts you might need to operate the JSP file behind the scene once it arrives to the client-->
    </head>
    <body>
        <div class="container">
            <% String usernameFromSession = SessionUtils.getUsername(request);%>
            <% String usernameFromParameter = request.getParameter(Constants.USERNAME) != null ? request.getParameter(Constants.USERNAME) : "";%>
            <% if (usernameFromSession == null) {%>
            <h1 style="font-size: 50px">Welcome to Rizpa v3.0</h1>
            <br/>
            <h2>Please enter your user name:</h2>
            <form id="loginForm" method="GET" action="loginUserToRizpa">
                <h4>Admin
                    <input type="checkbox" name="adminCheckBox">
                </h4>
                <input type="text" name="<%=Constants.USERNAME%>" value="<%=usernameFromParameter%>"/>
                <input type="submit" value="Login"/>
            </form>
            <% Object errorMessage = request.getAttribute(Constants.USER_NAME_ERROR);%>
            <% if (errorMessage != null) {%>
            <span class="bg-danger" style="color:red;"><%=errorMessage%></span>
            <% } %>
            <% } else {%>
            <h1>You already loged in with username: <%=usernameFromSession%></h1>
                <br>
                <a href="/index.html">Click here to enter Rizpa III login page</a>
            <% }%>
        </div>
    </body>
</html>