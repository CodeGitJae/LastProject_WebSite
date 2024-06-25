<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="/assets/css/signup_login.css">  

<%@ include file="../components/header.jsp" %>
  <div class="container">
      <div class="col-lg-12">
        <div class="page-content">

         <!-- *****아래 div태그는 지워고 사용하세요. ***** -->
			<section class="vh-100 gradient-custom">
			  <div class="container py-5 h-100">
			    <div class="row d-flex justify-content-center align-items-center h-100">
			      <div class="col-12 col-md-10 col-lg-6 col-xl-5">
			        <div class="card bg-dark text-white" style="border-radius: 1rem;">
			          <div class="card-body p-5 text-center animated fadeIn">
						
			            <div class="mb-md-5 mt-md-4 pb-5">
			
			              <h2 class="fw-bold mb-2 text-uppercase loginfont">로그인</h2>
			              <p class="text-white-50 mb-5">Please enter your login and password!</p>
			             	<p class="text-white-50 mb-5" style="font-size: 20px;">${warning}</p>
			              
						<form action="/member/login" method="post">
			              <div data-mdb-input-init class="form-outline form-white mb-4">
			              	<label class="form-label" for="username">아이디</label>
			                <input id="username" name="username" class="form-control form-control-lg" />
			              </div>
			
			              <div data-mdb-input-init class="form-outline form-white mb-4">
   			                <label class="form-label" for="password">비밀번호</label>
			                <input type="password" id="password" name="password" class="form-control form-control-lg" />
			              </div>
						
								<!-- 	비밀번호 찾기 부분	 -->
	<!--		              <p class="small mb-5 pb-lg-2"><a class="text-white-50" href="#!">Forgot password?</a></p> -->
			
			              <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-3" type="submit">로그인</button>
						</form>
							<!-- 소셜 로그인  -->
<!-- 			              <div class="d-flex justify-content-center text-center mt-4 pt-1">
			                <a href="#" class="text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
			                <a href="#" class="text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
			                <a href="#" class="text-white"><i class="fab fa-google fa-lg"></i></a>
			              </div> -->
			
			            </div>
			
			            <div>
			              <p class="mb-0">회원이 아니신가요?? <a href="/member/signup" class="text-white-50 fw-bold"> 회원가입</a>
			              </p>
			            </div>
			
			          </div>
			        </div>
			      </div>
			    </div>
			  </div>
			</section>										
        </div>
      </div>
  </div>
  
  <%@ include file="../components/footer.jsp" %>
  