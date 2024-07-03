<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/write.css">

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content" style="color:#fff;">
          <h2>게시글 수정하기</h2><br>
			<form method="post" action="" enctype="multipart/form-data">
			  <input type="hidden" name="writer" value="<sec:authentication property="principal.username"/>">
			  <div class="mb-3">
				<label for="title" class="form-label">제목</label>
				<input class="form-control" id="title" value="${starspot.title}" name="title">
				</div>
				<div class="mb-3">
				  <label for="address" class="form-label">주소</label>
				  <input class="form-control" id="address" value="${starspot.address}" name="address">
				</div>
				<div class="mb-3">
				  <div>
				    <label for="image" class="form-label">이미지</label>
  				    <input class="form-control" type="file" id="image" multiple name="uploadImages">
  				    <div class="image-container">
					  <c:forEach items="${starspot.images}" var="image" varStatus="status">
					    <div class="image-item">
					      <img src="${pageContext.request.contextPath}/${image.imagePath}" alt="Image">
					    </div>
					    
					    <div class="checkbox-item">
					      <input type="checkbox" name="deleteImages" value="${image.id}">
					      <label for="deleteImages">삭제</label>
					    </div>
					  </c:forEach>
					</div>
				  </div>
				</div>
         		<div class="mb-3">
				  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
				  <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" name="content">${starspot.content}</textarea>
				</div>
         		<button type="submit" class="btn btn-primary mt-3">수정</button>
          	</form>
        </div>
      </div>
    </div>
  </div>
  
<%@ include file="../components/footer.jsp" %>