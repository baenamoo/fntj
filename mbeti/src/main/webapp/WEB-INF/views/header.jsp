<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jambox</title>
	<link rel="icon" type="image/png"  href="/resources/images/common/favicon.png"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap">
	<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Pacifico" />
	<link rel="stylesheet" href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="/resources/semantic.min.css">
	<link rel="stylesheet" href="/resources/css/common.css">
	<link rel="stylesheet" href="/resources/css/contents.css">
	
	<script src="/resources/jquery3.3.1.min.js"></script>
	<script src="/resources/semantic.min.js"></script>
	<script src="/resources/js/bootstrap.js"></script>
	<script src="https://unpkg.com/boxicons@latest/dist/boxicons.js"></script>
	<script>
		if (window.location.pathname == '/main'){document.write('<link rel="stylesheet" href="/resources/css/main.css">');}
	</script>
</head>

<body>


<div class="header">
	<div class="top-left">
		<a href="https://www.16personalities.com/ko" target="_blank" title="새창열림" class="mbti-btn">mbti</a>
	</div>
	<div class="logo">
		<h1><a href="/home" class="logo-btn">jambox</a></h1>
	</div>
	<div class="navbox">
		<ul class="nav">
			<li><a href="/about">About</a></li>
			<li><a href="/match/list"><span>jam</span> search</a></li>
			<li><a href="/notice/list">Notice</a></li>
			<li><a href="/freeBoard/list">Community</a></li>
			<li><a href="/contact">Contact</a></li>
		</ul>
	</div>
	<div class="top-right">
		
		<div class="login">
			<c:if test="${member == null}">
				<a href="/login" class="login-btn">
					<i class='bx bx-user-circle' ></i>
				</a>
			</c:if>
			
			<c:if test="${member != null}">
			<div class="unamebox">
				<span><strong class="uname">${member.userName}</strong>님,</span>
				<span>반갑습니다!</span>
			</div>			
			<a href="#n" class="dropdown-toggle login-btn" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				<i class='bx bx-user-circle' ></i>
			</a>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<ul class="dropdown-menu">
						<c:if test="${member.getEnabled() == 2}">
						<li><a href="/admin/index">관리자페이지</a></li>
						</c:if>
						<li><a href="/user/memberProfile">회원정보관리</a></li>
						<li><a href="/user/logout">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			</c:if>
		</div>
		<div class="menu">			
			<a href="#n" class="menu-btn"><span></span></a>
			<a href="#n" class="close-btn"><i class='bx bx-x'></i></a>
			<div class="menu-box">							
				<ul class="nav">
					<li><a href="/about">About</a></li>
					<li><a href="/match/list"><span>jam</span> Search</a></li>
					<li><a href="/notice/list">Notice</a></li>
					<li><a href="/freeBoard/list">Community</a></li>
					<li><a href="/contact">Contact</a></li>
				</ul>
				<div class="mypage">
					<c:if test="${member == null}">
						<a href="/login" class="mypage-btn"><i class='bx bx-user-circle' ></i>login</a>
					</c:if>
					<c:if test="${member != null}">
						<a href="/user/memberProfile" class="mypage-btn"><i class='bx bx-user-circle' ></i>mypage</a>
					</c:if>
					
				</div>
				<div class="wbg">
					<ul class="wbg-item">
						<li class="box_count"><span></span></li>
						<li class="box_count"><span></span></li>
						<li class="box_count"><span></span></li>
						<li class="box_count"><span></span></li>
						<li class="box_count"><span></span></li>
					</ul>
				</div>
			</div>
		</div><!-- menu  -->
		
	</div><!-- toright  -->
</div>
	
	
<script>
	$(document).ready(function() {
		var menubtn = $(".menu .menu-btn");
		var closebtn = $(".menu .close-btn");
			menubtn.on("click", function(){
				$("body").toggleClass("on");
			});
			closebtn.on("click", function(){
				$("body").toggleClass("on");
			});	
		
		var nava = $(".menu .menu-box .nav li a");
			nava.on("mouseover", function(){
				$("body").addClass("nava");
			});
			nava.on("mouseleave", function(){
				$("body").removeClass("nava");
			});	
			
		var obj = $(".menu-box");
    		obj.ul = obj.find(" .nav");
    		obj.btn = obj.ul.find("a");
    	var count = $("[class*='box_count']");
    	
   		function fn_set_box(idx){
   			count.removeClass("active").eq(idx).addClass("active");
   			var ctf = $(count.eq(idx).find("span"));
   		}
   		obj.btn.on("mouseover", function(){
   			var idx = obj.btn.parent().index($(this).parent());
   			fn_set_box(idx);
   		});
	});	
</script>