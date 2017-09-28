<%@ page import="com.qysoft.rapid.utils.RenderHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%= RenderHelper.includedStyle(request, "/static/plugins/layui/css/layui.css") %>
    <%= RenderHelper.includedStyle(request, "/static/css/css.css") %>
    <%
        String ctx = request.getContextPath();
    %>
    <script>
        var ctx = '<%=ctx %>';
    </script>
    <style>
    </style>
    <title>webKit</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">webKit</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul id="menu-nav" class="layui-nav layui-nav-tree"  lay-filter="test">
                <c:forEach items="${zyxxList }" var="yjzy">
                    <li class="layui-nav-item">
                        <a class="" zyid="${yjzy.zyid}" href="javascript:void(0);" zylj="${yjzy.zylj}" zymc="${yjzy.zymc}">
                            ${yjzy.zymc}
                        </a>
                        <c:if test="${yjzy.ejzy_size > 0}" >
                            <dl class="layui-nav-child">
                                <c:forEach items="${yjzy.ejzyList }" var="ejzy">
                                    <dd>
                                        <a zyid="${ejzy.zyid}" href="javascript:void(0);" zylj="${ejzy.zylj}" zymc="${ejzy.zymc}">
                                            <i class="layui-icon">&#xe617;</i>
                                            ${ejzy.zymc}
                                        </a>
                                    </dd>
                                </c:forEach>
                            </dl>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul id="lay-main-title" class="layui-tab-title">
                <li class="layui-this" zyid="00">工作看板</li>
            </ul>
            <div id="lay-main-content" class="layui-tab-content" style="padding: 0px;">
                <div class="layui-tab-item layui-show" zyid="00">
                    <iframe src="<%=ctx%>/gzkb" class="lay-webkit-main-frame" frameborder="0" ></iframe>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<%--<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>--%>
<%=RenderHelper.includedJavascript(request, "/static/plugins/layui/layui.js") %>
<%=RenderHelper.includedJavascript(request, "/static/js/index.js") %>
</body>
</html>