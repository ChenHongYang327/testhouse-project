<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<style type="text/css">
		form {
			width: 100%;
			text-align: center
		}
	</style>
	<meta charset="UTF-8">
	<title>登入頁面</title>
</head>

<body>
	<form id="form1" method="POST">
		<label>帳號:</label> <input type="text" id="username" name="username"> <br>
		<label>密碼:</label> <input type="password" id="password" name="password"><br>
		<a href="registered.jsp">註冊</a><br> 
		<input type="button" id="btn01" value="登入" />
	</form>
	
	<%


	%>
</body>

<script type="text/javascript">
	const btn01 = document.getElementById('btn01');//常數
	//input點擊Enter監聽
	document.getElementById('username').addEventListener("keyup", function (event) {
		event.preventDefault();
		if (event.keyCode === 13) {
			document.getElementById('btn01').click();
		}
	});
	//input點擊Enter監聽
	document.getElementById('password').addEventListener("keyup", function (event) {
		event.preventDefault();
		if (event.keyCode === 13) {
			document.getElementById('btn01').click();
		}
	});
	//button監聽
	btn01.onclick = () => {
		var name = document.getElementById('username');//區域變數
		var pw = document.getElementById('password');//區域變數
		if (name.value == "" || pw.value == "") {
			alert("帳號或密碼不能為空,請重新輸入");
			return;
		}
		var data = {
			"account": document.getElementById('username').value,
			"password": document.getElementById('password').value
		};
		/*alert(JSON.stringify(data));*/
		/*  $.ajax({
			url : "LoginCheck", //後端的URL
			type : "POST", //用POST的方式
			ccontentType: "application/json", 
			data : {
				data:JSON.stringify(data)
			},
			 success: function(data){alert(response);}
		});   */

		fetch("LoginCheck", {
			method: 'POST',
			body: JSON.stringify(data),
			headers: {
				'Content-Type': 'application/json; charset=UTF-8',
			}
		})
			.then(response => response.json())	
			.then(result => {
				console.log(result)
				if (result.Status == "FAIL") {
					alert("帳號或密碼錯誤");
					//location.reload();
				}
				else {
					location.reload();
				}
			});

		
	}
</script>

</html>