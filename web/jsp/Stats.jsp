<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Stats</title>
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">

    <!-- start: Css -->
    <link rel="stylesheet" type="text/css" href="assets/dist/css/bootstrap-datepicker.min.css">

    <!-- plugins -->
    <link rel="stylesheet" type="text/css" href="assets/css/plugins/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="assets/css/plugins/animate.min.css"/>
    <!-- end: Css -->

    <link rel="stylesheet" type="text/css" href="assets/css/style.css">

</head>

<style>
    #content {
        padding-left: 0px;
        padding-right: 0px;
        width: 100%;
    }
</style>

<body class="dashboard">

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

                <a class="navbar-brand" style="color:white;" href="/menu"><sec:authentication
                        property="principal.username"/></a>
            </c:if>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">


                <li>
                    <a style="color: white" href="/factoryoverview">Factory Overview</a>
                </li>
                <li>
                    <a style="color: white" href="/updatelist">Update Employee</a>
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

<div class="container-fluid mimin-wrapper" style="width: 100%">


    <div id="content" style="padding-top:40px;">
        <div class="col-md-6 ">
            <div class="col-md-12">


                <div class="panel chart-title">
                    <div class="col-md-4"><h3><span class="fa fa-area-chart"></span>Factory Statistics</h3></div>

                    <h3>
                        <form class="form-horizontal" name="stats" id="stats" method="post" action="stats"
                              enctype="multipart/form-data">
                            <div class="col-md-6">

                                <div class="input-group input-daterange">
                                    <input type="text" id="before" name="meh" value="${beforeDate}"
                                           class="form-control">
                                    <div class="input-group-addon">To</div>
                                    <input type="text" id="after" name="after" value="${afterDate}"
                                           class="form-control">


                                </div>

                            </div>
                            <button type="submit" id="search" name="search" onclick="onSave()" value="Search"
                                    class="btn btn-primary">Search
                            </button>
                        </form>

                    </h3>
                </div>
                <c:if test="${pageContext.request.method=='POST'}">
                <div class="panel">
                    <div class="col-md-12 padding-0">
                        <div class="col-md-6">
                            <div class="panel box-v1">
                                <div class="panel-heading bg-white border-none">
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
                                        <h4 class="text-left">Total Hours Worked</h4>
                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                                        <h4>
                                            <span class="icon-user icons icon text-right"></span>
                                        </h4>
                                    </div>
                                </div>
                                <div class="panel-body text-center">
                                    <h1>${hoursWorked}</h1>
                                    <p>Hours</p>
                                    <hr/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="panel box-v1">
                                <div class="panel-heading bg-white border-none">
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
                                        <h4 class="text-left">Hours Late</h4>
                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                                        <h4>
                                            <span class="icon-basket-loaded icons icon text-right"></span>
                                        </h4>
                                    </div>
                                </div>
                                <div class="panel-body text-center">
                                    <h1>${hoursLate}</h1>
                                    <p>Hours</p>
                                    <hr/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 padding-0">
                        <div class="col-md-6">
                            <div class="panel box-v1">
                                <div class="panel-heading bg-white border-none">
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
                                        <h4 class="text-left">Total Sick Leave</h4>
                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                                        <h4>
                                            <span class="icon-user icons icon text-right"></span>
                                        </h4>
                                    </div>
                                </div>
                                <div class="panel-body text-center">
                                    <h1>${sickLeave}</h1>
                                    <p>Days</p>
                                    <hr/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="panel box-v1">
                                <div class="panel-heading bg-white border-none">
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-left padding-0">
                                        <h4 class="text-left">Total Leave</h4>
                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-6 text-right">
                                        <h4>
                                            <span class="icon-basket-loaded icons icon text-right"></span>
                                        </h4>
                                    </div>
                                </div>
                                <div class="panel-body text-center">
                                    <h1>${Leave}</h1>
                                    <p>Days</p>
                                    <hr/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Doughnut Chart</h4>
                    </div>
                    <div class="panel-body">
                        <div id="doughnut-chart"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel" >
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Chart Showing Staff Frequency of Sick leave</h4>
                    </div>
                    <div class="panel-body">
                        <div id="area-chart"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="col-md-12">
                <div class="panel" >
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Day Frequency of Sick Leave</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-chart"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Day Frequency of Leave</h4>
                    </div>
                    <div class="panel-body">
                        <div id="line-chart"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Staff Frequency of Leave</h4>
                    </div>
                    <div class="panel-body">
                        <div id="leave-chart"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="panel" style="padding-bottom: 80px">
                <div class="panel-heading panel-heading-white">
                    <h4>Bar Graph Showing Overtime vs Hours Worked vs Hours Late</h4>
                </div>
                <div class="panel-body">
                    <div id="line-overtime" style="height:400px;"></div>
                </div>
            </div>
        </div>

            <div class="col-md-6">
                <div class="panel" >
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Overtime vs Hours Worked vs Hours Late(Sales)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-sales"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Overtime vs Hours Worked vs Hours Late(Electrical)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-electrical"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Overtime vs Hours Worked vs Hours Late(Machine)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-machine"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Line Graph Showing Overtime vs Hours Worked vs Hours Late(Boiler)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-boiler"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Line Graph Showing Overtime vs Hours Worked vs Hours Late(Fitting)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-fitting"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar  Graph Showing Overtime vs Hours Worked vs Hours Late(Stores)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-stores"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Overtime vs Hours Worked vs Hours Late(Sanitation)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-drawing"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="panel">
                    <div class="panel-heading panel-heading-white text-center">
                        <h4>Bar Graph Showing Overtime vs Hours Worked vs Hours Late(Office)</h4>
                    </div>
                    <div class="panel-body">
                        <div id="bar-office"></div>
                    </div>
                </div>
            </div>



        </c:if>

    </div>

</div>
<!-- start: Javascript -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.ui.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>


<!-- plugins -->
<script src="assets/js/plugins/moment.min.js"></script>


<script src="assets/js/plugins/jquery.nicescroll.js"></script>

<script src="<spring:url value="/assets/dist/js/bootstrap-datepicker.js"/>"></script>
<script src="assets/js/plugins/morris.min.js"></script>
<script src="assets/js/plugins/raphael.min.js"></script>
<!-- custom -->

<script type="text/javascript">
    $('.input-daterange input').each(function () {
        $(this).datepicker({
            format: 'dd/mm/yyyy'

        });
    })
</script>
<script type="text/javascript">
    (function (jQuery) {
        Morris.Bar({
            element: 'area-chart',
            resize: true,
            data: [

                <c:if test="${not empty staffFreq}">
                <c:forEach items="${staffFreq}" var="List">

                {x: '${List.key}', y: ${List.value}},

                </c:forEach>
                </c:if>

            ],
            xkey: 'x',
            ykeys: ['y'],
            labels: ['Staff'],
            lineColors: ['#444']
        });

        Morris.Bar({
            element: 'leave-chart',
            resize: true,
            data: [

                <c:if test="${not empty staffleaveFreq}">
                <c:forEach items="${staffleaveFreq}" var="List">

                {x: '${List.key}', y: ${List.value}},

                </c:forEach>
                </c:if>

            ],
            xkey: 'x',
            ykeys: ['y'],
            labels: ['Staff'],
            lineColors: ['#444']
        });

        Morris.Bar({
            element: 'line-chart',
            resize: true,
            data: [

                <c:if test="${not empty leaveFreq}">
                <c:forEach items="${leaveFreq}" var="List">

                {x: '${List.key}', y: ${List.value}},

                </c:forEach>
                </c:if>
            ],


            xkey: 'x',
            ykeys: ['y'],
            labels: ['Abesntee'],
            barColors: ['#6C76FF']
        });

        Morris.Donut({
            element: 'doughnut-chart',
            resize: true,
            data: [
                <c:if test="${not empty typeFrequency}">
                <c:forEach items="${typeFrequency}" var="List">

                {value: '${List.value}', label: '${List.key}'},

                </c:forEach>
                </c:if>
            ],
            colors: ['#FF3835', '#515151', '#6C76FF', '#ddd'],
            /*formatter: function (x) { return x + "%"}
             }).on('click', function(i, row){
             console.log(i, row);*/
        });

        Morris.Bar({
            element: 'line-overtime',
            parseTime: false,
            hideHover: 'auto',
            resize: true,
            data: [


                <c:if test="${not empty compFreq}">
                <c:forEach items="${compFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
        },

        </c:forEach>
        </c:if>


        ],
        xkey: 'y',
                ykeys: ['a', 'b', 'c', 'd'],
                labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


    });

        Morris.Bar({
            element: 'bar-sales',
            parseTime: false,
           // hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty salesFreq}">
                <c:forEach items="${salesFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });


        Morris.Bar({
            element: 'bar-electrical',
            parseTime: false,
           // hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty electricalFreq}">
                <c:forEach items="${electricalFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });


        Morris.Bar({
            element: 'bar-machine',
            parseTime: false,
          //  hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty machineFreq}">
                <c:forEach items="${machineFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });

        Morris.Bar({
            element: 'bar-boiler',
            parseTime: false,
            //hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty boilerFreq}">
                <c:forEach items="${boilerFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });

        Morris.Bar({
            element: 'bar-fitting',
            parseTime: false,
            //hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty fittingFreq}">
                <c:forEach items="${fittingFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });

        Morris.Bar({
            element: 'bar-stores',
            parseTime: false,
          //  hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty storesFreq}">
                <c:forEach items="${storesFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });

        Morris.Bar({
            element: 'bar-drawing',
            parseTime: false,
           // hideHover: 'false',
            resize: true,
            data: [


                <c:if test="${not empty drawingFreq}">
                <c:forEach items="${drawingFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });

        Morris.Bar({
            element: 'bar-office',
            parseTime: false,
            //hideHover: 'true',
            resize: true,
            data: [


                <c:if test="${not empty officeFreq}">
                <c:forEach items="${officeFreq}" var="List">


                {
                    y: '${List.key}',
                    a: ${List.value.overtimeHrsDouble},
                    b: ${List.value.overTimeHrshalf},
                    c:${List.value.hrsWorked},
                    d:${List.value.hrsLate}
                },

                </c:forEach>
                </c:if>


            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c', 'd'],
            labels: ['OverTime(2x)', 'Overtime(1/2)', 'Hrs Worked', 'Hrs Late'],


        });





        Morris.Bar({
            element: 'bar-chart',
            resize: true,
            data: [
                <c:if test="${not empty dayFrequency}">
                <c:forEach items="${dayFrequency}" var="List">

                {x: '${List.key}', y: ${List.value}},

                </c:forEach>
                </c:if>
            ],
            xkey: 'x',
            ykeys: ['y'],
            labels: ['Absentee'],
            barColors: ['#515151']
        }).on('click', function (i, row) {
            console.log(i, row);
        });

    })(jQuery);
</script>

<script>
    function onSave() {
        $.ajax({
            type: "post",
            url: "/getstatsdate",
            datatype: 'json',
            data: {
                before: document.getElementById("before").value,
                after: document.getElementById("after").value,
            },

            success: function (response) {
                document.getElementById("before").value = "",
                        document.getElementById("after").value = ""


            },
            error: function (jqXHR, exception) {
                alert('Something is wrong');
            }
        });
    }
</script>
<!-- end: Javascript -->
</body>
</html>