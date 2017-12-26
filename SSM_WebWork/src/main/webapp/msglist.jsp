 <%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
pageContext.setAttribute("APP_PATH",request.getContextPath());
%>


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
</head>
<body>
	<script type="text/javascript">
	window.onload = functable;	
	function functable(){
		$('#table').bootstrapTable({
				//请求方法
	    	   method: 'post',
	    	    //是否显示行间隔色
	    	   striped: true,
	    	   //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
	    	   cache: false, 
	    	   contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	    	   //是否显示分页（*）  
	    	   pagination: true,   
	    	    //是否启用排序  
	    	   sortable: false,
	    	   //是否显示刷新按钮 
               showRefresh: true,   
               //是否显示所有的列（选择显示的列）
	    	   showColumns: true,
	    	 //是否显示详细视图和列表视图的切换按钮
	    	   showToggle: true,                  
	     	 	//传递参数                 
	    	  //传递参数
	    	   //初始化加载第一页，默认第一页
	    	   //我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
	    	   pageNo:1,   
	    	   //每页的记录行数（*）   
	    	   pageSize: 5,  
	    	   //可供选择的每页的行数（*）    
	    	   pageList: [5,10,15,20,30],
	    	   //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
	    	   url: 'messagelist',
	    	   //默认值为 'limit',传11给服务端的参数为：limit, offset, search, sort, order Else
	    	   //queryParamsType:'',   
	    	   //分页方式：client客户端分页，server服务端分页（*）
	    	   sidePagination: "server",
	    	   queryParams: function (params) { // 请求服务器数据时发送的参数，可以在这里添加额外的查询参数，返回false则终止请求

	    	          return {
	    	              pageSize: params.limit, // 每页要显示的数据条数
	    	              //页码
	    	              pageNo: (params.offset/params.limit)+1  
	    	          }
	    	      },
	    	   columns: [{ //编辑datagrid的列   field不可以加id  否则会在出现id取不出来
	    			field : '',
	    			title : '#',
	    			align : 'center',
	    			checkbox : true,
	    			visible: true                  
	    		}, 
	    		{
	    			field : 'position.psName',
	    			title : '职位名称',		
	    			width : 190,
	    			formatter : function(value, row, index) {
	    				if (row.position) {
	    					return row.position.psName;
	    				} else {
	    					return value;
	    				}
	    			} 
	    		
	    		}, 
	    		
	    		{
	    			field : 'position.money',
	    			title : '薪酬',
	    			width : 80,
	    			formatter : function(value, row, index) {
	    				if (row.position) {
	    					return row.position.money;
	    				} else {
	    					return value;
	    				}
	    			} 
	    		
	    		},
	    		{
	    			field : 'company.address',
	    			title : '公司地址',
	    			formatter : function(value, row, index) {
	    				if (row.company) {
	    					return row.company.address;
	    				} else {
	    					return value;
	    				}
	    			},width : 110
	    		},
	    		{
	    			field : 'time',
	    			title : '发布时间',
	    			width : 130,
	    			formatter : function(value, row, index) {
	    				if (row.time) {
	    					return row.time;
	    				} else {
	    					return value;
	    				}
	    			} 
	    		
	    		},
	    		{
	    			field : 'company.cpName',
	    			title : '公司名称',
	    			width : 1,
	    			events: ShowCpName,
	    			formatter : function(value, row, index) {
	    				if (row.company) {
	    					return "<button  class='btnCpName btn btn-xs btn-primary' '>显示</button>"
	    				} else {
	    					return value;
	    				}
	    			}
	    		},
	    		{
	    			field : 'company.cpInfo',
	    			title : '公司简介',
	    			width : 1,
	    			formatter : function(value, row, index) {
	    				if (row.company) {
	    					return "<button  class='btn btn-xs btn-primary' >显示</button>"
	    				} else {
	    					return value;
	    				}
	    			}
	    		},
	    		{
	    			field : 'position.required',
	    			title : '职位需求',
	    			formatter : function(value, row, index) {
	    				if (row.position) {
	    					return  "<button  class='btn btn-xs btn-primary' >显示</button>"
	    				} else {
	    					return value;
	    				}
	    			},width : 1
	    		},
	    		
	    		{
	    			field : 'position.psUrl',
	    			title : '职位链接',
	    			formatter : function(value, row, index) {
	    				if (row.position) {
	    					return "<button  class='btn btn-xs btn-primary'  >显示</button>"
	    				} else {
	    					return value;
	    				}
	    			},width : 1
	    		},
	    		{
	    			field : 'company.cpUrl',
	    			title : '公司链接',
	    			formatter : function(value, row, index) {
	    				if (row.company) {
	    					return "<button  class='btn btn-xs btn-primary' >显示</button>"
	    				} else {
	    					return value;
	    				}
	    			},width :1
	    		},
	    		{
	    			title: '操作',
	    			align: 'center',
	    			width : 80,
	    			events: operateEvents,
	    			formatter: actionFormatter
	    		}],
	    	   pagination:true
			
			
		})
	}
	

	</script>
	
	<script type="text/javascript">
	
	function clear_model(){
		 
		 $("#search_time").val("");
		 $("#search_position_psName").val("");
		 $("#search_position_money").val("");
		 $("#search_company_cpName").val("");
		 $("#search_company_address").val("");
	}
	
	function addMsg(){
		
		$.ajax({
			type: "post",
			url: "getAllWithCompany",
			contentType: "application/x-www-form-urlencoded",
			dataType:"json",
			success: function(data)
			{
				var txt = '<option></option>';
				for(i=0;i<data.rows.length;i++)
					{
					 txt += '<option>' + data.rows[i].cpName+'</option>';  
					}
				  	  
		            document.getElementById('add_option').innerHTML=txt;  
					$("#addModel").modal('show');	
			}
			
		});
	}
	
	function add_one(){
		var opt={
				"time":$("#add_time").val(),
				"psName":$("#add_position_psName").val(),
				"psMoney":$("#add_position_money").val(),
				"cpName":$("#add_option").val(),
				"position":{
					"required":$("#add_position_required").val(),
					"psUrl":$("#add_position_psUrl").val(),
					"money":$("#add_position_money").val(),
					"psName":$("#add_position_psName").val()
				}
		};
		
				
			 
		
		 $.ajax({
			 type:"post",
			 url: 'addMessageByMsg',
			 contentType: "application/json",
			 dataType:"json",
			 data:JSON.stringify(opt),
			 success:function(result){
				window.location.href="/SSM_WebWork/msglist.jsp";
				alert(result.message);

			 }
		 })
		
		
		
	}
	
	function search_one(){
		var opt={
				url: 'searchMessageListByMsg',
		        silent: true,
				query:{
				"time":$("#search_time").val(),
				"psName":$("#search_position_psName").val(),
				"psMoney":$("#search_position_money").val(),
				"cpName":$("#search_option").val(),
				"cpAddress":$("#search_company_address").val()
				}
			  };
			$("#searchModel").modal('hide');
			$('#table').bootstrapTable('refresh',opt);
			clear_model();
		}
	//返回data 获取出json数据
	function searchMsg(){
		
		
		$.ajax({
			type: "post",
			url: "getAllWithCompany",
			contentType: "application/x-www-form-urlencoded",
			dataType:"json",
			success: function(data)
			{
				var txt = '<option></option>';
				for(i=0;i<data.rows.length;i++)
					{
					 txt += '<option>' + data.rows[i].cpName+'</option>';  
					}
				  console.log(txt);//  
		            document.getElementById('search_option').innerHTML=txt;  
					$("#searchModel").modal('show');	
			}
			
		});
	}
	
	 function actionFormatter(value, row, index) {
            var id = value;
            var result = "";
            result += "<a href='javascript:;' class='showpicturebtn btn btn-xs blue' title='图片'><span class='glyphicon glyphicon-picture'></span></a>";
            result += "<a href='javascript:;' class='showbtn btn btn-xs green' title='查看'><span class='glyphicon glyphicon-search'></span></a>";
            result += "<a href='javascript:;' class='updatebtn btn btn-xs blue'title='编辑'><span class='glyphicon glyphicon-pencil'></span></a>";
            result += "<a href='javascript:;' class='deletebtn btn btn-xs red' title='删除'><span class='glyphicon glyphicon-remove'></span></a>";
            return result;
        }
	 
	 
	 function delete_one(){
		 $.ajax({
			type: "post",
			url: "deleteMsg",
			contentType: "application/x-www-form-urlencoded",
			data:{'id':$("#delete_id").val()},
			success: function(msp)
			{
				alert(msp)
				window.location.href="/SSM_WebWork/msglist.jsp";
			}
			 
		 });
	 }
	 
	 function update_one(){
		 
		 var args={
					"id":$("#update_id").val(),
					"time":$("#update_time").val(),
					"psName":$("#update_position_psName").val(),
					"psMoney":$("#update_position_money").val(),
					"cpName":$("#update_company_cpName").val(),
					"cpAddress":$("#update_company_address").val(),
					"position":{
						"id":$("#update_position_id").val(),
						"psName":$("#update_position_psName").val(),
						"money":$("#update_position_money").val(),
						"required":$("#update_position_required").val(),
						"psUrl":$("#update_position_psUrl").val()
					},
					"company":{
						"id":$("#update_company_id").val(),
						"address":$("#update_company_address").val(),
						"cpInfo":$("#update_company_cpInfo").val(),
						"cpUrl":$("#update_company_cpUrl").val(),
						"cpName":$("#update_company_cpName").val()
					}
				   }
			

		 $.ajax({
			 type:"post",
			 url:"updateMsg",
			 contentType: "application/json",
			 dataType:"json",
			 data:JSON.stringify(args),
			 success:function(result){
				window.location.href="/SSM_WebWork/msglist.jsp";
				alert(result.message);

			 }
		 })
		 
	 }
	 
	 window.ShowCpName ={
			 'click .btnCpName' :function (e, value, row, index)
			 {
				 $("#btn_company_cpName").html(row.company.cpName);
				 $("#modalCpName").modal('show');
			 }
			 
	 }
	 
	 window.operateEvents = {
			 'click .deletebtn': function (e, value, row, index) 
			 	{
			 		
					$("#delete_id").val(row.id);
					$("#delete_position_psName").html(row.position.psName);
					$("#delete_position_money").html(row.position.money);
					$("#delete_company_cpName").html(row.company.cpName);
					$("#delete_company_address").html(row.company.address);
					$("#delete_company_cpInfo").html(row.company.cpInfo);
					$("#delete_position_required").html(row.position.required);
					$("#delete_time").html(row.time);
					$("#delete_position_psUrl").html(row.position.psUrl);
					$("#delete_company_cpUrl").html(row.company.cpUrl);
					$("#deleteModel").modal('show');
				
		   		},'click .updatebtn': function (e, value, row, index) 
			 	{
		   			$("#update_id").val(row.id);
		   			$("#update_position_id").val(row.position.id);
		   			$("#update_company_id").val(row.company.id);
					$("#update_position_psName").val(row.position.psName);
					$("#update_position_money").val(row.position.money);
					$("#update_company_cpName").val(row.company.cpName);
					$("#update_company_address").val(row.company.address);
					$("#update_company_cpInfo").val(row.company.cpInfo);
					$("#update_position_required").val(row.position.required);
					$("#update_time").val(row.time);
					$("#update_position_psUrl").val(row.position.psUrl);
					$("#update_company_cpUrl").val(row.company.cpUrl);
					$("#updateModel").modal('show');
					
				
		   		},'click .showbtn': function (e, value, row, index) 
			 	{
		   			$("#show_id").html(row.id);
					$("#show_position_psName").html(row.position.psName);
					$("#show_position_money").html(row.position.money);
					$("#show_company_cpName").html(row.company.cpName);
					$("#show_company_address").html(row.company.address);
					$("#show_company_cpInfo").html(row.company.cpInfo);
					$("#show_position_required").html(row.position.required);
					$("#show_time").html(row.time);
					$("#show_position_psUrl").html(row.position.psUrl);
					$("#show_company_cpUrl").html(row.company.cpUrl);
					$("#showModel").modal('show');
					
				
		   		},'click .showpicturebtn': function  (e, value, row, index) 
		   		{
		   			
		   			var opt = row.position.required;
		   			
		   			$.ajax({
		   				type: "post",
		   				url:'getPictureData',
		   				dataType:"json",
		   				data:{positionInfo:opt},
		   				success :function(json){
		   					var data3 = json;
							//将画布上的清除
		   					var containerDom = document.getElementById("img");
		   					containerDom.innerHTML = '';			
		   					drawImg.ready(data3 , "img");
		   				}
		   				
		   			});
		   			
		   			$("#pictureModel").modal('show');
		   			opt = null;
		   		}
			
	}
	 
	 
	 function showAllPicture(){

		 $.ajax({
				type: "post",
				url:'getAllPictureData',
				dataType:"json",
				success :function(json){
					var data4 = json;
					//将画布上的清除
					var containerDom = document.getElementById("img");
					containerDom.innerHTML = '';			
					drawImg.ready(data4 , "img");
				}
				
			});
			$("#pictureModel").modal('show');
			opt = null;
		 
	 }
	 
	 function showAllPicture2(){

		 $.ajax({
				type: "post",
				url:'getAllPictureData2',
				dataType:"json",
				success :function(json){
					var data5 = json;
					//将画布上的清除
					var containerDom = document.getElementById("img");
					containerDom.innerHTML = '';			
					drawImg.ready(data5 , "img");
				}
				
			});
			$("#pictureModel").modal('show');
			opt = null;
		 
	 }
	</script>
	<!-- 作用时间：文档加载完毕
	等效于$(document).ready(function(){...});  -->
	<script type="text/javascript">
	
	</script>
	
	
	
	
	<!-- cpName模态框 -->
	<div  id = "modalCpName" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
 	 	<div class="modal-dialog modal-sm" role="document">
   		 <div class="modal-content">
     		<label class="form-label" id = "btn_company_cpName"></label><br><br>
   		 </div>
  		</div>
	</div>
	
	<!-- 图片模态框 -->
	<div id="pictureModel"class="modal fade"  tabindex="-1" role="dialog">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>	
  				<div id="img"></div>			
   		    </div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	<!-- 删除模态框 -->
	<div class="modal fade" id="deleteModel" tabindex="-1" role="dialog">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
     			 	<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       				 	<h4 class="modal-title">删除</h4>
     				</div>
     				
     	 			<div class="modal-body">
     	 			
			        				<form  id ='deleteForm' class="bs-example bs-example-form" role = "form">
			                    		<div class = "input-group" >
			                    			
			                    			<label class="form-label" >确定要删除此条记录</label><br><br>
			                    			<input id="delete_id" type="hidden" value="">
			                    			<label class="form-label" >职位名称</label>&nbsp;&nbsp;<label class="form-label" id = "delete_position_psName"></label><br><br>
			                    			<label class="form-label" >薪酬</label>&nbsp;&nbsp;	<label class="form-label" id = "delete_position_money"></label><br><br>
			                    			<label class="form-label" >公司名称</label>&nbsp;&nbsp;<label class="form-label" id = "delete_company_cpName"></label><br><br>
			                    			<label class="form-label" >公司地址</label>&nbsp;&nbsp;<label class="form-label" id = "delete_company_address"></label><br><br>
			                    			<label class="form-label" >公司简介</label>&nbsp;&nbsp;<label class="form-label" id = "delete_company_cpInfo"></label><br><br>
			                    			<label class="form-label" >职位需求</label>&nbsp;&nbsp;<label class="form-label" id = "delete_position_required"></label><br><br>
			                    			<label class="form-label" >发布时间</label>&nbsp;&nbsp;<label class="form-label" id = "delete_time"></label><br><br>
			                    			<label class="form-label" >职位链接</label>&nbsp;&nbsp;<label class="form-label" id = "delete_position_psUrl"></label><br><br>
			                    			<label class="form-label" >公司链接</label>&nbsp;&nbsp;<label class="form-label" id = "delete_company_cpUrl"></label><br><br>
			                   
			                    		</div>
			                    	</form>
  					</div>
					
     			 	<div class="modal-footer">
        				<<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
        				<button type="button" class="btn btn-primary" onclick="delete_one()">YES</button>
      				</div>
   			 </div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 显示模态框 -->
	<div id="showModel"class="modal fade"  tabindex="-1" role="dialog">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
     			 	<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       				 	<h4 class="modal-title">显示</h4>
     				</div>
     				
     	 			<div class="modal-body">
     	 			
			        				<form  id ='showForm' class="bs-example bs-example-form" role = "form">
			                    		<div class = "input-group" >
			                    			     
			                    			<label class="form-label" >职位名称</label>&nbsp;&nbsp;<label class="form-label" id = "show_position_psName"></label><br><br>
			                    			<label class="form-label" >薪酬</label>&nbsp;&nbsp;	<label class="form-label" id = "show_position_money"></label><br><br>
			                    			<label class="form-label" >公司名称</label>&nbsp;&nbsp;<label class="form-label" id = "show_company_cpName"></label><br><br>
			                    			<label class="form-label" >公司地址</label>&nbsp;&nbsp;<label class="form-label" id = "show_company_address"></label><br><br>
			                    			<label class="form-label" >公司简介</label>&nbsp;&nbsp;<label class="form-label" id = "show_company_cpInfo"></label><br><br>
			                    			<label class="form-label" >职位需求</label>&nbsp;&nbsp;<label class="form-label" id = "show_position_required"></label><br><br>
			                    			<label class="form-label" >发布时间</label>&nbsp;&nbsp;<label class="form-label" id = "show_time"></label><br><br>
			                    			<label class="form-label" >职位链接</label>&nbsp;&nbsp;<label class="form-label" id = "show_position_psUrl"></label><br><br>
			                    			<label class="form-label" >公司链接</label>&nbsp;&nbsp;<label class="form-label" id = "show_company_cpUrl"></label><br><br>
			                   
			                    		</div>
			                    	</form>
  					</div>
  					
  					<div class="modal-footer">
  						<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
        				<button type="button" class="btn btn-primary" data-dismiss="modal">YES</button>        				
      				</div>
					
     			 
   			 </div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	<!-- 修改模态框 -->
	<div id="updateModel"class="modal fade"  tabindex="-1" role="dialog">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
     			 	<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       				 	<h4 class="modal-title">修改</h4>
     				</div>
     				
     	 			<div class="modal-body">
     	 			
			        				<form  id ='updateForm' class="bs-example bs-example-form" role = "form">
			                    		<div class = "input-group" >
			                    			     
			                    			<input id="update_id" type="hidden">
			                    			<input id="update_position_id" type="hidden">    
			                    			<input id="update_company_id" type="hidden">        
			                    			<label class="form-label" >职位名称</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_position_psName"type="text" class="form-control" placeholder="Name" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >薪酬</label>&nbsp;&nbsp;	<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_position_money"type="text" class="form-control" placeholder="money" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >公司名称</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_company_cpName"type="text" class="form-control" placeholder="address" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >公司地址</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_company_address"type="text" class="form-control" placeholder="address" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >公司简介</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_company_cpInfo"type="text" class="form-control" placeholder="Info" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >职位需求</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_position_required"type="text" class="form-control" placeholder="require" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >发布时间</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_time"type="text" class="form-control" placeholder="time" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >职位链接</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_position_psUrl"type="text" class="form-control" placeholder="url" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >公司链接</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="update_company_cpUrl"type="text" class="form-control" placeholder="url" aria-describedby="basic-addon1"></div><br><br>
			                   
			                    		</div>
			                    	</form>
  					</div>
  					
  					<div class="modal-footer">
  						<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
        				<button type="button" class="btn btn-primary" onclick ="update_one()" data-dismiss="modal">YES</button>        				
      				</div>
					
     			 
   			 </div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 搜索模态框 -->
	<div id="searchModel"class="modal fade"  tabindex="-1" role="dialog">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
     			 	<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       				 	<h4 class="modal-title">搜索</h4>
     				</div>
     				
     	 			<div class="modal-body">
     	 			
			        				<form  id ='searchForm' class="bs-example bs-example-form" role = "form">
			                    		<div class = "input-group" >
			                    			     
			                    			       
			                    			<label class="form-label" >职位名称</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="search_position_psName"type="text" value="" class="form-control" placeholder="Name" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >薪酬</label>&nbsp;&nbsp;	<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="search_position_money"type="text" value="" class="form-control" placeholder="money" aria-describedby="basic-addon1"></div><br><br>
											<label class="form-label" >公司地址</label>&nbsp;&nbsp;	<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="search_company_address"type="text" value="" class="form-control" placeholder="address" aria-describedby="basic-addon1"></div><br><br>
			                    			<label for="mysel">公司名称</label>&nbsp;&nbsp;<select id="search_option" class="form-control"></select><br><br><br><br>
			                    			<label class="form-label" >发布时间</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="search_time"type="text" value="" class="form-control" placeholder="time" aria-describedby="basic-addon1"></div><br><br>
			                   				
			                    		</div>
			                    	</form>
  					</div>
  					
  					<div class="modal-footer">
  						<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
        				<button type="button" class="btn btn-primary" onclick ="search_one()">YES</button>        				
      				</div>
					
     			 
   			 </div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
		<!-- 添加模态框 -->
	<div id="addModel"class="modal fade"  tabindex="-1" role="dialog">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
     			 	<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
       				 	<h4 class="modal-title">添加</h4>
     				</div>
     				
     	 			<div class="modal-body">
     	 			
			        				<form  id ='addForm' class="bs-example bs-example-form" role = "form">
			                    		<div class = "input-group" >
			                    		       
			                    			<label class="form-label" >职位名称</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="add_position_psName"type="text" class="form-control" placeholder="Name" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >薪酬</label>&nbsp;&nbsp;	<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="add_position_money"type="text" class="form-control" placeholder="money" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >职位需求</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="add_position_required"type="text" class="form-control" placeholder="require" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >发布时间</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="add_time"type="text" class="form-control" placeholder="time" aria-describedby="basic-addon1"></div><br><br>
			                    			<label class="form-label" >职位链接</label>&nbsp;&nbsp;<div class="input-group"> <span class="input-group-addon" id="basic-addon1">@</span><input id="add_position_psUrl"type="text" class="form-control" placeholder="url" aria-describedby="basic-addon1"></div><br><br>
			                    			<label for="mysel">公司名称</label>&nbsp;&nbsp;<select id="add_option" class="form-control"></select><br><br><br><br>
			                    		</div>
			                    	</form>
  					</div>
  					
  					<div class="modal-footer">
  						<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
        				<button type="button" class="btn btn-primary" onclick ="add_one()" >YES</button>        				
      				</div>
					
     			 
   			 </div><!-- /.modal-content -->
 		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	


	<div class="container">
		<!-- 标题 -->
		<div class="row ">
			<div class="col-md-12">
				<h1>SSM_WebWork</h1>
			</div>
		</div>
		<!-- 按钮 --> 
		<div class="row">
			<div class="col-md-4 col-md-offset-9">
				<button  class="btn btn-sm btn-primary" onclick="showAllPicture()">需求标签</button>
				<button  class="btn btn-sm btn-primary" onclick="showAllPicture2()">技术标签</button>
				<button  class="btn btn-sm btn-primary" onclick="addMsg()">添加</button>
				<button  class="btn btn-sm btn-primary" onclick="searchMsg()">搜索</button>
			</div>
		</div>
		<div class="row">
			<div>
				<table id="table" class="table table-hover">
					<thead>
					<tr>
						<th>#</th>
						<th>职位名称</th>
						<th>薪酬</th>
						<th>公司名称</th>
						<th>公司地址</th>
						<th>发布时间</th>
						<th>公司简介</th>
						<th>职位需求</th>
						<th>职位链接</th>
						<th>公司链接</th>
						<th>操作</th>
					</tr>
					</thead>
				</table>
			
			</div>
		
		</div>
	</div>
	
	
	
	
	<!-- 图形生成的代码可省略。。。。。 -->
<script type="text/javascript">
var drawImg = {
		canvasDom: null ,
		data:[], //数据
		ctx: null, //花边
		finImgData : null , //最终图片
		finImgMsg: null, //存放是否已写信息
		colorArr : [ //颜色选择
			'#3C6B15',
			'#6b4b38',
			'#eb9a2f',
			'#12acab',
			'#eb9a2f',
		],
		
		ready : function( data ,  containerId ){
			this.data = data;
			this.canvasDom = document.createElement("canvas");
			this.canvasDom.setAttribute("width", 600);
			this.canvasDom.setAttribute("height", 400);
			this.canvasDom.style.backgroundColor  = 'white';
			var containerDom = document.getElementById(containerId);
			containerDom.appendChild(this.canvasDom);
			
			this.ctx = this.canvasDom.getContext('2d');
			this.finImgData = this.ctx.createImageData(600 , 400);
			this.finImgMsg = [];
			
			for(var i=0 ; i<600 ; i++){
				this.finImgMsg[i] = [];
				for(var j=0 ; j<400 ; j++){
					this.finImgMsg[i][j] = 0;
				}
			}
			
			this.cavulateData();
			//log(this.data)
			this.draw();
		},
		/**
		 * 计算标签大小
		 */
		cavulateData : function(){
			var dataArr = [];
			var obj ;
			for(var name in this.data ){
				dataArr.push( {name:name , count : this.data[name]}) 
			}
			dataArr.sort(function(x , y){
				if(Math.floor(x.count) == Math.floor(y.count)){
					return 0;
				}
				if( Math.floor(x.count) > Math.floor(y.count)){
					return -1;
				}else{
					return 1;
				}
			})
			var shift=-6;
			for(var i =0 ; i< dataArr.length ; i++){
				if(i==0){
					dataArr[0].count = 68;
					continue;
				}
				if(shift < 0){
					shift ++
				}
				dataArr[i] .count =Math.floor(dataArr[i-1].count  * 99/ 100)+shift;
				if(dataArr[i] .count < 13){
					dataArr[i] .count = 17;
				}
			}
			
			
			this.data = dataArr;
			
		},
		/**
		 * 开始画
		 */
		draw : function(){
			for(var i = 0 ; i< this.data.length ; i++){
				this.drawWord(this.data[i].name , this.data[i].count);
			}
				
			
			this.ctx.putImageData(this.finImgData ,0,0);
		},
		/**
		 * 单一一个标签画
		 */
		drawWord: function(word , size){

			var fillStyle = this.colorArr[ random(this.colorArr.length-1)];
			this.ctx.fillStyle = fillStyle;
			this.ctx.font = "900 " + size + "px   微软雅黑";
			var w =this.ctx.measureText(word).width;
			this.ctx.textBaseline = "top";
			this.ctx.fillText(word ,0 ,0 );
			var wordImgData = this.ctx.getImageData( 0 , 0 , w , size +10 );
			wordImgData = this.randomRotateImgeData(wordImgData)
			this.ctx.clearRect(0 ,0 , 600 ,400 );
			//初始化查找点
			
			var centerPoint = this.getCenterPoint();
			var i = 0;
			while (i<1000){
				if(centerPoint.isFullRound()){
					centerPoint.clearRound();
					
				}
				var pos = centerPoint.getCenterPos(wordImgData .width , wordImgData.height);
				pos.x = 600 /2 +pos.x;
				pos.y = 400 /2 +pos.y;
				
				if(this.isAbleDraw(wordImgData  , pos.x , pos.y)){
					for(var i = 0 ; i < wordImgData.width  ; i++){
						for(var j=0 ; j < wordImgData.height ; j++){
							var  point= getXY(wordImgData , i  ,j );
							if( point[3]!=0 ){
								setXY(this.finImgData ,pos.x+i,pos.y+j,point);
								this.finImgMsg[pos.x+i-1][pos.y+j-1] = 1
							}
						}
					}
					break;
				}
				i++;
				centerPoint = this.getCenterPoint( centerPoint);
			}
		},
		
		/**
		 * 是否可以画
		 */
		isAbleDraw : function ( wordImg, x , y){
			var w = wordImg.width;
			var h = wordImg.height;
			
			for(var i = 0 ; i <  w ; i++){
				for(var j = 0 ; j < h ; j++){
					
					var wordPoint = getXY(wordImg , i ,j);
					//检测文字图片上该点是否有痕迹，不全为白为有痕迹
					if(wordPoint[3]!=0 ){
						var finx = x+i-1;
						var finy = y+j- 1;
						if((finx <0)||(finx >= 600)||(finy <0)||(finy >=400))
							return false;
						if(this.finImgMsg[finx][finy] == 1)
							return false;
					}
				}
			}
		
			
		return true;
		},
		
		
		
		/**
		 * 随机旋转
		 */
		randomRotateImgeData : function(imgData){
			var newImageData = this.ctx.createImageData(imgData.height , imgData.width);
			if(random(9)>6){
				for(var i = 0 ; i < imgData.height ; i++){
					for(var j = 0 ; j < imgData.width ; j++){
						var point = getXY(imgData , j , i);
						setXY(newImageData , imgData.height - i -1 ,j ,point );
					} 
				}
				imgData = newImageData;
			}
		
			return imgData;
		},
		
		/**
		 * 用于标签的位置选择
		 */
		getCenterPoint : function(centerPoint){
			
			//没有传入centerPoint,默认初始化
			if( typeof centerPoint != 'object'){
				//centerPoint对象，用于存储以往已经选择的点的信息
				var centerPoint = {
					round : 1, //第几圈
					choose : [], //已选择的点
					nowChoose : null,
					revert : 0,
					/**
					 * 随机选择点
					 */
					randPoint : function (){
						var chooseCount  = this.round == 1 ?  1 : this.round * 2 + (this.round - 2) * 2; //总共可以选择的点
						//所有情况已经遍历了，增加一环 ,重置
						if(this.choose.length == chooseCount ){
							this.round++;
							this.choose = [];
							this.revert = 0;
							return this.randPoint(); 
						}
						
						while(true){
							this.nowChoose = random(chooseCount-1);
							if(!inArray(this.nowChoose , this.choose)){
								this.choose.push(this.nowChoose);
								
								break;
							}
						} 
						
						return this.nowChoose;
					},
					getCenterPos:function(w , h){
						var shift = 0.5; //偏移率
						var shiftw = random(1) ? random( w*shift) : -random( w*shift); 
						var shifth = random(1) ? random( h*shift) : -random( h*shift); 
						var pos = {
							x : 0,
							y : 0
						}
						
						if(this.nowChoose === null){
							return false;
						}
					
						if(this.round != 1){
							var quadrant = Math.floor((this.nowChoose)/(this.round-1));
							var distance =  (this.nowChoose+1) % this.round;//象限的偏移
							
							//log(quadrant)
							
							switch(quadrant){
								case 0 :
									pos.x = w / 2  *  distance  ;
									pos.y = h / 2 *  (this.round -distance) ;
									break;
								case 1 :
									pos.x = w / 2  *  (this.round -distance);
									pos.y = h / 2 *  (-distance) ;
									break;
								case 2 :
									pos.x = w / 2  *  (-distance);
									pos.y = h / 2 *  -(this.round - distance) ;
									break;
								case 3 :
									pos.x = w / 2  * -(this.round - distance) ;
									pos.y = h / 2 * distance;;
									break;
							}
						}
						
					
						pos.x +=  shiftw;
						pos.y +=  shifth;
						
						pos.x = Math.floor(pos.x - w/2)
						pos.y = Math.floor(pos.y - h/2)
						return pos;
						
					},
					isFullRound: function(){
						if(this.revert) return false;
						var chooseCount  = this.round == 1 ?  1 : this.round * 2 + (this.round - 2) * 2; //总共可以选择的点
						return this.choose.length == chooseCount;
					},
					clearRound:function(){
						this.choose = [];
						this.revert = 1;
					}
				};
			
			}
			
			centerPoint.randPoint();
			return  centerPoint;
		}
		
	}






	function random(num){
		return Math.floor(Math.random() *( num+1));
	}
	function log(str){
		console.log(str);
	}

	/**
	 * imgData根据坐标获取
	 */
	function getXY(imgData ,x, y){
		var res = []; 
		var w = imgData.width;
		var h = imgData.height;
		
		var pos = ( y * w + x) * 4;
		
		res[0] =  imgData.data[pos];
		res[1] =  imgData.data[pos+1];
		res[2] =  imgData.data[pos+2];
		res[3] =  imgData.data[pos+3];
		return res;
	}
	/**
	 * imgData根据坐标修改
	 */
	function setXY(imgData ,x, y,res){
		var w = imgData.width;
		var h = imgData.height;
		
		var pos = ( y * w + x) * 4;
		
		imgData.data[pos] = res[0] ;
		imgData.data[pos+1] = res[1] ;
		imgData.data[pos+2] = res[2] ;
		imgData.data[pos+3] = res[3] ;
	}

	/**
	 *将重心坐标改成边缘坐标
	 * 适用于x和y轴
	 */
	function center2abs(center , w , pos ){
		return center - Math.floor(w/2)+pos;
	}

	function inArray(son , arr){
		for(var i = 0 ; i < arr.length ; i++){
			if(arr[i] == son){
				return true;
			}
		}
		return false;
	}


</script>


</body>
</html>