<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/detail.css">
<link
	href="https://cdn.jsdelivr.net/gh/sun-typeface/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-content">
				<div class="starhead">
					<b class="menutitle">${b.title}</b> <a href="/board/list" class="list">목록가기</a>
				</div>
				<hr>
				<div class="title">
					<h3 class="title-data">${b.title}</h3>
					<div class="information">
						<p class="info-id">작성: ${b.member.username} </p>
						<p class="info-date">${b.createDate}</p>
					</div>
				</div>
				<div class="text">
					<div class="correction-top">
						<div class="correction">
							<a href="/board/update?id=${b.id}" class="correction-data">수정</a><a>|</a> 
							<a href="/board/delete?id=${b.id}" class="delete-data">삭제</a>
						</div>
					</div>
					<c:forEach items="${b.images}" var="image">
						<img src="<%= request.getContextPath() %>/${image.imagePath}"
							style="max-width: 100%; height: auto;">
						<br>
					</c:forEach>
					<br>
					<pre>${b.content}</pre>
				</div>
				<br>
				<div class="comment">
				<h3 class="comment-count">댓글<span>1</span></h3>
				<form action="" method="get" id="comment-form">
					<textarea id="comment-text" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"></textarea>
					<button type="button" id="comment-btn">입력</button>
				</form>
					<p class="comment-id">${b.id}</p>
					<p class="comment-date">${b.createDate}</p>
					<hr>
					<p class="comment-id">${b.id}</p>
					<p class="comment-date">${b.createDate}</p>
					<hr>
					<p class="comment-id">${b.id}</p>
					<p class="comment-date">${b.createDate}</p>

				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../components/footer.jsp"%>
<script>

</script>
>