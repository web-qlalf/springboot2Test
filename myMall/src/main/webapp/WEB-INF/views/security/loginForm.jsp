<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<jsp:include page='../global-header.jsp' />
<jsp:include page='../global-slider.jsp' />
<!--Login-Section-->

<section class='sptb'>
	<div class='container customerpage'>
		<div class='row'>
			<div class='single-page'>
				<div class='col-lg-5 col-xl-4 col-md-6 d-block mx-auto'>
					<div class='wrapper wrapper2'>
						<c:url value='j_spring_security_check' var='loginUrl' />
						<form id='login' action='${loginUrl}' class='card-body'
							method='post' tabindex='500'>
							<c:if test='${param.error != null}'>
								<p class='text-primary ml-1'>로그인 실패</p>
								<p class='text-dark mt-1'>${error_message}</p>
								</p>
							</c:if>
							<h3>Login</h3>
							<div class='mail'>
								<input type='text' name='email' value='${username}'> <label>아이디</label>
							</div>
							<div class='passwd'>
								<input type='password' name='pw'> <label>비밀번호</label>
							</div>
							<div class='loginBtn'>
								<button type='button' id='saveBtn'
									class='btn btn-primary btn-block login-go'>로그인</button>
							</div>
							<p class='mb-2'>
								<a href='forgot.html'>비밀번호 찾기</a>
							</p>
							<p class='text-dark mb-0'>
								아직 회원이 아니신가요? <a href='register.html' class='text-primary ml-1'>가입하기</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--/Login-Section-->
<script>
	$(document).ready(function() {
		// 		$('#login').submit(function (event) {
		// 			event.preventDefault(); //prevent default action
		// 			var formData = new FormData(this); //Creates new FormData object
		// // 			console.log(this);
		// 			loginUser(formData);
		// 		});

		$('#saveBtn').click(function() {
			var result = validation();
			var user_id = $('input[name=email]').val();
			var user_pw = $('input[name=pw]').val();
			if (result) {
				//var formData = new FormData($('#login')); //Creates new FormData object
				var list = {
					'email' : user_id,
					'pw' : user_pw,
				}
				loginUser(list);
				//$('#login').submit();
			}
		});

	});

	function validation() {
		var user_id = $('input[name=email]').val();
		//var user_id = $('input[name=my_id]').val();
		var user_pw = $('input[name=pw]').val();
		//var user_pw = $('input[name=my_pass]').val();
		var result = true;

		if (!user_id || !user_pw) {
			result = false;
			// 			alert();
			swal({
				text : '아이디와 비밀번호를 확인하세요.',
				closeOnEsc : false, //ESC 클릭시 안 꺼짐
				closeOnClickOutside : false, //버튼 외 영역 클릭시 창이 안 꺼짐
				buttons : {
					'확인' : true
				}
			});
		}

		return result;
	}

	function loginUser(formData) {
		//console.log(formData);
		var user_id = $('input[name=email]').val();
		var user_pw = $('input[name=pw]').val();
		console.log('????????????');
		var list = {
			"email" : user_id,
			"pw" : user_pw
		}

		var myHeaders = new Headers();
		myHeaders.append("Authorization", "Basic Og==");
		myHeaders.append("Content-Type", "application/json");

		var raw = JSON.stringify(list);

		var requestOptions = {
		  method: 'POST',
		  headers: myHeaders,
		  body: raw,
		  redirect: 'follow'
		};

		fetch("http://localhost:8888/api/v1/login", requestOptions)
		  .then(response => response.text())
		  .then(function(res){
			  var test = JSON.parse(res);
			  console.log(test);
			  console.log(test.message);
			  location.href="/admin/welcome";
		  })
		  .catch(error => console.log('error', error));
	}
</script>
<jsp:include page='../global-footer.jsp' />


