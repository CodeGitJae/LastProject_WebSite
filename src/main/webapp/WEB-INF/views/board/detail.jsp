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
				
				<sec:authorize access="isAnonymous()">
				<div class="comment">
				<h3 class="comment-count">댓글<span> ${b.comment.size()} 개</span></h3>
					<c:forEach items="${b.comment}" var="comment">
						<p class="comment-id">${comment.member.username}</p>
						<p class="comment-date">${comment.content}</p> 
						<hr>
					</c:forEach>
					</div>	
				</sec:authorize>
				 <!--  로그인 인증 받은 경우 (권한에 관계없음) -->
				<sec:authorize access="isAuthenticated()">
				<div class="comment">
				<h3 class="comment-count">댓글<span> ${b.comment.size()} 개</span></h3>
				<form action="/boardComment/detail?id=${b.id}" method="post" id="comment-form">
					<textarea id="comment-text" name="content" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"></textarea>
					<button type="submit" id="comment-btn">입력</button>
				</form>
				
					<c:forEach items="${b.comment}" var="comment">
						<p class="comment-id">${comment.member.username}</p>
						<p class="comment-date">${comment.content}</p> 
						  <div class="UDBtn" style="text-align: right;  margin-right: 20px;">
<%--  							<a id="updateBtn" type="button" style="background-color: #FFA500; margin-right: 10px; padding: 5px 10px; color: black;" 
											 href="/boardComment/update?id=${b.id}&commentId=${comment.id}">수정</a> --%>
							<a id="deleteBtn" type="button" style="background-color: #FFA500; padding: 5px 10px; color: black;" 
											 href="/boardComment/delete?id=${b.id}&commentId=${comment.id}">삭제</a>
						  </div>
						<hr>
					</c:forEach>
				</div>
				</sec:authorize>
				<!--  댓글 수정 모달 시작 -->
				<div class="modal fade" id="myModal" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title">댓글 수정</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				      <table>
					      <tr>
					      	<td>사용자명</td>
					      	<td><input class="form-control" id="username" readonly></td>
					      </tr>
					      <tr>
					      	<td>내용</td>
					      	<td>
					      		<textarea class="form-control" rows="10" id="comment" name="comment"></textarea>
					      	</td>
					      </tr>
				      </table>

				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">저장 취소</button>
				        <button type="button" class="btn btn-success">댓글 수정</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!--  댓글 수정 모달 끝 -->
			</div>
		</div>
	</div>
</div>

<%@ include file="../components/footer.jsp"%>
<script>
/* var action = '';
var url = '';
var type = '';
var val = 0;
var button = '';

$(document).ready(function(){

	// 수정하기 버튼 클릭
	$("a[id='updateBtn']").click(function(e){
		e.preventDefault();
		
		button = $(this);

		action='update';
		type = 'PUT';
		val = this.value;

		// 수정할 코멘트의 정보를 포함한 부모 요소를 찾습니다.
	    var row = button.closest('.UDBtn').parent().parent();
	    
	    // 코멘트의 username과 content를 가져옵니다.
	    var userName = row.find('.comment-id').first().text().trim();
	    var content = row.find('.comment-date').first().text().trim();
	    
	    // 가져온 정보를 모달에 채워 넣습니다.
	    $("#username").val(userName);
	    $("#comment").val(content);
	    
        console.log(username, content);
		// content 담기
		var row = button.parent().parent();
		console.log(row.children());
		var items = row.children();
		
		var username = items.eq(2).text();
		var comment = items.eq(3).text();
		console.log(username, comment) 
		
        
        
        // 모달을 띄웁니다.
        $("#myModal").modal();
	});

	// 삭제하기 버튼 클릭
	$("button[name='delete']").click(function(){
		val = this.value;
		$.ajax({
			url : '/board/' + val,
			type : 'DELETE',
		});
		location.reload();
	})
	
	// Modal의 Submit 버튼 클릭
	$("#modalSubmit").click(function(){
		
		if(action == 'update'){
			url = '/board/detail?id='+ id +'&commentId=' + commentId;
		}

		var data = {
			"bno" : bno,
			"userName" : $("#userName").val(),
			"contents" : $("#contents").val()
		};
		
		$.ajax({
			url : url,
			type : type,
			data : data
		})
		
		location.reload();
	});
	

}); */

</script>
>