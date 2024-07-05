<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="/assets/css/updateProfile.css">

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-content">
		         <div class="featured-games header-text">
		            <div class="heading-section" style="text-align: center;"> 
		           	   <h4><em>내 정보</em> 수정</h4>
		            </div>
				  	<div class="main-profile ">
						<div class="row">
							<form:form action="/member/update/processedDone" method="post" modelAttribute="member" >
									<div class="col-lg-6 align-self-center" style="margin: 0 auto;">
										<input type="hidden" value="${error}" class="errorMessage">
										<ul>
											<li>비밀번호 <form:password path="password"/></li>
											<li><form:errors path="password" cssClass="invalid-feedback d-block"/></li>
											<li>비밀번호 확인 <form:password path="confirmPassword"/></li>
											<li><form:errors path="confirmPassword" cssClass="invalid-feedback d-block"/></li>
											<li>닉네임 <form:input path="nickname"/></li>
											<li><form:errors path="nickname" cssClass="invalid-feedback d-block"/></li>
											<li>이메일 <form:input path="email"/></li>
											<li><form:errors path="email" cssClass="invalid-feedback d-block"/></li>
										</ul>
									</div>
									<div class="main-border-button"
										style="text-align: center; margin-top: 20px;">
										<button type="submit">수정하기</button>
									</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="../components/footer.jsp" %>
<script>
$(document).ready(function(){
	let error = $(".errorMessage").val();
	if(error != ""){
		alert(error);
	}
	
});
</script>
  