<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/write.css">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content" style="color:#fff;">
          <h2>${b.title}</h2><br>
			<form method="post" action="/board/update?id=${b.id}" enctype="multipart/form-data" id="formTag">
			  <div class="mb-3">
			    <div>
			      <div class="image-container">
				    <c:forEach items="${b.images}" var="image" varStatus="status">
				      <div class="imageDiv" style="text-align: right;">
					      <div class="image-item">
					        <input type="hidden" name="saveImageId" value="${image.id}">
					        <input type="hidden" name="savedBoardImage" value="${image.imagePath}">
					        <img id="imgSrc" src="${contextPath}/${image.imagePath}" alt="Image">
					      </div>
					    							
					      <div class="button-item">		<!--  style="position: relative; top: 210px; right: 60px;" -->
					        <input type="hidden" name="deleteImages" value="${image.id}">
					        <button class="btn btn-secondary delBtn" type="button" style="">삭제</button>
					      </div>
				       </div>
				    </c:forEach>
				  </div>
			    </div>
		      </div>
			 
			  <label for="image" class="form-label">이미지</label>
			  <input class="form-control mb-3" type="file" id="image" name="uploadToBoardImage"  multiple>
		  	  <div class="mb-3">
		      	 <input type="hidden" name="bViews" value="${b.views}">
		      	 <input type="hidden" name="username" value="${b.member.username}"/>
				 <label for="title" class="form-label">제목</label>
				 <input class="form-control" id="title" value="${b.title}" name="title">
			  </div>
			 
       	      <div class="mb-3">
			    <label for="content" class="form-label">내용</label>
			    <textarea class="form-control" id="content" rows="10" name="content">${b.content}</textarea>
			  </div>
			 
       		 <button type="submit" class="btn btn-secondary updateBtn" style="float: right;">수정완료</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  
<%@ include file="../components/footer.jsp" %>
<script>
$(document).ready(function(){
	
	$(".updateBtn").click(function(e){
		e.preventDefault();
	
		let title = $("#title").val();
		let content = $("#content").val();
//		console.log(title, content);
		
		let formTag = $("#formTag");
		
		if(title == ""){
			alert("제목이 입력되지 않았습니다.")
			$("#title").focus();
			return ;
		}
		
		if(content == ""){
			alert("내용이 입력되지 않았습니다.")
			$("#content").focus();
			return ;
		}
		
		formTag.submit();
		
	});
	
	
  /* 이미지 삭제 버튼 동작 */
  $(".delBtn").click(function(e) {
    e.preventDefault();
    
   let imageId = $(this).parent().children().val();
   let imageFilePath = $(this).parent().prev().children().eq(2).attr('src');
   let imageTag = $(this).parent().parent();
// 	console.log(imageId, imageFilePath);
   	
   	$.ajax({
   		type: "post",
   		url: "/board/removeFile",
   		data: { 
   			"imageFilePath" : imageFilePath,
   			"imageId" : imageId	
   		},
   		success: function(result){ // 삭제 성공 실패에 따라 true, false 값을 받게됨.
//   			console.log(result);
   			if(result === true){
   				// 사진 파일 삭제 성공 시 추가된 이미지 박스도 삭제
   				imageTag.remove();
   			}
   		}
   		
   	});
    
  });
  
});
</script>