<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	margin: 0px;
}

.wrapper {
	width: 100%;
}

.regist-area {
	width: 20%;
	height: 500px;
	background: yellow;
}

.list-area {
	width: 60%;
	height: 500px;
	background: pink;
	overflow:scroll;
}

.detail-area {
	width: 20%;
	height: 500px;
	background: blue;
}

.regist-area, .list-area, .detail-area {
	float: left;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		getList();

		$("form[name='regist-form']").find("button").click(function() {
			regist();
		});
	});
	//비동기 요청!!
	function regist() {
		$.ajax({
			url : "/admin/member/regist",
			type : "post",
			data : {
				id : $($("form[name='regist-form']").find("input[name='id']"))
						.val(),
				pass : $(
						$("form[name='regist-form']")
								.find("input[name='pass']")).val(),
				name : $(
						$("form[name='regist-form']")
								.find("input[name='name']")).val()
			},
			success : function(result) {
				var json = JSON.parse(result);
				//목록갱신!!
				if (json.result == 1) {
					getList();
				} else {
					alert("등록에 실패했습니다");
				}
			},
			error : function(result) {
				alert(result);
			}
		});
	}
	function getList() {
		$.ajax({
			url : "/admin/member/list",
			type : "get",
			success : function(result) {
				renderList(JSON.parse(result));
			},
			error : function(result) {

			}
		});
	}

	// 리스트 화면 처리
	function renderList(json) {
		$(".list-area").append("<table width='100%' border='1'>");
		$(".list-area").append("<tr>");
		$(".list-area").append("<td width='10%'>seq</td>");
		$(".list-area").append("<td width='30%'>id</td>");
		$(".list-area").append("<td width='30%'>pass</td>");
		$(".list-area").append("<td width='30%'>name</td>");
		$(".list-area").append("</tr>");
		
		for(var i=0;i<json.memberList.length;i++){
			$(".list-area").append("<tr>");
			$(".list-area").append("<td>"+i+"</td>");
			$(".list-area").append("<td>dd</td>");
			$(".list-area").append("<td>dd</td>");
			$(".list-area").append("<td>dd</td>");
			$(".list-area").append("</tr>");
		}
		
		$(".list-area").append("</table>");
	}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="regist-area">
			<form name="regist-form">
				<input type="text" name="id" placeholder="아이디입력" /> <input
					type="text" name="pass" placeholder="비번입력" /> <input type="text"
					name="name" placeholder="이름입력" />
				</p>
				<button type="button">등록</button>
			</form>
		</div>
		<div class="list-area"></div>
		<div class="detail-area">
			<form name="detail-form">
				<input type="text" name="pass" placeholder="비번입력" /> <input
					type="text" name="name" placeholder="이름입력" />
				</p>
				<button type="button">수정</button>
				<button type="button">삭제</button>
			</form>
		</div>
	</div>
</body>
</html>