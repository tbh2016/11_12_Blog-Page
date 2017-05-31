<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Add Static Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <script>tinymce.init({
                selector: '#static-content',
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
            <h1 id="pageTitle">Add Static Page</h1>
            <hr/>
            <div class="col-md-12">

            </div>
            <br><br> <br>
            <form action="addStaticPage" method="POST">
                <div class="row">
                    <div class="col-md-1">
                        <label for="static-title" class="control-label">Title: </label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name="static-title" id="static-title"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1">
                        <label for="static-name" class="control-label">Display Link As: </label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name="static-link-name" id="static-name"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-lg-1">
                        <label class="control-label" for="start-date">Start Date:</label>
                    </div>
                    <div class="col-lg-6">
                        <input type="date" id="start-date" class="form-control"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-lg-1">
                        <label class="control-label" for="end-date">End Date:</label>
                    </div>
                    <div class="col-lg-6">
                        <input type="date" id="end-date" class="form-control"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-lg-1">
                        <label class="control-label" for="nav-position">Link Position:</label>
                    </div>
                    <div class="col-lg-6">
                        <select class="form-control" name="nav-position">
                            <option value="0">Select link position to come after</option>
                            <c:forEach var="currentLink" items="${navLinks}">
                                <option value="${currentLink.position}">${currentLink.linkName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-1">
                        <label for="static-content" class="control-lable">Content: </label>
                    </div>

                    <div class="col-md-6">
                        <textarea name="static-content" id="static-content" class="form-control"></textarea>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-offset-1 col-md-6">
                        <input type="submit" value="Add Static Page" class="btn btn-default"/>
                        <a href="${pageContext.request.contextPath}/admin">
                            <input type="button" class="btn btn-default" value="Cancel" />
                        </a>
                    </div>
                </div>
            </form>
            <hr>


        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
