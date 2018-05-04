<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <link href="<spring:url value="/assets/dist/css/bootstrap-datepicker.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/jquery.timepicker.css"/>" rel="stylesheet">
    <title>Register</title>


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

                <a class="navbar-brand" style="color:white;"><sec:authentication
                        property="principal.username"/></a>
            </c:if>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasRole('ROLE_HR') and isAuthenticated()">

                    <li>
                        <a style="color: white" href="/approvals">Approvals</a>
                    </li>
                </sec:authorize>
                <li>
                    <a style="color: white" href="/updatelist">Employee List</a>
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
            <h1 class="entry-title"><span>Update Employee</span></h1>
            <hr>
            <form class="form-horizontal" method="POST" name="Staff" id="signup"
                  enctype="multipart/form-data">
                <div class="form-group">

                    <label class="control-label col-sm-3">Name</label>
                    <div class="col-md-8 col-sm-9">
                        <div class="input-group">
                            <input type="name" name="name" style="width: 250px" class="form-control" value="${name}"
                                   id="name">
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3">Employment Date</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group input-daterange">
                            <input type="text" name="employmentDate" style="width: 250px" class="form-control"
                                   value="${empDate}" id="empDate"/>

                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3">Department</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${department}" id="department">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Hours Worked</label>
                    <div class="col-md-2">
                        <div class="input-group input-timerange">
                            <input type="text" name="before" id="hoursWorked" value="${hoursWorked}"
                                   class="form-control ">

                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Leave Balance</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${leaveBalance}" id="leaveBalance">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Sick Leave Balance</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${sickleaveBalance}" id="sickleaveBalance">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Family Responsibility Balance</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${famleaveBalance}" id="famleaveBalance">

                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3">Sick Leave Due (3 years)</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${sickleaveDue}" id="sickleaveDue">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Family Responsibility Due(Year)</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${famleaveDue}" id="famleaveDue">

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Leave Due(Per Month)</label>
                    <div class="col-md-5 col-sm-8">
                        <div class="input-group">
                            <input type="text" name="department" style="width: 250px" class="form-control"
                                   value="${leaveDue}" id="leaveDue">

                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-10">
                        <input type="button" type="button" value="Save" onclick="onSave()" class="btn btn-primary">
                        <input type="button" value="Back" class="btn btn-primary"
                               onclick="window.location='/updatelist'">

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

<script src="<spring:url value="/assets/jquery.timepicker.js"/>"></script>
<script src="<spring:url value="/assets/dist/js/bootstrap-datepicker.js"/>"></script>


<script>
    function onSave() {


        $.ajax({
            type: "post",
            url: "/update",
            datatype: 'json',
            data: {
                name: document.getElementById("name").value,
                employmentDate: document.getElementById("empDate").value,
                department: document.getElementById("department").value,
                leaveBalance: document.getElementById("leaveBalance").value,
                hoursWorked: document.getElementById("hoursWorked").value,
                sickleaveBalance: document.getElementById("sickleaveBalance").value,
                sickLeaveDue: document.getElementById("sickleaveDue").value,
                leaveDue: document.getElementById("leaveDue").value,
                famleaveDue: document.getElementById("famleaveDue").value,
                famleaveBalance:document.getElementById("famleaveBalance").value


            },

            success: function (response) {
                alert("User Successfully updated");
                window.location.href = "/updatelist"

            },
            error: function () {
                alert('Somethings Wrong');
            }
        });

    }
</script>


<script type="text/javascript">
    $('.input-daterange input').each(function () {
        $(this).datepicker({
            format: 'dd/mm/yyyy'

        });
    })
</script>
<script type="text/javascript">
    $('.input-timerange input').each(function () {
        $(this).timepicker({
            'scrollDefault': 'now',
            'show2400': 'true',
            'timeFormat': 'G:i'
        });
    })
</script>


<script src="/assets/js/bootstrap.js"></script>
</html>
