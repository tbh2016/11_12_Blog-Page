<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <style> 

            .button {
                background-color: #4CAF50;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }

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
            <h1 id="pageTitle">Edit Home Page</h1>
            <hr/>

            <br><br> <br>

            <div class="row">
                <sf:form class="form-horizontal" role="form" 
                         action="${pageContext.request.contextPath}/admin/updateHomePage" modelAttribute="siteData" method="POST" >
                    <sf:hidden path="siteDataId"/>
                    <sf:hidden path="user.userId"/>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="homepage-title">Home Page Title</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input id="homepage-title" class="form-control" path="title"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="homepage-content">Home Page Content</label>
                        </div>
                        <div class="col-md-4">
                            <sf:textarea id="homepage-content" class="form-control" path="content"/>
                        </div>

                    </div>

                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1">
                           
                        </div>
                        <div class="col-md-4">
                            <input type="submit" class="btn btn-default " value="Update Home Page Info">
                        </div>
                    </div>
                </sf:form>    

            </div>

            <div>
                &nbsp;
                <hr/>
            </div>

        </div>

        <footer id="panel-footer">

            <div> ${homeSite.contact.address} </div>
            <div>${homeSite.contact.contactEmail}</div>
            <div> ${homeSite.contact.contactPhone} </div>
        </footer>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
