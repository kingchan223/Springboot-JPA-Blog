<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<div class="container">
<form action="/auth/loginProc" method="post">
  <div class="form-group">
    <label for="username">UserName:</label>
    <input type="text" name="username" class="form-control" placeholder="Enter UserName" id="username">
  </div>

   <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
  </div>

  <button id="btn-login" class="btn btn-primary">Login!</button>
  <a href="https://kauth.kakao.com/oauth/authorize?client_id=f3d3be8cb55b4e2edb2363e211881661&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
  <img height="38" src="/image/kakao_login_button.png"/></a>
  
</form>

</div>
<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>


