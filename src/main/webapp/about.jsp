<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="${sessionScope.locale}">
<head>

<title>About Page</title>
<script src="js/popper.min.js"></script>
<script src="js/jquery-3.6.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>

<jsp:include page="fragments/mainMenu.jsp"/>
<div class="col-lg-7 mx-auto p-4 py-md-5">

    <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
        <span class="fs-4"><fmt:message key="about"/></span>
    </header>

    <main>
        <p class="fs-5 col-md-12">DELIVERY group of companies began work in 2001. The main specialization of the group
        is the provision of transport and logistics services both on the territory of Ukraine and abroad.
        Delivery and pick-up of goods is carried out at the companys warehouse or at the customers door.
        We deliver goods from 1 kg to 8 tons with the provision of packing services for your goods.
        The company provides such services as transportation of goods across Ukraine with the type of delivery
        Warehouse - Warehouse, Warehouse - Door, Door - Warehouse, Door - Door, address pickup and delivery of
        goods around the city and in populated areas, responsible storage , post-payment in cash, additional packaging
         of goods, return of containers and documents, provision of a personal manager.</p><br>

        <p class="fs-5 col-md-12">Standard rates for transportation are set depending on the weight and volume of the cargo.
         There are separate tariffs for the transportation of palletized and oversized cargo, car tires and rims.
         You can also take advantage of tailor-made industry solutions, where you will be offered packaging and a
         customized shipping rate. A flexible system of discounts has been developed for our customers.
         Customers registered on the website are provided with additional options: cargo tracking and delivery management.
         </p>



    </main>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>