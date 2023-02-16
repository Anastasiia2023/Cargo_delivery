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
<meta charset="UTF-8">
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
      <h3><fmt:message key="manage.role"/></h3>
	  <hr>
	</div>
	<!-- Form START -->
<form class="file-upload" method="GET" action="controller">
                                            <input type="hidden" name="action" value="manage-role">
	<!-- Role -->

            <div class="row mb-5 gx-5">
              <div class="row g-3">
                <div class="dropdown col-md-3">
                  <c:choose>
                   <c:when test="${not empty requestScope.userRole}">
                   <input type="hidden" name="user-role" id="user-role" value="${userRole.value}">
                    <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                     data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="user-role-btn" value="${userRole}">
                     ${userRole}
                    </button>
                   </c:when>
                 <c:otherwise>
                   <input type="hidden" name="user-role" id="user-role">
                    <button type="button" class="btn btn-lg dropdown-toggle" style="background-color: #e3f2fd;"
                       data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false" id="user-role-btn">
                       <fmt:message key="role"/>
                    </button>
                 </c:otherwise>
                 </c:choose>
                 <ul class="dropdown-menu scrollable-menu" aria-labelledby="dropdownRole" id="user-role-dropdown"
                                 style="height: auto; max-height: 200px; overflow-x: hidden;">

                    <c:forEach items="${roles}" var="role">
                      <li><button class="dropdown-item" type="button" value="${role.value}">${role}</button></li>

                    </c:forEach>
                 </ul>
                </div>
                <script>
                  $(function(){
                  $("#user-role-dropdown").on('click', 'li button', function(){
                  $("#user-role-btn").text($(this).text());
                  $("#user-role-btn").val($(this).text());
                  $("#user-role").val($(this).val());
                  });
                  });
                </script>

                <div class="row g-3">
                          <div class="dropdown col-md-5">
                             <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="submit"/></button>
                          </div>

                        </div>
                      </form>


                      <form class="file-upload" method="GET" action="controller">
                            <input type="hidden" name="action" value="manage-role">
                            <div class="row g-3">

                            <div class="gap-2">
                               <button type="submit" class="btn btn-danger btn-lg"id="clean"><fmt:message key="clean"/></button>

                            </div>
                            </div>
                          <!-- Form END -->
                          </form>

                          <c:if test="${not empty requestScope.listUsers}">
                                       <table class="table table-bordered" style="background-color: #e3f2fd;">
                                         <thead>
                                            <tr >
                                               <th scope="col"><fmt:message key="id"/></th>
                                               <th scope="col"><fmt:message key="name.surname"/></th>
                                               <th scope="col"><fmt:message key="role"/></th>
                                               <th scope="col"><fmt:message key="action"/></th>
                                            </tr>
                                         </thead>

                                         <tbody>
                                           <c:forEach var="user" items="${requestScope.listUsers}">
                                             <tr class="table-light">
                                               <td><c:out value="${user.id}"/></td>
                                               <td><c:out value="${user.name} ${user.surname}"/></td>
                                               <td><c:out value="${roles[user.role]}"/> ${user.role}</td>
                                               <td><c:if test="${user.role == 2}">
                                                                   <form class="file-upload" method="POST" action="controller">
                                                                                                                              <input type="hidden" name="action" value="manage-role">
                                                                                                     <input type="hidden" name="user-role" id="user-role" value="${user.role}">
                                                                                                     <input type="hidden" name="user-id" id="user-id"value="${user.id}">
                                                                                           <button type="submit" class="btn btn-danger btn-sm rounded-0">Set Manager</button>
                                                                                           </form>
                                                                   </c:if>
                                                                   <c:if test="${user.role == 3}">
                                                                       <form class="file-upload" method="POST" action="controller">
                                                                                                                                  <input type="hidden" name="action" value="manage-role">
                                                                                     <input type="hidden" name="user-role" id="user-role" value="${user.role}">
                                                                                   <input type="hidden" name="user-id" id="user-id"value="${user.id}">
                                                                           <button type="submit" class="btn btn-danger btn-sm rounded-0">Set User</button>
                                                                           </form>
                                                                   </c:if></td>

                                             </tr>
                                           </c:forEach>
                                         </tbody>
                                        </table>

                                    <form class="file-upload" method="GET" action="controller">
                                            <input type="hidden" name="action" value="manage-role">
                                            <input type="hidden" name="user-role" id="user-role-pag">
                               <jsp:include page="../fragments/pagination.jsp"/>
                                        </form>
                                    <script>
                                                  $(".btn").click(function(){
                                                      $("#user-role-pag").val($("#user-role").val());
                                                  });
                                                </script>
                                 </c:if>





                            </div>
                           </div>
                          </div>
                          </body>
                          </html>