<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Static Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <style> 

            input[type=text], input[type=password] {
                width: 100%;

                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
                float:left;
            }
            button {
                background-color: #000;
                border: none;
                color: white;
                margin: 8px 0;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                cursor: pointer;
                float:left;
            }
            #loginBox {
                width: 350px;
                height: 180px;
                border: 1px solid black;
                margin: 25px;
            }
            #textBox {
                width: 500px;
                height: 200px;
                border: 1px solid black;
                margin: 25px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid">
                <div class="navbar-header">

                </div>
                <ul class="nav navbar-nav">

                    <c:forEach items="${navLinks}" var="link">

                        <c:choose>
                            <c:when test="${link.siteId == homeSite.siteData.siteDataId}">
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}${link.linkReference}?siteId=${link.siteId}">${link.linkName}</a></li>
                                </c:when>
                                <c:otherwise>
                                <li role="presentation"><a href="${pageContext.request.contextPath}${link.linkReference}?siteId=${link.siteId}">${link.linkName}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </ul>        

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <form action="${pageContext.request.contextPath}/Page/searchHashTags" method="GET" onsubmit="${pageContext.request.contextPath} /">
                            <input type="search" name="search" placeholder="Search Hashtags">                        
                                <!--<input type="submit" name="search-button" class="btn btn-default" onsubmit="${pageContext.request.contextPath} /">-->
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li><a href="${pageContext.request.contextPath}/login" id="loginLink"> <span class="glyphicon glyphicon-log-in"></span> Login </a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/j_spring_security_logout" id="logoutLink"> <span class="glyphicon glyphicon-log-out"></span> Logout ${pageContext.request.userPrincipal.name} </a></li>
                            </c:otherwise>
                        </c:choose>

                </ul>

            </div>
        </nav>

        <div class="container">
            <br><br> <br>
            <div class="row">
                <div class="col-md-12">
                    <h1>Create New User Account</h1>
                    <c:if test="${param.login_error == 1}">
                        <h3>Wrong id or password!</h3>
                    </c:if>
                </div>
            </div>
            <div class="col-md-12">
                <br>

                <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/user/addUser">
                    <div class="form-group">
                        <label for="userName" class="col-md-4 control-label"><b>Username</b></label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder="Username" name="userName" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="firstName" class="col-md-4 control-label"><b>First Name</b></label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder="Last Name" name="firstName" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="lastName" class="col-md-4 control-label"><b>Last Name</b></label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder="First Name" name="lastName" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="password" class="col-md-4 control-label"><b>Password</b></label> 
                        <div class="col-md-6">
                            <input type="password" class="form-control" placeholder="Password" name="password" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-6">
                            <input type="submit" class="btn btn-default" value="Create Account"/>
                            <a href ="${pageContext.request.contextPath}/">
                                <input type="button" class="btn btn-default" value="Cancel"/>
                            </a>
                        </div>
                    </div>

                </form>

            </div>

            <div>
                &nbsp;
                <hr/>
            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
