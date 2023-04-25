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
<link rel="stylesheet" href="../css/bootstrap-icons.css">
</head>



<div class="container">
 <div class="row">
   <div class="col-12">
    <!-- Page title -->
	<div class="my-5">
      <h3><fmt:message key="orders"/></h3>
	  <hr>
	</div>
	<!-- Form START -->
        <form method="POST" action="controller">
          <input type="hidden" name="action" value="manage-order">
      <div class="row mb-3 gx-3">
        <div class="row g-3">
          <div class="form-group col-md-2" >
            <!-- Status -->
            <select class="selectpicker form-control" name="order-status" id="order-status" style="background-color: #e3f2fd;">
              <c:choose>
                <c:when test="${empty requestScope.orderStatus}">
                   <option disabled selected value="">Order Status</option>
                </c:when>
                <c:otherwise>
                   <option selected value="${orderStatus.value}">${orderStatus}</option>
                </c:otherwise>
              </c:choose>
              <c:forEach var="status" items="${requestScope.orderStatuses}">
                <c:if test="${requestScope.orderStatus ne status}">
                   <option value="${status.value}">${status}</option>
                </c:if>
              </c:forEach>
            </select>
          </div>
        </div>
      </div>

      <!-- City From -->
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

          <!-- City To -->
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

      <!-- Date From -->
      <div class="row mb-5 gx-5">
        <div class="row g-3">
          <div class="col-md-3">
            <label fo class="form-label">Date From</label>
            <input type="date" class="form-control" placeholder="" aria-label="Date From" value="${dateFrom}" name="date-from" id="date-from">
          </div>
          <!-- Date To -->
          <div class="col-md-3">
            <label fo class="form-label">Date To</label>
            <input type="date" class="form-control" placeholder="" aria-label="Date To" value="${dateTo}" name="date-to" id="date-to">
          </div>
        </div>
      </div>

      <!-- Button -->
      <div class="row mb-5 gx-5">
        <div class="row g-3">
          <div class="dropdown col-md-2">
            <button type="submit" class="btn btn-primary"><fmt:message key="submit"/></button>
            <button type="submit" class="btn btn-danger" id="clean"><fmt:message key="clean"/></button>
          </div>
        </div>
      </div>

        <script>
          $("#clean").click(function(){
          $("#order-status").val("");
          $("#date-from").val("");
          $("#date-to").val("");
          $("#city-from-btn").val("");
          $("#city-from-id").val("");
          $("#city-to-btn").val("");
          $("#city-to-id").val("");
          });
        </script>
</form>
       <!-- Table -->

       <div class="row mb-5 gx-5">
        <table class="table table-bordered" style="background-color: #e3f2fd;">
          <thead>
            <tr>
              <th scope="col"><fmt:message key="id"/>
              <c:if test="${requestScope.sort ne 'id'}">
                                       <a href="controller?action=manage-order&sort=id&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                          <i class="bi bi-arrow-down-up"></i>
                                       </a>
                                   </c:if>
                                   <c:if test="${requestScope.sort eq 'id' && requestScope.order eq 'ASC'}">
                                      <a href="controller?action=manage-order&sort=id&order=DESC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                         <i class="bi bi-arrow-up"></i>
                                      </a>
                                   </c:if>
                                   <c:if test="${requestScope.sort eq 'id' && requestScope.order eq 'DESC'}">
                                      <a href="controller?action=manage-order&sort=id&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                          <i class="bi bi-arrow-down"></i>
                                      </a>
                                   </c:if></th>
              <th scope="col"><fmt:message key="route"/>
              <c:if test="${requestScope.sort ne 'route_id'}">
                                                        <a href="controller?action=manage-order&sort=route_id&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down-up"></i>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'route_id' && requestScope.order eq 'ASC'}">
                                                       <a href="controller?action=manage-order&sort=route_id&order=DESC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                          <i class="bi bi-arrow-up"></i>
                                                       </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'route_id' && requestScope.order eq 'DESC'}">
                                                       <a href="controller?action=manage-order&sort=route_id&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down"></i>
                                                       </a>
                                                    </c:if></th>
              <th scope="col"><fmt:message key="creation.date"/>
              <c:if test="${requestScope.sort ne 'creation_date'}">
                                                        <a href="controller?action=manage-order&sort=creation_date&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down-up"></i>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'creation_date' && requestScope.order eq 'ASC'}">
                                                       <a href="controller?action=manage-order&sort=creation_date&order=DESC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                          <i class="bi bi-arrow-up"></i>
                                                       </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'creation_date' && requestScope.order eq 'DESC'}">
                                                       <a href="controller?action=manage-order&sort=creation_date&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down"></i>
                                                       </a>
                                                    </c:if></th>
              <th scope="col"><fmt:message key="estimate.delivery.terms"/>
              <c:if test="${requestScope.sort ne 'delivery_date'}">
                                                        <a href="controller?action=manage-order&sort=delivery_date&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down-up"></i>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'delivery_date' && requestScope.order eq 'ASC'}">
                                                       <a href="controller?action=manage-order&sort=delivery_date&order=DESC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                          <i class="bi bi-arrow-up"></i>
                                                       </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'delivery_date' && requestScope.order eq 'DESC'}">
                                                       <a href="controller?action=manage-order&sort=delivery_date&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down"></i>
                                                       </a>
                                                    </c:if></th>
              <th scope="col"><fmt:message key="status"/>
              <c:if test="${requestScope.sort ne 'statuses'}">
                                                        <a href="controller?action=manage-order&sort=statuses&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down-up"></i>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'statuses' && requestScope.order eq 'ASC'}">
                                                       <a href="controller?action=manage-order&sort=statuses&order=DESC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                          <i class="bi bi-arrow-up"></i>
                                                       </a>
                                                    </c:if>
                                                    <c:if test="${requestScope.sort eq 'statuses' && requestScope.order eq 'DESC'}">
                                                       <a href="controller?action=manage-order&sort=statuses&order=ASC&offset=${offset}&records=${records}&order-status=${orderStatus.value}&city-from-id=${cityFrom.id}&city-to-id=${cityTo.id}&date-from=${dateFrom}&date-to=${dateTo}">
                                                           <i class="bi bi-arrow-down"></i>
                                                       </a>
                                                    </c:if></th>
              <th scope="col"><fmt:message key="delivery.cost"/></th>
              <th scope="col"><fmt:message key="invoice"/></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="order" items="${requestScope.listOrders}">
              <tr class="table-light" id="${order.id}">
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.cityFrom}-${order.cityTo}"/></td>
                <td><c:out value="${order.creationDate}"/></td>
                <td><c:out value="${order.estimatedDeliveryDate}"/></td>
                <td><c:out value="${order.status}"/></td>
                <td><c:out value="${order.price}"/></td>
                <td><c:choose>
                <c:when test="${order.invoiced}">
                <form method="GET" action="controller">
                                        <input type="hidden" name="action" value="invoice">
                                                  <input type="hidden" name="order-id" id="order-id" value="${order.id}">
                                                  <input type="hidden" name="user-id" id="user-id"value="${order.userId}">
                                        <button type="submit" class="btn btn-primary">Download Invoice</button>
                                        </form>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="controller">
                        <input type="hidden" name="action" value="invoice">
                                  <input type="hidden" name="order-id" id="order-id" value="${order.id}">
                                  <input type="hidden" name="user-id" id="user-id"value="${order.userId}">
                        <button type="submit" class="btn btn-primary">Generate Invoice</button>
                        </form>
                </c:otherwise>

                </c:choose>
                <c:if test="${order.status == 'Payed'}">
                                                    <form method="GET" action="controller">
                                                                                  <input type="hidden" name="action" value="pay">
                                                                                       <input type="hidden" name="order-id" id="order-id" value="${order.id}">
                                                                                  <button type="submit" class="btn btn-primary">Confirm Payment</button>
                                                                            </form>
                                                                            </c:if></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

       </div>

    <form method="POST" action="controller">
      <input type="hidden" name="action" value="manage-order">
       <input type="hidden" name="order-status" id="order-status-pag">
       <input type="hidden" name="city-from-id" id="city-from-id-pag">
        <input type="hidden" name="city-to-id" id="city-to-id-pag">
       <input type="hidden" name="date-from" id="date-from-pag">
       <input type="hidden" name="date-to" id="date-to-pag">
        <jsp:include page="../fragments/pagination.jsp"/>
    </form>
     <script>
              $(".btn").click(function(){
                  $("#order-status-pag").val($("#order-status").val());
                  $("#date-from-pag").val($("#date-from").val());
                  $("#date-to-pag").val($("#date-to").val());
                  $("#city-from-id-pag").val($("#city-from-id").val());
                  $("#city-to-id-pag").val($("#city-to-id").val());
              });
            </script>
</div>
</div>
</div>

<form method="GET" action="controller">
 <input type="hidden" name="action" value="report-pdf">
 <input type="hidden" name="order-status" id="order-status-report">
 <input type="hidden" name="city-from-id" id="city-from-id-report">
 <input type="hidden" name="city-to-id" id="city-to-id-report">
 <input type="hidden" name="date-from" id="date-from-report">
 <input type="hidden" name="date-to" id="date-to-report">
 <div class="gap-3 d-md-flex justify-content-md-end text-center">
   <button type="submit" class="btn btn-primary btn-lg" id="download"><fmt:message key="download.report"/></button>
 </div>
</form>
    <script>
      $("#download").click(function(){
      $("#order-status-report").val($("#order-status").val());
      $("#city-from-id-report").val($("#city-from-id").val());
      $("#city-to-id-report").val($("#city-to-id").val());
      $("#date-from-report").val($("#date-from").val());
      $("#date-to-report").val($("#date-to").val());
      });
    </script>




























