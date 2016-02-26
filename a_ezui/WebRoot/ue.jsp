<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String ezuiPath = path + "/ezui";
%>
<!DOCTYPE HTML>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>
</head>
<body>
    <!-- å è½½ç¼è¾å¨çå®¹å¨ -->
    <script id="container" name="content" type="text/plain">è¿éåä½ çåå§ååå®¹</script>
    <!-- éç½®æä»¶ -->
    <script type="text/javascript" src="<%=path %>/js/ueditor.config.js"></script>
    <!-- ç¼è¾å¨æºç æä»¶ -->
    <script type="text/javascript" src="<%=path %>/js/ueditor.all.js"></script>
    <!-- å®ä¾åç¼è¾å¨ -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
</body>
</html>