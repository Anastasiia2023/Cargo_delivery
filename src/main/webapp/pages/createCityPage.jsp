<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="resources"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>

<title>Create CITY</title>
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
        <h3><fmt:message key="create.city"/></h3>
	    <hr>
	  </div>

      <form method="POST" action="controller">
        <input type="hidden" name="action" value="create-city">
          <div class="row mb-5 gx-5">
	         <!-- Add City -->
	          <div class="col-xxl-8 mb-5 mb-xxl-0">
		        <div class="bg-secondary-soft px-4 py-5 rounded">
		           <div class="row g-2">
			         <h4 class="mb-4 mt-0"><fmt:message key="add.city"/></h4>
			         <!-- City to add -->
			          <div class="col-md-6">
			             <label for="City" ><fmt:message key="city"/></label>
                          <input type="text" class="form-control" name="city"
                          placeholder=<fmt:message key="enter.city"/>>
                      </div>
                      <!-- Country
                       <div class="col-md-6">
                      			             <label for="Country" ><fmt:message key="country"/></label>
                                                <input type="text" class="form-control" name="country"
                                                placeholder=<fmt:message key="enter.country"/>>
                                            </div> -->
                      <!-- button -->
                      <div class="gap-3 d-md-flex justify-content-md text-center">
                        <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
                      </div>
                   </div>
                </div>
              </div>
          </div>
      </form>

      <div class="container">
          <!-- <form method="GET" action="controller">
          <input type="hidden" name="action" value="delete-city">-->
          <!-- Delete City -->
        <div class="row g-3">
           <h4 class="mb-4 mt-0"><fmt:message key="delete.city"/></h4>
           <!-- City to Delete -->
          <div class="form-group row">
            <div class="dropdown col-md-5">
               <button type="button" class="btn dropdown-toggle" style="background-color: #e3f2fd;"
                 data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" >
                 <fmt:message key="city"/>
               </button>
                <ul class="dropdown-menu scrollable-menu" aria-labelledby="dropdownCity"
                  style="height: auto; max-height: 200px; overflow-x: hidden;">
                  <c:forEach items="${listCity}" var="city">
                    <li><button class="dropdown-item" type="button" value="${city.id}">${city.name}</button></li>
                  </c:forEach>
                </ul>
            </div>

            <script>
              $(function(){
              $(".dropdown-menu").on('click', 'li button', function(){
              $(this).parents(".dropdown").find('.btn').text($(this).text());
              $(this).parents(".dropdown").find('.btn').val($(this).text());
              $("#city-id").val($(this).val());
              });
              });
            </script>
          </div>
          <form method="POST" action="controller">
            <input type="hidden" name="action" value="delete-city">
            <div class="gap-3 d-md-flex justify-content-md text-center">
              <input type="hidden" name="city-id" id="city-id">
              <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
            </div>
          </form>
        </div>
      </div>
   </div>
 </div>
</div>
</body>
</html>