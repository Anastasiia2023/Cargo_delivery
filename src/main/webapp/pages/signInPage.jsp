<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="${sessionScope.locale}">
<head>

<title>Main Page</title>
<script src="../js/popper.min.js"></script>
<script src="../js/jquery-3.6.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>



<div class="col-lg-5 mx-auto p-4 py-md-5">
    <header class="d-flex align-items-center pb-0 mb-3 border-bottom">
        <span class="fs-4"><fmt:message key="sign.in"/></span>
    </header>

    <form method="POST" action="controller">
        <input type="hidden" name="action" value="sign-in">
        <c:set var="error" value="${requestScope.error}"/>

        <div class="form-group">
            <c:if test="${not empty requestScope.message}">
                <span class="text-success"><fmt:message key="${requestScope.message}"/></span>
            </c:if><br>
            <label class="form-label fs-5" for="email"><fmt:message key="email"/>: </label>
            <input class="form-control" type="email" name="email" id="email"
                   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" required value="${requestScope.email}">
            <c:if test="${fn:contains(error, 'email')}">
                <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
            </c:if><br>
        </div>

        <div class="form-group">
            <label class="form-label  fs-5" for="password"><fmt:message key="password"/>: </label>
            <input class="form-control" type="password" name="password" id="password" required>
            <c:if test="${fn:contains(error, 'pass')}">
                <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
            </c:if><br>
        </div>

        <button type="submit" class="btn btn-secondary"><fmt:message key="sign.in"/></button>

    </form>

    <p class="fs-6 col-md-8">
        <fmt:message key="forgot.password"/>
        <a href="ressetPass.jsp" class="link-dark"><fmt:message key="reset.password"/></a>
    </p>

    <p class="fs-6 col-md-8">
        <fmt:message key="no.account"/>
        <a href="signUp.jsp" class="link-dark"><fmt:message key="sign.up"/></a>
    </p>
 </div>
