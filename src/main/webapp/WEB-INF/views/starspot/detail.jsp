<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../components/header.jsp" %>

  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="page-content">
			<div style="color: #fff">
	        	<h1>${data.title}</h1>
				주소 : ${data.address}<br>
				작성일 : ${data.createdate}<br>
				<c:forEach items="${data.images}" var="image">
					<img src="<%= request.getContextPath() %>/${image.imagePath}" style="width:300px"><br>
				</c:forEach>
				${data.content}<br>
	          </div>
        </div>
      </div>
    </div>
  </div>
  
  <%@ include file="../components/footer.jsp" %>
  