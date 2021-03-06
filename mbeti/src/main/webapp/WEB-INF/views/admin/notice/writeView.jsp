<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<jsp:include page="../../header.jsp"></jsp:include>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form[name='writeForm']");
		$(".write_btn").on("click", function(){
			if(fn_valiChk()){
				return false;
			}
			formObj.attr("action", "/admin/notice/write");
			formObj.attr("method", "post");
			formObj.submit();
		});
		fn_addFile();
	})
	function fn_valiChk(){
		var regForm = $("form[name='writeForm'] .chk").length;
		for(var i = 0; i<regForm; i++){
			if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null){
				alert($(".chk").eq(i).attr("title"));
				return true;
			}
		}
	}
	function fn_addFile(){
		var fileIndex = 1;
		//$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
		$(".fileAdd_btn").on("click", function(){
			$("#fileIndex").append("<div class='uploadbox'><input type='file' class='fileinput' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' class='btn btn-sm btn-basic' id='fileDelBtn'>"+"삭제"+"</button></div>");
		});
		$(document).on("click","#fileDelBtn", function(){
			$(this).parent().remove();
			
		});
	}
	
</script>


	<!-- //contents  -->
	<div class="contents">
		<div class="layout">
		<h2 class="h2">공지사항 글작성</h2>
		<jsp:include page="../../nav.jsp"></jsp:include>
	
				<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
				
				<form name="writeForm" method="post" action="/admin/notice/write" enctype="multipart/form-data" onsubmit="return FormSubmit(this);">
					<div class="form-group">
						<label for="title">제목</label>
						<input type="text" id="title" name="title" class="chk form-control" placeholder="제목을 입력하세요." title="제목을 입력하세요."/>
					</div>
					<div class="form-group">
						<label for="content">내용</label>
						<textarea id="content" name="content" class="chkc ckeditor" placeholder="내용을 입력하세요." title="내용을 입력하세요." style="resize: none;"></textarea>						
					</div>
					<script>
						CKEDITOR.replace('content');
					</script>
					<div class="form-group">
						<label for="writer">작성자</label>
						<input type="text" id="writer" name="writer" class="chk" title="작성자를 입력하세요." value="${member.userName}" />
					</div>
					<div class="form-group">
						<label for="fileIndex">파일추가</label>							
						<button class="fileAdd_btn btn btn-basic" type="button">파일추가</button>
						<div id="fileIndex" class="formcontrol">
						</div>						
					</div>
					<div class="form-group form-btn">
						<a href="/notice/list" class="btn btn-danger">목록</a>	
						<button class="btn btn-success write_btn" type="submit">작성</button>
					</div>
				</form>
		
		</div><!-- //layout  -->
	</div><!-- //contents  -->

<jsp:include page="../../footer.jsp"></jsp:include>
						