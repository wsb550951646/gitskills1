<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索框</title>

<!-- 引入jquery样式 
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.0.0.min.js"></script>
-->

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<!-- 引入bootstrap的样式 -->
 <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

 <script src="${APP_PATH}/static/bootstrap-table/js/bootstrap-table.js"></script>
 <script src="${APP_PATH}/static/bootstrap-select/js/bootstrap-select.js"></script>

 <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="${APP_PATH}/static/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
  <link href="${APP_PATH}/static/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" >
  
 

<style type="text/css" media="screen">
      body {
        background-color: #fffff;
        margin: 0;
      }
      body,
      input,
      button {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      }
      .container { margin: 30px auto 40px auto; width: 800px; text-align: center; }
 
      a { color: #4183c4; text-decoration: none; font-weight: bold; }
      a:hover { text-decoration: underline; }
 
      h3 { color: #666; }
      ul { list-style: none; padding: 25px 0; }
      li {
        display: inline;
        margin: 10px 50px 10px 0px;
      }
      input[type=text],
      input[type=password] {
        font-size: 13px;
        min-height: 32px;
        margin: 0;
        padding: 7px 8px;
        outline: none;
        color: #333;
        background-color: #fff;
        background-repeat: no-repeat;
        background-position: right center;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        transition: all 0.15s ease-in;
        -webkit-transition: all 0.15s ease-in 0;
        vertical-align: middle;
      }
      .button {
        position: relative;
        display: inline-block;
        margin: 0;
        padding: 8px 15px;
        font-size: 13px;
        font-weight: bold;
        color: #333;
        text-shadow: 0 1px 0 rgba(255,255,255,0.9);
        white-space: nowrap;
        background-color: #eaeaea;
        background-image: -moz-linear-gradient(#fafafa, #eaeaea);
        background-image: -webkit-linear-gradient(#fafafa, #eaeaea);
        background-image: linear-gradient(#fafafa, #eaeaea);
        background-repeat: repeat-x;
        border-radius: 3px;
        border: 1px solid #ddd;
        border-bottom-color: #c5c5c5;
        box-shadow: 0 1px 3px rgba(0,0,0,.05);
        vertical-align: middle;
        cursor: pointer;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-touch-callout: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        -webkit-appearance: none;
      }
      .button:hover,
      .button:active {
        background-position: 0 -15px;
        border-color: #ccc #ccc #b5b5b5;
      }
      .button:active {
        background-color: #dadada;
        border-color: #b5b5b5;
        background-image: none;
        box-shadow: inset 0 3px 5px rgba(0,0,0,.15);
      }
      .button:focus,
      input[type=text]:focus,
      input[type=password]:focus {
        outline: none;
        border-color: #51a7e8;
        box-shadow: inset 0 1px 2px rgba(0,0,0,.075), 0 0 5px rgba(81,167,232,.5);
      }
      
      label[for=search] {
        display: block;
        text-align: left;
      }
      #search label {
        font-weight: 200;
        padding: 5px 0;
      }
      #search input[type=text] {
        font-size: 18px;
        width: 705px;
      }
      #search .button {
        padding: 10px;
        width: 90px;
      }
 
    </style>
</head>
<body>
<div class="container">
  <div id="search">
  	
  		<br><br><br><br><br><br><br><br><br><br><br><br><br>
  		<img alt="" src="${APP_PATH}/static/js/1.png">
  		<br>
    <input type="text" id="keyWord" value="" name="keyWord"><br><br>
    <input class="button" onclick="searchKeyWord()" type="submit" value="百度一下">
  </div>
</div>
<div style="text-align:center;margin:100px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>
<script type="text/javascript">
function searchKeyWord(){
	var temp = $("#keyWord").val()
	$.ajax({
		type:'POST',
		url:"searchKeyWord",
		data:{keyWord:temp},
		success:function(result){
			alert("爬取成功");
			window.location.href="/SSM_WebWork/msglist.jsp";

		 }
	});
}
</script>

</body>
</html>