let index = {
	init:function(){
			$("#btn-save").on("click",()=>{//this바인딩하기 위해 애로우펑션을 쓴다.
				this.save()
			});
			
			$("#btn-delete").on("click",()=>{
				this.deleteById()
			});
			
			$("#btn-update").on("click",()=>{
				this.update()
			});
			
			$("#btn-reply-save").on("click",()=>{
				this.replySave()
			});
			
		},
		save:function(){
			//alert('user의 save함수 호출됨');
			let data = {
				title:$("#title").val(),
				content:$("#content").val(),
			};
	
			$.ajax({
				type:"POST",
				url:"/api/board",
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8",
				dataType:"json"
			}).done(function(resp){
				alert("your writing is registered!");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
	
		deleteById:function(){
			let id = $("#id").text();
			$.ajax({
				type:"DELETE",
				url:"/api/board/" + id,
				dataType:"json"
			}).done(function(resp){
				alert("your writing is deleted!");
				location.href = "/";
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		update:function(){
			let id = $("#id").val();
			
			let data = {
				title:$("#title").val(),
				content:$("#content").val(),
			};
	
			$.ajax({
				type:"PUT",
				url:"/api/board/" + id,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8",
				dataType:"json"
			}).done(function(resp){
				alert("your writing is modified!");
				location.href = "/board/"+id;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		replySave:function(){
			let data = {
				content:$("#reply-content").val(),
			};
			let boardId = $("#boardId").val();
			$.ajax({
				type:"POST",
				url:`/api/board/${boardId}/reply`,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8",
				dataType:"json"
			}).done(function(resp){
				alert("your comment is registered!");
				location.href = `/board/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
		},
		
		replyDelete:function(boardId, replyId){
			$.ajax({
				type:"DELETE",
				url:`/api/board/${boardId}/reply/${replyId}`,
				dataType:"json"
			}).done(function(resp){
				alert("your comment is deleted!");
				location.href = `/board/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error));
			}); 
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