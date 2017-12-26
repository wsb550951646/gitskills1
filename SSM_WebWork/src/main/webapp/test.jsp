<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
	<form action="testFomat" method="post" enctype="application/x-www-form-urlencoded">
		
		<input name = "date" type = "text" value="2017-06-26">
		<input id ="form1" type="submit" value="提交">
		
	</form>
	
	<button value="按钮" onclick="test()" class="button"></button>
	
	 
	
 
	<script type="text/javascript">
	
	function test(){
		$.ajax({
			type:"POST",
			contentType:"application/x-www-form-urlencoded",
			url:"testFomat",
			data:{date:"1997-11-02"},
			success: function(result){
				alert(result.msg);
			}
			});
				
	}
<!--
	$(function(){
		$(".button").click(function(){	
		$.ajax({
			type:"POST",
			contentType:"application/x-www-form-urlencoded",
			url:"testFomat",
			data:{"date":"1997-11-02"}
		
			});
			
		});
		
	})
	-->
	</script>

	
</body>
</html>