<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="resources"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<head>

<title>Manage Order Page</title>
<meta charset="UTF-8">
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>



<body>

<jsp:include page="fragments/mainMenu.jsp"/>

<jsp:include page="pages/manageOrderPage.jsp"/>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>