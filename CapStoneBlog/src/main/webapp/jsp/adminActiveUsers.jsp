<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
            <div class="row">
                <div class="col-md-12">
                    <h1>Active Users</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>
                                    User Name
                                </th>
                                <th>
                                    First Name
                                </th>
                                <th>
                                    Last Name
                                </th>
                                <th>
                                    Options
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>
                                        <c:out value="${user.userName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${user.firstName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${user.lastName}"/>
                                    </td>
                                    <td>
                                        <a id="del" href="${pageContext.request.contextPath}/user/admin/deactivateUser?userId=${user.userId}">
                                            Delete
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script>
        $(document).ready(function() {
            $("#del").on("click", function() {
                alert("Are you sure you want to delete?");
            });
        });
        </script>
        
    </body>
</html>
