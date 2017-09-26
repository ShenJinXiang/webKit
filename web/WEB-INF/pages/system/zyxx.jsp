<%@ page import="com.qysoft.rapid.utils.RenderHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%= RenderHelper.includedStyle(request, "/static/plugins/layui/css/layui.css") %>
    <%
        String ctx = request.getContextPath();
    %>
    <script>
        var ctx = '<%=ctx%>';
    </script>
    <style>
    </style>
    <title>资源管理</title>
</head>
<body class='layui-anim layui-anim-upbit' style="padding: 15px;">
zyxx
<table id="zyxxTable" class="layui-table">
    <thead>
        <tr>
            <th style="text-align: center;">排序</th>
            <th style="text-align: center;">资源名称</th>
            <th style="text-align: center;">资源路径</th>
            <th style="text-align: center;">资源类型</th>
            <th style="text-align: center;">操作</th>
        </tr>
    </thead>
    <tbody id="zyxx_tbody">
    </tbody>
</table>
<%--<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>--%>
<%=RenderHelper.includedJavascript(request, "/static/plugins/layui/layui.js") %>
<%=RenderHelper.includedJavascript(request, "/static/js/module-config.js") %>
<%=RenderHelper.includedAutoJavascript(request) %>
</body>
</html>