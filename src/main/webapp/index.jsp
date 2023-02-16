
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<head>

<title>Main Page</title>
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>

<jsp:include page="fragments/mainMenu.jsp"/>



<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="img/Noticia-mejora-de-la-financiacion-europea-para-los-corredores-de-valencia-con-zaragoza-y-madrid-Anex.jpg"
      class="d-block w-100" alt="<fmt:message key='pic'/>">
    </div>
    <div class="carousel-item">
      <img src="img/slider1-silceb.jpg" class="d-block w-100" alt="<fmt:message key='pic'/>">
    </div>

  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"  data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"  data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>




<jsp:include page="fragments/footer.jsp"/>

</body>
</html>