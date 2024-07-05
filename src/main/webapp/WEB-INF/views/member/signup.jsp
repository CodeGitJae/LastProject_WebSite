<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="/assets/css/signup_login.css">  
    
<%@ include file="../components/header.jsp" %>

  <div class="container">
      <div class="col-lg-12">
        <div class="page-content">
         <!-- *****아래 div태그는 지워고 사용하세요. ***** -->
           <div class="featured-games header-text">
             <div class="heading-section">
				<section class="vh-100 gradient-custom">
				  <div class="container py-5 h-100">
				    <div class="row d-flex justify-content-center align-items-center h-100">
				      <div class="col-12 col-md-10 col-lg-7 col-xl-7">
				        <div class="card bg-dark text-white" style="border-radius: 1rem;">
				          <div class="card-body p-2 text-center animated fadeIn">
				            <div class="mb-md-5 mt-md-4">
				            
				              <h2 class="fw-bold mb-2 text-uppercase singupfont">회원가입</h2>
				              <p class="text-white-50 mb-5">별스팟웹에 오신것을 환영 합니다.</p>
				              <p>${error}</p>
			
								<form:form class="signUP" method="post" action="/member/signup" modelAttribute="member">	
					                <div data-mdb-input-init class="form-outline">
		   			                   <label class="form-label">아이디</label>
					                   <form:input path="username" class="form-control form-control-lg" placeholder="3~10자의 영문, 숫자로 입력"/>
					                </div>
					                <div class="err-bolck"><form:errors path="username" cssClass="invaild-feedback d-block"/></div>
									
									
					                <div data-mdb-input-init class="form-outline form-white">
					              	  <label class="form-label">비밀번호</label>
	 				                  <form:password path="password" class="form-control form-control-lg" placeholder="3~10자의 영문, 숫자로 입력"/>
			                	   </div>
			                	   <div class="err-bolck"><form:errors path="password" cssClass="invaild-feedback d-block"/></div>
									
									
								   <div data-mdb-input-init class="form-outline form-white">
					              	 <label class="form-label">비밀번호 확인</label>
					                 <form:password path="confirmPassword" class="form-control form-control-lg" placeholder="비밀번호 재 입력란"/>
				                   </div>
					               <div class="err-bolck"><form:errors path="confirmPassword" cssClass="invaild-feedback d-block"/></div>
					              
					              
					               <div data-mdb-input-init class="form-outline form-white">
					              	 <label class="form-label">닉네임</label>
					                 <form:input path="nickname" class="form-control form-control-lg" placeholder="3~10자의 한글, 영문, 숫자로 입력"/></div>
				                   <div class="err-bolck"><form:errors path="nickname" cssClass="invaild-feedback d-block"/></div>
					              
					              
					              <div data-mdb-input-init class="form-outline form-white">
					              	<label class="form-label" for="email">이메일</label>
					                <form:input path="email" class="form-control form-control-lg" placeholder="이메일 입력란"/></div>
			                	  <div class="err-bolck"><form:errors path="email" cssClass="invaild-feedback d-block"/></div>
					
					              <button data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-light btn-lg px-3" type="submit">가입완료</button>
							   </form:form>
				            </div>
			            	<div>
			              	   <p class="mb-0">이미 회원이신가요? <a href="/member/login" class="text-white-50 fw-bold"> 되돌아가기</a></p>
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
  	</div>
 </div>
  
  <%@ include file="../components/footer.jsp" %>

<script type="text/javascript">
/* $(document).ready(function(){
	$("body").on("click", "#userBtn, #emailBtn",function(){
	 let username = $("#id").val();
	 let checkBtn = document.getElementById("checkResult");

	 // 아이디 중복 체크 비동기 처리
	 $.ajax({
		 type: "post",
		 url: "/member/duplicateCheck",
		 data: {
			 "username" : username
		 },
		 success: function(result){
			 console.log("요청성공", result);
			 
			 if(result == "enabled"){
				 checkBtn.style.color = "green";
				 checkBtn.innerHTML =  "사용 가능한 아이디 입니다.";
			 } else {
				 checkBtn.style.color = "red"; 
				 checkBtn.innerHTML = "이미 사용중인 아이디 입니다.";
			 }
		 },
		 error: function(err){
			 console.log("에러발생", err);
		 }
		 
	 });
		
	});
	
});

// 입력 필드(#id)가 변경될 때 메시지 초기화
$("#id").on("input", function() {
    let checkBtn = document.getElementById("checkResult");
    checkBtn.innerHTML = ""; // 메시지 초기화
}); */
</script>