
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<head>

<title>Contact Page</title>
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>

<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<div class="col-lg-7 mx-auto p-4 py-md-5">

    <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
        <span class="fs-4"><fmt:message key="contacts"/></span>
    </header>

    <main>
        <p class="fs-5 col-md-8">41 St John's Way, Thetford IP24 3NW, UK</p>

        <p class="fs-5 col-md-8">customerservice@cargodelivery.com</p>

        <p class="fs-5 col-md-8">+44(0)7538 225277</p>
    </main>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>