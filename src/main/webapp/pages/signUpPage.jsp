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
        <span class="fs-4"><fmt:message key="sign.up"/></span>
    </header>

    <form method="POST" action="controller">
        <input type="hidden" name="action" value="sign-up">
        <c:set var="error" value="${requestScope.error}"/>

        <div class="form-group">
            <br>
            <label class="form-label fs-5" for="email"><fmt:message key="email"/>*: </label>
            <input class="form-control" type="email" name="email" id="email"
                   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" required value="${requestScope.user.email}">
            <c:if test="${fn:contains(error, 'email')}">
                <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
            </c:if>
            <br>
        </div>

        <div class="form-group">
            <label class="form-label fs-5" for="password"><fmt:message key="password"/> *: </label>
            <input class="form-control" type="password" name="password" id="password"
                   title="<fmt:message key="password.requirements"/>"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20}$" required>
            <c:if test="${fn:contains(error, 'pass')}">
                <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
            </c:if>
            <br>
        </div>

        <div class="form-group">
            <label class="form-label fs-5" for="confirm-password"><fmt:message key="confirm.password"/> *: </label>
            <input class="form-control" type="password" name="confirm-password" id="confirm-password"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,20}$"
                   title="<fmt:message key="password.requirements"/>" required>
            <br>
        </div>

        <div class="form-group">
            <label class="form-label fs-5" for="name"><fmt:message key="first.name"/>: </label>
            <input class="form-control" type="text" name="name" id="name"
                   title="<fmt:message key="name.requirements"/>" pattern="^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\- ]{1,30}"
                   required value="${requestScope.user.name}">
            <c:if test="${fn:contains(error, '.name')}">
                <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
            </c:if>
            <br>
        </div>

        <div class="form-group">
            <label class="form-label fs-5" for="surname"><fmt:message key="last.name"/>: </label>
            <input class="form-control" type="text" name="surname" id="surname"
                   title="<fmt:message key="surname.requirements"/>" pattern="^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\- ]{1,30}"
                   required value="${requestScope.user.surname}">
            <c:if test="${fn:contains(error, 'surname')}">
                <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
            </c:if>
            <br>
        </div>

        <div class="form-group">
          <label class="form-label fs-5" for="phone"><fmt:message key="phone"/>*: </label>
          <input class="form-control" type="text" name="phone" id="phone"
            title="<fmt:message key="phone.requirements"/>" pattern="^\+?[1-9][0-9]{7,14}$"
             required value="${requestScope.user.phone}">
          <c:if test="${fn:contains(error, 'phone')}">
             <span class="text-danger"><fmt:message key="${requestScope.error}"/></span>
           </c:if>
           <br>
        </div>



        <button type="submit" class="btn btn-secondary"><fmt:message key="sign.up"/></button>
    </form>

    <p class="fs-6 col-md-8">
        <fmt:message key="have.account"/>
        <a href="signIn.jsp" class="link-dark"><fmt:message key="sign.in"/></a>
    </p>
  </div>
