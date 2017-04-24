<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav" style="background-color: #0E4d94; position: static;">
            <h1 class="title" style=" color: #FFF; font-weight: bold;">大连交通大学后勤维修处理系统</h1>
            <a class="icon pull-right" href="${contextPath}/app/worker/routeWorkerInfo.action" style="color: #fff;">
                <i class="fa fa-user-circle" aria-hidden="true"></i>
            </a>
        </header>
        <div class="content">
            <div class="card">
                <div class="card-header">
                    <header class="bar bar-nav" style="background-color: #FFF;">
                        <h3 class="title"><b>待办工作</b></h3>
                    </header>
                </div>
            </div>
            <c:forEach items="${lists}" var="list">
                <div class="card">
                    <div class="card-header" style="font-size: .8rem;">
                        <i class="fa fa-circle" aria-hidden="true" style="color: green;"></i>
                        报修单号：${list.listNumber}
                    </div>
                    <div class="card-content">
                        <div class="card-content-inner">
                        ${list.listBigDescription}
                        </div>
                    </div>
                    <div class="card-footer">
                        <span>${list.liststatetime}</span>
                        <a href="${contextPath}/app/worker/routeDetails/${list.listNumber}.action">详情>></a>
                    </div>
                </div>
            </c:forEach>
            <div class="card">
                <div class="card-header"></div>
            </div>
        </div>
        <nav class="bar bar-tab" style="background: #0E4d94;opacity: 1;">
            <a class="tab-item external active" href="${contextPath}/app/worker/routeDoingList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                </span>
                <span class="badge">${maintenanceList.onDoMaintenanceSum}</span>
            </a>
            <a class="tab-item external" href="${contextPath}/app/worker/routeWaitingList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-bell-o" aria-hidden="true"></i>
                </span>
            </a>
            <a class="tab-item external" href="${contextPath}/app/worker/routeLatestList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-briefcase" aria-hidden="true"></i>
                </span>
                <span class="badge">${maintenanceList.allDoMaintenanceSum}</span>
            </a>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>