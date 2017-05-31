<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Static Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <script>tinymce.init({
                selector: '#staticPage-content',
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
            <h1 id="pageTitle">Edit Static Page</h1>
            <hr/>
            <div class="col-md-12">




            </div>
            <br><br> <br>

            <h2>Edit The Details For The Static Page </h2>
            <sf:form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/updateStaticPage" modelAttribute="pageData" method="POST">
                <sf:hidden path="siteDataId"/>
                <sf:hidden path="user.userId"/>
                <sf:hidden path="pageType"/>
                <div class="row">
                    <div class="col-md-3 col-md-offset-1 text-center">
                        <label class="control-label" for="staticPage-title">Page Title</label>
                    </div>
                    <div class="col-md-4">
                        <sf:input id="staticPage-title" class="form-control" path="title"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-md-offset-1 text-center">
                        <label class="control-label" for="staticPage-title">Link Name</label>
                    </div>
                    <div class="col-md-4">
                        <input id="staticPage-link-name" class="form-control" name="link-name" value="${link.linkName}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-md-offset-1 text-center">
                        <label class="control-label" for="staticPage-content">Page Content</label>
                    </div>
                    <div class="col-md-4">
                        <sf:textarea id="staticPage-content" class="form-control" path="content"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-md-offset-1 text-center">
                        <label class="control-label" for="staticPage-startDate">Start Date</label>
                    </div>
                    <div class="col-md-4">
                        <sf:input type="date" id="staticPage-startDate" class="form-control" path="startDate"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-md-offset-1 text-center">
                        <label class="control-label" for="staticPage-endDate">End Date</label>
                    </div>
                    <div class="col-md-4">
                        <sf:input type="date" id="staticPage-endDate" class="form-control" path="endDate"/>
                    </div>
                </div>
                <div class="col-md-offset-4">
                    <input type="submit" class="btn btn-default " value="Update Page Info">
                    <input type="submit" class="btn btn-default" value="Cancel" onclick="${pageContext.request.contextPath} / admin"/>
                </div>
            </sf:form>

            <hr>


        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
