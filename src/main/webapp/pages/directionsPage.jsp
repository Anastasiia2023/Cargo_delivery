<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "custom" uri = "../WEB-INF/custom.tld"%>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>

<title>Main Page</title>
<meta charset="UTF-8">
<script src="../js/popper.min.js"></script>
<script src="../js/jquery-3.6.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-icons.css">
</head>
<body>


<div class="container">
<div class="row">
    <div class="col-12">
	<!-- Page title -->
	<div class="my-5">
     <h3><fmt:message key="directions"/></h3>
	 <hr>
	</div>
	<!-- Form START -->



     <!-- City From -->

      <form class="file-upload" method="POST" action="controller">
        <input type="hidden" name="action" value="directions">
        <div class="row mb-5 gx-5">
          <div class="row g-3">
            <div class="dropdown col-md-3">
              <c:choose>
               <c:when test="${not empty requestScope.cityFrom}">
               <input type="hidden" name="city-from-id" id="city-from-id" value="${cityFrom.id}">
                <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                 data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="city-from-btn" value="${cityFrom.name}">
                 ${cityFrom.name}
                </button>
               </c:when>
             <c:otherwise>
               <input type="hidden" name="city-from-id" id="city-from-id">
                <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                   data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="city-from-btn">
                   <fmt:message key="city.from"/>
                </button>
             </c:otherwise>
             </c:choose>
             <ul class="dropdown-menu scrollable-menu" aria-labelledby="dropdownCity" id="city-from-dropdown"
                style="height: auto; max-height: 200px; overflow-x: hidden;">
                <c:forEach items="${listCity}" var="city">
                  <li><button class="dropdown-item" type="button" value="${city.id}">${city.name}</button></li>
                </c:forEach>
             </ul>
            </div>
            <script>
              $(function(){
              $("#city-from-dropdown").on('click', 'li button', function(){
              $("#city-from-btn").text($(this).text());
              $("#city-from-btn").val($(this).text());
              $("#city-from-id").val($(this).val());
              });
              });
            </script>


        <div class="dropdown col-md-3">
          <c:choose>
             <c:when test="${not empty requestScope.cityTo}">
               <input type="hidden" name="city-to-id" id="city-to-id" value="${cityTo.id}">
               <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                   data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="city-to-btn" value="${cityTo.name}">
                   ${cityTo.name}
               </button>
             </c:when>
           <c:otherwise>
             <input type="hidden" name="city-to-id" id="city-to-id">
              <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                 data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="city-to-btn">
                 <fmt:message key="city.to"/>
              </button>
           </c:otherwise>
          </c:choose>
          <ul class="dropdown-menu scrollable-menu" aria-labelledby="dropdownCity" id="city-to-dropdown"
            style="height: auto; max-height: 200px; overflow-x: hidden;">
            <c:forEach items="${listCity}" var="city">
               <li><button class="dropdown-item" type="button" value="${city.id}">${city.name}</button></li>
            </c:forEach>
          </ul>
        </div>
           <script>
              $(function(){
              $("#city-to-dropdown").on('click', 'li button', function(){
              $("#city-to-btn").text($(this).text());
              $("#city-to-btn").val($(this).text());
              $("#city-to-id").val($(this).val());
              });
              });
              </script>
        </div>
       </div>

        <div class="row g-3">
          <div class="dropdown col-md-5">
             <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="submit"/></button>
          </div>

        </div>
      </form>


      <form class="file-upload" method="GET" action="controller">
            <input type="hidden" name="action" value="directions">
            <div class="row g-3">

            <div class="gap-2">
               <button type="submit" class="btn btn-danger btn-lg"id="clean"><fmt:message key="clean"/></button>

            </div>
            </div>
          <!-- Form END -->
          </form>


       <c:if test="${not empty requestScope.listRoute}">
          <form method="POST" action="controller">
            <input type="hidden" name="action" value="directions">
            <input type="hidden" name="city-from-id" id="city-from-id" value="${cityFrom.id}">
            <input type="hidden" name="city-to-id" id="city-to-id" value="${cityTo.id}">
             <table class="table table-bordered" style="background-color: #e3f2fd;">
               <thead>
                  <tr >
                     <th scope="col"><fmt:message key="id"/>
                     <c:if test="${requestScope.sort ne 'id'}">
                                                               <a href="controller?action=directions&sort=id&order=ASC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                  <i class="bi bi-arrow-down-up"></i>
                                                               </a>
                                                           </c:if>
                                                           <c:if test="${requestScope.sort eq 'id' && requestScope.order eq 'ASC'}">
                                                              <a href="controller?action=directions&sort=id&order=DESC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                 <i class="bi bi-arrow-up"></i>
                                                              </a>
                                                           </c:if>
                                                           <c:if test="${requestScope.sort eq 'id' && requestScope.order eq 'DESC'}">
                                                              <a href="controller?action=directions&sort=id&order=ASC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                  <i class="bi bi-arrow-down"></i>
                                                              </a>
                                                           </c:if></th>
                     <th scope="col"><fmt:message key="city.from"/>
                     <c:if test="${requestScope.sort ne 'city_from_id'}">
                                                               <a href="controller?action=directions&sort=city_from_id&order=ASC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                  <i class="bi bi-arrow-down-up"></i>
                                                               </a>
                                                           </c:if>
                                                           <c:if test="${requestScope.sort eq 'city_from_id' && requestScope.order eq 'ASC'}">
                                                              <a href="controller?action=directions&sort=city_from_id&order=DESC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                 <i class="bi bi-arrow-up"></i>
                                                              </a>
                                                           </c:if>
                                                           <c:if test="${requestScope.sort eq 'city_from_id' && requestScope.order eq 'DESC'}">
                                                              <a href="controller?action=directions&sort=city_from_id&order=ASC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                  <i class="bi bi-arrow-down"></i>
                                                              </a>
                                                           </c:if></th>
                     <th scope="col"><fmt:message key="city.to"/>
                     <c:if test="${requestScope.sort ne 'city_to_id'}">
                                                               <a href="controller?action=directions&sort=city_to_id&order=ASC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                  <i class="bi bi-arrow-down-up"></i>
                                                               </a>
                                                           </c:if>
                                                           <c:if test="${requestScope.sort eq 'city_to_id' && requestScope.order eq 'ASC'}">
                                                              <a href="controller?action=directions&sort=city_to_id&order=DESC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                 <i class="bi bi-arrow-up"></i>
                                                              </a>
                                                           </c:if>
                                                           <c:if test="${requestScope.sort eq 'city_to_id' && requestScope.order eq 'DESC'}">
                                                              <a href="controller?action=directions&sort=city_to_id&order=ASC&offset=${offset}&records=${records}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}">
                                                                  <i class="bi bi-arrow-down"></i>
                                                              </a>
                                                           </c:if></th>
                     <th scope="col"><fmt:message key="distance"/></th>
                     <th scope="col"><fmt:message key="estimate.delivery.terms"/></th>
                  </tr>
               </thead>

               <tbody>
                 <c:forEach var="route" items="${requestScope.listRoute}">
                   <tr class="table-light">
                     <td><c:out value="${route.id}"/></td>
                     <td><c:out value="${route.cityFrom}"/></td>
                     <td><c:out value="${route.cityTo}"/></td>
                     <td><c:out value="${route.distance}"/></td>
                     <td><custom:date terms="${route.terms}"/></td>
                   </tr>
                 </c:forEach>
               </tbody>
              </table>

     <jsp:include page="../fragments/pagination.jsp"/>

          </form>
       </c:if>




  </div>
 </div>
</div>
</body>
</html>
