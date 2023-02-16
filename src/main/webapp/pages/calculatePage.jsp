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

<div class="container">
 <div class="row">
  <div class="col-12">
    <!-- Page title -->
	<div class="my-5">
      <h3><fmt:message key="calculation.form"/></h3>
	  <hr>
	</div>
	<!-- Form START -->
    <form class="file-upload" method="POST" action="controller">
      <input type="hidden" name="action" value="calculate">
      <c:set var="error" value="${requestScope.error}"/>
      <c:set var="routes" value="${requestScope.listRoute}"/>
      <c:set var="r" value='1'/>
      <div class="row mb-3 gx-3">
        <!-- Route -->
    	<div class="dropdown col-md-6">
    	  <c:choose>
            <c:when test="${not empty requestScope.chosenRoute}">
              <input type="hidden" name="route-id" id="route-id" value="${parcel.routeId}">
              <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="route-btn" value="${parcel.routeId}">
                ${chosenRoute}
              </button>
            </c:when>
            <c:otherwise>
              <input type="hidden" name="route-id" id="route-id">
               <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                 data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="route-btn">
                 <fmt:message key="route"/>
               </button>
            </c:otherwise>
          </c:choose>
          <ul class="dropdown-menu scrollable-menu" aria-labelledby="dropdownRoute" id="route-dropdown"
            style="height: auto; max-height: 200px; overflow-x: hidden;">
            <c:forEach items="${listRoute}" var="route">
               <li><button class="dropdown-item" type="button" value="${route.id}">${route.cityFrom}-${route.cityTo}</button></li>
            </c:forEach>
          </ul>
        </div>
        <script>
          $(function(){
          $("#route-dropdown").on('click', 'li button', function(){
          $("#route-btn").text($(this).text());
          $("#route-btn").val($(this).text());
          $("#route-id").val($(this).val());
          });
          });
        </script>
      </div>
  </div>

        <!-- Cost -->
        <div class="col-md-2">
          <label class="form-label"><fmt:message key="cost"/></label>
          <c:choose>
            <c:when test="${not empty requestScope.parcel}">
              <input type="text" class="form-control" placeholder="" aria-label="Cost" id="cost" name="user-cost" value="${parcel.cost}">
            </c:when>
            <c:otherwise>
              <input type="text" class="form-control" placeholder="" aria-label="Cost" id="cost" name="user-cost" value="">
            </c:otherwise>
          </c:choose>
        </div>

        <!-- Width -->
         <div class="col-md-2">
           <label class="form-label"><fmt:message key="width"/></label>
           <c:choose>
              <c:when test="${not empty requestScope.parcel}">
                <input type="text" class="form-control" placeholder="" aria-label="Width" id="width" name="width" value="${parcel.width}">
              </c:when>
              <c:otherwise>
                <input type="text" class="form-control" placeholder="" aria-label="Width" id="width" name="width" value="">
              </c:otherwise>
           </c:choose>
         </div>

         <!-- Length -->
         <div class="col-md-2">
           <label class="form-label"><fmt:message key="length"/></label>
           <c:choose>
              <c:when test="${not empty requestScope.parcel}">
                <input type="text" class="form-control" placeholder="" aria-label="Length" id="length" name="length" value="${parcel.length}">
              </c:when>
              <c:otherwise>
                <input type="text" class="form-control" placeholder="" aria-label="Length" id="length" name="length" value="">
              </c:otherwise>
           </c:choose>
         </div>

        <!-- Height -->
        <div class="col-md-2">
          <label class="form-label"><fmt:message key="height"/></label>
          <c:choose>
             <c:when test="${not empty requestScope.parcel}">
                <input type="text" class="form-control" placeholder="" aria-label="Height" id="height" name="height" value="${parcel.height}">
             </c:when>
             <c:otherwise>
                <input type="text" class="form-control" placeholder="" aria-label="Height" id="height" name="height" value="">
             </c:otherwise>
          </c:choose>
        </div>

        <!-- Weight -->
        <div class="col-md-2">
           <label class="form-label"><fmt:message key="weight"/></label>
           <c:choose>
              <c:when test="${not empty requestScope.parcel}">
                 <input type="text" class="form-control" placeholder="" aria-label="Weight" id="weight" name="weight" value="${parcel.weight}">
              </c:when>
              <c:otherwise>
                 <input type="text" class="form-control" placeholder="" aria-label="Weight" id="weight" name="weight" value="">
              </c:otherwise>
           </c:choose>
        </div>

        <!-- Package Type-->
        <div class="my-3">
          <div class="col-md-2">
             <p class="font-weight-normal"><fmt:message key="package.type"/></p>
          </div>
          <div class="col-md-4">
             <select name="package-type" class="form-select mt-2" style="background-color: #e3f2fd;">
                <c:if test="${not empty requestScope.parcel}">
                  <option selected value="${parcel.packageType}">${parcel.packageType}</option>
                </c:if>
                <option value="Package"><fmt:message key="package"/></option>
                <option value="Cargo"><fmt:message key="cargo"/></option>
                <option value="Documents"><fmt:message key="documents"/></option>
             </select>
          </div>
        </div>
        <!-- Package Service-->
        <div class="col-md-2">
           <p class="font-weight-normal"><fmt:message key="package.service"/></p>
        </div>
        <div class="col-md-1">
          <c:choose>
             <c:when test="${not empty requestScope.parcel}">
               <div class="form-check form-check-inline">
                  <label class="form-check-label" for="inlineCheckbox1"></label>
                     <c:choose>
                        <c:when test="${parcel.packageService}">
                          <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="package-service" val="" checked="checked">
                        </c:when>
                         <c:otherwise>
                           <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="package-service" val="">
                         </c:otherwise>
                     </c:choose>
               </div>
             </c:when>
              <c:otherwise>
                <div class="form-check form-check-inline">
                   <label class="form-check-label" for="inlineCheckbox1"></label>
                   <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="package-service" val="">
                </div>
              </c:otherwise>
          </c:choose>
        </div>

        <script>
          $(function(){
          $("#inlineCheckbox1").on('click', 'input', function(){
          console.log($("#inlineCheckbox1").is(':checked'));
           if($("#inlineCheckbox1").is(':checked')){
           $("#inlineCheckbox1").val('1');
           } else {
           $("#inlineCheckbox1").val('0');
           }
           });
           });
        </script>

        <!-- Total Cost-->
        <div class="my-2">
          <c:if test="${not empty requestScope.totalCost}">
            <div class="col-md-2">
              <label class="form-label"><fmt:message key="total.cost"/></label>
              <input type="text" class="form-control" placeholder="" aria-label="totalCost" value="${requestScope.totalCost}">
            </div>
          </c:if>
        </div>

         <!-- Button -->
         <div class="my-2">
           <div class="gap-2">
             <button type="submit" class="btn btn-primary btn-lg" id="calculate"><fmt:message key="calculate"/></button>
           </div>
         </div>

    </form>
    <form class="file-upload" method="GET" action="controller">
      <input type="hidden" name="action" value="calculate">
      <div class="gap-2">
         <button type="submit" class="btn btn-danger btn-lg"id="clean"><fmt:message key="clean"/></button>
      </div>
    <!-- Form END -->
    </form>
</div>
</div>
</div>













