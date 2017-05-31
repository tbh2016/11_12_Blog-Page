<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title><c:out value="${mainBlogSite.siteData.title}"/></title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/PiratesCSS.css" rel="stylesheet" type="text/css" />
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <style> 
            img{
                max-width:100%;
                width: 100%;
                max-height: 100%;
                height: 100%;
            }
        </style>
    </head>

    <body>
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container-fluid">
                <div class="navbar-header">

                </div>
                <ul class="nav navbar-nav">
                    <c:forEach items="${mainBlogSite.links}" var="link">
                        <c:choose>
                            <c:when test="${link.siteId == mainBlogSite.siteData.siteDataId}">
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
            <h1 id="pageTitle"><c:out value="${mainBlogSite.siteData.title}"/></h1>
            <hr/>

            <div class="col-md-12">
                <div class="col-md-7">
                    <br>
                    <c:forEach var="currentBlog" items="${mainBlogSite.blogs}">
                        <h3 id="leftBigDivTitle"> 
                            <a href="${pageContext.request.contextPath}/Page/displayBlogPage?siteId=${currentBlog.siteDataId}&commentCounter=0">${currentBlog.title}</a>
                        </h3>

                        ${currentBlog.content}
                        <br>
                    </c:forEach>
                </div>

                <div class="col-md-5" id="rightBigDiv">
                    <h3> Popular Hashtags </h3>
                    <c:forEach var="currentHashtag" items="${mainBlogSite.hashTags}">
                        <div>
                            <a href="${pageContext.request.contextPath}/Page/displayBlogsForHashTag?hashTagId=${currentHashtag.hashTagId}">${currentHashtag.hashTag}</a>
                        </div>
                    </c:forEach>
                    <br>
                    <br>
                    <br>
                    <h3> Categories </h3>
                    <c:forEach var="currentCategoryTag" items="${categories}">
                        <div>
                            <a href="${pageContext.request.contextPath}/Page/displayBlogsForCategory?categoryId=${currentCategoryTag.categoryId}">${currentCategoryTag.categoryName}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <footer class="panel-footer">
            <div> <c:out value="${mainBlogSite.contact.address}"/> </div>
            <div> <c:out value="${mainBlogSite.contact.contactEmail}"/> </div>
            <div> <c:out value="${mainBlogSite.contact.contactPhone}"/> </div>
        </footer>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
