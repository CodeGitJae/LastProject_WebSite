$(document).ready(() => {

	$('#comment-btn').on('click', (e) => {
		e.preventDefault();
		
		const content = $('#comment-text').val();
		const writer = $('#comment-writer').val();
		const boardid = $('#board-id').val();
		
		if(!writer) {
			alert('로그인한 회원만 이용하실 수 있습니다.');
			return;
		}
		
		if(!content) {
			alert('내용을 입력해 주세요');
			return;
		}
		
		$.ajax({
            type: "POST",
            url: "/starspot/reply?boardid=" + boardid,
            contentType: "application/json",
            data: JSON.stringify({
                content: content,
                writer: writer
            }),
            success: function(response) {
                // 요청이 성공했을 때 실행할 코드
                alert("댓글 등록 완료");
                addReply(response);
                $('#comment-text').val('');
            },
            error: function(xhr, status, error) {
                // 요청이 실패했을 때 실행할 코드
                alert("An error occurred: " + error);
                console.log(xhr, status, error);
            }
        });
	});
	
	$('.comment-section').on('click', '.delete-reply', (e) => {
		e.preventDefault();		
		const id = e.target.dataset.replyid;

		$.ajax({
            type: "DELETE",
            url: "/starspot/reply?id=" + id,
            success: function() {
                // 요청이 성공했을 때 실행할 코드
                alert("댓글 삭제 완료");
                $('.reply-' + id).remove();
            },
            error: function(xhr, status, error) {
                // 요청이 실패했을 때 실행할 코드
                alert("An error occurred: " + error);
                console.log(xhr, status, error);
            }
        });
	});
	
	$('.comment-section').on('click', '.update-reply', (e) => {
		e.preventDefault();
		
		const id = e.target.dataset.replyid;
		const commentContent = $(`.reply-${id}`).closest('.commentBox').find('.comment-content').text();
        $('#modal-textarea').val(commentContent);
        $('.replyid').val(id);
        
	    $('#modal').show();
	});
	
	$('.update-btn').on('click', function() {
		const id = $('.replyid').val();
		const content = $('#modal-textarea').val();
		
		$.ajax({
            type: "PUT",
            url: "/starspot/reply",
            contentType: "application/json",
            data: JSON.stringify({
                id: id,
                content: content
            }),
            success: function() {
                // 요청이 성공했을 때 실행할 코드
                alert("댓글 수정 완료");
                
                updateReply(id, content);
                $('#modal').hide();
            },
            error: function(xhr, status, error) {
                // 요청이 실패했을 때 실행할 코드
                alert("An error occurred: " + error);
                console.log(xhr, status, error);
            }
        });
	});
	
	// 모달 닫기
	$('#close-modal').on('click', function() {
	    $('#modal').hide();
	});
	
	// 모달 배경을 클릭하여 닫기
	$('#modal').on('click', function(event) {
	    if ($(event.target).is('#modal')) {
	        $(this).hide();
	    }
	});
	
});



const addReply = (response) => {
	const replyBox = $('.comment-section');
	let replyForm =
	`
		<div class="commentBox reply-${response.id}">
            <div class="comment-header">
                <span class="comment-author">${response.writer}</span>
                <span class="comment-date">${response.createdate}</span>
            </div>
            <div class="comment-content-box">
            	<pre class="comment-content">${response.content}</pre>
    ` +
	            '<c:if test="${authenticatedUsername == ' + response.writer + '}">' +
		`            
		            <div class="comment-actions">
		                <a href="" class="update-reply" data-replyid="${response.id}">수정</a> |
		                <a href="" class="delete-reply" data-replyid="${response.id}">삭제</a>
		            </div>
	            </c:if>
            </div>
        <hr>
        </div>
	`;
	
	replyBox.append(replyForm);
	
}

const updateReply = (id, response) => {
	const replyElement = $(`.reply-${id}`);
    replyElement.find('.comment-content').text(response);
}