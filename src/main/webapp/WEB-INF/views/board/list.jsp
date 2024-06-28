<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
    
<%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
         <!-- *****아래 div태그는 지워고 사용하세요. ***** -->
            <div class="featured-games header-text">
<!--                   페이지 제목 입력 ( 지우면 헤더 안따라옴) -->
			   <div class="heading-section">
			     <h4><em>자유	</em> 게시판 </h4>
				 <div class="write mb-2" style="text-align: right;">
				 	 <a href="#">최신순</a> <a
						 href="#">조회순</a> | <a
						 href="/board/write" class="" style="font-weight: 900;">글쓰기</a>
				 </div>
			   </div>
			   <!-- 본문 시작하는 곳 -->
			    <table class="table">
				  <thead>
				    <tr>
				      <th scope="col">번호</th>
				      <th scope="col">제목</th>
				      <th scope="col">글쓴이</th>
				      <th scope="col">글작성일</th>
				      <th scope="col">조회수</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">1</th>
				      <td><a href="#">제목 내용이들어가는 곳입니다.</a></td>
				      <td>geust1</td>
				      <td>2024-06-28</td>
				      <td>2</td>
				    </tr>
				  </tbody>
				</table>                          
			   <!--  본문 끝나는 곳 -->
            </div>
        </div>
      </div>
  </div>
</div>
  <%@ include file="../components/footer.jsp" %>