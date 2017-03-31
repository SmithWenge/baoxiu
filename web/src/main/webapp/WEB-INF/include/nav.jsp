<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/header.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<style>
    .header ul{
        padding-left: 0;
        margin-bottom: 0;
        background: #444444;
        height: 55px;
    }
    .header ul li {
        float: left;
        height: 55px;
        line-height: 55px;
        color: whitesmoke;
        list-style: none;
    }
    .header ul li a{
        color: white;
    }
</style>
<link href="${contextPath}/static/plugins/nav/nav.css" rel="stylesheet" type="text/css" />
<div class="header">
    <ul>
        <li style="width: 2%;"></li>
        <li style="width: 10%;">老干部学籍管理</li>
        <li style="width: 6%;"> <span class="glyphicon glyphicon-user"> </span> ${adminLogin.adminLoginName}</li>
        <li style="width: 75%;"><a href="${contextPath}/admin/routePass.action">更改密码</a></li>
        <li style="width: 5%;"><a href="${contextPath}/admin/logout.action"><span class="glyphicon glyphicon-off"></span> 退出</a></li>
    </ul>
</div>
    <ul id="accordion" class="accordion">
        <li>
            <div class="link">
                <a id="index_info" href="${contextPath}/admin/home/index.action">
                    首页
                </a>
            </div>
        </li>
        <li>
            <div class="link" id="userManageNav">
                用户管理
                <i class="fa fa-chevron-down"></i>
            </div>
            <ul class="submenu">
                <shiro:hasPermission name="student:list">
                    <li>
                        <a id="student" href="${contextPath}/admin/student/routePage.action">
                            学生管理
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="teacher:list">
                    <li>
                        <a id="teacher" href="${contextPath}/admin/teacher/routePage.action">
                            教师管理
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="register:list">
                    <li>
                        <a id="user_register" href="${contextPath}/admin/register/routePage.action">
                            注册用户管理
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasRole name="super">
                    <li>
                        <a id="role" href="${contextPath}/admin/role/list.action">
                            角色管理
                        </a>
                    </li>
                </shiro:hasRole>
                <shiro:hasPermission name="user:list">
                    <li>
                        <a id="user_manage" href="${contextPath}/admin/user/page.action">
                            用户管理
                        </a>
                    </li>
                </shiro:hasPermission>
            </ul>
        </li>
        <li>
            <div class="link" id="courseManageNav">
                课程管理
                <i class="fa fa-chevron-down"></i>
            </div>
            <ul class="submenu">
                <shiro:hasPermission name="department:list">
                    <li>
                        <a id="department" href="${contextPath}/admin/department/page.action">
                            系管理
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="major:list">
                    <li>
                        <a id="major" href="${contextPath}/admin/major/page.action">
                            专业管理
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="course:list">
                    <li>
                        <a id="course" href="${contextPath}/admin/course/routePage.action">
                            课程管理
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="monitor:list">
                    <li>
                        <a id="leader" href="${contextPath}/admin/course/leader/page.action">
                            班长
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="changeCourse:list">
                    <li>
                        <a id="courseChange" href="${contextPath}/admin/course/change/route.action">
                            换课
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="score:list">
                    <li>
                        <a id="score" href="${contextPath}/admin/score/routeList.action">
                            成绩管理
                        </a>
                    </li>
                </shiro:hasPermission>
            </ul>
        </li>
        <shiro:hasPermission name="room:list">
            <li>
                <div class="link" id="roomManageNav">
                    <a id="room" href="${contextPath}/admin/room/routeRoom.action">
                        教室
                    </a>
                </div>
            </li>
        </shiro:hasPermission>
        <li>
            <div class="link" id="financeManageNav">
                财务管理
                <i class="fa fa-chevron-down"></i>
            </div>
            <ul class="submenu">
                <shiro:hasPermission name="charge:list">
                    <li>
                        <a id="finance" href="${contextPath}/admin/finance/routePage.action">
                            收费
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="unpayment:list">
                    <li>
                        <a id="unpayFinance" href="${contextPath}/admin/finance/unpayment.action">
                            未缴费查询
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="payment:list">
                    <li>
                        <a id="payFinance" href="${contextPath}/admin/finance/payment.action">
                            已缴费查询
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="payment:list">
                    <li>
                        <a id="allPay" href="${contextPath}/admin/finance/query/all.action">
                            缴费信息
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="refund:list">
                    <li>
                        <a id="refundFinance" href="${contextPath}/admin/finance/refund/route.action">
                            退款
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="financeChangeCourse:list">
                    <li>
                        <a id="changeCourseFinance" href="${contextPath}/admin/finance/course/change/list.action">
                            换课费用
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="bill:list">
                    <li>
                        <a id="financePrint" href="${contextPath}/admin/finance/printPage.action">
                            打印发票
                        </a>
                    </li>
                </shiro:hasPermission>
            </ul>
        </li>
        <shiro:hasPermission name="downSign:view">
            <li>
                <div class="link">
                    <a id="signUp" href="${contextPath}/admin/offline/sign.action">
                        线下报名
                    </a>
                </div>
            </li>
        </shiro:hasPermission>
        <li>
            <div class="link" id="countManageNav">
                统计/分析
                <i class="fa fa-chevron-down"></i>
            </div>
            <ul class="submenu">
                <shiro:hasRole name="memberCount">
                    <li>
                        <a id="count" href="${contextPath}/admin/count/index.action" target="_blank">
                            学员统计
                        </a>
                    </li>
                </shiro:hasRole>
                <shiro:hasPermission name="financeCount:list">
                    <li>
                        <a id="financeCount" href="${contextPath}/admin/finance/routeEcharts.action">
                            财务统计
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="financeAnalysis:list">
                    <li>
                        <a id="financeAnalysis" href="${contextPath}/admin/finance/routeCount.action">
                            财务分析
                        </a>
                    </li>
                </shiro:hasPermission>
            </ul>
        </li>
        <shiro:hasRole name="studentCount">
            <li>
                <div class="link">
                    <a id="card" href="${contextPath}/admin/student/count/routePage.action">
                        考勤
                    </a>
                </div>
            </li>
        </shiro:hasRole>
        <li>
            <div class="link" id="configManageNav">
                配置管理
                <i class="fa fa-chevron-down"></i>
            </div>
            <ul class="submenu">
                <shiro:hasPermission name="backcolorSet:set">
                    <li>
                        <a id="setting" href="${contextPath}/admin/setting/route.action">
                            背景色配置
                        </a>
                    </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="messageSet:set">
                    <li>
                        <a id="basic" href="${contextPath}/admin/setting/routeBasic.action">
                            信息配置
                        </a>
                    </li>
                </shiro:hasPermission>
            </ul>
        </li>
        <shiro:hasPermission name="log:list">
            <li>
                <div class="link" id="logManageNav">
                    <a id="log" href="${contextPath}/admin/log/routePage.action">
                        查看日志
                    </a>
                </div>
            </li>
        </shiro:hasPermission>
        <li>
            <div class="link">
                <a id="help" href="${contextPath}/admin/user/helpRouter.action">
                    帮助
                </a>
            </div>
        </li>
    </ul>
    <input type="hidden" id="roleId" value="${sessionScope.adminLogin.adminRole}">

<%--<%@include file="/WEB-INF/include/javascript.jsp"%>--%>

<script type="text/javascript" src="${contextPath}/static/plugins/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/static/plugins/nav/index.js"></script>
