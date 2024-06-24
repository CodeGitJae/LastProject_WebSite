<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome.css">

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content" style="color:#fff;">
			<form method="post" action="" enctype="multipart/form-data">
				<div class="mb-3">
				  <label for="title" class="form-label">제목</label>
				  <input class="form-control" id="title" placeholder="제목" name="title">
				</div>
				<div class="mb-3">
				  <label for="address" class="form-label">주소</label>
				  <input class="form-control" id="address" placeholder="주소" name="address">
				</div>
				<div class="mb-3">
				  <label for="image" class="form-label">이미지</label>
				  <input class="form-control" type="file" id="image" multiple name="uploadImages">
				</div>
         		<div class="mb-3">
				  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
				  <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" name="content"></textarea>
				</div>
         		<button type="submit" class="btn btn-primary mt-3">Submit</button>
          	</form>
        </div>
      </div>
    </div>
  </div>
  
<%@ include file="../components/footer.jsp" %>