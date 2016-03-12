<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String ezuiPath = path + "/ezui";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta charset="UTF-8">
	<title>ezui</title>
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/demo.css">
	<script type="text/javascript" src="<%=ezuiPath %>/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ezuiPath %>/jquery.easyui.min.js"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north'" style="height:90px">
		<h1 style=" float:left;margin: 8; padding:10px; color:#0092DC; cursor: default; font-family: 微软雅黑, 黑体, 宋体; font-size: 40px">GK666</h1>
			<div class="userinfo" style="float: right; margin-right: 20;margin-top: 10">您好，帅哥 ！<a href="#">退出</a> </div>
		</div><!--
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		--><div data-options="region:'east',split:true" title="East" style="width:10%;"></div>
		<div data-options="region:'west',split:true,hideCollapsedContent:false,collapsed:true" title="菜单" style="width:217px;">
		
		<div style="width:200px;height:auto;background:#7190E0;padding:5px;">
	<div class="easyui-panel" title="LOL" collapsible="true" style="width:100%;height:auto;padding:10px;">
		<table>
			<tr>
				<td>
					<a href="#" onclick="submit('伊泽瑞尔')" class="easyui-linkbutton" plain="true">德玛西亚</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="#" onclick="submit('劫')" class="easyui-linkbutton" plain="true">艾欧尼亚</a>
				</td>
			</tr><tr>
				<td>
					<a href="#" onclick="submit('伊芙琳')" class="easyui-linkbutton" plain="true">诺克萨斯</a>
				</td>
			</tr>
		</table>
	</div>
	<br/>
	<div class="easyui-panel" title="File and Folder Tasks" collapsible="true" style="width:200px;height:auto;padding:10px;">
		Make a new folder<br/>
		Publish this folder to the Web<br/>
		Share this folder
	</div>
	<br/>
	<div class="easyui-panel" title="Other Places" collapsible="true" collapsed="true" style="width:200px;height:auto;padding:10px;">
		New York<br/>
		My Pictures<br/>
		My Computer<br/>
		My Network Places
	</div>
	<br/>
	<div class="easyui-panel" title="Details" collapsible="true" style="width:200px;height:auto;padding:10px;">
		My documents<br/>
		File folder<br/><br/>
		Date modified: Oct.3rd 2010
	</div>
</div>
		</div>
		<div data-options="region:'center',title:'列表'">
					<div class="easyui-tabs" id="div_center" style="width:100%;height:100%;">
					  <div title="gk666"  style="padding:5px"><!--
 			  <iframe src="" id="parent_frame_content" name="parent_frame_content" style="width: 100%;height: 100%" frameborder="0"></iframe> 
		    --></div>
		</div>
	</div>
	<script type="text/javascript">
	function submit(title){
	var url_str = "<%=path %>/backstage/getArticle.html";
	var content ='<iframe id="parent_frame_content" name="parent_frame_content" scrolling="auto" frameborder="0"  src="'+url_str+'" style="width:100%;height:100%;"></iframe>';
	var tb_array = $('#div_center').tabs('tabs');
			if(tb_array.length>0) {
				for(var i=0;i<tb_array.length;i++) {
					$('#div_center').tabs('close', i);
				}
			}
	$('#div_center').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	</script>
</body>
</html>
