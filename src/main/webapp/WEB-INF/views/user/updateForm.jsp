<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp" %>

<div class="container">
<form>
  <input type = "hidden" id="id" value="${principal.user.id }"/>
  <div class="form-group">
    <label for="username">UserName(Cannot Modify 🙅)</label>
    <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter UserName" id="username" readonly>
  </div>
  <div class="form-group">
    <label for="pwd">Your Email address(you can change)</label>
    <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter Email" id="email">
  </div>
   <div class="form-group">
    <label for="password">Password(you can change)</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
</form>
  <button id="btn-update" class="btn btn-primary">modify profile</button>
</div>
<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>


