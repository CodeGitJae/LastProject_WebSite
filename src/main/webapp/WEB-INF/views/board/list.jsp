<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
         <!-- *****아래 div태그는 지워고 사용하세요. ***** -->
            <div class="featured-games header-text">
<!--                   페이지 제목 입력 ( 지우면 헤더 안따라옴) -->
			   <div class="heading-section">
			     <h4><em>자유	</em> 게시판 </h4>
				 <div class="write mb-2" style="text-align: right; margin: 25px; font-size: 20px; font-weight: bold;">
				 	 <a href="/board/write" class="" style="font-weight: 900;">글쓰기</a>
				 </div>
			   </div>
			   <!-- 본문 시작하는 곳 -->
			   <div class="resultMessage">
			   	<p>${message}</p>
			   	<p>${errorMessage}</p>
			   	</div>
			    <table class="table table-secondary">
				  <thead>
				    <tr>
				      <th scope="col">번호</th>
				      <th scope="col">제목</th>
				      <th scope="col">글쓴이</th>
				      <th scope="col">글작성일</th>
				      <th scope="col">조회수</th>
				    </tr>
				  </thead>
 				  <c:forEach items="${paging.content}" var="board">
				  <tbody>
				    <tr>
				      <th scope="row">${board.id}</th>
				      <td><a href="/board/detail?id=${board.id}">${board.title}</a></td>
				      <td>${board.member.username}</td>
				      <td>${board.createDate}</td>
				      <td>${board.views}</td>
				    </tr>
				  </tbody>
				  </c:forEach>
				</table>
				<nav aria-label="..." class="d-flex justify-content-center">
				  <ul class="pagination" >
				  <c:choose>
			      <c:when test="${paging.hasPrevious()}">
			      	<li class="page-item">
				      <a class="page-link" href="/board/list?page=${pagination.currentPage - 1}">Previous</a>
				    </li>
				    </c:when>
    			  <c:otherwise>
				    <li class="page-item disabled">
				      <a class="page-link">Previous</a>
				    </li>
			      </c:otherwise>
				    </c:choose>
				       <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="page">
					    <li class="page-item ${page == (pagination.currentPage -1) ? 'active' : ''}">
					      <a class="page-link" href="/board/list?page=${page + 1}">${page + 1}</a>
					    </li>
					   </c:forEach>
			   		  <c:choose>
			   			<c:when test="${paging.hasNext()}">
   						  <li class="page-item">
						    <a class="page-link" href="/board/list?page=${pagination.currentPage + 1}">Next</a>
						  </li>
					    </c:when>
					    <c:otherwise>
						  <li class="page-item disabled">
					        <a class="page-link">Next</a>
					      </li>
						 </c:otherwise>
				     </c:choose>
				  </ul>
				</nav>     
			   <!--  본문 끝나는 곳 -->
            </div>
        </div>
      </div>
  </div>
</div>
  <%@ include file="../components/footer.jsp" %>