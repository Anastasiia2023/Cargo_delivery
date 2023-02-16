<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <ul class="pagination justify-content-center">
      <input type="hidden" name="records" id="records" value="${requestScope.records}">
      <input type="hidden" name="offset" id="offset">
          <c:if test="${requestScope.end > 3}">
               <c:set var="currentOffset" value="0"/>
              <li class="page-item">
                    <button type="submit" class="btn btn-outline-secondary" id="btn-first">1</button>
              </li>
              <c:if test="${requestScope.end > 4}">
                  <li class="page-item">
                      <c:set var="currentOffset" value="${(requestScope.start - 2) * requestScope.records}"/>
                            <button type="submit" class="btn btn-outline-secondary" id="btn-second" ><</button>
                  </li>
              </c:if>
          </c:if>
          <c:forEach var="page" begin="${requestScope.start}" end="${requestScope.end}">
              <li class="page-item">
                  <c:set var="currentOffset" value="${(page - 1) * requestScope.records}"/>
                     <button type="submit" class="btn ${requestScope.currentPage eq page ? 'btn-outline-primary' : 'btn-outline-dark'}" value="${page}">${page}</button>
              </li>
          </c:forEach>
          <c:if test="${requestScope.end < requestScope.pages}">
              <c:if test="${requestScope.end + 1 < requestScope.pages}">
                  <li class="page-item">
                      <c:set var="currentOffset" value="${(requestScope.end) * requestScope.records}"/>
                      <button type="submit" class="btn btn-outline-secondary" id="btn-third">></button>
                  </li>
              </c:if>
              <li class="page-item">
                  <c:set var="currentOffset" value="${(requestScope.pages - 1) * requestScope.records}"/>
                  <button type="submit" class="btn btn-outline-secondary" id="btn-fourth">${requestScope.pages}</button>
              </li>
          </c:if>
      </ul>

<script>
                             $(".btn-outline-primary").click(function(){
                             var va = $(this).val() - 1
                             console.log(va * ${requestScope.records})
                             $("#offset").val(va * ${requestScope.records});
                                });

                             $(".btn-outline-dark").click(function(){
                                var va = $(this).val() - 1
                                console.log(va * ${requestScope.records})
                                $("#offset").val(va * ${requestScope.records});
                             });

                            $("#btn-first").click(function(){
                                  $("#offset").val(0);
                                 });

                            $("#btn-second").click(function(){
                                   $("#offset").val(${(requestScope.start - 2) * requestScope.records});
                            });

                            $("#btn-third").click(function(){
                                      $("#offset").val(${(requestScope.end) * requestScope.records});
                            });

                            $("#btn-fourth").click(function(){
                                     $("#offset").val(${(requestScope.pages - 1) * requestScope.records});
                              });
                         </script>