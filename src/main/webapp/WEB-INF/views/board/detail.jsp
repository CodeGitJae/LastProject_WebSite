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
						<c:if test="${authentication.getName() == b.member.username}">
							<div class="correction">
								<a href="/board/update?id=${b.id}" class="correction-data">수정</a><a>|</a> 
								<a href="/board/delete?id=${b.id}" class="delete-data">삭제</a>
							</div>
						</c:if>
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
				
				<!--  로그인 인증이 없는 경우 -->
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
					<div class="comment" id="commentList">
						<h3 class="comment-count">댓글<span> ${b.comment.size()} 개</span></h3>
						<form action="/boardComment/detail?id=${b.id}" method="post" id="comment-form">
							<input type="hidden" id="username" value="${b.id}">
							<textarea id="comment-text" name="content" placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"></textarea>
							<button type="submit" id="comment-btn">입력</button>
					    </form>
						<c:forEach items="${b.comment}" var="comment">
							<input class="commentbId" type="hidden" data-bid="${b.id}">
							<input class="commentId" type="hidden" data-id="${comment.id}">
							<p class="comment-id">${comment.member.username}</p>
							<p class="comment-date">${comment.content}</p>
							<c:if test="${authentication.getName() == comment.member.username}"> 
							  <div class="UDBtn" style="text-align: right;  margin-right: 20px;">
	  							<%-- <a class="updateBtn" type="button" style="background-color: #FFA500; margin-right: 10px; padding: 5px 10px; color: black;" 
												 href="/boardComment/update?id=${b.id}&commentId=${comment.id}">수정</a> --%>
								 <a class="updateBtn" type="button" style="background-color: #FFA500; margin-right: 10px; padding: 5px 10px; color: black;">수정</a> 
												
								 <a class="deleteBtn" type="button" style="background-color: #FFA500; padding: 5px 10px; color: black;" 
												 href="/boardComment/delete?id=${b.id}&commentId=${comment.id}">삭제</a>
							  </div>
							</c:if>
							<hr>		
					    </c:forEach>
					</div>
				</sec:authorize>
				
				
				<!--  댓글 수정 모달 시작 -->
				<div class="modal fade" id="myModal" tabindex="-1">
				  <div class="modal-dialog">
				    <div class="modal-content" style="position: relative; top:350px; width: 100%;">
				      <div class="modal-header">
				         <h5 class="modal-title">댓글 수정</h5>
				         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
					      <table style="width: 95%;">
					      	<tbody>
						      <tr>
						      	<td style="color: gray;">사용자명</td>
						      	<td><input class="form-control username" name="username" readonly></td>
						      </tr>
						      <tr>
						      	<td style="color: gray;">내용</td>
						      	<td>
						      		<textarea class="form-control comment" rows="10" id="updateContent" ></textarea>
						      	</td>
						      </tr>
					      	</tbody>
					      </table>
				      </div>
				      
				      <div class="modal-footer">
				       	<button type="button" class="btn btn-success modalSubmit">댓글 수정</button>
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">수정 취소</button>
				      </div>
				    </div>
				  </div>
				</div>
				<!--  댓글 수정 모달 끝 -->
				<input class="successMessage" type="hidden" value="${success}"> 
			</div>
		</div>
	</div>
</div>
<%@ include file="../components/footer.jsp"%>
<script>
let bId = 0;
let commentId = 0;
let username = '';
let content = '';

$(document).ready(function(){
	
	// 게시글 수정 성공 시 멘트 alert창으로 띄우기
	let success = $(".successMessage").val();
		
		if(success != ""){
			setTimeout(function(){
				alert(success);
			}, 100);
		}

		
	// 수정하기 버튼 클릭
$(".updateBtn").click(function(e){
	e.preventDefault();
	

	bId = $(this).parent().prev().prev().prev().prev().data('bid');
	commentId = $(this).parent().prev().prev().prev().data('id');
	username = $(this).parent().prev().prev().text();
 	originContent = $(this).parent().prev().text(); 

	console.log(username, commentId, bId);

	$(".username").val(username);
	$(".comment").val(originContent);

	 // 모달을 띄웁니다.
    $("#myModal").modal("show");
	 
	
	});
 
//Modal의 Submit 버튼 클릭
$(".modalSubmit").click(function(e){
	 e.preventDefault();
 	 
/* 	 console.log(content); */
/* 	 let row = $(this).parent().prev().children().children().children();
	 let ser = row.eq(1).children().eq(1).children().val(); 	 
	 console.log(":::::::::::::::::::::::",ser);*/

	 content = document.querySelector("#updateContent").value;
	 $(".comment").val(content);
	 
	 	$.ajax({
			type : "post",
			url : "/boardComment/update",
			data : {
				"commentId": commentId,
				"content" : content,
				"bId" : bId
				
			},
			success: function(res){
				console.log("데이터 전송을 성공했습니다.", res);
				location.href= res;
				
			},
			error: function(err){
				console.log("에러가 발생했습니다. : ", err)
			}
		}); 
	});
	   
});
</script>
