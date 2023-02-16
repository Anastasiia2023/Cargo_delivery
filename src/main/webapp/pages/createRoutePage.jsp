<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Main Page</title>
<script src="../js/popper.min.js"></script>
<script src="../js/jquery-3.6.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
</head>
<body>

<div class="container">
 <div class="row">
   <div class="col-12">
	 <!-- Page title -->
	  <div class="my-5">
        <h3><fmt:message key="create.route"/></h3>
	    <hr>
	  </div>


      <!--<form method="POST" action="controller">
        <input type="hidden" name="action" value="create-city">

      </form>-->

      <div class="container">

          <!-- <form method="GET" action="controller">
          <input type="hidden" name="action" value="">-->
          <!-- City From Route -->
        <div class="row g-3">
        <h5 class="mb-4 mt-0"><fmt:message key="city.from"/></h5>

 <!-- Route City From -->
          <div class="form-group row">
            <div class="dropdown col-md-5">
               <button type="button" class="btn dropdown-toggle" style="background-color: #e3f2fd;"
                 data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="city-from-btn">
                  <fmt:message key="city.from"/>
               </button>
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
          </div>





          <div class="container">
          <div class="row mb-3 gx-3">
                    <!-- <form method="GET" action="controller">
                    <input type="hidden" name="action" value="">-->
                    <!-- City  To -->
                  <div class="row g-3">
                     <h5 class="mb-4 mt-0"><fmt:message key="city.to"/></h5>
                     <!-- City to Delete -->
                    <div class="form-group row">
                      <div class="dropdown col-md-5">
                         <button type="button" class="btn dropdown-toggle" style="background-color: #e3f2fd;"
                           data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="city-to-btn" >
                            <fmt:message key="city.to"/>
                         </button>
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
</div>
</div>








          <form method="POST" action="controller">
            <input type="hidden" name="action" value="create-route">
            <div class="row g-3">

            <div class="col-md-4">
                                			   <label class="form-label"><fmt:message key="distance"/></label>
                                			   <input type="text" class="form-control" placeholder="" aria-label="Distance" name="distance">
                                			</div>

                                			<div class="col-md-4">
                                                 <label class="form-label"><fmt:message key="terms"/></label>
                                                     <input type="text" class="form-control" placeholder="" aria-label="Terms" name="terms">
                                                            </div>



            <div class="gap-3 d-md-flex justify-content-md text-center">
              <input type="hidden" name="city-to-id" id="city-to-id">
              <input type="hidden" name="city-from-id" id="city-from-id">
              <button type="submit" class="btn btn-primary"><fmt:message key="create"/></button>
            </div>
          </form>

          <c:if test="${not empty requestScope.listRoute}">
                    <form method="POST" action="controller">
                      <input type="hidden" name="action" value="routes">
                      <input type="hidden" name="city-from-id" id="city-from-id" value="${cityFrom.id}">
                      <input type="hidden" name="city-to-id" id="city-to-id" value="${cityTo.id}">
                       <table class="table table-bordered" style="background-color: #e3f2fd;">
                         <thead>
                            <tr >
                               <th scope="col"><fmt:message key="id"/></th>
                               <th scope="col"><fmt:message key="city.from"/></th>
                               <th scope="col"><fmt:message key="city.to"/></th>
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
                               <td><c:out value="${route.terms}"/> day(s)</td>
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
 </div>
</div>
</div>
</div>
</div>
</body>
</html>