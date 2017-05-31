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
            #hiddenBox{
                width: 97%;
                height: 325px;
                border: 1px solid black;
                float:right;
            }
            #uploadImagePreview{
                width: 80%;
                height: 80%;
                border: 1px solid black;
                margin: 30px auto;

            }

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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Page/displayStaticPage">Static</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Page/displayHomePage">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Page/displayBlogPage">Blog</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Page/displayEditPage">Edit</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/Page/displayLoginPage">Login</a></li>
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
            <h1 id="pageTitle">Edit Page</h1>
            <hr/>
            <div class="col-md-12">
                <div class="col-md-7">

                </div>
                <div class="col-md-5">
                    <input type="search" name="search" placeholder="Search.."> &nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/Page/displayLoginPage" id="loginLink">Login </a>
                </div>
            </div>
            <br><br> <br>
            <h1>
                Edit Page Title
            </h1>
            <div class="col-md-12">
                <div class="col-md-7">
                    <form method="post" action="${pageContext.request.contextPath}/">
                        <textarea id="mytextarea" name="post"></textarea>
                    </form>

                    <!--                    <sf:form class="form-horizontal" role="form" modelAttribute=""
                                                 action="" method="POST">
                                            <div class="form-group">
                                                
                                                <div class="col-md-8">
                                                    <sf:input type="text" class="form-control" id="add-"
                                                              path="" placeholder=""/>
                                                    <sf:errors path="" cssclass="error"></sf:errors>
                                                </div>
                                                <input type="submit" class="btn btn-default col-md-4" value="Add Hashtag"/>
                                            </div>
                                            
                                            <div class="form-group">
                                            <div id="hiddenBox">
                                                <div id="uploadImagePreview">
                                                    
                                                </div>
                                            </div>  
                                            </div>
                                            
                                            <div class="form-group">
                                                
                                                <div class="col-md-8">
                                                    <sf:input type="text" class="form-control" id="add-"
                                                              path="" placeholder=""/>
                                                    <sf:errors path="" cssclass="error"></sf:errors>
                                                </div>
                                                <input type="submit" class="btn btn-default col-md-4" value="Upload Image"/>
                                            </div>
                                            
                                            <div class="form-group">
                                                
                                                <div class="col-md-8">
                                                    <sf:input type="text" class="form-control" id="add-"
                                                              path="" placeholder=""/>
                                                    <sf:errors path="" cssclass="error"></sf:errors>
                                                </div>
                                                <input type="submit" class="btn btn-default col-md-4" value="Upload Image"/>
                                            </div>
                                            
                                            <div class="form-group">
                                                
                                                <div class="col-md-8">
                                                    <sf:input type="text" class="form-control" id="add-"
                                                              path="" placeholder=""/>
                                                    <sf:errors path="" cssclass="error"></sf:errors>
                                                </div>
                                                <input type="submit" class="btn btn-default col-md-4" value="Upload Image"/>
                                            </div>
                                        </sf:form>    -->



                </div>

            </div>

            <div>
                &nbsp;
                <hr/>
            </div>



        </div>

        <footer id="panel-footer">
            <div> ${contact.address} </div>
            <div>${contact.contactEmail}</div>
            <div> ${contact.contactPhone} </div>
        </footer>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
