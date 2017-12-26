<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎</title>
<% 
pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
</head>
 <script type="text/javascript" src="${APP_PATH}/static/js/jquery-1.9.1.min.js"></script>
 <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
 <script src="${APP_PATH}/static/echarts/echarts.js"></script>
 <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="row">
	<div class="col-xs-6">
            <h3>条形图</h3>
    </div>
    <div class="col-xs-6">
            <h3>条形图</h3> 
 	</div>

</div>
    <div class="row">
            <div class="col-md-6">
           		 <div id="main" style="width: 500px;height: 400px;"></div>
            </div>
            <div class="col-md-6">
              	 <div id="tbSecond" style="width: 500px;height: 400px;"></div>
            </div>
   </div>  
   
   <div class="row">
   	<div class="col-md-6">
   		<h3>饼状图加背景</h3>
    </div>
    <div class="col-md-6">
    	 <h3>放大缩小</h3>
     </div>
   </div> 
 
   <div class="row">
    	<div class="col-md-6">
    	 	<div id="bzt3" style="width: 500px;height: 400px;"></div>
    	</div>
    	
    	<div class="col-md-6">
    	<div id="dataZoom" style="width: 500px;height: 400px;"></div>
    </div>
    </div>
    
  	
     
   
   
    	

 
    <div class="wrapper wrapper-content  animated fadeInRight">  
        <div class="row">  
            <div class="col-sm-12">  
                <div class="ibox ">  
                    <div class="ibox-title">  
                        <h5>测试报表</h5>  
                    </div>  
                    <div class="ibox-content">  
                        <div id="main1" style="width: 600px;height:400px;"></div>  
                    </div>  
                </div>  
            </div>  
        </div>  
    </div> 	
    
   
   
            <script type="text/javascript">
            var myChart = echarts.init(document.getElementById("main"));
            var option = {
                title:{
                    text:"第一个图标演示示例"
                },
                tooltip:{
                    text:"this is tool tip"
                },
                legend:{
                    data:['销量']
                },
                xAxis:{
                    data:["寸衫","羊毛衫","裤子","袜子","皮鞋","帽子"]
                },
                yAxis:{},
                series:[{
                            name:["销量"],
                            type:"bar",
                            data:[5,20,36,6,43,1000]
                        }]
            };

             myChart.setOption(option);

           

        </script>
  
    
   
       
        <script type="text/javascript">
            var tbSecond = echarts.init(document.getElementById("tbSecond"));
            // alert(tbSecond);
            var pieOption = {
                    title:{
                        text:"饼状图"
                    },
                    series : [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',  //面积比例
                            data:[
                                {value:235, name:'视频广告'},
                                {value:274, name:'联盟广告'},
                                {value:310, name:'邮件营销'},
                                {value:335, name:'直接访问'},
                                {value:400, name:'搜索引擎'}
                            ]
                        }
                    ]
                };
            // alert(pieOption);
            tbSecond.setOption(pieOption);

        </script>
        
         <script type="text/javascript">
            var bzt3 = echarts.init(document.getElementById("bzt3"));
            bzt3.setOption({
                title:{
                        text:"饼状图"
                    },
                //增加背景色
                backgroundColor:"#EEEFF4",
                //增加阴影块
                itemStyle:{
                    emphasis:{
                        shadowBlur:200,
                        shadowColor:"rgba(0,0,0,0.8)"
                    }
                },
                series:[
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',
                            data:[
                                {value:235, name:'视频广告'},
                                {value:274, name:'联盟广告'},
                                {value:310, name:'邮件营销'},
                                {value:335, name:'直接访问'},
                                {value:400, name:'搜索引擎'}
                            ]
                        }
                    ]
            });
        </script>
        
         <script type="text/javascript">
            var dataZoom = echarts.init($("#dataZoom")[0]);
            dataZoom.setOption(
                {       
                    xAxis:{
                                    type:"value"
                                },
                    yAxis:{
                                    type:"value"
                                },
                                dataZoom:[
                                    {
                                        type:"slider",
                                        start:10,
                                        end:60
                                    }
                                ],
                                series:[
                                    {
                                        type:"scatter",
                                        itemStyle:{
                                            normal:{
                                                opacity:0.8
                                            }
                                        },
                               symbolSize:function(val)
                                        {
                                            return val[2] * 40;
                                        },
                              data:[["14.616","7.241","0.896"],["3.958","5.701","0.955"],["2.768","8.971","0.669"],["9.051","9.710","0.171"],["14.046","4.182","0.536"],["12.295","1.429","0.962"],["4.417","8.167","0.113"],["0.492","4.771","0.785"],["7.632","2.605","0.645"],["14.242","5.042","0.368"]]
                                    }
            ]});
        </script>
        
        
  
        
        <script type="text/javascript">
        	$(document).ready(function(){
        		var showDivId = 'main1';
        		var jsonURL = 'getTestReport';
        		 createTestReport(showDivId,jsonURL); 
        	});
       </script>
       
        <script type="text/javascript">
        	 function createTestReport(showDivId,jsonURL){  
                 var myChart = echarts.init(document.getElementById(showDivId));  
                 // 初始化report对象  
                 initReport(myChart);  
                 myChart.showLoading({text: '正在努力的读取数据中...'  });  
                 // 调用后台获取json数据  
                 callbackFn(myChart,jsonURL);  
             }  
        	 
        </script>
        
       <script type="text/javascript">	 
        	 function initReport(myChart){
        		   // 显示标题，图例和空的坐标轴  
                 myChart.setOption({  
                     title: {  
                         text: '异步数据加载示例'  
                     },  
                     tooltip: {},  
                     legend: {  
                         data:['销量']  
                     },  
                     xAxis: {  
                         data: []  
                     },  
                     yAxis: {},  
                     series: [{  
                         name: '销量',  
                         type: 'bar',  
                         data: []  
                     }]  
                 });  
        		 
        		 
        	 }
        	
        
        
        </script>
        
        <script type="text/javascript">
        
   	 function callbackFn(myChart,jsonURL){
   		 $.ajax({
   			url:jsonURL,
   			dataType:'json',
   			success : function(jsonData){
   				 myChart.setOption({  
                       xAxis: {  
                       data: jsonData.categories  
                         },  
                       series: [{  
                             // 根据名字对应到相应的系列  
                       name: '销量',  
                       data: jsonData.data  
                         }]  
                     });  
                     // 设置加载等待隐藏  
                     myChart.hideLoading();  
   			}
   			 
   		 });
   		 
   		 
   	 }
        
        </script>
    
</body>
</html>