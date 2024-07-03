<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/detail.css">
<link
	href="https://cdn.jsdelivr.net/gh/sun-typeface/SUITE/fonts/static/woff2/SUITE.css"
	rel="stylesheet">
	
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.username"
		var="authenticatedUsername" />
</sec:authorize>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="page-content">
				<div class="starhead">
					<b class="menutitle">국내 천문대</b> <a href="/observatory" class="list">목록가기</a>
				</div>
				<hr>
				<div class="title">
					<h3 class="title-data">${data.title}</h3>
					<div class="information">
						<p class="info-id">작성 관리자</p>
						<p class="info-date">${data.createdate}</p>
					</div>
				</div>
				<hr>
				<div class="text">
					<%-- 					<div class="correction-top">
						<div class="correction">
							<a href="/starspot/update?id=${data.id}" class="correction-data">수정</a><a>|</a>
							<a href="" class="delete-data">삭제</a>
						</div>
					</div> --%>
					<img src="${pageContext.request.contextPath}/${data.image}"
						style="max-width: 100%; height: auto;"> <br>
					<div class="text-body">
						<p class="address-data">홈페이지 : ${data.homepage}</p>
						<p class="address-data">천문대 : ${data.title}</p>
						<p class="address-data">위치 : ${data.address}</p>
						<p class="address-data">전화번호 : ${data.tel}</p>
						<br> <br>
						<pre>${data.content}</pre>
					</div>
				</div>
				<br>
				<div id="map">지도</div>
				<br><br>
	
				<div class="weather">날씨</div>
				<br><br>

					<div class="share">
					<p>공유하기</p>
						<button id="share-fb" class="share-button">페이스북</button>&nbsp;&nbsp;
						<button id="share-tw" class="share-button">트위터</button>&nbsp;&nbsp;
						<button id="share-ko" class="share-button">카카오톡</button>
					</div>
					
				<br>

				<div class="comment">
					<h4 class="comment-count">댓글</h4>
					<form action="" method="get" id="comment-form">
						<textarea id="comment-text"
							placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"></textarea>
						<button type="button" id="comment-btn">입력</button>
					</form>
					<p class="comment-id">${data.id}</p>
					<p class="comment-date">${data.createdate}</p>
					<hr>
					<p class="comment-id">${data.id}</p>
					<p class="comment-date">${data.createdate}</p>
					<hr>
					<p class="comment-id">${data.id}</p>
					<p class="comment-date">${data.createdate}</p>

				</div>

		</div>
	</div>
</div>

<%@ include file="../components/footer.jsp"%>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2ad95b349288a2f15e7c503c543bb5fe&libraries=services"></script>

<script>//지도출력
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch('${data.address}', function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="padding:3px;color:black;font-size:14px;width:max-content;">${data.title}<br>${data.address}</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>


<script>//공유하기
	const url = encodeURI(window.location.href);
	
	//Facebook
	const shareFacebook = () => {
		window.open("http://www.facebook.com/sharer/sharer.php?u=" + url, '${data.title}', 'width=300px,height=350px');
	}
	
	
	//Twitter
	const shareTwitter = () => {
	const text = '${data.title}'
		window.open("https://twitter.com/intent/tweet?text=" + text + "&url=" +  url, text, 'width=500px,height=650px')
	}
	
	$('#share-fb').on('click', () => {
		shareFacebook();
	});
	
	$('#share-tw').on('click', () => {
		shareTwitter();
	});
</script>
