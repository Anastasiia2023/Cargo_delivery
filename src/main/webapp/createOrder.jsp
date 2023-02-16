<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">

<head>

<title>Create Order Page</title>
<meta charset="UTF-8">
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>


<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="pages/createOrderPage.jsp"/>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>