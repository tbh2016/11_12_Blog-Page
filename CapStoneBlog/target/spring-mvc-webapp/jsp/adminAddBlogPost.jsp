<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Add Blog Post</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <script>tinymce.init({
                selector: '#mytextarea',
                theme: 'modern',
                width: 600,
                height: 300,
                plugins: [
                    'advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker',
                    'searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking',
                    'save table contextmenu directionality emoticons template paste textcolor',
                    'insertdatetime media table contextmenu paste jbimages save'
                ],
                toolbar: ' save | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | print preview media fullpage | forecolor backcolor emoticons | link image jbimages',
                relative_urls: false

            });
        </script>
        <style> 

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
            <h1 id="pageTitle">Add Blog Post</h1>
            <hr/>
            <div class="col-md-12">

            </div>
            <br><br> <br>
            <div class="col-lg-12">
                <form class="form-horizontal" method="post" action="addBlogPost">
                    <div class="row">
                        <div class="col-lg-8">
                            <textarea id="mytextarea" name="blog-post"></textarea>
                        </div>
                        <div class="col-lg-4">
                            <div class="row">
                                <div class="col-lg-2">
                                    <label class="control-label" for="blog-title">Blog Title</label>
                                </div>
                                <div class="col-lg-10">
                                    <input id="blog-title" class="form-control" name="blog-title"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-2">
                                    <label class="control-label" for="blog-title">Blog Category</label>
                                </div>
                                <div class="col-lg-10">
                                    <select class="form-control" name="category">
                                        <option value="0">Select a Category if Applicable</option>
                                        <c:forEach var="currentCatergory" items="${catergories}">
                                            <option value="${currentCatergory.categoryId}">${currentCatergory.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-2">
                                    <label class="control-label" for="start-date">Start Date</label>
                                </div>
                                <div class="col-lg-10">
                                    <input type="date" id="start-date" class="form-control" path="startDate"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-lg-2">
                                    <label class="control-label" for="end-date">End Date</label>
                                </div>
                                <div class="col-lg-10">
                                    <input type="date" id="end-date" class="form-control" path="endDate"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-10 col-md-offset-2">
                                    <input type="submit" class="btn btn-default" value="Create Blog Post" onclick="return confirm('Proceed and add new blog post?')" />
                                    <input type="submit" class="btn btn-default" value="Cancel" onclick="${pageContext.request.contextPath} / admin"/>
                                </div>
                            </div>
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
