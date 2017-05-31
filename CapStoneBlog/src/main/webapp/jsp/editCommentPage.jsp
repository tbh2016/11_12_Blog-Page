<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Edit Comment Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <script src='${pageContext.request.contextPath}/js/tinyMCE/js/tinymce/tinymce.min.js'></script>
        <style> 
            input[type=search] {
                width: 225px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
                background-color: white;
                background-image: url('searchicon.png');
                background-position: 10px 10px; 
                background-repeat: no-repeat;
                padding: 12px 20px 12px 40px;
                -webkit-transition: width 0.4s ease-in-out;
                transition: width 0.4s ease-in-out;
            }
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
            #pictureBox {
                width: 500px;
                height: 200px;
                border: 1px solid black;
                margin: 25px;
            }
            #hashtagBox{
                width: 250px;
                height: 427px;
                border: 1px solid black;
                margin: 25px;

            }
            footer{
                text-align: center;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>Comment</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
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
            <br><br> <br>
            <h1>
                Edit Comment Page
            </h1>
            <div class="row">
                <sf:form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/updateComment" modelAttribute="comment" method="POST" >
                    <sf:hidden path="commentId"/>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="comment-title">Comment Title</label>
                        </div>
                        <div class="col-md-4">
                            <sf:input id="comment-title" class="form-control" path="title"/>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-3 col-md-offset-1 text-center">
                            <label class="control-label" for="comment-content">Home Page Content</label>
                        </div>
                        <div class="col-md-4">
                            <sf:textarea id="comment-content" class="form-control" path="content"/>
                        </div>
                    </div>
                    <br>
                    <div class="col-md-offset-4">
                        <input type="submit" class="btn btn-default " value="Update Comment">
                    </div>
                </sf:form>    

            </div>

            <div>
                &nbsp;
                <hr/>
            </div>



            <footer id="footerText">

                <div> ${homeSite.contact.address} </div>
                <div>${homeSite.contact.contactEmail}</div>
                <div> ${homeSite.contact.contactPhone} </div>
            </footer>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
