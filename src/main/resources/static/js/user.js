let index = {
	init:function(){
		$("#btn-save").on("click",()=>{//this바인딩하기 위해 애로우펑션을 쓴다.
			this.save()
		});
		
		$("#btn-update").on("click",()=>{//this바인딩하기 위해 애로우펑션을 쓴다.
			this.update()
		});
		
	},
	save:function(){
		//alert('user의 save함수 호출됨');
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		//console.log(data);
		// ajax호출시 비동기가 default임.
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으,로 자바 오브젝트로 변환해준다.
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), // http body데이터
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType:"json"//요청을 서버로 해서 응답이 왔을 때 생긴게 json이라면 javascript로 바꿔줌. 
		}).done(function(resp){
			if(resp.status === 500){
				alert("Fail to join usㅠㅠ");
			}else{
				alert("WELCOME!");
				location.href = "/";
			}
			
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax통신을 이용해서 3개의데이터를 json으로 변경하여 insert요청!!
	},
	
	update:function(){
		//alert('user의 save함수 호출됨');
		let data = {
			id:$("#id").val(),
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		$.ajax({
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data), // http body데이터
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType:"json"//요청을 서버로 해서 응답이 왔을 때 생긴게 json이라면 javascript로 바꿔줌. 
		}).done(function(resp){
			alert("Your Profile is Changed!");
			console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax통신을 이용해서 3개의데이터를 json으로 변경하여 insert요청!!
	},

}
index.init();

//  /api/user/login
//	login:function(){
//		//alert('user의 save함수 호출됨');
//		let data = {
//			username:$("#username").val(),
//			password:$("#password").val(),
//		};
//
//		$.ajax({
//			type:"POST",
//			url:"/api/user/login",
//			data:JSON.stringify(data), // http body데이터
//			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
//			dataType:"json"//요청을 서버로 해서 응답이 왔을 때 생긴게 json이라면 javascript로 바꿔줌. 
//		}).done(function(resp){
//			alert("WELCOME!");
//			console.log(resp);
//			location.href = "/";
//		}).fail(function(error){
//			alert(JSON.stringify(error));
//		}); // ajax통신을 이용해서 3개의데이터를 json으로 변경하여 insert요청!!
//	}