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
      <h3><fmt:message key="my.orders"/></h3>
	  <hr>
	</div>
	<!-- Form START -->
	<form method="POST" action="controller">
	  <input type="hidden" name="action" value="my-order">
      <div class="row mb-3 gx-3">
        <!-- Status -->
        <div class="row g-3">
          <div class="form-group col-md-2" >
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

        <!-- Date From -->
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

        <!-- Button -->
        <div class="row g-3">
          <div class="dropdown col-md-2">
            <button type="submit" class="btn btn-primary" >Submit</button>
            <button type="submit" class="btn btn-danger" id="clean"><fmt:message key="clean"/></button>
          </div>
        </div>

        <script>
          $("#clean").click(function(){
          $("#order-status").val("");
          $("#date-from").val("");
          $("#date-to").val("");
          });
        </script>
        </div>


</form>

        <div class="row g-3">
          <table  class="table table-bordered" style="background-color: #e3f2fd;">
            <thead>
              <tr>
                 <th scope="col"><fmt:message key="id"/></th>
                 <th scope="col" ><fmt:message key="route"/></th>
                 <th scope="col" ><fmt:message key="creation.date"/></th>
                 <th scope="col"><fmt:message key="estimate.delivery.terms"/></th>
                 <th scope="col"><fmt:message key="status"/></th>
                 <th scope="col"><fmt:message key="delivery.cost"/></th>
                 <th scope="col"><fmt:message key="invoice"/></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="order" items="${requestScope.listOrders}">
                <tr class="table-light">
                   <td><c:out value="${order.id}"/></td>
                   <td><c:out value="${order.cityFrom}-${order.cityTo}"/></td>
                   <td><c:out value="${order.creationDate}"/></td>
                   <td><c:out value="${order.estimatedDeliveryDate}"/></td>
                    <td><c:out value="${order.status}"/></td>
                    <td><c:out value="${order.price}"/></td>
                    <td><c:if test="${order.invoiced}">
                        <form method="GET" action="controller">
                        <input type="hidden" name="action" value="invoice">
                            <input type="hidden" name="order-id" id="order-id" value="${order.id}">
                            <input type="hidden" name="user-id" id="user-id"value="${order.userId}">
                        <button type="submit" class="btn btn-primary">Download Invoice</button>
                        </form>
                    </c:if>
                    <c:if test="${order.waitingPayment}">
                    <form method="POST" action="controller">
                                                  <input type="hidden" name="action" value="pay">
                                                       <input type="hidden" name="order-id" id="order-id" value="${order.id}">
                                                  <button type="submit" class="btn btn-primary">Complete Payment</button>
                                            </form>
                                            </c:if>
                    </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>

        </div>
        <form method="POST" action="controller">
        	  <input type="hidden" name="action" value="my-order">
        	         <input type="hidden" name="order-status" id="order-status-pag">
                     <input type="hidden" name="date-from" id="date-from-pag">
                     <input type="hidden" name="date-to" id="date-to-pag">
               <jsp:include page="../fragments/pagination.jsp"/>
        </form>
         <script>
                      $(".btn").click(function(){
                          $("#order-status-pag").val($("#order-status").val());
                          $("#date-from-pag").val($("#date-from").val());
                          $("#date-to-pag").val($("#date-to").val());
                      });
                    </script>




          </div>
         </div>
        </div>





