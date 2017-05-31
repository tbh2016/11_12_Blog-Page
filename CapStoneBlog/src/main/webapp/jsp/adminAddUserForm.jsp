<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Create New Admin Accountgit </title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <style> 

            .panel-footer {
                background: #101010;
                color: white;
                text-align: center;
                position: relative;
                height: 100px;
                clear:both;
                padding-top:10px;
                margin-top: -150px;
                position: fixed;
                bottom: 0;
                width: 100%;
            }

        </style>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid">    
                <ul class="nav navbar-nav">
                    <%@ include file="adminLinkHeader.jsp" %>
                </ul> 
                <ul class="nav navbar-nav navbar-right">
                    <li><input type="search" name="search" placeholder="Search.."></li> 
                        <c:choose>
                            <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li><a href="${pageContext.request.contextPath}/login" id="loginLink"> <span class="glyphicon glyphicon-log-in"></span> Login </a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/j_spring_security_logout" id="logoutLink"> <span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                            </c:otherwise>
                        </c:choose>
                </ul>
            </div>
        </nav>

        <div class="container">
            <br><br> <br>
            <div class="row">
                <div class="col-md-12">
                    <h1>Create New Admin Account</h1>
                    <c:if test="${param.login_error == 1}">
                        <h3>Wrong id or password!</h3>
                    </c:if>
                </div>
            </div>
            <div class="col-md-12">
                <br>

                <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/user/admin/addUser">
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
                        <label for="manager" class="col-md-4 control-label"><b>Manager</b></label>
                        <div class="col-md-6">
                            <input type="checkbox" name="isManager" checked/>
                        </div>
                    </div>
                    <div class="form-group">    
                        <label for="manager" class="col-md-4 control-label"><b>Owner</b></label>
                        <div class="col-md-6">
                            <input type="checkbox" name="isAdmin"/>
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

        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
