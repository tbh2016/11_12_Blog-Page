<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Blog</title>
        <!-- Bootstrap core CSS -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
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
            <h1 id="pageTitle">Admin Blog</h1>
            <hr/>
            <div class="col-md-12">




            </div>
            <br><br> <br>

            <h3>Create A New Blog Post</h3>
            <div class="row">
                <div class="col-md-3">
                    <a href="${pageContext.request.contextPath}/admin/displayAddBlogPost">
                        <input type="button" class="btn btn-default" value="Add Blog Post" onclick=/>
                    </a>
                </div>
            </div> 
            <br>
            <h3>All Blog Pages</h3>
            <table id="allBlogPagesList" class="table table-hover">
                <tr>
                    <th width="70%">Title</th>
                    <th width="15%">Edit</th>
                    <th width="15%">Delete</th>
                </tr>
                <c:forEach var="currentBlogPage" items="${blogPages}">
                    <tr>
                        <td>
                            <c:out value="${currentBlogPage.title}"/>
                        </td>
                        <td>
                            <a href="displayEditBlogPage?siteDataId=${currentBlogPage.siteDataId}">
                                Edit
                            </a>
                        </td>
                        <td>
                            <a id="del" href="deleteBlogPost?siteDataId=${currentBlogPage.siteDataId}">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>

            </table>            
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

