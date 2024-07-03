<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="./components/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">

         <!-- ***** Featured Games Start ***** -->
          <div class="row">
            <div class="col-lg-12">
              <div class="featured-games header-text">
                <div class="heading-section">
                  <h4><em>별자리 스팟</em> TOP 5</h4>
                </div>
                <div class="owl-features owl-carousel">
                  <c:forEach items="${top5Starspot}" var="starspot">
	                 <div class="item">
	                    <div class="thumb">
	                      <img src="${starspot.images[0].imagePath}" alt="">
	                      <div class="hover-effect">
	                        <h6>${starspot.views} views</h6>
	                      </div>
	                    </div>
	                    <h4>${starspot.title}<br><span>${starspot.views} views</span></h4>
	                    <ul>
	                      <li><i class="fa fa-star"></i> 4.8</li>
	                      <li><i class="fa fa-download"></i> 2.3M</li>
	                    </ul>
	                  </div>
                  </c:forEach>
                  
                </div>
              </div>
            </div>
          </div>
          <!-- ***** Featured Games End ***** -->

          <!-- ***** 오늘의 달모양  시작***** -->
          <div class="most-popular">
            <div class="row">
              <div class="col-lg-12">
                <div class="heading-section">
                  <h4><em>Today Of</em> Moon Shape</h4>
                </div>
                <div class="row">
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
  <!--                     <img src="assets/images/popular-01.jpg" alt=""> -->
                      <h4>Fortnite<br><span>Sandbox</span></h4>
                      <ul>
                        <li><i class="fa fa-star"></i> 4.8</li>
                        <li><i class="fa fa-download"></i> 2.3M</li>
                      </ul>
                    </div>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
  <!--                     <img src="assets/images/popular-02.jpg" alt=""> -->
                      <h4>PubG<br><span>Battle S</span></h4>
                      <ul>
                        <li><i class="fa fa-star"></i> 4.8</li>
                        <li><i class="fa fa-download"></i> 2.3M</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- ***** 오늘의 달모양  끝***** -->
          
          <!-- ***** 오늘의 날씨 $ 미세먼지 시작***** -->
          <div class="most-popular">
            <div class="row">
              <div class="col-lg-12">
                <div class="heading-section">
                  <h4><em>Today Of</em> Weather & FineDust</h4>
                </div>
                <div class="row">
                  <div class="col-lg-3 col-sm-3">
                    <div class="item">
  <!--                     <img src="assets/images/popular-01.jpg" alt=""> -->
                      <h4>Dust<br><span class="PM10"></span><span class="PM25"></span></h4>
                    </div>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
  <!--                     <img src="assets/images/popular-02.jpg" alt=""> -->
                      <h4>Weather<br><span class="TMP"></span><span class="REH"></span><span class="PCP"></span></h4>
                    </div>
                  </div>
                  <div class="col-lg-12">
                    <div class="main-button">
                      <a href="#">자세히 보기</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- ***** 오늘의 날씨 $ 미세먼지 끝***** -->

<!-- ***** 별자리 스팟 크롤링 시작***** -->
          <div class="most-popular">
            <div class="row">
              <div class="col-lg-12">
                <div class="heading-section">
                  <h4><em>Star Spot</em> More Pickture</h4>
                </div>
                <div class="row">
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
                    	<img id="picture1" src="" alt="">
                    </div>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
                    	<img id="picture2" src="" alt="">
                    </div>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
                    	<img id="picture3" src="" alt="">
                    </div>
                  </div>

                  <div class="col-lg-12">
                    <div class="main-button">
                      <a href="#">사진 더 보기</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- ***** 별자리 스팟 크롤링 끝***** -->

          <!-- ***** Gaming Library Start ***** -->
          <div class="gaming-library">
            <div class="col-lg-12">
              <div class="heading-section">
                <h4><em>오늘의 메인</em> 추천글</h4>
              </div>
              <div class="item">
                <ul>
                  <li><img src="/assets/images/game-01.jpg" alt="" class="templatemo-item"></li>
                  <li><h4>Dota 2</h4><span>Sandbox</span></li>
                  <li><h4>Date Added</h4><span>24/08/2036</span></li>
                  <li><h4>Hours Played</h4><span>634 H 22 Mins</span></li>
                  <li><h4>Currently</h4><span>Downloaded</span></li>
                  <li><div class="main-border-button"><a href="#">좋아요</a></div></li>
                </ul>
              </div>
              <div class="item">
                <ul>
                  <li><img src="/assets/images/game-02.jpg" alt="" class="templatemo-item"></li>
                  <li><h4>Fortnite</h4><span>Sandbox</span></li>
                  <li><h4>Date Added</h4><span>22/06/2036</span></li>
                  <li><h4>Hours Played</h4><span>740 H 52 Mins</span></li>
                  <li><h4>Currently</h4><span>Downloaded</span></li>
                  <li><div class="main-border-button"><a href="#">좋아요</a></div></li>
                </ul>
              </div>
              <div class="item last-item">
                <ul>
                  <li><img src="/assets/images/game-03.jpg" alt="" class="templatemo-item"></li>
                  <li><h4>CS-GO</h4><span>Sandbox</span></li>
                  <li><h4>Date Added</h4><span>21/04/2036</span></li>
                  <li><h4>Hours Played</h4><span>892 H 14 Mins</span></li>
                  <li><h4>Currently</h4><span>Downloaded</span></li>
                  <li><div class="main-border-button"><a href="#">좋아요</a></div></li>
                </ul>
              </div>
            </div>
            <div class="col-lg-12">
              <div class="main-button">
                <a href="#">게시판으로 이동</a>
              </div>
            </div>
          </div>
          <!-- ***** Gaming Library End ***** -->
        </div>
      </div>
    </div>
  </div>
  
  <%@ include file="./components/footer.jsp" %>
  
<script type="text/javascript">

	function PM(pm) {
		let result = "";
		
		if (pm < 30) {
			result = "(좋음)";
		} else if (pm >= 31 && pm < 80) {
			result = "(보통)";
		} else if (pm >= 81 && pm < 150) {
			result = "(나쁨)";
		} else if (pm >= 151) {
			result = "(매우 나쁨)";
		}
		
		return result;
	}

	function connectGeo(position) {
	    $.ajax({
	        type:'GET',
	        url: 'http://13.209.237.30:5000/position_dust_info?x=' + parseInt(position.coords.latitude) + '&y=' + parseInt(position.coords.longitude),
	        dataType:'json',
	        success : function(result){
	        	$(".PM10").text("오늘의 미세먼지: " + parseInt(result[0]["PM10"]) + PM(parseInt(result[0]["PM10"])));
	        	$(".PM25").text("오늘의 초미세먼지: " + parseInt(result[0]["PM2.5"]) + PM(parseInt(result[0]["PM2.5"])));
	        },
	        error : function(request, status, error) {
	           console.log(error);
	        }
	     })
	     
	     $.ajax({
	        type:'GET',
	        url: 'http://13.209.237.30:5000/get_weather?x=' + parseInt(position.coords.latitude) + '&y=' + parseInt(position.coords.longitude),
	        dataType:'json',
	        success : function(result){
	        	$(".TMP").text("기온: " + parseInt(result["result"]["기온"][0]));
	        	$(".REH").text("습도: " + parseInt(result["result"]["습도"][0]));
	        	$(".PCP").text("예측 강수량: " + parseInt(result["result"]["강수량"][0]));
	        },
	        error : function(request, status, error) {
	           console.log(error);
	        }
	     })
	  
	} function errorGeo() {
	  alert("위치 연결이 안됨");
	}
	
	navigator.geolocation.getCurrentPosition(connectGeo, errorGeo);
	

</script>


<script>
	$.ajax({
	    type:'GET',
	    url: 'http://13.209.237.30:5000/get_img?keyword='+'별스팟',
	    dataType:'json',
	    success : function(result){
	    		document.getElementById("picture1").src = result["result"][0];
	    		document.getElementById("picture2").src = result["result"][3];
	    		document.getElementById("picture3").src = result["result"][5];
	    },
	    error : function(request, status, error) {
	       console.log(error);
	    }
	 })
</script>
  