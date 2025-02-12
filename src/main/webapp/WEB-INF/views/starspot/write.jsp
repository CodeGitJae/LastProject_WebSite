<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome.css">

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content" style="color:#fff;">
        <h2>게시글 등록하기</h2><br>
			<form method="post" action="" enctype="multipart/form-data">
				<input type="hidden" name="writer" value="<sec:authentication property="principal.username"/>">
				<div class="mb-3">
				  <label for="title" class="form-label">제목</label>
				  <input class="form-control" id="title" placeholder="제목을 입력해주세요." name="title">
				</div>
				<div class="mb-3 address-box">
				  <label for="address" class="form-label">주소</label>
				  <input class="form-control" id="address" placeholder="클릭 시 주소검색창이 열립니다." name="address" readonly>
				</div>
				<div class="mb-3">
				  <label for="image" class="form-label">이미지</label>
				  <input class="form-control" type="file" id="image" multiple name="uploadImages">
				</div>
         		<div class="mb-3">
				  <label for="exampleFormControlTextarea1" class="form-label">내용</label>
				  <textarea class="form-control" id="exampleFormControlTextarea1" rows="10" name="content"></textarea>
				</div>
         		<button type="submit" class="btn btn-primary mt-3">등록</button>
          	</form>
        </div>
      </div>
    </div>
  </div>
  
<%@ include file="../components/footer.jsp" %>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function daumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수

                document.getElementById("address").value = roadAddr;
                
            }
        }).open();
    }
    
    $('.address-box').on('click', () => {
    	daumPostcode();
    });
</script>