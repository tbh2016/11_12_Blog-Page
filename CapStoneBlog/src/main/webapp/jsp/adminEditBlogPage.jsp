<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Blog Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <script>tinymce.init({
                selector: '#blog-content',
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
            <h1 id="pageTitle">Edit Blog Post</h1>
            <hr/>
            <div class="col-md-12">

            </div>
            <br>
            <br> 
            <br>
            <h2>Edit The Details For The Blog Page </h2>

            <div class="row">
                <sf:form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/updateBlogPage" modelAttribute="blogSite" method="POST" >
                    <sf:hidden path="siteData.siteDataId"/>
                    <sf:hidden path="siteData.pageType"/>
                    <sf:hidden path="siteData.user.userId"/>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="blog-title">Blog Title</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input id="blog-title" class="form-control" path="siteData.title"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="blog-title">Blog Category</label>
                        </div>
                        <div class="col-md-4">
                            <sf:select class="form-control" name="category" path="category.categoryId">
                                <sf:option value="0">Select a Category if Applicable</sf:option>
                                <c:forEach var="currentCategory" items="${categories}">
                                    <sf:option value="${currentCategory.categoryId}">${currentCategory.categoryName}</sf:option>
                                </c:forEach>
                            </sf:select>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="blog-content">Blog Content</label>
                        </div>
                        <div class="col-md-4">
                            <sf:textarea id="blog-content" class="form-control" path="siteData.content"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="start-date">Start Date</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input type="date" id="start-date" class="form-control" path="siteData.startDate"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="end-date">End Date</label>
                        </div>  
                        <div class="col-md-4">
                            <sf:input type="date" id="end-date" class="form-control" path="siteData.endDate"/>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-offset-4">
                        <input type="submit" class="btn btn-default " value="Update Blog Info">
                        <input type="submit" class="btn btn-default" value="Cancel" onclick="${pageContext.request.contextPath} / admin"/>
                        <a href="${pageContext.request.contextPath}/admin/deleteBlogPost?blogId=${siteData.siteDataId}">
                            <input type="button" class="btn btn-default" value="Delete" onclick="${pageContext.request.contextPath} / admin"/>
                        </a>
                    </div>
                </sf:form>    


            </div>
            <hr/>

            <center><h3>Comments</h3></center>
                <c:forEach var="currentComment" items="${blogSite.comments}">
                <div class="row">
                    <div class="panel panel-white post panel-shadow">
                        <div class="post-heading">
                            <div class="title h4">
                                <c:out value="${currentComment.title}"/>
                            </div>
                            <div class="title h5">
                                <b>
                                    <c:out value="${currentComment.user.userName}"/>
                                </b>
                            </div>
                            <h6 class="text-muted time">
                                <c:out value="${currentComment.commentDate}"/>
                            </h6>
                        </div>
                        <div> 
                            <p>
                                <c:out value="${currentComment.content}"/>
                            </p>
                        </div>
                        <div class="col-md-offset-8">
                            <a href="${pageContext.request.contextPath}/admin/displayEditCommentPage?commentId=${currentComment.commentId}">
                                <input type="button" class="btn btn-warning " value="Edit Comment">
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/deleteComment?commentId=${currentComment.commentId}">
                                <input type="button" class="btn btn-danger" value="Delete Comment" onclick="${pageContext.request.contextPath} / admin"/>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>



        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

