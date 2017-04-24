<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav bar-nav-2" style="background-color: #0E4d94; position: static;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">大连交通大学后勤报修系统</h1>
        </header>
        <div class="content">
            <div class="card">
                <div class="card-header">
                    <header class="bar bar-nav" style="background-color: #FFF;">
                        <h3 class="title">
                            <i class="fa fa-circle" aria-hidden="true" style="color:#33CC00;"></i>
                            <b>报修单详情（${maintenanceList.listNumber}）</b>
                        </h3>
                    </header>
                </div>
            </div>
            <div class="card">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="list-block">
                            <ul>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">校区</div>
                                        <div class="item-after">${maintenanceList.distinctName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">地点</div>
                                        <div class="item-after">${maintenanceList.buildingName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">位置</div>
                                        <div class="item-after">${maintenanceList.roomName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">设备</div>
                                        <div class="item-after">${maintenanceList.equipmentName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">故障描述</div>
                                        <div class="item-after">${maintenanceList.listDescription}</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="list-block" style="font-size: 0.7rem;">
                            <ul>
                                <c:forEach items="${allStates}" var="state">
                                    <li class="item-content">
                                        <div class="item-media"><i class="icon icon-f7"></i>
                                        </div>
                                        <div class="item-inner">
                                            <div class="item-title">${state.liststatetime}</div>
                                            <div class="item-after">
                                                <c:if test="${state.listState == 1}">已提交</c:if>
                                                <c:if test="${state.listState == 2}">已派单</c:if>
                                                <c:if test="${state.listState == 3}">以处理</c:if>
                                                <c:if test="${state.listState == 4}">已评价</c:if>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <a href="${contextPath}/app/user/turn/repairList/router/${maintenanceList.userTel}.action" class="button button-fill button-success" external>返回</a>
                    <a href="${contextPath}/app/user/redirect/index.action" class="button button-fill button-primary" external>首页</a>
                </div>
            </div>
            <div class="card-header">
            </div>
        </div>

        <nav class="bar bar-tab" style="background-color: #0E4d94;">
            <h3 class="title title-2" style=" color: #FFF; font-weight: bold;">程序设计：大连交通大学56工作室</h3>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>

<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>