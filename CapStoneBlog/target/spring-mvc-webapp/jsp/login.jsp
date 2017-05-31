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
            #hiddenLoginBox{
                width: 200px;
                height: 150px;
                margin: 25px;

                margin: auto;
            }
            #label{
                float:left;
                font-weight: bold;
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
                            <li><a href="${pageContext.request.contextPath}/j_spring_security_logout" id="logoutLink"> <span class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                            </c:otherwise>
                        </c:choose>
                </ul>


            </div>
        </nav>

        <div class="container">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name == null}">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 id="pageTitle">Login</h1>
                            <c:if test="${param.login_error == 1}">
                                <h3>Wrong id or password!</h3>
                            </c:if>
                                <h3><c:out value="${errorMessage}"/></h3>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <br>

                        <form class="form-horizontal" role="form" method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
                            <div class="form-group">
                                <label for="j_username" class="col-md-4 control-label"><b>Username</b></label>
                                <div class="col-md-6">
                                    <input type="text" value="${userName}" class="form-control"
                                           placeholder="Username" name="j_username" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-group">

                                <label for="j_password" class="col-md-4 control-label"><b>Password</b></label> 
                                <div class="col-md-6">
                                    <input type="password" class="form-control" placeholder="Password" name="j_password" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-6">
                                    <input type="submit" class="btn btn-default" value="Sign In"/>
                                    <a href="${pageContext.request.contextPath}/user/displayUserForm">
                                        <input type="button" class="btn btn-default" value="Create Account"/>
                                    </a>
                                </div>
                            </div>

                        </form>

                    </div>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/j_spring_security_logout" id="logoutLink">
                        <input type="button" class="btn btn-default" value="Logout"/> 
                    </a>
                </c:otherwise>
            </c:choose>


        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

