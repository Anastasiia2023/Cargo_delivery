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

<c:if test="${not empty requestScope.message}">

      <span class="text-success"><fmt:message key="${requestScope.message}"/></span>

</c:if><br>

<c:if test="${not empty requestScope.error}">
POPUP
      <span class="text-success"><fmt:message key="${requestScope.error}"/></span>
</c:if><br>




<div class="container">
 <div class="row">
  <div class="col-12">
	<!-- Page title -->
	<div class="my-5">
     <h3><fmt:message key="my.profile"/></h3>
	 <hr>
	</div>
	<!-- Form START -->
	<form class="file-upload" method="POST" action="controller">
      <input type="hidden" name="action" value="delete-user">
      <div class="row mb-5 gx-5">
      <!-- Delete button -->
        <div class="col-xxl-8 mb-5 mb-xxl-0">
          <div class="gap-3 d-md-flex justify-content-md-end text-center ">
             <button type="submit" class="btn btn-danger btn-lg "><fmt:message key="delete.account"/></button>
          </div>
        </div>
      </div>
    </form>

    <!-- Form START -->
	<form class="file-upload" method="POST" action="controller">
      <input type="hidden" name="action" value="edit-profile">
      <c:set var="error" value="${requestScope.error}"/>
	    <div class="row mb-5 gx-5">

	      <!-- Contact detail -->
	       <div class="col-xxl-8 mb-5 mb-xxl-0">
		     <div class="bg-secondary-soft px-4 py-5 rounded">
		        <div class="row g-3">
			      <h4 class="mb-4 mt-0"><fmt:message key="contact.details"/></h4>

			      <!-- First Name -->
			      <div class="col-md-6">
			        <label class="form-label"><fmt:message key="first.name"/></label>
			        <input type="text" class="form-control" placeholder="" id="name" name="name" aria-label="First name"
			        title="<fmt:message key="name.requirements"/>" pattern="^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\- ]{1,30}" required
			        value="${sessionScope.loggedUser.name}">
			      </div>

		          <!-- Last name -->
		         <div class="col-md-6">
			        <label class="form-label"><fmt:message key="last.name"/></label>
			        <input type="text" class="form-control" placeholder="" aria-label="Last name" value="${sessionScope.loggedUser.surname}"
			        id="surname" name="surname" title="<fmt:message key="surname.requirements"/>" pattern="^[A-Za-zА-ЩЬЮЯҐІЇЄа-щьюяґіїє'\- ]{1,30}"
                    required>
			     </div>

			     <!-- Phone number -->
			     <div class="col-md-6">
			       <label class="form-label"><fmt:message key="phone"/></label>
			       <input type="text" class="form-control" placeholder="" aria-label="Phone number" value= "${sessionScope.loggedUser.phone}"
			       id="phone" name="phone" title="<fmt:message key="phone.requirements"/>" pattern="^\+?[1-9][0-9]{7,14}$" required>
			     </div>

			     <!-- Email -->
			     <div class="col-md-6">
			       <label for="inputEmail4" class="form-label"><fmt:message key="email"/></label>
			       <input type="email" class="form-control" id="email" name="email"
			       pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" required value="${sessionScope.loggedUser.email}">
			     </div>

			     <!--Date of birth -->
			     <div class="col-md-6">
			       <label fo class="form-label"><fmt:message key="birthday"/></label>
			       <input type="date" class="form-control" placeholder="" aria-label="Date of birth" value="dd/mm/year ">
			     </div>

			     <!-- Address -->
			     <div class="col-md-6">
			       <label class="form-label"><fmt:message key="address"/></label>
			       <input type="text" class="form-control" placeholder="" aria-label="Address" value="">
			     </div>
				 <!-- Row END -->

				 <!-- Button -->
                 <div class=" gap-3 d-md-flex justify-content-md-end text-center">
		            <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="edit"/></button>
                 </div>
                </div>
             </div>
           </div>
        </div>
    </form>

    <!-- Change password -->
	<form class="file-upload" method="POST" action="controller">
      <input type="hidden" name="action" value="change-password">
      <div class="col-xxl-8 mb-5 mb-xxl-0">
		<div class="bg-secondary-soft px-4 py-5 rounded">
		  <div class="row g-3">
			<h4 class="my-4"><fmt:message key="change.password"/></h4>

			<!-- Old password -->
			<div class="col-md-6">
			  <label for="exampleInputPassword1" class="form-label"><fmt:message key="old.password"/></label>
			  <input type="password" class="form-control" id="old-password" name="old-password">
			</div>

			<!-- New password -->
			<div class="col-md-6">
			   <label for="exampleInputPassword2" class="form-label"><fmt:message key="new.password"/></label>
			   <input type="password" class="form-control" id="password" name="password">
			</div>

			<!-- Confirm password -->
			<div class="col-md-12">
			   <label for="exampleInputPassword3" class="form-label"><fmt:message key="confirm.password"/></label>
			   <input type="password" class="form-control" id="confirm-password" name="confirm-password">
			</div>

			<!-- Button -->
            <div class="gap-3 d-md-flex justify-content-md-end text-center">
               <button type="submit" class="btn btn-primary btn-lg"><fmt:message key="change.password"/></button>
            </div>
		  </div>
		</div>
	  </div>
	</form> <!-- Form END -->
  </div>
 </div>
</div>
