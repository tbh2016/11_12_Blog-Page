<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Categories</title>
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

                <%@ include file="adminLinkHeader.jsp" %>
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
            <h1 id="pageTitle">Edit Categories</h1>
            <hr/>
            <div class="col-md-12">

                <br><br> <br>

                <div class="row">
                    <h3>Create A New Category</h3>
                    <form class="form-horizontal" role="form" method="POST" action="addCategory">
                        <div class="form-group">
                            <label for="categoryName" class="col-md-1 control-label">Category Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="categoryName" placeholder="Category"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="categoryDescription" class="col-md-1 control-label">Description:</label>
                            <div class="col-md-8">
                                <textarea class="form-control" name="description" rows="3" placeholder="Write a description of the Category here"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Category"/>
                            </div>
                        </div>
                    </form>


                    <h3>All Categories</h3>
                    <table id="allBlogPagesList" class="table table-hover">
                        <tr>
                            <th width="15%">Category</th>
                            <th width="70%">Description</th>
                            <th width="15%">Delete</th>
                        </tr>
                        <c:forEach var="currentCategory" items="${categories}">
                            <tr>
                                <td>
                                    <c:out value="${currentCategory.categoryName}"/>
                                </td>
                                <td>
                                    <c:out value="${currentCategory.description}"/>
                                </td>
                                <td>
                                    <a href="deleteCategory?categoryId=${currentCategory.categoryId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>            
                </div>
            </div>

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

