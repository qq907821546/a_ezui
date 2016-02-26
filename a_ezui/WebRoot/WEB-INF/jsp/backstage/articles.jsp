<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String ezuiPath = path + "/ezui";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ezui</title>
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=ezuiPath %>/demo.css">
	<script type="text/javascript" src="<%=ezuiPath %>/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ezuiPath %>/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	$(function(){
	$('#tt').datagrid({
			    url:'<%=path %>/backstage/findArticles.html',
			    method:'get',
				border:false,
				fit:true,
				fitColumns:true,
				pageSize : 13,
				pageList : [13,20,30],
				pagination:true,
				sortName:"tittle",
				sortOrder:"desc",
				remoteSort:false,
				loadMsg:"正在加载,请稍后...",
				rownumbers:true,
				//rowStyler:function(index,row){ 
				//if(row.tittle=="2"){
				// return "background-color:#9a654a";
				//}
				//if(index==8){
				// return "background-color:red";
				//}
				//},
				//queryParams:{ "searchKey": "name", "searchValue": "value" },
    columns:[[
        {field:'ck',checkbox:true},
        {field:'tittle',title:'标题',width:'30%'},
        {field:'createUser',title:'作者',width:'30%'},
        {field:'createTime',title:'创建时间',width:'40%',align:'center'}
    ]],
    checkOnSelect:true,
    selectOnCheck:true,
    //onLoadSuccess:function(data){    
   // if(data){    
   // $.each(data.rows,function(index,item){
   // $('#tt').datagrid("checkRow",index);
   // });
   // }
    //},
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addArticle();
					}
					},{
					id:'btn',
					text:'刷新',
					iconCls:'icon-reload',
					handler:function(){
						location.href="<%=path %>/backstage/getArticle.html";
					}
					},{
					id:'btncancel',
					text:'删除',
					iconCls:'icon-cancel',
					handler:function(){
						var checkedItems = $('#tt').datagrid('getChecked');
						var ids = "";
						$.each(checkedItems,function(index,item){
						    if(index != checkedItems.length-1){
							ids += item.id+",";
							}else{
							ids += item.id
							}
						});
						$.ajax({
								url:"<%=path %>/backstage/delArticle.html",
								type:"POST",
								dataType:"text",
								data:{
								ids:ids
								},
								success:function(data){
								alert(data);
								location.href="<%=path %>/backstage/getArticle.html";
								}
							});
						//console.log(ids.join(","));
					}
				}]
            });
          
            
            
            //设置分页控件 
    var p = $('#tt').datagrid('getPager'); 
    p.pagination({ 
        //pageSize: 5,//每页显示的记录条数，默认为10 
        //pageList: [5,10,15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
    }); 
});
	--></script>
  </head>
  
  <body>
  <div class="easyui-layout" style="width:100%;height:100%;">
	   <!--<div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
				--><!--<table class="easyui-datagrid"
						data-options="url:'datagrid_data1.json',method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
					<thead>
						<tr>
							<th data-options="field:'itemid'" width="80">Item ID</th>
							<th data-options="field:'productid'" width="100">Product ID</th>
							<th data-options="field:'listprice',align:'right'" width="80">List Price</th>
							<th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
							<th data-options="field:'attr1'" width="150">Attribute</th>
							<th data-options="field:'status',align:'center'" width="60">Status</th>
						</tr>
					</thead>
				</table>
			-->
			<table id="tt"></table>
			<!--</div>-->
			<div id="p" style="padding:10px;">
				<iframe id="frame_content" name="frame_content" style="width: 100%;height: 100%" frameborder="0"></iframe> 
            </div>
            
		</div>
	<script type="text/javascript">
		function addArticle(){
		url = "<%=path %>/backstage/addArticle.html";
		var frame1 = document.getElementById("frame_content");
		frame1.src = url;
		  $('#p').dialog({
			title: "文章信息",
			width: 850,
			resizable:true,
			closed: false,    
			cache: false,    
			height: 400
          });
		}
	</script>
  </body>
</html>
