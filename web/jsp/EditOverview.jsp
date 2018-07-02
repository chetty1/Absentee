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

        .autocomplete-suggestions {
            border: 1px solid #999;
            background: #FFF;
            overflow: auto;
        }

        .autocomplete-suggestion {
            padding: 5px 5px;
            white-space: nowrap;
            overflow: hidden;
            font-size: 22px
        }

        .autocomplete-selected {
            background: #F0F0F0;
        }

        .autocomplete-suggestions strong {
            font-weight: bold;
            color: #3399FF;
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

                <a class="navbar-brand" style="color:white;" href="/register"><sec:authentication
                        property="principal.username"/></a>
            </c:if>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">


                <li> <a style="color: white" href="/register">Register</a> </li>
                <li> <a style="color: white" href="/editlist">Edit List</a> </li>
                <li> <a style="color: white" href="/logout">Logout</a> </li>

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
            <h1 class="entry-title"><span>Clock In</span></h1>
            <hr>
            <form class="form-horizontal" method="post" name="signup" id="signup" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="control-label col-sm-3">Name</label>
                    <div class="col-md-8 col-sm-9">
                        <div class="input-group">
                            <input style="width: 250px" class="form-control" name="emailid" value="${name}" id="name">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Hours Worked</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-timerange">
                            <input type="number" id="beforeTime" value="${hrsworked}" name="beforeTime" class="form-control ">
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Hours Late</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-timerange">
                            <input type="number" id="hourslate" name="beforeTime" value="${hrslate}"
                                   class="form-control ">

                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">On Site(Local)</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-timerange">
                            <input type="number" id="beforeOnsite" name="beforeTime" value="${onsitelocal}" class="form-control ">

                        </div>
                    </div>

                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3">On Site(Away)</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-timerange">
                            <input type="number" id="beforeawaySite" name="beforeTime" value="${onsiteaway}"
                                   class="form-control ">

                        </div>
                    </div>

                </div>


                <div class="form-group">
                    <label class="control-label col-sm-3">Time and a Half</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-timerange">
                            <input type="number" id="timeandahalf" name="beforeTime" value="${timeandahalf}"
                                   class="form-control ">

                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label class="control-label col-sm-3">Double Pay</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-timerange">
                            <input type="number" id="doublepay" name="beforeTime" value="${doublepay}"
                                   class="form-control ">

                        </div>
                    </div>

                </div>






                <div class="form-group">
                    <label class="control-label col-sm-3">Date</label>
                    <div class="col-md-4 col-sm-9">
                        <div class="input-group input-daterange">
                            <input type="text" id="date" name="beforeTime" class="form-control" value="${date}">

                        </div>
                    </div>

                </div>



                <div class="form-group">
                    <label class="control-label col-sm-3">Absent</label>
                    <div class="col-md-8 col-sm-8">
                        <div class="input-group" style="padding-top: 8px">
                            <label class="form-check-label">
                                <input type="checkbox" class="checkbox-inline" name="absent"
                                       id="Absent" value="option1"${absent}>

                            </label>


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
<script src="<spring:url value="/assets/jquery.autocomplete.min.js"/>"></script>

<script>
    $(document).ready(function () {

        $('#name').autocomplete({
            serviceUrl: '/getName',
            paramName: "Name",
            delimiter: ",",
            transformResult: function (response) {

                return {
                    //must convert json to javascript object before process
                    suggestions: $.map(JSON.parse(response), function (item) {
                        return {value: item.name, data: item.id};
                    })

                };

            }


        });

    });
</script>

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
            url: "/edituser",
            datatype: 'json',
            data: {
                name: document.getElementById("name").value,
                beforeTime: document.getElementById("beforeTime").value,
                onsiteLocalbefore: document.getElementById("beforeOnsite").value,
                onsiteawaybefore: document.getElementById("beforeawaySite").value,
                timeandahalf:document.getElementById("timeandahalf").value,
                doublepay:document.getElementById("doublepay").value,
                date: document.getElementById("date").value,
                absent: document.getElementById("Absent").checked,
                hourslate:document.getElementById("hourslate").value
            },

            success: function (response) {
                alert("User Successfully Added");
                document.getElementById("name").value = "",
                        document.getElementById("beforeTime").value = "00:00",
                        document.getElementById("beforeOnsite").value = "00:00",
                        document.getElementById("beforeawaySite").value = "00:00",
                        document.getElementById("Absent").checked = false,
                        document.getElementById("date").value = "",
                        document.getElementById("timeandahalf").value="00:00",
                        document.getElementById("doublepay").value= "00:00",
                        document.getElementById("hourslate").value="00:00",
                window.location="/editlist"


            },
            error: function (jqXHR, exception) {
                alert('Something is wrong');
            }
        });

    }
</script>



<script src="/assets/js/bootstrap.js"></script>
</html>
