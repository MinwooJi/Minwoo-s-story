<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h1>도서 관리</h1>
	<div>
		<button id="listBtn">목록조회</button>
		(상세 내용을 조회하기 위해서 목록의 아이템을 클릭하세요)
	</div>
	<ul id="isbnUL"></ul>
	<div>
		<button id="addBtn">추가</button>
		<button id="modBtn">수정</button>
		<button id="delBtn">삭제</button>
	</div>
	<form id="inputForm">
		<div>
			<span>ISBN</span>
			<input type="text" id="isbn" name="isbn">
		</div>
		<div>
			<span>제목</span>
			<input type="text" id="title" name="title">
		</div>
		<div>
			<span>저자</span>
			<input type="text" id="author" name="author">
		</div>
		<div>
			<span>출판사</span>
			<input type="text" id="publisher" name="publisher">
		</div>
		<div>
			<span>가격</span>
			<input type="text" id="price" name="price">
		</div>
		<div>
			<span>도서종류</span>
			<input type="text" id="catalogue" name="catalogue">
		</div>
		<div>
			<span>출판국가</span>
			<input type="text" id="nation" name="nation">
		</div>
		<div>
			<span>통화</span>
			<input type="text" id="currency" name="currency">
		</div>
		<div>
			<textarea id="description" name="description"></textarea>
		</div>
	</form>
	<h2 id="result"></h2>
	<script>
	function getListBook() {
		$.ajax({
			url: "list",
			success: function(data) {
				$("#isbnUL").empty();
				data.forEach(function(book) {
					$("#isbnUL").append(`<li id="isbn\${book.isbn}">\${book.isbn}</li>`)	
				});
			}
		});
	}
	
	// 화면 로딩시 목록 가져오기
	getListBook();		
	
	$("#listBtn").click(function () {
		getListBook();					
	});
	
	$("#isbnUL").on("click", "li", function (e) {
		$.ajax({
			url: "searchBook",
			data: {
				isbn: $(this).text()
			},
			success: function(data) {
				console.dir(data);
				currentIsbn = data.isbn;
				$("#isbn").val(data.isbn);
				$("#title").val(data.title);
				$("#author").val(data.author);
				$("#publisher").val(data.publisher);
				$("#price").val(data.price);
				$("#catalogue").val(data.catalogue);
				$("#nation").val(data.nation);
				$("#currency").val(data.currency);
				$("#description").val(data.description);
			}
		});
	});
	let currentIsbn = "";
	$("#addBtn").click(function () {
		if (currentIsbn === $("#isbn").val()) {
			$("#result").text("존재하는 도서번호입니다.(등록불가능)");
			$("#isbn").val(currentIsbn);
			return;
		}
		$.ajax({
			url: "insertBook",
			type: "POST",
			data: $("#inputForm").serialize(),
			success: function(data) {
				getListBook();
				formReset();	
				$("#result").text("등록 성공");
				
				$("#isbn").val("");
			}
		});
	});
	$("#modBtn").click(function () {
		if (currentIsbn != $("#isbn").val()) {
			$("#result").text("도서번호가 변경되었습니다.(수정불가능)");
			$("#isbn").val(currentIsbn);
			return;
		}
		
		$.ajax({
			url: "updateBook",
			type: "POST",
			data: $("#inputForm").serialize(),
			success: function(data) {
				formReset();	
				$("#result").text("수정 성공");
			}
		});
	});
	$("#delBtn").click(function () {
		if (currentIsbn != $("#isbn").val()) {
			$("#result").text("도서번호가 변경되었습니다.(삭제불가능)");
			$("#isbn").val(currentIsbn);
			return;
		}
		
		const isbn = $("#isbn").val();
		$.ajax({
			url: "removeBook",
			data: {
				isbn: isbn
			},
			success: function(data) {
				console.dir(data);
				$(`#isbn\${isbn}`).remove();
				formReset();	
				$("#result").text("삭제 성공");
				currentIsbn = "";
			}
		});
	});
	
	function formReset() {
		$("#isbn").val("");
		$("#title").val("");
		$("#author").val("");
		$("#publisher").val("");
		$("#price").val("");
		$("#catalogue").val("");
		$("#nation").val("");
		$("#currency").val("");
		$("#description").val("");
	}
</script>
</body>
</html>















