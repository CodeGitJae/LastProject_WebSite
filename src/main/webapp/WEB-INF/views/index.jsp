<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./components/header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-content">
				<!-- ***** Featured Games Start ***** -->
				<div class="row">
					<div class="col-lg-12">
						<div class="featured-games header-text">
							<div class="heading-section">
								<h4>
									별자리 스팟 TOP 5
								</h4>
							</div>
							<div class="owl-features owl-carousel">
								<c:forEach items="${top5Starspot}" var="starspot">
									<div class="item">
										<a href="/starspot/detail?id=${starspot.id}">
											<div class="thumb">
												<img src="${starspot.images[0].imagePath}" class="thumb-img">
												<div class="hover-effect">
													<h6>${starspot.views}views</h6>
												</div>
											</div>
											<h4>${starspot.title}<br>
												<span>${starspot.views} views</span>
											</h4>
										</a>
										<ul>
											<li><i class="fa fa-tag"></i>
												&nbsp;${starspot.replies.size()}</li>
											<li><i class="fa fa-heart"></i>
												&nbsp;${starspot.likes.size()	}</li>
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
                      <h4>Today Dust<br><span class="todayPM10"></span><span class="todayPM25"></span></h4>
                    </div>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
                      <h4>Today Weather<br><span class="todayTMP"></span><span class="todayREH"></span><span class="todayPCP"></span></h4>
                    </div>
                  </div>
                  
                  <div class="col-lg-3 col-sm-3">
                    <div class="item">
                      <h4>Today Dust<br><span class="tomorrowPM10"></span><span class="tomorrowPM25"></span></h4>
                    </div>
                  </div>
                  <div class="col-lg-3 col-sm-6">
                    <div class="item">
                      <h4>Today Weather<br><span class="tomorrowTMP"></span><span class="tomorrowREH"></span><span class="tomorrowPCP"></span></h4>
                    </div>
                  </div>

                </div>
              </div>
            </div>
          </div>
          <!-- ***** 오늘의 날씨 $ 미세먼지 끝***** -->
          
          
          <div class="most-popular">
            <div class="row">
              <div class="col-lg-12">
                <div class="heading-section">
                  <h4><em>별스팟 관련 사진</em></h4>
                </div>
                <div class="row">
                  <c:forEach var="i" begin="0" end="4">
		               <div class="item" style="width:250px; height:300px;">
		                   <img style="width:100%; height:100%;" id="pic-${0+i*3}" class="starpictures" src="" alt="">
	                  </div>
		             
		              <div class="item" style="width:250px; height:300px;">
		                   <img style="width:100%; height:100%;" id="pic-${1+i*3}" class="starpictures" src="" alt="">
	                  </div>
		             
		              <div class="item" style="width:250px; height:300px;">
		                   <img style="width:100%; height:100%;" id="pic-${2+i*3}" class="starpictures" src="" alt="">
	                  </div>
                  </c:forEach>
                </div>
              </div>
            </div>
          </div>
          <!-- ***** 별자리 스팟 크롤링 끝***** -->

         <!-- ***** 자유게시판 최근 글 n개 시작 ***** -->
          <div class="gaming-library profile-library">
            <div class="col-lg-12">
              <div class="heading-section">
                <h4><em>최근 </em> 게시글 정보</h4>
              </div>
              
              <div class="item">
                <table class="table table-dark">
				  <thead>
				    <tr>
				      <th scope="col">No</th>
				      <th scope="col">제목</th>
				      <th scope="col">글쓴이</th>
				      <th scope="col">작성일</th>
				      <th scope="col">조회수</th>
				    </tr>
				  </thead>
			  	<c:forEach items="${boardList}" var="b">
				  <tbody>
				    <tr>
				      <th>${b.id}</th>
				      <td><a href="/board/detail?id=${b.id}">${b.title}</a></td>
				      <td>${b.member.username}</td>
				      <td>${b.createDate}</td>
				      <td>${b.views}</td>
				    </tr>
				  </tbody>
 	             	 </c:forEach>
				</table>
              </div>
	             <div class="col-lg-12">
	              <div class="main-button">
	                <a href="/board/list">게시판으로 이동</a>
	              </div>
	            </div>
            </div>
          </div>
          <!-- ***** 자유게시판 최근 글 n개 끝 ***** -->
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
	        	$(".todayPM10").text("오늘의 미세먼지: " + parseInt(result[0]["PM10"]) + PM(parseInt(result[0]["PM10"])) + "㎍/㎥" );
	        	$(".todayPM25").text("오늘의 초미세먼지: " + parseInt(result[0]["PM2.5"]) + PM(parseInt(result[0]["PM2.5"])) + "㎍/㎥" );
	        	
	        	$(".tomorrowPM10").text("내일의 미세먼지: " + parseInt(result[1]["PM10"]) + PM(parseInt(result[1]["PM10"])) + "㎍/㎥" );
	        	$(".tomorrowPM25").text("내일의 초미세먼지: " + parseInt(result[1]["PM2.5"]) + PM(parseInt(result[1]["PM2.5"])) + "㎍/㎥" );
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
	        	$(".todayTMP").text("오늘 기온: " + parseInt(result["result"]["기온"][0]) + "°C");
	        	$(".todayREH").text("오늘 습도: " + parseInt(result["result"]["습도"][0]) + "%");
	        	$(".todayPCP").text("오늘 예측 강수량: " + parseInt(result["result"]["강수량"][0]) + "mm");
	        	
	        	$(".tomorrowTMP").text("내일 기온: " + parseInt(result["result"]["기온"][1]) + "°C");
	        	$(".tomorrowREH").text("내일 습도: " + parseInt(result["result"]["습도"][1]) + "%");
	        	$(".tomorrowPCP").text("내일 예측 강수량: " + parseInt(result["result"]["강수량"][1]) + "mm");
	        },
	        error : function(request, status, error) {
	        }
	     })
	  
	} function errorGeo() {
	    $.ajax({
	        type:'GET',
	        url: 'http://13.209.237.30:5000/position_dust_info?x=37&y=127',
	        dataType:'json',
	        success : function(result){
	        	$(".todayPM10").text("오늘의 미세먼지: " + parseInt(result[0]["PM10"])+ PM(parseInt(result[0]["PM10"])) + "㎍/㎥" );
	        	$(".todayPM25").text("오늘의 초미세먼지: " + parseInt(result[0]["PM2.5"]) + PM(parseInt(result[0]["PM2.5"])) + "㎍/㎥");
	        	
	        	$(".tomorrowPM10").text("내일의 미세먼지: " + parseInt(result[1]["PM10"]) + PM(parseInt(result[1]["PM10"])) + "㎍/㎥");
	        	$(".tomorrowPM25").text("내일의 초미세먼지: " + parseInt(result[1]["PM2.5"]) + PM(parseInt(result[1]["PM2.5"])) + "㎍/㎥");
	        },
	        error : function(request, status, error) {
	           console.log(error);
	        }
	     })
	     
	     $.ajax({
	        type:'GET',
	        url: 'http://13.209.237.30:5000/get_weather?x=37&y=127',
	        dataType:'json',
	        success : function(result){
	        	$(".todayTMP").text("오늘 기온: " + parseInt(result["result"]["기온"][0]) + "°C");
	        	$(".todayREH").text("오늘 습도: " + parseInt(result["result"]["습도"][0]) + "%");
	        	$(".todayPCP").text("오늘 예측 강수량: " + parseInt(result["result"]["강수량"][0]) + "mm");
	        	
	        	$(".tomorrowTMP").text("내일 기온: " + parseInt(result["result"]["기온"][1]) + "°C");
	        	$(".tomorrowREH").text("내일 습도: " + parseInt(result["result"]["습도"][1]) + "%");
	        	$(".tomorrowPCP").text("내일 예측 강수량: " + parseInt(result["result"]["강수량"][1]) + "mm");
			}
	     })
	}
	
	navigator.geolocation.getCurrentPosition(connectGeo, errorGeo);
	

</script>


<script>
	$.ajax({
	    type:'GET',
	    url: 'http://13.209.237.30:5000/get_img?keyword='+'별스팟',
	    dataType:'json',
	    success : function(result){
	    	var count;
	    	const starpic = $('.starpictures');
	    	
	    	
	    	for (count = 0; count < result["result"].length; count++){
				id = 'pic-' + count;
				$('#' + id).attr('src', result["result"][count])
	    		
	    	}
	    	
	    },
	    error : function(request, status, error) {
	       console.log(error);
	    }
	 })
</script>
  
