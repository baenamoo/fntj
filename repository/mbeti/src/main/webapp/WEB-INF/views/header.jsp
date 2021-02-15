<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- 이게 있어아 c:if문 사용 가능 -->
<link rel="stylesheet" href="/resources/css/bootstrap.css">

<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" 
			date-toggle="collapse" data-target="#bs-example-navbar-collapse-1"	aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/main">MBETI</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li ><a href="/main">메인</a></li>
				<li><a href="/notice/list">공지사항</a></li>
				<li class="active"><a href="/notice/notice">자유게시판</a></li>
			</ul>
			
			<c:if test="${member == null}">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="home">로그인</a></li>
						<li><a href="/user/register">회원가입</a></li>
					</ul>
				</li>
			</ul>
			</c:if>
			
			<c:if test="${member != null}">
			<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/user/memberUpdateView">회원정보관리</a></li>
					<li><a href="/user/logout">로그아웃</a></li>
				</ul>
			</li>
			</ul>
			</c:if>
		</div>
	</nav>
	
	<script src="/resources/js/bootstrap.js"></script>