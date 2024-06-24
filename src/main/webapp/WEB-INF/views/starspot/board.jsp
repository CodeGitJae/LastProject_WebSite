<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content" style="color:white">
        	<div>
        		<a href="/starspot/write">글쓰기</a><br><br>
        	</div>
			<c:forEach items="${list}" var="item">
				<a href="/starspot/detail?id=${item.id}">
				${item.title}
				</a><br>
				${item.content}<br>
				${item.address }<br>
				${item.createdate}<br>
				<img src="${item.images[0].imagePath}" style="width:100px">
				<br><hr>
			</c:forEach>
        </div>
      </div>
    </div>
  </div>
  
<%@ include file="../components/footer.jsp" %>
  