<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>

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
                    <li><input type="search" name="search" placeholder="Search.."></li> 
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
            <h1>Pat's Party Pavilion</h1>
            <hr/>
            <div>
                <h1>An error has occurred...</h1>
                <h3>${errorMessage}</h3>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    </body>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


</html>