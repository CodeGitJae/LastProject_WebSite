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
				<br> <br>

				<div class="weather" style="border:1px solid; padding: 0.5em; width: 360px">날씨</div>
				
				<div class="dustInfo" style="border:1px solid; padding: 0.5em; width: 360px">
					<span class="1PM10"></span>
					<span class="1PM25"></span>
				</div>
				
				<div class="dustInfo" style="border:1px solid; padding: 0.5em; width: 360px">
					<span class="2PM10"></span>
					<span class="2PM25"></span>
				</div>
				
				<div class="dustInfo" style="border:1px solid; padding: 0.5em; width: 360px">
					<span class="3PM10"></span>
					<span class="3PM25"></span>
				</div>
				<br> <br>

				<div class="share">
					<p>공유하기</p>
					<button id="share-fb" class="share-button">페이스북</button>
					&nbsp;&nbsp;
					<button id="share-tw" class="share-button">트위터</button>
					&nbsp;&nbsp; <span class="button gray medium"><a href="#"
						onclick="clip(); return false;">URL주소복사</a></span>
				</div>

				<br>

				<div class="comment">
					<h4 class="comment-count">댓글</h4>
					<form action="" method="get" id="comment-form">
						<input id="comment-writer" type="hidden"
							value="${authenticatedUsername}"> <input id="board-id"
							type="hidden" value="${data.id}">
						<textarea id="comment-text"
							placeholder="칭찬과 격려의 댓글은 작성자에게 큰 힘이 됩니다 :)"></textarea>
						<button type="button" id="comment-btn">등록</button>
					</form>
					<div class="comment-section">
						<c:forEach items="${data.replies}" var="reply">
							<div class="commentBox reply-${reply.id}">
								<div class="comment-header">
									<span class="comment-author">${reply.writer}</span> <span
										class="comment-date">${reply.createdate}</span>
								</div>
								<div class="comment-content-box">
									<pre class="comment-content">${reply.content}</pre>
									<c:if test="${authenticatedUsername == reply.writer}">
										<div class="comment-actions">
											<a href="" class="update-reply" data-replyid="${reply.id}">수정</a>
											| <a href="" class="delete-reply" data-replyid="${reply.id}">삭제</a>
										</div>
									</c:if>
								</div>
								<hr>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- reply modal -->
<div class="modal-background" id="modal">
	<div class="modal-content">
		<h3 style="color: black; margin-bottom: 15px;">댓글 수정</h3>
		<input class="replyid" type="hidden">
		<textarea id="modal-textarea" rows="4" cols="30"
			placeholder="Write your content here..."></textarea>
		<button type="button" class="btn btn-dark mt-3 update-btn">수정완료</button>
	</div>
</div>

<input id="forAjax" type="hidden" value="${data.address}">

<%@ include file="../components/footer.jsp"%>
<script
	src="${pageContext.request.contextPath}/assets/js/observatory-detail.js"></script>
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


	<script type="text/javascript">

function clip(){

	var url = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	url = window.document.location.href;
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")
}

</script>

<script>

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


	let address = document.getElementById("forAjax").value

	$.ajax({
		type:'GET',
		url: 'http://13.209.237.30:5000/dust_info?address='+address,
		dataType:'json',
		success : function(result){
			
			$(".1PM10").text("오늘의 미세먼지: " + parseInt(result[0]["PM10"]) + PM(parseInt(result[0]["PM10"])) + ", ");
			$(".1PM25").text("오늘의 초미세먼지: " + parseInt(result[0]["PM2.5"]) + PM(parseInt(result[0]["PM2.5"])));
			$(".2PM10").text("내일의 미세먼지: " + parseInt(result[1]["PM10"]) + PM(parseInt(result[1]["PM10"])) + ", ");
			$(".2PM25").text("내일 초미세먼지: " + parseInt(result[1]["PM2.5"]) + PM(parseInt(result[1]["PM2.5"])));
			$(".3PM10").text("모레의 미세먼지: " + parseInt(result[2]["PM10"]) + PM(parseInt(result[2]["PM10"])) + ", ");
			$(".3PM25").text("초미세먼지: " + parseInt(result[2]["PM2.5"]) + PM(parseInt(result[2]["PM2.5"])));
		},
		error : function(request, status, error) {
			console.log(error);
		}
	})
</script>