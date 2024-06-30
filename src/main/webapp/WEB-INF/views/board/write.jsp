<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
         <!-- *****아래 div태그는 지워고 사용하세요. ***** -->
            <div class="featured-games header-text">
<!--                   페이지 제목 입력 ( 지우면 헤더 안따라옴) -->
			   <div class="heading-section">
			     <h4><em>흰색 </em> 핑크색 </h4>
			   </div>
			   <!-- 본문 시작하는 곳 -->
				<form method="post" action="/board/write" enctype="multipart/form-data">
				  <div class="mb-4" style="margin: 0px, 40px, 0px, 40px;">
				  <sec:authentication property="principal.username" var="username"/>
					<input type="hidden" name="username" value="${username}"/>
				    <label for="title" class="form-label" style="color: white;">제목</label>
				    <input class="form-control" id="title" name="title">
				  </div>
				  <div class="mb-3">
				    <label for="content" style="color: white;" class="form-label">내용</label><br>
				    <textarea style="border-radius: 10px;" id="content" name="content" rows="15" cols="121"></textarea>
				  </div>
				  <div class="mb-3">
				  	<input type="file" class="form-control" id="image" name="uploadToBoardImage" multiple>
				   <button style="float: right; margin-top: 10px;" type="submit" class="btn btn-secondary">게시글 등록</button>
				  </div>
			  	</form>                     
			   <!--  본문 끝나는 곳 -->
            </div>
        </div>
      </div>
  </div>
</div>
  <%@ include file="../components/footer.jsp" %>
  