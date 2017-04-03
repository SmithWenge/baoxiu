<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="layui-layout layui-layout-admin">
    <!-- 头部区域 -->
    <div class="layui-header header">
        <!-- logo -->
        <div class="logo">
            <h1>报修管理系统 <span class="version">0.0.1</span></h1>
        </div>

        <ul class="layui-nav" lay-filter="">
            <li class="layui-nav-item"><a href="">大数据</a></li>
            <li class="layui-nav-item">
                <a href="">${sessionScope.adminInfo.adminName}</a>
                <dl class="layui-nav-child">
                    <dd><a href="">修改密码</a></dd>
                    <dd><a href="${contextPath}/login/logout.action">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <!-- 侧边栏 -->
    <div class="layui-side layui-bg-black side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item" id="first">
                    <a href="javascript:;"><i class="layui-icon">&#xe649;</i> 位置管理</a>
                    <dl class="layui-nav-child">
                        <dd id="placeDistinct"><a href="${contextPath}/admin/place/distinct/list.action"><i class="layui-icon">&#xe602;</i> 校区管理</a></dd>
                        <dd><a href="${contextPath}/admin/place/building/list.action"><i class="layui-icon">&#xe602;</i> 地点管理</a></dd>
                        <dd><a href="${contextPath}/admin/place/room/index.action"><i class="layui-icon">&#xe602;</i> 位置管理</a></dd>
                        <dd><a href="./ajax-post.html"><i class="layui-icon">&#xe602;</i> ajax-post</a></dd>
                        <dd><a href="./data-grid.html"><i class="layui-icon">&#xe602;</i> 数据表格</a></dd>
                        <dd><a href="./highcharts.html"><i class="layui-icon">&#xe602;</i> 图表charts</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" id="two">
                    <a href="javascript:;"><i class="layui-icon">&#xe641;</i> 设备管理</a>
                    <dl class="layui-nav-child">
                        <dd id="set"><a href="${contextPath}/admin/set/index.action"><i class="layui-icon">&#xe602;</i> 设备组管理</a></dd>
                        <dd><a href="${contextPath}/admin/equipment/route/page.action"><i class="layui-icon">&#xe602;</i> 设备管理</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目三</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe62c;</i> 导航边栏二</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目一</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目二</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目三</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe62d;</i> 导航边栏三</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目一</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目二</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目三</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon">&#xe64a;</i> 导航边栏四</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目一</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目二</a></dd>
                        <dd><a href="javascript:;"><i class="layui-icon">&#xe602;</i> 栏目三</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>