<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Contact Info</title>
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
            <h1 id="pageTitle">Edit Contact Info</h1>
            <hr/>

            <div class="row">
                <sf:form class="form-horizontal" role="form" 
                         action="${pageContext.request.contextPath}/admin/updateContact" modelAttribute="contact" method="POST" >
                    <sf:hidden path="contactInfoId"/>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="contact-address">Contact Address</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input id="contact-address" class="form-control" path="address"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="contact-email">Contact Email</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input id="contact-email" class="form-control" path="contactEmail"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="contact-phone">Contact Phone</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input id="contact-phone" class="form-control" path="contactPhone"/>
                        </div>
                    </div>   
                    <br>
                    <div class="row">
                        <div class="col-md-offset-4 col-md-4">
                            <input type="submit" class="btn btn-default " value="Update Contact Info">
                            <a href="${pageContext.request.contextPath}/admin">
                                <input type="button" class="btn btn-default" value="Cancel" />
                            </a>
                        </div>
                    </div>
                </sf:form>    
            </div>



        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
