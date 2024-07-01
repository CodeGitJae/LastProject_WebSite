<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>

<style>
/* 일요일 날짜 빨간색 */
.fc-day-sun a {
  color: #FF6347;
}

/* 토요일 날짜 파란색 */
.fc-day-sat a {
  color: #6495ED;
  text-decoration: none;
}

.fc-daygrid-day {
     position: relative;
}

.fc-daygrid-day img {
  position: absolute;
  top: 25px; /* 날짜 숫자 아래로 이미지 배치 */
  left: 50%;
  transform: translateX(-50%);
  width: 80%;
  height: calc(100% - 25px); /* 날짜 숫자를 제외한 나머지 부분 채움 */
  object-fit: cover;
  z-index: 0;
  padding: 5px
}

.fc-today-button {
	background-color: transparent;
	color: #e2e2e2; /* 글자색 설정 */
}

</style>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">

          <div id='calendar'></div>
          
        </div>
      </div>
    </div>
  </div>
  
<%@ include file="../components/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/mooncalendar.js"></script>
  