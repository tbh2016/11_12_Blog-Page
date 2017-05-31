<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title><c:out value="${staticSite.siteData.title}"/></title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <style> 
/*
            #pictureBox {
                width: 350px;
                height: 200px;
                border: 1px solid black;
                margin: 25px;
                border-radius: 5px;
            }
            */#textBox{
                width: 80%;
                height: 500px;
                margin: 25px;
                border-radius: 5px;

                left: 100px;
            }
            img{
                display: block;
                margin-left: auto;
                margin-right: auto;
            }
        </style>
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid">    
                <ul class="nav navbar-nav">
                    <c:forEach items="${staticSite.links}" var="link">
                        <c:choose>
                            <c:when test="${link.siteId == staticSite.siteData.siteDataId}">
                                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}${link.linkReference}?siteId=${link.siteId}">${link.linkName}</a></li>
                                </c:when>
                                <c:otherwise>
                                <li role="presentation"><a href="${pageContext.request.contextPath}${link.linkReference}?siteId=${link.siteId}">${link.linkName}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <sec:authorize access="hasRole('ROLE_MANAGER')">
                        <li role="presentation" ><a href="${pageContext.request.contextPath}/admin">Admin Portal</a></li>
                        </sec:authorize>
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
            <h1 id="pageTitle"><c:out value="${staticSite.siteData.title}"/></h1>
            <hr/>

            <br>

            <div class="col-md-12" id="textBox">

                ${staticSite.siteData.content}

            </div>
        </div>

        <footer class="panel-footer">
            <div> <c:out value="${staticSite.contact.address}"/> </div>
            <div> <c:out value="${staticSite.contact.contactEmail}"/> </div>
            <div> <c:out value="${staticSite.contact.contactPhone}"/> </div>
        </footer>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
