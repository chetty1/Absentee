<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chett_000
  Date: 2017/06/02
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/css/3-col-portfolio.css" rel="stylesheet">
    <link href="/assets/css/flipclock.css" rel="stylesheet">
    <script src="/assets/js/jquery.js"></script>
    <title>Register</title>

    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/font-awesome/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/dist/css/bootstrap-datepicker.css"/>" rel="stylesheet">
    <style>

        body {
            background: #fff;
            font-family: 'Roboto', sans-serif;
            color: #333;
            line-height: 22px;
        }

        h1, h2, h3, h4, h5, h6 {
            font-family: 'Roboto Condensed', sans-serif;
            font-weight: 400;
            color: #111;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        h1, h2, h3 {
            text-transform: uppercase;
        }

        input .hello {
            position: absolute;
            top: 0;
            right: 0;
            margin: 0;
            padding: 0;
            font-size: 12px;
            cursor: pointer;
            opacity: 1;
            filter: alpha(opacity=1);
        }

        .form-inline .form-group {
            margin-left: 0;
            margin-right: 0;
        }

        .control-label {
            color: #333333;
        }
    </style>
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
                    <a style="color: white" href="/approvals">Approvals</a>
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

        <section>
            <h1 class="entry-title"><span>Approve Absentee</span></h1>
            <hr>
            <form class="form-horizontal" method="post"  name="signup" id="signup" enctype="multipart/form-data">
                <div class="form-group">

                    <label class="control-label col-sm-3">Name</label>
                    <div class="col-md-8 col-sm-9">
                        <div class="input-group">
                            <input type="name" style="width: 250px" class="form-control" name="user" value="${name}" id="name">
                        </div>
                    </div>




                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3">Leave Type</label>
                    <div class="col-md-8 col-sm-8">
                        <div class="input-group" style="padding-top: 8px">
                            <select class="form-control" id="leaveSelect">
                                <option>Unpaid Leave</option>
                                <option>Sick Leave</option>
                                <option>Family Responsibility Leave</option>

                                <option>Annual Leave</option>
                                <option>Overtime Leave</option>
                            </select>
                        </div>
                    </div>
                </div>




                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-10">
                        <input type="button" value="Save" onclick="onSave()" class="btn btn-primary">
                    </div>
                </div>

            </form>
        </section>
    </div>
</div>
</div>
</div>
</div>


</body>
<script src="<spring:url value="/assets/js/bootstrap.js"/>"></script>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>
<script src="<spring:url value="/assets/dist/js/bootstrap-datepicker.js"/>"></script>

<script type="text/javascript">
    $('.input-daterange input').each(function() {
        $(this).datepicker({
            format: 'dd/mm/yyyy'

        });
    })
</script>
<script>
    function onSave() {

    $.ajax({
            type: "post",
            url: "/${id}/approveuser",
            datatype: 'json',
            data: {
                name: document.getElementById("name").value,
                leaveSelect:document.getElementById("leaveSelect").value,
            },

            success: function (response) {
                alert("User Successfully Added");
                document.getElementById("name").value = "";
                document.getElementById("leaveSelect").value="",

                document.location="/approvals";

            },
            error: function (jqXHR, exception) {
                alert('Something is wrong');
            }
        });
    }
</script>


<script src="<spring:url value="/assets/js/mqttws31.js"/>"></script>

<script src="/assets/js/bootstrap.js"></script>
</html>
