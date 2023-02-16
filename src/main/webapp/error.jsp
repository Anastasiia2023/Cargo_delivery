<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<head>

<title>Error Page</title>
<meta charset="UTF-8">
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
   <body>
      <div class="d-flex align-items-center justify-content-center vh-100">
         <div class="text-center">
            <h1 class="display-1 fw-bold">404</h1>
              <p class="fs-3"> <span class="text-danger">Opps!</span> Page not found.</p>
                <p class="lead">
                   The page you are looking for doesnt exist.
                </p>
                <a href="index.jsp" class="btn btn-primary">Go Home</a>
         </div>
      </div>
   </body>
</html>