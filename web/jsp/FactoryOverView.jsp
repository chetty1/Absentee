<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: chett_000
  Date: 2018/01/24
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/dist/css/bootstrap-datepicker.css"/>" rel="stylesheet">

    <title>Overview</title>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color:#000b54" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:if test="${not empty pageContext.request.userPrincipal}">

                <a class="navbar-brand" style="color:white;" href="/approvals"><sec:authentication
                        property="principal.username"/></a>
            </c:if>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a style="color: white" href="/updatelist">Update Employees</a>
                </li>

<sec:authorize access="hasRole('ROLE_MANAGER') and isAuthenticated()">
    <li>
    <a style="color: white" href="/stats">Factory Statistics</a>
    </li>
    </sec:authorize>

                <li>
                    <a style="color: white" href="/logout">Logout</a>
                </li>
            </ul>


        </div>
        <!--<div class="col-md-sxxsx8" style="align-content: center">
            <i class="fa fa-shopping-cart pull-right" style="color: grey; "></i>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<h3></h3>
<h3></h3>
<h3></h3>
<div class="container" style="width: 100%">
    <div class="row">
        <div class="col-md-8">
            <h1 class="page-header">
                <h3>Factory Overview: ${datebefore}-${dateafter}</h3>
            </h1>
            <form class="form-horizontal" name="factoryoverview" id="factoryoverview" method="POST" action="/factoryoverview" enctype="multipart/form-data">

            <div class="col-md-5">
                <div class="input-group input-daterange">
                    <input type="text" name="before" id="before" class="form-control">
                    <div class="input-group-addon">To</div>
                    <input type="text" name="after" id="after" class="form-control">
                </div>

            </div>

                <button type="submit" name="button" onclick="onSave()" class="btn btn-default" data-dismiss="modal">
                    Search
                </button>
                </form>
            </div>
        </div>


<div class="row">
        <p></p>
        <div class="col-md-12">
            <table class="table table-bordered" style="text-align: center" id="teaTable">
                <thead>
                <tr>

                    <th style="text-align: center">Name</th>

                    <th style="text-align: center">Hrs Worked</th>
                    <th style="text-align: center">Hrs Late</th>
                    <th style="text-align: center">Over Time(1/2)</th>
                    <th style="text-align: center">Over Time(2)</th>
                    <th style="text-align: center">Unpaid Leave</th>
                    <th style="text-align: center">Sick Leave</th>
                    <th style="text-align: center">Family Respon</th>
                    <th style="text-align: center">Site Local</th>
                    <th style="text-align: center">Site Away</th>
                    <th style="text-align: center">Annual Leave</th>
                    <th style="text-align: center">Over Time Leave</th>

                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty spentList}">

                    <c:forEach items="${spentList}" var="List">
                        <tr>
                            <td>${List.key}</td>

                            <td>${List.value.hrsWorked}</td>
                            <td>${List.value.hrsLate}</td>
                            <td>${List.value.overTimeHrshalf}</td>
                            <td>${List.value.overtimeHrsDouble}</td>
                            <td>${List.value.unpaidLeave}</td>
                            <td>${List.value.sickLeave}</td>
                            <td>${List.value.famRespon}</td>
                            <td>${List.value.onSiteLocal}</td>
                            <td>${List.value.onSiteAway}</td>
                            <td>${List.value.annualLeave}</td>
                            <td>${List.value.otLeave}</td>
                        </tr>
                    </c:forEach>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>

</div>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>

<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
<script src="<spring:url value="/assets/dist/js/bootstrap-datepicker.js"/>"></script>

<script type="text/javascript">
    $('.input-daterange input').each(function () {
        $(this).datepicker({
            format: 'dd/mm/yyyy'

        });
    })
</script>

<script>
    function onSave() {
          $.ajax({
         type: "post",
         url: "/getdate",
         datatype: 'json',
         data: {
         before: document.getElementById("before").value,
         after:document.getElementById("after").value,
         },

         success: function (response) {
         document.getElementById("before").value = "",
         document.getElementById("after").value=""


         },
         error: function (jqXHR, exception) {
         alert('Something is wrong');
         }
         });
    }
</script>

</body>

</html>
