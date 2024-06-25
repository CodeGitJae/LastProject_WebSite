<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/board.css">

<div class="container">
	<div class="row">
		<div class="col-lg-15">
			<div class="page-content">
				<div class="starhead">
					<b class="menutitle" style="font-size: 35px">별스팟</b>
					<div class="write">
						<a href="" class="">제목순</a> <a href="" class="">조회순</a> |
						<a href="/starspot/write" class="" style="font-weight: 900;">글쓰기</a>
					</div>
				</div>
				<br>
				<br>
				<div class="row">
					<c:forEach items="${list}" var="item" varStatus="status">
						<div class="col-md-4 mb-4">
							<div class="card">
								<a href="/starspot/detail?id=${item.id}">
								<div class="card-img">
									<img src="${item.images[0].imagePath}" class="card-img-top">
								</div>
								<div class="card-body">
									<p class="card-title">${item.title}</p>
									<p>
										<span class="card-createdate">${item.createdate}</span>
										<span class="card-views">조회수 27</span>
									</p>
								</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="paging">
					<i class="fa-solid fa-angles-left" id="first_page"></i>
					<i class="fa-solid fa-angle-left" id="prev_page"></i>
					<div class="pages">
						<span class="active">1</span>
			            <span>2</span>
			            <span>3</span>
			            <span>4</span>
			            <span>5</span>
					</div>
					<i class="fa-solid fa-angle-right" id="next_page"></i>
					<i class="fa-solid fa-angles-right" id="last_page"></i>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">

const countPerPage = 6; // 페이지당 데이터 건수
const showPageCnt = 5; // 화면에 보일 페이지 번호 개수

$(function() {
	

}
</script>

</div>

<%@ include file="../components/footer.jsp"%>


