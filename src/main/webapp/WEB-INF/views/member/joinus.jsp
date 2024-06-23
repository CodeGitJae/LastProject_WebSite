<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<!-- Font online-->
<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
<!-- Animate.css-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<link rel="stylesheet" href="/assets/css/joinus.css">  

<%@ include file="../components/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<div class="page-content">
			<!-- *****아래 div태그는 지우고 사용하세요. ***** -->
				<h2 style="color: #d3d3d3; margin-bottom: 30px;">회원가입</h2>
				<div class="container">
					<form class="signUP" method="post" action="/member/joinus">
						<div class="animated fadeIn" style="text-align: center;">
						  <div class="form-group">
						    <label for="username" class="form-label" style="color: #d3d3d3; font-weight: bold;">아이디</label>
						    <input class="validate" id="id" name="username"  placeholder="아이디 입력란">
						    <button class="validate btn-primary">중복검사 </button>
						  </div>

						  <div class="form-group">
						    <label for="password" class="form-label" style="color: #d3d3d3; font-weight: bold;">비밀번호</label>
						    <input type="password" id="password"  name="password" placeholder="비밀번호 입력란">
						  </div>
						  <div class="form-group">
						    <label for="confirmPassword" class="form-label" style="color: #d3d3d3; font-weight: bold;">비밀번호 확인</label>
						    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인">
						  </div>
  						   <div class="form-group">
						    <label for="nickname" class="form-label" style="color: #d3d3d3; font-weight: bold;">닉네임</label>
						    <input id="nickname" name="nickname" placeholder="닉네임 입력란">
						  </div>
						  <div class="form-group">
						    <label for="email" class="form-label" style="color: #d3d3d3; font-weight: bold;">이메일</label>
						    <input type="email" class="validate" id="email" name="email" placeholder="이메일 입력란">
						  	<button class="validate btn-primary">중복검사</button>
						  </div>
						  <button type="submit" class="btn btn-primary">가입완료</button>
					  </div>
					</form>
				</div>
   			</div>
		</div>
	</div>
</div>

<!-- Google JQuery CDN -->

  <%@ include file="../components/footer.jsp" %>
  
  <script>
 
  </script>
  