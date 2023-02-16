
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setBundle basename="resources"/>
<html lang="${sessionScope.locale}">
<head>
<title>Create City Page</title>
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

<jsp:include page="fragments/mainMenu.jsp"/>
<jsp:include page="pages/createCityPage.jsp"/>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>