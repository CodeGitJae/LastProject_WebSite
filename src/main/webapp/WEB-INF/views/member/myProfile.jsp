<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="../components/header.jsp" %>
<link rel="stylesheet" href="/assets/css/myProfile.css">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">

          <!-- ***** Banner Start ***** -->
          <div class="row">
            <div class="col-lg-12">
              <div class="main-profile ">
                <div class="row">
                 <div class="col-lg-8">
                 	<img src="/assets/images/banner-bg.jpg" alt="" style="border-radius: 23px;">
                 </div>
                  <div class="col-lg-12 align-self-center">
                    <div class="main-info header-text div1">
                      <h4>마이페이지 내 정보 보기</h4>   <!--  선택해서 사용하기 -->
                    </div>
                  </div>
                  <div class="col-lg-6 align-self-center div2">
                    <ul>
                      <li>아이디  <span class="user">${member.username} </span></li>
                      <li>닉네임 <span>${member.nickname}</span></li>
                      <li>이메일 <span>${member.email}</span></li>
                      <li><p>비밀번호를 변경하시려면 [내정보 수정]을 클릭하세요.</p></li>
                    </ul>
                  </div>
                  <div class="main-border-button div3">
                    <a href="/member/update">내정보 수정</a>
                	<a href="/member/delete" class="deleteBtn" data-user="${member.username}">
                		회원탈퇴
              		</a> 
                  </div>
                </div>
                <div class="row">
                  <div class="col-lg-12">
                    <div class="clips">
                      <div class="row">
                        <div class="col-lg-12">
                          <div class="heading-section">
                            <h4><em>내가 찜한</em> 별자리 스팟</h4>
                          </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                          <div class="item">
                            <div class="thumb">
                             <!--  <img src="assets/images/clip-01.jpg" alt="" style="border-radius: 23px;"> -->
                              <a href="#" target="_blank"><i class="fa fa-play"></i></a>
                            </div>
                            <div class="down-content">
                              <h4>별자리 이름 넣는곳</h4>
                              <span><i class="fa fa-eye"></i> 250</span>
                            </div>
                          </div>
                        </div>
                        <div class="col-lg-12">
                          <div class="main-button">
                            <a href="#">별자리 스팟으로 이동</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- ***** Banner End ***** -->

          <!-- ***** Gaming Library Start ***** -->
          <div class="gaming-library profile-library">
            <div class="col-lg-12">
              <div class="heading-section">
                <h4><em>내가 쓴</em> 게시글 정보</h4>
              </div>
              <div class="item">
                <ul>
                  <li><img src="/assets/images/game-01.jpg" alt="" class="templatemo-item"></li>
                  <li><h4>Dota 2</h4><span>Sandbox</span></li>
                  <li><h4>Date Added</h4><span>24/08/2036</span></li>
                  <li><h4>Hours Played</h4><span>634 H 22 Mins</span></li>
                  <li><h4>Currently</h4><span>Downloaded</span></li>
                </ul>
              </div>
	             <div class="col-lg-12">
	              <div class="main-button">
	                <a href="#">게시판으로 이동</a>
	              </div>
	            </div>
            </div>
          </div>
          <!-- ***** Gaming Library End ***** -->
        </div>
      </div>
    </div>
  </div>
 
   <%@ include file="../components/footer.jsp" %>
 <script>
 $(document).ready(function(){
	$(".deleteBtn").click(function(){
		username = $(this).data("user");
		
		if(confirm("정말 삭제하시겠습니까?")){	
			$.ajax({
				type: "GET",
				url: "/member/delete",
				data: {
					"username": username
				},
				success: function(result){
				window.location.href = "/";
				alert(username + "님의 계정이 삭제되었습니다.");

				},
				error: function(err){
					alert("삭제 처리 중 오류가 발생했습니다.");
				}
			}); 
		}
	});
 });
 </script>
  