<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<head>

<title>Main Page</title>
<meta charset="UTF-8">
<script src="../js/popper.min.js"></script>
<script src="../js/jquery-3.6.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>

 <nav class="navbar navbar-expand-lg" style="background-color: #e3f2fd;">


  <div class="container-fluid">

    <a class="navbar-brand" href="index.jsp">Cargo Delivery</a>
     <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Переключатель навигации">
      <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
       <div class="navbar-nav">
         <a class="nav-link active" aria-current="page" href="about.jsp"><fmt:message key="about"/></a>
         <a class="nav-link active" aria-current="page" href="contact.jsp"><fmt:message key="contact"/></a>
       </div>
       <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
         <ul class="navbar-nav">
            <li class="nav-item dropdown">
             <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
                     data-bs-toggle="dropdown" aria-expanded="false">
                      <fmt:message key="service"/>
             </a>
              <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
               <li><a class="dropdown-item" href="controller?action=calculate"><fmt:message key="calculate"/></a></li>
               <li><a class="dropdown-item" href="controller?action=directions"><fmt:message key="directions.terms"/></a></li>

              </ul>
             </li>
         </ul>
       </div>

        <c:if test="${sessionScope.role eq 2}">
         <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
             <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
              data-bs-toggle="dropdown" aria-expanded="false">
              <fmt:message key="orders"/>
              </a>
           <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
            <li><a class="dropdown-item" href="controller?action=create-order"><fmt:message key="create.order"/></a></li>
            <li><a class="dropdown-item" href="controller?action=my-order"><fmt:message key="my.orders"/></a></li>
           </ul>
           </li>
          </ul>
         </div>


        </c:if>

         <c:if test="${sessionScope.role eq 3}">
          <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
              <a class="nav-link active" aria-current="page" href="controller?action=manage-order"><fmt:message key="reports"/></a>
            </div>
          </div>


          <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
            <ul class="navbar-nav">
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button"
                data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="routes"/>
                </a>
                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                <li><a class="dropdown-item" href="controller?action=create-city"><fmt:message key="create.city"/></a></li>
                 <li><a class="dropdown-item" href="controller?action=create-route"><fmt:message key="create.route"/></a></li>
                 </ul>
              </li>
            </ul>
          </div>



         </c:if>

         <c:if test="${sessionScope.role eq 0}">
                   <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                     <div class="navbar-nav">
                       <a class="nav-link active" aria-current="page" href="controller?action=manage-role"><fmt:message key="manage.role"/></a>
                     </div>
                   </div>

         </c:if>




        <ul class="navbar-nav ms-auto mx-4 mb-4 mb-md-0">
          <c:choose>
           <c:when test="${sessionScope.loggedUser eq null}">
           <li class="nav-item">
            <a class="nav-link"  href="signIn.jsp"><fmt:message key="sign.in"/></a>
           </li>
           <li class="nav-item">
            <a class="nav-link"  href="signUp.jsp"><fmt:message key="sign.up"/></a>
           </li>
          </c:when>
          <c:otherwise>
          <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                      <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                          <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenu" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="profile"/>
                          </a>
                          <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenu">
                           <li><a class="dropdown-item" href="profile.jsp"><fmt:message key="my.profile"/></a></li>
                           <li><a class="dropdown-item" href="controller?action=sign-out"><fmt:message key="log.out"/></a></li>
                          </ul>
                        </li>
                      </ul>
                    </div>
          </c:otherwise>

         </c:choose>
        </ul>
        <form method="POST" class="d-flex" >
          <label>
           <select name="locale" onchange='submit();'>
            <option value="en" ${sessionScope.locale eq 'en' ? 'selected' : ''}>
              <fmt:message key="en"/>
            </option>
            <option value="uk_UA" ${sessionScope.locale eq 'uk_UA' ? 'selected' : ''}>
              <fmt:message key="ua"/>
            </option>
           </select>
          </label>
        </form>
     </div>
  </div>
</nav>


