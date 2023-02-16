<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<html lang="${sessionScope.locale}">
<head>

<title>My Order Page</title>

<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>



<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="pages/myOrderPage.jsp"/>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>