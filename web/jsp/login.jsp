<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <title>Login</title>

    <!-- Bootstrap Core CSS -->

    <!-- Custom CSS -->
    <link href="<spring:url value="/assets/login.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/assets/css/flipclock.css"/>" rel="stylesheet">
    <script src="<spring:url value="/assets/js/jquery.js"/>"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div class="container">

    <!-- Page Header -->


    <div class="card card-container">

        <form wid action="${loginUrl}" class="form-signin" method="post">
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    <p>Invalid username and password.</p>
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    <p>You have been logged out successfully.</p>
                </div>
            </c:if>
            <input type="text" id="inputEmail" class="form-control" placeholder="Username" name="username"
                   required autofocus>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password"
                   name="password" required>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block btn-signin" id="loginBtn" type="submit">Sign in
            </button>
        </form><!-- /form -->

    </div>

</div>

</body>
<script src="<spring:url value="/assets/js/jquery.js"/>"></script>

<script src="<spring:url value="/assets/js/mqttws31.js"/>"></script>

<script src="<spring:url value="/assets/login.js"/>"></script>
<script src="<spring:url value="/assets/js/flipclock.js"/>"></script>

</html>