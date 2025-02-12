<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/board.css">
<link
	href="https://cdn.jsdelivr.net/gh/sun-typeface/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-content">

				<div class="starhead">
					<b class="menutitle" style="font-size: 35px">국내 천문대</b>
					<div class="write">
						<a href="/observatory?sortField=id">최신순</a>
						<a href="/observatory?sortField=views">조회순</a>
					</div>
					
				</div>

				<br> <br>

				<div class="row">
					<c:forEach items="${paging.content}" var="ovitem"
						varStatus="status">
						<div class="col-md-4 mb-4" style="height: 350px;">
							<div class="card">
								<a href="/observatory/detail?id=${ovitem.id}">
									<div class="card-img">
										<img src="${ovitem.image}" class="card-img-top">
									</div>
									<div class="card-body">
										<p class="card-title">${ovitem.title}&nbsp;<span>[${ovitem.replies.size()}]</span></p>
										<p>
											<span class="card-createdate">${ovitem.createdate}</span> <span
												class="card-views">조회 ${ovitem.views}</span>
										</p>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- Pagination Links -->
				<div class="paging">
					<c:if test="${pagination.hasPreviousBlock}">
						<a href="?page=0&sortField=${sortField}"> <i
							class="fa-solid fa-angles-left" id="first_page"></i>
						</a>
						&nbsp;
						<a href="?page=${pagination.startPage - 1}&sortField=${sortField}">
							<i class="fa-solid fa-angle-left" id="prev_page"></i>
						</a>
					</c:if>

					<div class="pages">
						<c:forEach var="i" begin="${pagination.startPage}"
							end="${pagination.endPage}">
							<c:choose>
								<c:when test="${i == pagination.currentPage}">
									<span class="active">${i + 1}</span>
								</c:when>
								<c:otherwise>
									<a href="?page=${i}&sortField=${sortField}">${i + 1}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>

					<c:if test="${pagination.hasNextBlock}">
						<a href="?page=${pagination.endPage + 1}&sortField=${sortField}">
							<i class="fa-solid fa-angle-right" id="next_page"></i>
						</a>
						&nbsp;
			            <a
							href="?page=${pagination.totalPages - 1}&sortField=${sortField}">
							<i class="fa-solid fa-angles-right" id="last_page"></i>
						</a>
					</c:if>
				</div>

			</div>
		</div>
	</div>
</div>

<%@ include file="../components/footer.jsp"%>
