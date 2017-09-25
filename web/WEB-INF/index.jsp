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
    <style>
    </style>
    <title>webKit</title>
</head>
<body>
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
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <c:forEach items="${zyxxList }" var="yjzy">
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;">${yjzy.zymc}</a>
                            <c:if test="${yjzy.ejzy_size > 0}" >
                                <dl class="layui-nav-child">
                                    <c:forEach items="${yjzy.ejzyList }" var="ejzy">
                                        <dd><a href="javascript:;">${ejzy.zymc}</a></dd>
                                    </c:forEach>
                                </dl>
                            </c:if>
                    </li>
                </c:forEach>
                <%--<li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>--%>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
            <%--<iframe src="https://eve.tiancity.com" id="main-frame" class="lay-webkit-main-frame" frameborder="0" ></iframe>--%>
            <%--<iframe src="<%=ctx%>/system/zygl" id="main-frame" class="lay-webkit-main-frame" frameborder="0" ></iframe>--%>
        <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
            <ul class="layui-tab-title">
                <li class="layui-this">网站设置</li>
                <li>用户管理</li>
                <li>权限分配</li>
                <li>商品管理</li>
                <li>订单管理</li>
            </ul>
            <div class="layui-tab-content" style="padding: 0px;">
                <div class="layui-tab-item layui-show">
                    <iframe src="<%=ctx%>/system/zygl" class="lay-webkit-main-frame" frameborder="0" ></iframe>
                </div>
                <div class="layui-tab-item">
                    <iframe src="<%=ctx%>/druid/index.html" class="lay-webkit-main-frame" frameborder="0" ></iframe>
                </div>
                <div class="layui-tab-item">内容3</div>
                <div class="layui-tab-item">内容4</div>
                <div class="layui-tab-item">内容5</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<%=RenderHelper.includedJavascript(request, "/static/plugins/layui/layui.js") %>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
    $(".lay-webkit-main-frame").height($(".lay-webkit-main-frame").closest(".layui-body").height() - 66);
</script>
</body>
</html>