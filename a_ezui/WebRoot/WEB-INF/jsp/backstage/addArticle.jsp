<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String ezuiPath = path + "/ezui";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加文章</title>
    
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/demo.css">
	<script type="text/javascript" src="<%=ezuiPath %>/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ezuiPath %>/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=ezuiPath %>/easyui-ueditor.js"></script>
	<script type="text/javascript">
    	function clearForm(){
    	 $('#ff').form('clear');
   		 }
   		function submitForm(){
   		
   		var html = ue.getContent();
   		
   		$("#content").val(html);
   		
   		//alert($("#content").val());
   		
    	 $('#ff').form('submit', {
    		 url:"<%=path %>/backstage/saveArticle.html",
   			 onSubmit: function(){
        //进行表单验证
        //如果返回false阻止提交
    		},
    		success:function(data){
        	$.messager.alert("提示","操作成功","info",function(){
						window.top.frames["parent_frame_content"].document.location.reload();
					});
   		 }
});
   		 } 
    </script>
  </head>
  
  <body>
  
    <form id="ff" method="post">
                       标题：<input type="text" name="tittle"></br></br>
                       描述：</br><textarea name="describe" rows="3px" cols="30px"></textarea></br></br><!--   
                       内容：</br><textarea  name="content" rows="10px" cols="30px"></textarea></br>   
                       --><input id="content" type="hidden" name="content">
    </form>
    <!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="<%=path %>/js/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=path %>/js/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a> 
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a> 
  </body>
</html>
