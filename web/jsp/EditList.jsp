<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: chett_000
  Date: 2017/05/10
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet">

    <title>History</title>

    <style>


        #custom-search-input {
            margin:0;
            margin-top: 10px;
            padding: 0;
        }

        #custom-search-input .search-query {
            padding-right: 3px;
            padding-right: 4px \9;
            padding-left: 3px;
            padding-left: 4px \9;
            /* IE7-8 doesn't have border-radius, so don't indent the padding */

            margin-bottom: 0;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
        }

        #custom-search-input button {
            border: 0;
            background: none;
            /** belows styles are working good */
            padding: 2px 5px;
            margin-top: 2px;
            position: relative;
            left: -28px;
            /* IE7-8 doesn't have border-radius, so don't indent the padding */
            margin-bottom: 0;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            border-radius: 3px;
            color:#D9230F;
        }

        .search-query:focus + button {
            z-index: 3;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top"style="background-color:#000b54" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
<c:if test="${not empty pageContext.request.userPrincipal}">
<a class="navbar-brand" style="color:white;" ><sec:authentication property="principal.username" /></a>
    </c:if>

        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a style="color: white" href="/register">Register</a>
                </li>
                <li>
                    <a style="color: white" href="/logout">Logout</a>
                </li>


            </ul>
        </div>
        <!--<div class="col-md-8" style="align-content: center">
            <i class="fa fa-shopping-cart pull-right" style="color: grey; "></i>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<div class="container">



    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                <h3>Edit List</h3>
            </h1>
        </div>
        <p></p>
        <div class="row">

            <form  method="post" action="/editlist"  enctype="multipart/form-data">

                <div id="custom-search-input">
                    <div class="input-group col-lg-3" style="left: 1%">
                        <input type="text" class="  search-query form-control"  name="search" id="search" placeholder="Search" />
                        <span class="input-group-btn">
                                    <button class="btn btn-danger" onclick="onSearch()" type="submit">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                    </div>
                </div>

            </form>
        </div>

        <p></p>
        <table class="table table-bordered" style="text-align: center" id="teaTable">
            <thead>
            <tr>
                <th style="text-align: center">Date</th>
                <th style="text-align: center">Name</th>
                <th style="text-align: center">View</th>

            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty editList}">

                <c:forEach items="${editList}" var="List">
                    <tr>

                        <th >${List.date}</th>
                        <td>${List.staff.name}</td>
                        <td>

                            <a type="button" href="${List.id}/edit" class="btn button-info"
                               style="background-color: #4580e0;  color: white">view</a>

                        </td>
                    </tr>
                </c:forEach>
            </c:if>

            </tbody>
        </table>
    </div>




</div>


</body>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>

<script>
    function onSearch() {
        $.ajax({
            type: "post",
            url: "/getsearch",
            datatype: 'json',
            data: {
                search: document.getElementById("search").value,
            },

            success: function (response) {
                document.getElementById("search").value = ""


            },
            error: function (jqXHR, exception) {
                alert('Something is wrong');
            }
        });
    }
</script>
<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
</html>
