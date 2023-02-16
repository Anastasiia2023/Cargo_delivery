<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="resources"/>

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


<div class="container">
 <div class="row">
  <div class="col-12">
    <!-- Page title -->
	<div class="my-5">
      <h3><fmt:message key="create.order"/></h3>
	  <hr>
	</div>
	<!-- Form START -->
    <form class="file-upload" method="POST" action="controller">
      <input type="hidden" name="action" value="create-order">

      <!-- Delivery Type
      <div class="col-md-2">
        <p class="font-weight-normal"><fmt:message key="delivery.type"/></p>
        </div>
        <div class="dropdown col-md-5">
        <button type="button" class="btn dropdown-toggle" style="background-color: #e3f2fd;"
        data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" >
        Delivery Type
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
        <li><button class="dropdown-item" type="button">Address-Address</button></li>
        <li><button class="dropdown-item" type="button">Address-Department</button></li>
        <li><button class="dropdown-item" type="button">Department-Department</button></li>
        <li><button class="dropdown-item" type="button">Department-Address</button></li>
        </ul>
      </div>
      <script>
       $(function(){
       $(".dropdown-menu").on('click', 'li button', function(){
       $(this).parents(".dropdown").find('.btn').text($(this).text());
       $(this).parents(".dropdown").find('.btn').val($(this).text());
       });
       });
      </script> -->

      <c:set var="error" value="${requestScope.error}"/>
      <c:set var="routes" value="${requestScope.listRoute}"/>
      <div class="row mb-3 gx-3">

      <!-- Route -->
      <div class="dropdown col-md-6">
         <input type="hidden" name="route-id" id="route-id">
         <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
           data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="route-btn">
           <fmt:message key="route"/>
         </button>
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
          <input type="text" class="form-control" placeholder="" aria-label="Cost" id="cost" name="user-cost" value="">
        </div>

        <!-- Width -->
        <div class="col-md-2">
          <label class="form-label"><fmt:message key="width"/></label>
          <input type="text" class="form-control" placeholder="" aria-label="Width" id="width" name="width" value="">
        </div>

        <!-- Length -->
        <div class="col-md-2">
          <label class="form-label"><fmt:message key="length"/></label>
          <input type="text" class="form-control" placeholder="" aria-label="Length" id="length" name="length" value="">
        </div>

        <!-- Height -->
        <div class="col-md-2">
          <label class="form-label"><fmt:message key="height"/></label>
          <input type="text" class="form-control" placeholder="" aria-label="Height" id="height" name="height" value="">
        </div>

        <!-- Weight -->
        <div class="col-md-2">
          <label class="form-label"><fmt:message key="weight"/></label>
           <input type="text" class="form-control" placeholder="" aria-label="Weight" id="weight" name="weight" value="">
        </div>

        <!-- Package Type-->
        <div class="my-3">
          <div class="col-md-2">
             <p class="font-weight-normal"><fmt:message key="package.type"/></p>
          </div>
          <div class="col-md-4">
             <select name="package-type" class="form-select mt-2" style="background-color: #e3f2fd;">
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
           <div class="form-check form-check-inline">
              <label class="form-check-label" for="inlineCheckbox1"></label>
              <input class="form-check-input" type="checkbox" id="inlineCheckbox1" name="package-service" val="">
           </div>
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

        <!-- Button -->
        <div class="gap-3 d-md-flex justify-content-md-end text-center">
           <button type="submit" class="btn btn-primary btn-lg" id="create-order"><fmt:message key="create.order"/></button>
        </div>
        <!-- Form END -->
    </form>
 </div>
</div>
</div>
