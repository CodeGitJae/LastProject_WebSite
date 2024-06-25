<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="/assets/css/updateProfile.css">

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-content">
				<div class="row">
					<div class="col-lg-12">
						<div class="main-profile ">
							<div class="row">
								<div class="col-lg-2">
<!--                     <img src="/assets/images/profile.jpg" alt="" style="border-radius: 23px;"> -->
								</div>
								<div class="col-lg-12 align-self-center">
									<div class="main-info header-text" style="text-align: center">
										<h4>내정보 수정</h4>
										<!--  선택해서 사용하기 -->
									</div>
								</div>
								<form:form action="/member/update" method="post" modelAttribute="member">
									<div class="col-lg-6 align-self-center" style="margin: 0 auto;">
										<ul>
											<li>아이디  <form:input path="username" readonly/></li>
											<li><form:errors path="username" cssClass="invalid-feedback d-block"/></li>
											<li>비밀번호 <input value="${updateMember.password}" type="password" name="password"></li>
											<li>닉네임 <input value="${updateMember.nickname}" name="nickname"></li>
											<li>이메일 <input value="${updateMember.email}" name="email"></li>
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
</div>

<%@ include file="../components/footer.jsp" %>
  